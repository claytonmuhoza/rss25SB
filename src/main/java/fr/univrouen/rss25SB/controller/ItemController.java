package fr.univrouen.rss25SB.controller;

import fr.univrouen.rss25SB.dto.*;
import fr.univrouen.rss25SB.mapper.ItemMapper;
import fr.univrouen.rss25SB.model.Feed;
import fr.univrouen.rss25SB.model.Item;
import fr.univrouen.rss25SB.service.FeedService;
import fr.univrouen.rss25SB.service.ItemService;
import fr.univrouen.rss25SB.service.XmlService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/rss25SB")
public class ItemController {
    private final ItemService itemService;
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    // chemin pour afficher la liste des articles
    @GetMapping(value = "/resume/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<?> listItemsXml() {
        List<Item> items = itemService.getAll();

        //on récupère les champs dont on a besoin d'afficher et on utilise le bon Dto pour les convertir en XML
        List<ItemSummaryDto> dtos = items.stream()
                .map(item -> new ItemSummaryDto(
                        item.getId(),
                        item.getPublished() != null ? item.getPublished() : item.getUpdated(),
                        item.getGuid()))
                .toList();
        //on retourne la liste des items au format XML
        return ResponseEntity.ok(new ItemSummaryListDto(dtos));
    }

    //liste tous les items sur la page html
    @GetMapping(value = "/resume/html", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView listItemsHtml() {
        ModelAndView mav = new ModelAndView("summary");
        mav.addObject("items", itemService.getAll());
        return mav;
    }
    // afficher les détails d'un item
    @GetMapping(value = "/resume/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<?> getItemXml(@PathVariable Long id) {
        try {
            Item item = itemService.getByIdOrThrow(id);
            ItemDto dto = ItemMapper.entityToDto(item);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ResponseDto(id, "ERROR", e.getMessage()));
        }
    }
    // afficher les détails d'un item sur une page html
    @GetMapping(value = "/html/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getItemHtml(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        try {
            mav.setViewName("detail");
            mav.addObject("item", itemService.getByIdOrThrow(id));
        } catch (Exception e) {
            mav.setViewName("error");
            mav.addObject("id", id);
            mav.addObject("status", "ERROR");
            mav.addObject("description", e.getMessage());
        }
        return mav;
    }
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        try {
            itemService.deleteById(id);
            return ResponseEntity.ok(new ResponseDto(id, "DELETED","L'article a été supprimé avec succès!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDto(id, "ERROR", e.getMessage()));
        }
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<?> search(@RequestParam(required = false) String date,
                                    @RequestParam(required = false) String category) {
        try {
            List<Item> result;
            if (category != null) {
                result = itemService.searchByCategory(category);
            } else if (date != null) {
                // Analyse de la date au format "yyyy-MM-dd"
                LocalDate localDate = LocalDate.parse(date); // exemple : "2025-05-19"
                Date fromDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                result = itemService.searchByDate(fromDate);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDto(null, "ERROR", "Aucun critère"));
            }
            if(result.isEmpty()) return ResponseEntity.ok().body(new ResponseDto(null, "NONE", "Aucun article trouvé"));
            List<ItemDto> dtos = result.stream()
                    .map(ItemMapper::entityToDto)
                    .toList();

            return ResponseEntity.ok(new ItemListDto(dtos));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDto(null, "ERROR", e.getMessage()));
        }
    }
}
