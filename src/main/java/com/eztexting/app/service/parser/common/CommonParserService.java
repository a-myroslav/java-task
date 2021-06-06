package com.eztexting.app.service.parser.common;

import com.eztexting.app.service.parser.ParseResult;
import com.eztexting.app.service.parser.ParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class CommonParserService implements ParserService {

    private final Logger logger = LoggerFactory.getLogger(CommonParserService.class);

    @Async
    public CompletableFuture<ParseResult> getUrlContent(String url) throws Exception {
        List<String> urlContent = readUrl(new URL(url));

        logger.info("Url reading by " + Thread.currentThread().getName());

        return CompletableFuture.completedFuture(new ParseResult(url, urlContent));
    }

    public List<String> readUrl(URL url) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()))) {
            result = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
