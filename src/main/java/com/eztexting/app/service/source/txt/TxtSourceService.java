package com.eztexting.app.service.source.txt;

import com.eztexting.app.service.source.SourceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ListIterator;
import java.util.stream.Collectors;

@Service
public class TxtSourceService implements SourceService {

    @Value("${app.source}")
    private String sourceFileName;

    private ListIterator<String> urlsIterator;

    @Override
    public void readSource() throws Exception {
        try (final BufferedReader br = new BufferedReader(new FileReader(sourceFileName))) {
            urlsIterator = br.lines().collect(Collectors.toList()).listIterator();
        } catch (IOException e) {
            throw new Exception("Failed to parse input file", e);
        }
    }

    @Override
    public synchronized String getNextUrl() {
        return urlsIterator.hasNext() ? urlsIterator.next() : null;
    }

}
