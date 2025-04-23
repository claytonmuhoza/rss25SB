package fr.univrouen.rss25SB.service;

import fr.univrouen.rss25SB.model.Feed;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class XmlService {

    private final String XML_PATH = "classpath:xml/feed.xml";

    public Feed readFeed() throws Exception {
        Resource resource = new DefaultResourceLoader().getResource(XML_PATH);
        try (InputStream is = resource.getInputStream()) {
            JAXBContext context = JAXBContext.newInstance(Feed.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Feed) unmarshaller.unmarshal(is);
        }
        catch (Exception e) {
            throw new Exception("Erreur lors de la lecture du fichier XML");
        }
    }
    public String readFeedAsString(){
        Resource resource = new DefaultResourceLoader().getResource(XML_PATH);
        try{
            return resource.getContentAsString(Charset.defaultCharset());
        }
        catch (Exception e) {
            return "Erreur lors de la lecture du fichier XML";
        }
    }

    public void writeFeed(Feed feed) throws Exception {
        Resource resource = new DefaultResourceLoader().getResource(XML_PATH);
        Path path = Path.of(resource.getURI());
        try (OutputStream os = Files.newOutputStream(path)) {
            JAXBContext context = JAXBContext.newInstance(Feed.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(feed, os);
        }
    }
}

