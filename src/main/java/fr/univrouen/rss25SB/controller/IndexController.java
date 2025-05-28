package fr.univrouen.rss25SB.controller;

import fr.univrouen.rss25SB.model.AuthorDocument;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Ce controller va permettre d'afficher la page d'accueil et la page help
 *
 * Les deux pages correspond respectivement aux fichiers templates/index.html et templates/help.html
 *
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String getIndex(Model model) {
        List<AuthorDocument> authors = List.of(
                new AuthorDocument("MUHOZA Clayton", "https://github.com/claytonmuhoza"),
                new AuthorDocument("Zaraa Rami", "https://github.com/RamiZarraa")
        );

        model.addAttribute("authors", authors);
        return "index";
    }

    @GetMapping("/help")
    public String getHelp(Model model) {
        return "help";
    }
}