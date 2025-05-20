package fr.univrouen.rss25SB.service;

import fr.univrouen.rss25SB.dto.FeedDto;
import fr.univrouen.rss25SB.mapper.FeedMapper;
import fr.univrouen.rss25SB.model.Feed;
import fr.univrouen.rss25SB.model.Item;
import fr.univrouen.rss25SB.repository.FeedRepository;
import fr.univrouen.rss25SB.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FeedService {
    private final FeedRepository feedRepository;

    public FeedService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }


    public Feed saveFromDto(FeedDto dto) throws Exception {
        if (feedRepository.existsByTitleAndPubDate(dto.getTitle(), dto.getPubDate())) {
            throw new Exception("Flux déjà présent avec ce titre et cette date.");
        }
        Feed feed = FeedMapper.dtoToEntity(dto);


        return feedRepository.save(feed);
    }

    public Optional<Feed> findById(Long id) {
        return feedRepository.findById(id);
    }

    public void delete(Feed feed) {
        feedRepository.delete(feed);
    }
}

