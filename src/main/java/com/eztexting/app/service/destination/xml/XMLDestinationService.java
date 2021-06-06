package com.eztexting.app.service.destination.xml;

import com.eztexting.app.service.destination.DestinationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

@Service
public class XMLDestinationService implements DestinationService {

    @Value("${app.destination}")
    private String destinationFileName;

    XMLData xmlData = new XMLData();

    @Override
    public void putToDestination(String url, String  data) {
        xmlData.getItems().add(new XMLDataItem(url, data));
    }

    @Override
    public void save() {
        try {
            File file = new File(destinationFileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLData.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(xmlData, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
