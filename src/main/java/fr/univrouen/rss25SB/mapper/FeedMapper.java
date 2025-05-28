package fr.univrouen.rss25SB.mapper;

import fr.univrouen.rss25SB.dto.*;
import fr.univrouen.rss25SB.model.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//permet de transformer les données du modèle Feed en XML (DTO) qui est envoyé au client ou inversement (XML reçu du client vers le format du modèle)
public class FeedMapper {

    public static Feed dtoToEntity(FeedDto dto) {
        Feed feed = new Feed();
        feed.setTitle(dto.getTitle());
        feed.setPubDate(dto.getPubDate());
        feed.setCopyright(dto.getCopyright());
        feed.setLang(dto.getLang());
        feed.setVersion(dto.getVersion());

        List<LinkDto> linkDtos = Optional.ofNullable(dto.getLink()).orElse(List.of());

        List<Link> links = linkDtos.stream()
                .map(FeedMapper::linkDtoToEntity)
                .collect(Collectors.toList());
        feed.setLink(links);

        List<ItemDto> itemDtos = Optional.ofNullable(dto.getItem()).orElse(List.of());

        List<Item> items = itemDtos.stream()
                .map(ItemMapper::dtoToEntity)
                .peek(i -> i.setFeed(feed))
                .collect(Collectors.toList());
        feed.setItem(items);

        return feed;
    }

    public static FeedDto entityToDto(Feed feed) {
        FeedDto dto = new FeedDto();
        dto.setTitle(feed.getTitle());
        dto.setPubDate(feed.getPubDate());
        dto.setCopyright(feed.getCopyright());
        dto.setLang(feed.getLang());
        dto.setVersion(feed.getVersion());

        dto.setLink(feed.getLink()
                .stream()
                .map(FeedMapper::linkEntityToDto)
                .collect(Collectors.toList()));

        dto.setItem(feed.getItem()
                .stream()
                .map(ItemMapper::entityToDto)
                .collect(Collectors.toList()));

        return dto;
    }

    //  Méthodes utilitaires pour les sous-éléments

    private static Link linkDtoToEntity(LinkDto dto) {
        Link link = new Link();
        link.setHref(dto.getHref());
        link.setRel(dto.getRel());
        link.setType(dto.getType());
        return link;
    }

    private static LinkDto linkEntityToDto(Link entity) {
        LinkDto dto = new LinkDto();
        dto.setHref(entity.getHref());
        dto.setRel(entity.getRel());
        dto.setType(entity.getType());
        return dto;
    }
}
