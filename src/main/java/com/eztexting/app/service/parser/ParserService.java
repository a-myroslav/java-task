package com.eztexting.app.service.parser;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ParserService {
    CompletableFuture<List<String>> getUrlContent(String url) throws Exception;
}
