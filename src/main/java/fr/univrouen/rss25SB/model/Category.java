package fr.univrouen.rss25SB.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categoryType")
class Category {
    @XmlAttribute(required = true)
    private String term;

    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }
}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "imageType")
class Image {
    @XmlAttribute(required = true)
    private String type;

    @XmlAttribute(required = true)
    private String href;

    @XmlAttribute(required = true)
    private String alt;

    @XmlAttribute
    private Integer length;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getHref() { return href; }
    public void setHref(String href) { this.href = href; }

    public String getAlt() { return alt; }
    public void setAlt(String alt) { this.alt = alt; }

    public Integer getLength() { return length; }
    public void setLength(Integer length) { this.length = length; }
}
