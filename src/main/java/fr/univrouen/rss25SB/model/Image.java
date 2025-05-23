package fr.univrouen.rss25SB.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Image {

    @Column(name = "image_type", length = 50)
    private String type;

    @Column(name = "image_href", length = 2048) // pour les URL
    private String href;

    @Column(name = "image_alt", length = 512) // texte alternatif potentiellement long
    private String alt;

    @Column(name = "image_length")
    private Integer length;

    // Getters and setters

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
