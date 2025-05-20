package fr.univrouen.rss25SB.repository;

import fr.univrouen.rss25SB.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByPublishedAfter(Date date);
    List<Item> findByCategory_Term(String term);
}
