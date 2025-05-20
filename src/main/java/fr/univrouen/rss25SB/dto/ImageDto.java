package fr.univrouen.rss25SB.dto;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "imageType")
public class ImageDto {

    @XmlAttribute(required = true)
    private String type;

    @XmlAttribute(required = true)
    private String href;

    @XmlAttribute(required = true)
    private String alt;

    @XmlAttribute
    private Integer length;

    // Getters and Setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}

