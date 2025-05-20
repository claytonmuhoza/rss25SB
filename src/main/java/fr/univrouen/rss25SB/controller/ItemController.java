package fr.univrouen.rss25SB.controller;

import fr.univrouen.rss25SB.model.Feed;
import fr.univrouen.rss25SB.model.Item;
import fr.univrouen.rss25SB.service.XmlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class ItemController {
    private final XmlService xmlService;
    public ItemController(XmlService xmlService) {
        this.xmlService = xmlService;
    }
    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItems() throws Exception {
        return ResponseEntity.ok(xmlService.readFeed().getItem());
    }

    @PostMapping("/items")
    public ResponseEntity<String> addItem(@RequestBody Item item) throws Exception {
        Feed feed = xmlService.readFeed();
        feed.getItem().add(item);
        xmlService.writeFeed(feed);
        return ResponseEntity.ok("Item ajouté");
    }
}
