package fr.univrouen.rss25SB.dto;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "items", namespace = "http://univ.fr/rss25")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "items", propOrder = { "items" })
public class ItemListDto {

    @XmlElement(name = "item")
    private List<ItemDto> items;

    public ItemListDto() {}

    public ItemListDto(List<ItemDto> items) {
        this.items = items;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}