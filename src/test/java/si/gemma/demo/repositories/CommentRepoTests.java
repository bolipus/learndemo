package si.gemma.demo.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.log4j.Log4j2;
import si.gemma.demo.entities.Comment;
import si.gemma.demo.models.CommentType;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Log4j2
public class CommentRepoTests {

  private TestEntityManager entityManager;

  private CommentRepository commentRepository;

  @Autowired
  public CommentRepoTests(TestEntityManager entityManager, CommentRepository commentRepository) {
    this.entityManager = entityManager;
    this.commentRepository = commentRepository;
  }

  @Test
  public void findById() {
    Comment comment = new Comment();
    comment.setText("comment");
    comment.setType(CommentType.PLUS);
    comment = entityManager.persist(comment);

    Optional<Comment> commentOptional = commentRepository.findById(comment.getId());

    assertTrue(commentOptional.isPresent());
    if (commentOptional.isPresent()) {
      assertEquals(comment.getText(), commentOptional.get().getText(),
          "Message texts are not equal");
    }

    log.info("CreatedBy:" + comment.getCreatedBy());
    log.info("CreatedDate:" + comment.getCreatedDate());
  }


  @Test
  public void findAll() {
    Comment comment = new Comment();
    comment.setText("comment");
    comment.setType(CommentType.PLUS);
    comment = entityManager.persist(comment);

    List<Comment> comments = commentRepository.findAll();

    assertEquals(1, comments.size(), "The numbers of comment is not 1.");
    assertEquals(comment.getText(), comments.get(0).getText(), "Message texts are not equal");
  }


  @Test
  public void save() {
    Comment comment = new Comment();
    comment.setText("comment");
    comment.setType(CommentType.PLUS);

    comment = commentRepository.save(comment);

    Comment savedComment = entityManager.find(Comment.class, comment.getId());

    assertEquals(savedComment.getId(), comment.getId(),
        "Comment ids are not equals. Comment is not saved");
  }

}
