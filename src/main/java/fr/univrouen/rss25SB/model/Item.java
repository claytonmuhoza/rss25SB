package fr.univrouen.rss25SB.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1024) // URL longue possible
    private String guid;

    @Column(length = 512) // titres peuvent être longs
    private String title;

    @ElementCollection
    @CollectionTable(name = "item_category", joinColumns = @JoinColumn(name = "item_id"))
    private List<Category> category;

    @Temporal(TemporalType.TIMESTAMP)
    private Date published;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "image_type", length = 50)),
            @AttributeOverride(name = "href", column = @Column(name = "image_href", length = 2048)),
            @AttributeOverride(name = "alt", column = @Column(name = "image_alt", length = 1024)),
            @AttributeOverride(name = "length", column = @Column(name = "image_length"))
    })
    private Image image;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "content_value", columnDefinition = "TEXT")),
            @AttributeOverride(name = "type", column = @Column(name = "content_type", length = 50)),
            @AttributeOverride(name = "src", column = @Column(name = "content_src", length = 2048))
    })
    private Content content;

    @ElementCollection
    @CollectionTable(name = "item_author", joinColumns = @JoinColumn(name = "item_id"))
    private List<Author> authorOrContributor;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    // Getters & Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public void setAuthorOrContributor(List<Author> authorOrContributor) {
        this.authorOrContributor = authorOrContributor;
    }

    public Feed getFeed() { return feed; }

    public void setFeed(Feed feed) { this.feed = feed; }
}
