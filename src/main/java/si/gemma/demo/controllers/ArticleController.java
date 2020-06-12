package si.gemma.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import si.gemma.demo.entities.Article;
import si.gemma.demo.services.ArticleService;

@RestController
@RequestMapping(value = "/api/v1")
public class ArticleController {

  private ArticleService articleService;

  @Autowired
  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @GetMapping("/articles")
  public ResponseEntity<List<Article>> findAll() {
    return ResponseEntity.ok(articleService.findAll());
  }

  @GetMapping("/articles/search")
  public ResponseEntity<List<Article>> findByBodyContaining(
      @RequestParam(name = "term") String term) {
    return ResponseEntity.ok(articleService.findByBodyContaining(term));
  }
}
