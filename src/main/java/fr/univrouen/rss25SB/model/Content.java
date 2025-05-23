package fr.univrouen.rss25SB.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Content {

    @Column(name = "content_value", columnDefinition = "TEXT") // Peut contenir de longs textes
    private String value;

    @Column(name = "content_type", length = 50)
    private String type;

    @Column(name = "content_src", length = 2048) // URL potentiellement longue
    private String src;

    // Getters and setters

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
