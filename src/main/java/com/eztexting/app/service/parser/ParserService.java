package com.eztexting.app.service.parser;

import java.util.concurrent.CompletableFuture;

public interface ParserService {
    CompletableFuture<ParseResult> getUrlContent(String url) throws Exception;
}
