package fr.univrouen.rss25SB.dto;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authorType", propOrder = {"name", "email", "uri"})
public class AuthorDto {

    @XmlElement(required = true)
    private String name;

    @XmlElement
    private String email;

    @XmlElement
    private String uri;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

