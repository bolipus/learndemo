package si.gemma.demo.services;

import java.util.List;
import si.gemma.demo.entities.Comment;
import si.gemma.demo.exceptions.CommentNotFoundException;
import si.gemma.demo.exceptions.UserNotFoundException;

public interface CommentService {

  List<Comment> findAll();

  List<Comment> findAllByUser(Long userId) throws UserNotFoundException;

  Comment findById(Long id) throws CommentNotFoundException;

  Comment save(Comment comment);

  void delete(Long id);

}
