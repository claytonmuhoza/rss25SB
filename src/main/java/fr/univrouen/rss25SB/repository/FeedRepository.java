package fr.univrouen.rss25SB.repository;

import fr.univrouen.rss25SB.model.Feed;
import fr.univrouen.rss25SB.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
    boolean existsByTitleAndPubDate(String title, Date pubDate);

    List<Feed> item(List<Item> item);
}
