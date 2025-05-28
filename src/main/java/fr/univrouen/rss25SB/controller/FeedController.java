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
            Long lastItemId = itemService.saveAll(feedService.saveFromDto(dto).getItem());
            return ResponseEntity.ok(new ResponseDto(lastItemId,"INSERTED", null));

        } catch (SAXException | JAXBException e) {
            return ResponseEntity.ok(new ResponseDto(null,"ERROR","Erreur d'insertion le schema n'est pas valide" + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDto(null, "ERROR","Erreur d'insertion" + e.getMessage()));
        }
    }



}