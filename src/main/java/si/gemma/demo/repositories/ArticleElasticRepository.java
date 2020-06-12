package si.gemma.demo.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import si.gemma.demo.entities.Article;

@Repository
public interface ArticleElasticRepository extends ElasticsearchRepository<Article, String> {

  Optional<Article> findByLink(String link);

  List<Article> findByBodyContaining(String term);
}
