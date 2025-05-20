package fr.univrouen.rss25SB.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date pubDate;

    private String copyright;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Link> link;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> item;

    private String lang;

    private String version = "25";

    // Getters and setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Date getPubDate() { return pubDate; }

    public void setPubDate(Date pubDate) { this.pubDate = pubDate; }

    public String getCopyright() { return copyright; }

    public void setCopyright(String copyright) { this.copyright = copyright; }

    public List<Link> getLink() { return link; }

    public void setLink(List<Link> link) { this.link = link; }

    public List<Item> getItem() { return item; }

    public void setItem(List<Item> item) { this.item = item; }

    public String getLang() { return lang; }

    public void setLang(String lang) { this.lang = lang; }

    public String getVersion() { return version; }

    public void setVersion(String version) { this.version = version; }
}
