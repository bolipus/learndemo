package si.gemma.demo.services.impl;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import si.gemma.demo.entities.Comment;
import si.gemma.demo.entities.User;
import si.gemma.demo.exceptions.CommentNotFoundException;
import si.gemma.demo.exceptions.UserNotFoundException;
import si.gemma.demo.repositories.CommentRepository;
import si.gemma.demo.repositories.UserRepository;
import si.gemma.demo.services.CommentService;

@Service
@Log4j2
public class CommentServiceImpl implements CommentService {

  private CommentRepository commentRepository;
  private UserRepository userRepository;

  @Autowired
  public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
    this.commentRepository = commentRepository;
    this.userRepository = userRepository;
  }

  @Override
  public List<Comment> findAll() {
    return commentRepository.findAll();
  }

  @Override
  public List<Comment> findAllByUser(Long userId) throws UserNotFoundException {
    Optional<User> userOptional = userRepository.findById(userId);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("User with id " + userId + " not found");
    }
    return commentRepository.findByUser(userOptional.get());
  }

  @Override
  public Comment findById(Long id) throws CommentNotFoundException {
    Optional<Comment> commentOptional = commentRepository.findById(id);
    if (!commentOptional.isPresent()) {
      throw new CommentNotFoundException("Comment with id " + id + " not found");
    }
    return commentOptional.get();
  }

  @Override
  @Transactional
  public Comment save(Comment comment) {
    return commentRepository.save(comment);
  }

  @Override
  @Transactional
  public Comment update(Comment updatedComment) throws CommentNotFoundException {
    Comment comment = findById(updatedComment.getId());
    comment.setText(updatedComment.getText());
    comment.setType(updatedComment.getType());
    return commentRepository.save(comment);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    commentRepository.deleteById(id);
  }

}
