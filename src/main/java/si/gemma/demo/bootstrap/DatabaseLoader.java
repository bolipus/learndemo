package si.gemma.demo.bootstrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;
import si.gemma.demo.entities.Article;
import si.gemma.demo.entities.Comment;
import si.gemma.demo.entities.Role;
import si.gemma.demo.entities.User;
import si.gemma.demo.models.CommentType;
import si.gemma.demo.repositories.ArticleElasticRepository;
import si.gemma.demo.repositories.CommentRepository;
import si.gemma.demo.repositories.RoleRepository;
import si.gemma.demo.repositories.UserRepository;

@Component
@Log4j2
public class DatabaseLoader implements CommandLineRunner {

  private UserRepository userRepository;

  private RoleRepository roleRepository;

  private CommentRepository commentRepository;

  private ArticleElasticRepository articleElasticRepository;

  private Map<String, User> users = new HashMap<>();

  public DatabaseLoader(UserRepository userRepository, RoleRepository roleRepository,
      CommentRepository commentRepository, ArticleElasticRepository articleElasticRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.commentRepository = commentRepository;
    this.articleElasticRepository = articleElasticRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    addUsersAndRoles();
    addCommentsForUser();
    addArticles();

  }


  private void addUsersAndRoles() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String password = encoder.encode("password");

    Role userRole = new Role("ROLE_USER");
    userRole = roleRepository.save(userRole);


    Role adminRole = new Role("ROLE_ADMIN");
    adminRole = roleRepository.save(adminRole);

    User user = new User("user", password, "Janez", "Kovač");
    user.addRole(userRole);
    user = userRepository.save(user);
    users.put("user", user);

    User admin = new User("admin", password, "Admin", "Admin");
    admin.addRole(adminRole);
    admin = userRepository.save(admin);
    users.put("admin", admin);


    User master = new User("master", password, "Master", "Master");
    master.addRoles(new HashSet<>(Arrays.asList(userRole, adminRole)));
    master = userRepository.save(master);
    users.put("master", master);

  }

  private void addCommentsForUser() {
    User user = users.get("user");

    Comment comment = new Comment();
    comment.setText("comment");
    comment.setType(CommentType.PLUS);
    comment.setUser(user);

    commentRepository.save(comment);


    Comment comment2 = new Comment();
    comment2.setText("comment2");
    comment2.setType(CommentType.STAR);
    comment2.setUser(user);

    commentRepository.save(comment2);
  }

  public void addArticles() {
    Lorem lorem = LoremIpsum.getInstance();

    for (int i = 0; i < 2; i++) {
      Article article = new Article();
      article.setTitle(lorem.getTitle(2, 4));
      article.setSummary(lorem.getWords(5, 10));
      article.setBody(lorem.getWords(50, 200));
      article.setUser("user");
      article.setLink("Link");
      log.debug("Article:" + article);
      Article savedArticle = articleElasticRepository.save(article);

      log.debug("Saved article:" + savedArticle);

      Optional<Article> articleOptional = articleElasticRepository.findById(savedArticle.getId());
      if (articleOptional.isPresent()) {
        log.debug(articleOptional.get());
      }

    }
  }

}
