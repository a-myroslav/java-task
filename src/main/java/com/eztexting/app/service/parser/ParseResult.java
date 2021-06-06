package com.eztexting.app.service.parser;

import java.util.List;

public class ParseResult {
    private String url;
    private List<String> itemData;

    public ParseResult(String url, List<String> itemData) {
        this.url = url;
        this.itemData = itemData;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getItemData() {
        return itemData;
    }

    public void setItemData(List<String> itemData) {
        this.itemData = itemData;
    }
}
