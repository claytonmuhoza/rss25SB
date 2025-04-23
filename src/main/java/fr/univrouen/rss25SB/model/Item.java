package fr.univrouen.rss25SB.model;

import jakarta.xml.bind.annotation.*;

import java.util.List;
import java.util.Date;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemType", propOrder = {
        "guid", "title", "category", "published", "updated", "image", "content", "authorOrContributor"
})
public class Item {
    private String guid;
    private String title;

    @XmlElement(required = true)
    private List<Category> category;

    @XmlElement(name = "published")
    private Date published;

    @XmlElement(name = "updated")
    private Date updated;

    private Image image;
    private Content content;

    @XmlElements({
            @XmlElement(name = "author", type = Author.class),
            @XmlElement(name = "contributor", type = Author.class)
    })
    private List<Author> authorOrContributor;

    public String getGuid() { return guid; }
    public void setGuid(String guid) { this.guid = guid; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<Category> getCategory() { return category; }
    public void setCategory(List<Category> category) { this.category = category; }

    public Date getPublished() { return published; }
    public void setPublished(Date published) { this.published = published; }

    public Date getUpdated() { return updated; }
    public void setUpdated(Date updated) { this.updated = updated; }

    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }

    public Content getContent() { return content; }
    public void setContent(Content content) { this.content = content; }

    public List<Author> getAuthorOrContributor() { return authorOrContributor; }
    public void setAuthorOrContributor(List<Author> authorOrContributor) { this.authorOrContributor = authorOrContributor; }
}