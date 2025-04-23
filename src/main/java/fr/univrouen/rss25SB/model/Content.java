package fr.univrouen.rss25SB.model;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contentType")
class Content {
    @XmlValue
    private String value;

    @XmlAttribute(required = true)
    private String type;

    @XmlAttribute
    private String src;

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSrc() { return src; }
    public void setSrc(String src) { this.src = src; }
}