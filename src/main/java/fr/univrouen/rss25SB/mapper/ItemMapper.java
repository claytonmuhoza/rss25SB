package fr.univrouen.rss25SB.mapper;

import fr.univrouen.rss25SB.dto.*;
import fr.univrouen.rss25SB.model.*;

import java.util.List;
import java.util.stream.Collectors;
//permet de transformer les données du modèle Item en XML (DTO) qui est envoyé au client ou inversement (XML reçu du client vers le format du modèle)
public class ItemMapper {

    public static Item dtoToEntity(ItemDto dto) {
        Item item = new Item();
        item.setGuid(dto.getGuid());
        item.setTitle(dto.getTitle());
        item.setPublished(dto.getPublished());
        item.setUpdated(dto.getUpdated());

        item.setCategory(dto.getCategory()
                .stream()
                .map(ItemMapper::categoryDtoToEntity)
                .collect(Collectors.toList()));
        item.setAuthors(dto.getAuthors()
                .stream()
                .map(ItemMapper::authorDtoToEntity)
                .collect(Collectors.toList())
        );
        item.setContributors(dto.getContributors()
                .stream()
                .map(ItemMapper::authorDtoToEntity)
                .collect(Collectors.toList())
        );
        item.setContent(contentDtoToEntity(dto.getContent()));
        item.setImage(imageDtoToEntity(dto.getImage()));

        return item;
    }

    public static ItemDto entityToDto(Item entity) {
        ItemDto dto = new ItemDto();
        dto.setGuid(entity.getGuid());
        dto.setTitle(entity.getTitle());
        dto.setPublished(entity.getPublished());
        dto.setUpdated(entity.getUpdated());

        dto.setCategory(entity.getCategory()
                .stream()
                .map(ItemMapper::categoryEntityToDto)
                .collect(Collectors.toList()));
        dto.setAuthors(entity.getAuthors()
                .stream()
                .map(ItemMapper::authorEntityToDto)
                .collect(Collectors.toList()));
        dto.setContributors(entity.getContributors()
                .stream()
                .map(ItemMapper::authorEntityToDto)
                .collect(Collectors.toList()));
        dto.setContent(contentEntityToDto(entity.getContent()));
        dto.setImage(imageEntityToDto(entity.getImage()));

        return dto;
    }

    // Sous-méthodes utilitaires

    private static Category categoryDtoToEntity(CategoryDto dto) {
        Category c = new Category();
        c.setTerm(dto.getTerm());
        return c;
    }

    private static CategoryDto categoryEntityToDto(Category c) {
        CategoryDto dto = new CategoryDto();
        dto.setTerm(c.getTerm());
        return dto;
    }

    private static Author authorDtoToEntity(AuthorDto dto) {
        Author a = new Author();
        a.setName(dto.getName());
        a.setEmail(dto.getEmail());
        a.setUri(dto.getUri());
        return a;
    }

    private static AuthorDto authorEntityToDto(Author a) {
        AuthorDto dto = new AuthorDto();
        dto.setName(a.getName());
        dto.setEmail(a.getEmail());
        dto.setUri(a.getUri());
        return dto;
    }

    private static Content contentDtoToEntity(ContentDto dto) {
        if (dto == null) return null;
        Content c = new Content();
        c.setValue(dto.getValue());
        c.setType(dto.getType());
        c.setSrc(dto.getSrc());
        return c;
    }

    private static ContentDto contentEntityToDto(Content c) {
        if (c == null) return null;
        ContentDto dto = new ContentDto();
        dto.setValue(c.getValue());
        dto.setType(c.getType());
        dto.setSrc(c.getSrc());
        return dto;
    }

    private static Image imageDtoToEntity(ImageDto dto) {
        if (dto == null) return null;
        Image i = new Image();
        i.setType(dto.getType());
        i.setHref(dto.getHref());
        i.setAlt(dto.getAlt());
        i.setLength(dto.getLength());
        return i;
    }

    private static ImageDto imageEntityToDto(Image i) {
        if (i == null) return null;
        ImageDto dto = new ImageDto();
        dto.setType(i.getType());
        dto.setHref(i.getHref());
        dto.setAlt(i.getAlt());
        dto.setLength(i.getLength());
        return dto;
    }
}
