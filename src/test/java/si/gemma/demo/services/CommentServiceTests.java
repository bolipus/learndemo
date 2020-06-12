package si.gemma.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import si.gemma.demo.entities.Comment;
import si.gemma.demo.exceptions.CommentNotFoundException;
import si.gemma.demo.models.CommentType;
import si.gemma.demo.repositories.CommentRepository;
import si.gemma.demo.repositories.UserRepository;
import si.gemma.demo.services.impl.CommentServiceImpl;

@ExtendWith(SpringExtension.class)
public class CommentServiceTests {

  private @MockBean CommentRepository commentRepository;

  private @MockBean UserRepository userRepository;

  private CommentService commentService;

  @BeforeEach
  public void init() {
    commentService = new CommentServiceImpl(commentRepository, userRepository);
  }

  @Test
  public void findAll() {

    Comment comment = new Comment();
    comment.setText("comment");
    comment.setType(CommentType.PLUS);

    List<Comment> comments = new ArrayList<>();
    comments.add(comment);

    when(commentRepository.findAll()).thenReturn(comments);

    List<Comment> actualComments = commentService.findAll();

    verify(commentRepository, times(1)).findAll();
    assertEquals(comments.size(), actualComments.size(), "The number of comments is not equal");

  }


  @Test
  public void findById() {

    Comment comment = new Comment();
    comment.setId(99L);
    comment.setText("comment");
    comment.setType(CommentType.PLUS);

    when(commentRepository.findById(99L)).thenReturn(Optional.of(comment));

    Comment actualComment = null;
    try {
      actualComment = commentService.findById(99L);
    } catch (CommentNotFoundException e) {
      e.printStackTrace();
    }

    verify(commentRepository, times(1)).findById(99L);
    assertNotNull(actualComment);
    assertEquals(comment.getId(), comment.getId(), "Id are not equal");

  }

}
