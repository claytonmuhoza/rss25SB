package fr.univrouen.rss25SB.controller;

import fr.univrouen.rss25SB.dto.*;
import fr.univrouen.rss25SB.mapper.FeedMapper;
import fr.univrouen.rss25SB.mapper.ItemMapper;
import fr.univrouen.rss25SB.model.Feed;
import fr.univrouen.rss25SB.model.Item;
import fr.univrouen.rss25SB.service.FeedService;
import fr.univrouen.rss25SB.service.ItemService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/rss25SB")
public class FeedController {

    private final FeedService feedService;
    private final ItemService itemService;

    public FeedController(FeedService feedService, ItemService itemService) {
        this.feedService = feedService;
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



    @GetMapping(value = "/resume/html", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView listItemsHtml() {
        ModelAndView mav = new ModelAndView("summary");
        mav.addObject("items", itemService.getAll());
        return mav;
    }

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

    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResponseDto> insertFeed(@RequestBody String xmlContent) {
        try {
            // Validation XML via JAXB + XSD
            JAXBContext context = JAXBContext.newInstance(FeedDto.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = sf.newSchema(new ClassPathResource("xml/rss25.tp1.xsd").getFile());
            unmarshaller.setSchema(schema);

            InputStream xmlStream = new java.io.ByteArrayInputStream(xmlContent.getBytes());
            FeedDto dto = (FeedDto) unmarshaller.unmarshal(xmlStream);
            List<ItemDto> itemDtos = dto.getItem();
            Long lastItemId = itemService.saveAll(feedService.saveFromDto(dto).getItem());
            return ResponseEntity.ok(new ResponseDto(lastItemId,"INSERTED", null));

        } catch (SAXException | JAXBException e) {
            return ResponseEntity.ok(new ResponseDto(null,"ERROR","Erreur d'insertion" + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDto(null, "ERROR","Erreur d'insertion" + e.getMessage()));
        }
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