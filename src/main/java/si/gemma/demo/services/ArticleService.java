package si.gemma.demo.services;

import java.util.List;
import si.gemma.demo.entities.Article;

public interface ArticleService {
  List<Article> findAll();

  public List<Article> findByBodyContaining(String term);
}


