package fr.univrouen.rss25SB.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("something", "coming from the controller");
        return "index";
    }

    @GetMapping("/help")
    public String getHelp(Model model) {
        return "help";
    }
}