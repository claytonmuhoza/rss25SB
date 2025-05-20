package fr.univrouen.rss25SB.dto;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categoryType")
public class CategoryDto {

    @XmlAttribute(required = true)
    private String term;

    // Getters and Setters

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
