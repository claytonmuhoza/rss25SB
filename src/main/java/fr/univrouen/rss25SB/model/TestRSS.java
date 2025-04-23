package fr.univrouen.rss25SB.model;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;

public class TestRSS {
    public String loadFileXML() {
        Resource resource = new DefaultResourceLoader().getResource("classpath:xml/item.xml");
        try {
            return resource.getContentAsString(Charset.forName("UTF-8"));
        }
        catch (IOException e) {
            return ("Erreur lors de la lecture du fichier XML");
        }

    }
}
