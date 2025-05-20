package fr.univrouen.rss25SB.dto;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "items", namespace = "http://univ.fr/rss25")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "items", propOrder = { "items" })
public class ItemSummaryListDto {

    @XmlElement(name = "item")
    private List<ItemSummaryDto> items;

    public ItemSummaryListDto() {}

    public ItemSummaryListDto(List<ItemSummaryDto> items) {
        this.items = items;
    }

    public List<ItemSummaryDto> getItems() {
        return items;
    }

    public void setItems(List<ItemSummaryDto> items) {
        this.items = items;
    }
}
