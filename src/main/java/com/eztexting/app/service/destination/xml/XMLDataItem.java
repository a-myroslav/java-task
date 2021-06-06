package com.eztexting.app.service.destination.xml;

import javax.xml.bind.annotation.XmlElement;

public class XMLDataItem {
    private String url;

    private String itemData;

    public XMLDataItem() { }

    public XMLDataItem(String url, String itemData) {
        this.url = url;
        this.itemData = itemData;
    }

    public String getUrl() {
        return url;
    }

    @XmlElement
    public void setUrl(String url) {
        this.url = url;
    }

    public String getItemData() {
        return itemData;
    }

    @XmlElement(name = "item_data")
    public void setItemData(String itemData) {
        this.itemData = itemData;
    }
}
