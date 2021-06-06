package com.eztexting.app.service.source;

public interface SourceService {

    void readSource() throws Exception;

    String getNextUrl();
}
