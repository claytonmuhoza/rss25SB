package fr.univrouen.rss25SB.dto;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@XmlRootElement(name = "item", namespace = "http://univ.fr/rss25")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemType", propOrder = {
        "guid", "title", "category", "published", "updated", "image", "content", "author", "contributor"
})
public class ItemDto {

    @XmlElement(required = true)
    private String guid;

    @XmlElement(required = true)
    private String title;

    @XmlElement(required = true)
    private List<CategoryDto> category;

    @XmlElement(name = "published")
    private Date published;

    @XmlElement(name = "updated")
    private Date updated;

    @XmlElement(name = "image")
    private ImageDto image;

    @XmlElement(required = true)
    private ContentDto content;

    @XmlElement(name = "author")
    private List<AuthorDto> author = new ArrayList<>();

    @XmlElement(name = "contributor")
    private List<AuthorDto> contributor = new ArrayList<>();
    // Getters and Setters

    public String getGuid() { return guid; }
    public void setGuid(String guid) { this.guid = guid; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<CategoryDto> getCategory() { return category; }
    public void setCategory(List<CategoryDto> category) { this.category = category; }

    public Date getPublished() { return published; }
    public void setPublished(Date published) { this.published = published; }

    public Date getUpdated() { return updated; }
    public void setUpdated(Date updated) { this.updated = updated; }

    public ImageDto getImage() { return image; }
    public void setImage(ImageDto image) { this.image = image; }

    public ContentDto getContent() { return content; }
    public void setContent(ContentDto content) { this.content = content; }

    public List<AuthorDto> getAuthors() { return author; }
    public void setAuthors(List<AuthorDto> authors) { this.author = authors; }

    public List<AuthorDto> getContributors() { return contributor; }
    public void setContributors(List<AuthorDto> contributors) { this.contributor = contributors; }
}
