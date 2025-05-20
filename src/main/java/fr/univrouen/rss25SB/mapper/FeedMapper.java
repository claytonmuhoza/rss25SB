package fr.univrouen.rss25SB.mapper;

import fr.univrouen.rss25SB.dto.*;
import fr.univrouen.rss25SB.model.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .map(FeedMapper::itemDtoToEntity)
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
                .map(FeedMapper::itemEntityToDto)
                .collect(Collectors.toList()));

        return dto;
    }

    // === Méthodes utilitaires pour les sous-éléments ===

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

    private static Item itemDtoToEntity(ItemDto dto) {
        Item item = new Item();
        item.setGuid(dto.getGuid());
        item.setTitle(dto.getTitle());
        item.setPublished(dto.getPublished());
        item.setUpdated(dto.getUpdated());

        item.setCategory(dto.getCategory()
                .stream()
                .map(c -> {
                    Category cat = new Category();
                    cat.setTerm(c.getTerm());
                    return cat;
                }).collect(Collectors.toList()));

        item.setAuthorOrContributor(dto.getAuthorOrContributor()
                .stream()
                .map(a -> {
                    Author auth = new Author();
                    auth.setName(a.getName());
                    auth.setEmail(a.getEmail());
                    auth.setUri(a.getUri());
                    return auth;
                }).collect(Collectors.toList()));

        item.setContent(contentDtoToEntity(dto.getContent()));
        item.setImage(imageDtoToEntity(dto.getImage()));

        return item;
    }

    private static ItemDto itemEntityToDto(Item entity) {
        ItemDto dto = new ItemDto();
        dto.setGuid(entity.getGuid());
        dto.setTitle(entity.getTitle());
        dto.setPublished(entity.getPublished());
        dto.setUpdated(entity.getUpdated());

        dto.setCategory(entity.getCategory()
                .stream()
                .map(c -> {
                    CategoryDto cat = new CategoryDto();
                    cat.setTerm(c.getTerm());
                    return cat;
                }).collect(Collectors.toList()));

        dto.setAuthorOrContributor(entity.getAuthorOrContributor()
                .stream()
                .map(a -> {
                    AuthorDto adto = new AuthorDto();
                    adto.setName(a.getName());
                    adto.setEmail(a.getEmail());
                    adto.setUri(a.getUri());
                    return adto;
                }).collect(Collectors.toList()));

        dto.setContent(contentEntityToDto(entity.getContent()));
        dto.setImage(imageEntityToDto(entity.getImage()));

        return dto;
    }

    private static Content contentDtoToEntity(ContentDto dto) {
        if (dto == null) return null;
        Content content = new Content();
        content.setValue(dto.getValue());
        content.setType(dto.getType());
        content.setSrc(dto.getSrc());
        return content;
    }

    private static ContentDto contentEntityToDto(Content entity) {
        if (entity == null) return null;
        ContentDto dto = new ContentDto();
        dto.setValue(entity.getValue());
        dto.setType(entity.getType());
        dto.setSrc(entity.getSrc());
        return dto;
    }

    private static Image imageDtoToEntity(ImageDto dto) {
        if (dto == null) return null;
        Image img = new Image();
        img.setType(dto.getType());
        img.setHref(dto.getHref());
        img.setAlt(dto.getAlt());
        img.setLength(dto.getLength());
        return img;
    }

    private static ImageDto imageEntityToDto(Image entity) {
        if (entity == null) return null;
        ImageDto dto = new ImageDto();
        dto.setType(entity.getType());
        dto.setHref(entity.getHref());
        dto.setAlt(entity.getAlt());
        dto.setLength(entity.getLength());
        return dto;
    }
}
