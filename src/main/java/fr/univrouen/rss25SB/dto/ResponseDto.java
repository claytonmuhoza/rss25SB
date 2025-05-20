package fr.univrouen.rss25SB.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "status", "description"})
public class ResponseDto {

    @XmlElement
    private Long id;

    @XmlElement
    private String status;

    @XmlElement
    private String description;

    public ResponseDto() {}

    public ResponseDto(Long id, String status, String description) {
        this.id = id;
        this.status = status;
        this.description = description;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
