package fr.univrouen.rss25SB.service;

import fr.univrouen.rss25SB.dto.ItemDto;
import fr.univrouen.rss25SB.model.Feed;
import fr.univrouen.rss25SB.model.Item;
import fr.univrouen.rss25SB.repository.FeedRepository;
import fr.univrouen.rss25SB.repository.ItemRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final FeedRepository feedRepository;

    public ItemService(ItemRepository itemRepository, FeedRepository feedRepository) {
        this.itemRepository = itemRepository;
        this.feedRepository = feedRepository;
    }
    public long saveAll(List<Item> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("La liste d'articles est vide.");
        }

        List<Item> savedItems = items.stream().map(itemRepository::save).toList();
        return savedItems.stream()
                .mapToLong(Item::getId)
                .max()
                .orElseThrow(() -> new RuntimeException("Aucun article enregistré"));
    }


    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getByIdOrThrow(Long id) throws Exception {
        return itemRepository.findById(id)
                .orElseThrow(() -> new Exception("Article avec l'id " + id + " non trouvé."));
    }
    public List<Item> searchByDate(Date from) {
        return itemRepository.findAll().stream()
                .filter(item ->
                        (item.getPublished() != null && !item.getPublished().before(from)) ||
                                (item.getUpdated() != null && !item.getUpdated().before(from)))
                .toList();
    }
    public void deleteById(Long id) throws Exception {
        Item item = getByIdOrThrow(id);
        Feed feed = item.getFeed();

        // Supprimer l’item
        itemRepository.delete(item);

        // Vérifier s'il reste des items associés à ce feed
        if (feed != null && feed.getItem() != null && feed.getItem().size() == 1) {
            // C'était le dernier item, on supprime aussi le feed
            feedRepository.delete(feed);
        }
    }


    public List<Item> searchByCategory(String category) {
        return itemRepository.findByCategory_Term(category);
    }
}