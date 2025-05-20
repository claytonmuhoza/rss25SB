package fr.univrouen.rss25SB.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.text.SimpleDateFormat;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "item")
public class ItemSummaryDto {

    @XmlElement
    private Long id;

    @XmlElement
    private String date; // au format ISO, pour éviter JAXB/Date

    @XmlElement
    private String guid;

    public ItemSummaryDto() {}

    public ItemSummaryDto(Long id, Date date, String guid) {
        this.id = id;
        this.guid = guid;
        this.date = format(date);
    }

    private String format(Date date) {
        if (date == null) return null;
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date);
    }

    // Getters / Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getGuid() { return guid; }
    public void setGuid(String guid) { this.guid = guid; }
}
