package com.eztexting.app.service.destination.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "Data")
public class XMLData {
    private List<XMLDataItem> items = new LinkedList<>();

    public List<XMLDataItem> getItems() {
        return items;
    }

    @XmlElement(name = "item")
    public void setItems(List<XMLDataItem> items) {
        this.items = items;
    }
}
