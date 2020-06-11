package si.gemma.demo.models;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

  private Long id;

  private String text;

  private CommentType type;

  private String createdBy;

  private LocalDateTime createdDate;

  private String modifiedBy;

  private LocalDateTime modifiedDate;

  private String prettyCreatedDate;

  private String prettyModifiedDate;
}
