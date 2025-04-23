package fr.univrouen.rss25SB.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "linkType")
class Link {
    @XmlAttribute(required = true)
    private String href;

    @XmlAttribute(required = true)
    private String rel;

    @XmlAttribute(required = true)
    private String type;

    public String getHref() { return href; }
    public void setHref(String href) { this.href = href; }

    public String getRel() { return rel; }
    public void setRel(String rel) { this.rel = rel; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
