package si.gemma.demo.entities;

import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import si.gemma.demo.models.CommentType;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Comment extends Auditable {
  @Id
  @SequenceGenerator(name = "COMMENT_SEQ", allocationSize = 25, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ")
  private Long id;

  @NonNull
  private String text;

  @Enumerated(EnumType.STRING)
  @NonNull
  private CommentType type;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

}
