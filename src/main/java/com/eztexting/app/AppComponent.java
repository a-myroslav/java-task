package com.eztexting.app;

import com.eztexting.app.service.parser.ParserService;
import com.eztexting.app.service.parser.common.CommonParserService;
import com.eztexting.app.service.destination.DestinationService;
import com.eztexting.app.service.source.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class AppComponent {

    @Autowired()
    AppComponent(
            SourceService rService,
            DestinationService dService,
            ParserService pService
    ) throws Exception {

        rService.readSource();

        String url;
        List<CompletableFuture<List<String>>> featureList = new ArrayList<>();
        while ((url = rService.getNextUrl()) != null) {
            featureList.add(pService.getUrlContent(url));
        }

        CompletableFuture.allOf(featureList.toArray(new CompletableFuture[0])).join();

        for(CompletableFuture feature: featureList) {
            dService.putToDestination("sdsdfs", String.join(", ", (List<String>) feature.get()));
        }

        dService.save();
    }

}
