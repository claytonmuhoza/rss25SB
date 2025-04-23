package fr.univrouen.rss25SB.controllers;

import fr.univrouen.rss25SB.model.Feed;
import fr.univrouen.rss25SB.model.Item;
import fr.univrouen.rss25SB.service.XmlService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feeds")
public class FeedController {

    private final XmlService xmlService;

    public FeedController(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public String getFeed() throws Exception {
        return xmlService.readFeedAsString();
    }

    @PostMapping
    public ResponseEntity<String> postFeed(@RequestBody Feed feed) throws Exception {
        xmlService.writeFeed(feed);
        return ResponseEntity.ok("Feed ajouté");
    }

    @PutMapping
    public ResponseEntity<String> updateFeed(@RequestBody Feed feed) throws Exception {
        xmlService.writeFeed(feed);
        return ResponseEntity.ok("Feed mis à jour");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFeed() throws Exception {
        Feed empty = new Feed();
        xmlService.writeFeed(empty);
        return ResponseEntity.ok("Feed supprimé");
    }


}

