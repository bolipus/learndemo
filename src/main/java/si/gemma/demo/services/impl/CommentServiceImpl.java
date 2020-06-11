package si.gemma.demo.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.gemma.demo.entities.Comment;
import si.gemma.demo.entities.User;
import si.gemma.demo.exceptions.CommentNotFoundException;
import si.gemma.demo.exceptions.UserNotFoundException;
import si.gemma.demo.repositories.CommentRepository;
import si.gemma.demo.repositories.UserRepository;
import si.gemma.demo.services.CommentService;

@Service
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
  public Comment save(Comment comment) {
    return commentRepository.save(comment);
  }

  @Override
  public void delete(Long id) {
    commentRepository.deleteById(id);
  }


}
