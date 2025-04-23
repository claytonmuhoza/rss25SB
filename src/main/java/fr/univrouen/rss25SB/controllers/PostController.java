package fr.univrouen.rss25SB.controllers;

import fr.univrouen.rss25SB.model.Item;
import fr.univrouen.rss25SB.model.TestRSS;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class PostController {
    @RequestMapping(value = "/testpost", method = RequestMethod.POST, consumes = "application/xml")
    public String postTet(@RequestBody String flux) {
        return ("<result><response>Message reçu : </response>"
        + flux + "</result>");
    }
    @PostMapping(value = "/postrss", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String postRSS(){
        TestRSS rss = new TestRSS();
        return rss.loadFileXML();
    }

}
