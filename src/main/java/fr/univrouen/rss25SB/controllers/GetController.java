package fr.univrouen.rss25SB.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rss25")
public class GetController {
    @GetMapping("/resume")
    public String getListRSSinXML() {
        return "Envoi de la liste des flux RSS";
    }
    @GetMapping("/guid")
    public String getRSSinXML(@RequestParam(value = "guid") String texte) {
        return "Détail du contenu RSS demandé: " + texte;
    }
    @GetMapping("/test")
    public String getRSSinXML(@RequestParam(value = "nb") String guid, @RequestParam(value = "search") String titre) {
        return "Test: <br/>" +
                "guid = " + guid + "<br/>" +
                "titre = " + titre + "<br/>";
    }
}
