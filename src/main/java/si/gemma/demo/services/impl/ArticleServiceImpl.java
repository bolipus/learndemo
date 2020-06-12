package si.gemma.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.gemma.demo.entities.Article;
import si.gemma.demo.repositories.ArticleElasticRepository;
import si.gemma.demo.services.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

  private ArticleElasticRepository articleElasticRepository;

  @Autowired
  public ArticleServiceImpl(ArticleElasticRepository articleElasticRepository) {
    this.articleElasticRepository = articleElasticRepository;
  }


  @Override
  public List<Article> findAll() {
    List<Article> articles = new ArrayList<>();
    articleElasticRepository.findAll().forEach(articles::add);
    return articles;
  }


  @Override
  public List<Article> findByBodyContaining(String term) {
    return articleElasticRepository.findByBodyContaining(term);
  }


}
