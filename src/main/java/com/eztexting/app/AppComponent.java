package com.eztexting.app;

import com.eztexting.app.service.parser.ParseResult;
import com.eztexting.app.service.parser.ParserService;
import com.eztexting.app.service.destination.DestinationService;
import com.eztexting.app.service.source.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class AppComponent {

    private final SourceService rService;
    private final DestinationService dService;
    private final ParserService pService;

    private final ApplicationContext appContext;

    @Autowired()
    AppComponent(
            ApplicationContext appContext,
            SourceService rService,
            DestinationService dService,
            ParserService pService
    ) {
        this.appContext = appContext;
        this.rService = rService;
        this.dService = dService;
        this.pService = pService;
    }

    void runApp() throws Exception {
        rService.readSource();

        String url;
        List<CompletableFuture<ParseResult>> featureList = new ArrayList<>();
        while ((url = rService.getNextUrl()) != null) {
            featureList.add(pService.getUrlContent(url));
        }

        CompletableFuture.allOf(featureList.toArray(new CompletableFuture[0])).join();

        for(CompletableFuture<ParseResult> feature: featureList) {
            ParseResult result = feature.get();
            dService.putToDestination(result.getUrl(), String.join(", ", result.getItemData()));
        }

        dService.save();

        SpringApplication.exit(appContext);
    }

}
