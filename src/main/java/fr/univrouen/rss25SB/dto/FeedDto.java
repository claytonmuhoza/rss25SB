package fr.univrouen.rss25SB.dto;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "feed")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "feedType", propOrder = {"title", "pubDate", "copyright", "link", "item"})
public class FeedDto {

    @XmlElement(required = true)
    private String title;

    @XmlElement(required = true)
    private Date pubDate;

    @XmlElement(required = true)
    private String copyright;

    @XmlElement(name = "link")
    private List<LinkDto> link = new ArrayList<>();

    @XmlElement(name = "item", required = true)
    private List<ItemDto> item = new ArrayList<>();;

    @XmlAttribute(required = true)
    private String lang;

    @XmlAttribute(required = true)
    private String version = "25";

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Date getPubDate() { return pubDate; }
    public void setPubDate(Date pubDate) { this.pubDate = pubDate; }

    public String getCopyright() { return copyright; }
    public void setCopyright(String copyright) { this.copyright = copyright; }

    public List<LinkDto> getLink() { return link; }
    public void setLink(List<LinkDto> link) { this.link = link; }

    public List<ItemDto> getItem() { return item; }
    public void setItem(List<ItemDto> item) { this.item = item; }

    public String getLang() { return lang; }
    public void setLang(String lang) { this.lang = lang; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
}
