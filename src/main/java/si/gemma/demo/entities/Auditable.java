package si.gemma.demo.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import com.ocpsoft.pretty.time.PrettyTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class Auditable {

  public static PrettyTime prettyTime = new PrettyTime();

  @CreatedBy
  private String createdBy;

  @CreatedDate
  private LocalDateTime createdDate;

  @LastModifiedBy
  private String modifiedBy;

  @LastModifiedDate
  private LocalDateTime modifieDate;

  public String getPrettyCreatedDate() {
    Date date = Date.from(getCreatedDate().atZone(ZoneId.systemDefault()).toInstant());
    return prettyTime.format(date);
  }

  public String getPrettyModifiedDate() {
    Date date = Date.from(getCreatedDate().atZone(ZoneId.systemDefault()).toInstant());
    return prettyTime.format(date);
  }

}
