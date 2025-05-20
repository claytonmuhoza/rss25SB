package fr.univrouen.rss25SB.dto;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contentType")
public class ContentDto {

    @XmlValue
    private String value;

    @XmlAttribute(required = true)
    private String type;

    @XmlAttribute
    private String src;

    // Getters and Setters

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}

