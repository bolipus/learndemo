package si.gemma.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.gemma.demo.entities.Comment;
import si.gemma.demo.entities.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByUser(User user);
}
