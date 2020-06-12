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
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;

@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AuditableElastic {

  public static PrettyTime prettyTime = new PrettyTime();

  @CreatedBy
  private String createdBy;

  @CreatedDate
  @Field(type = FieldType.Date)
  private LocalDateTime createdDate;

  @LastModifiedBy
  private String modifiedBy;

  @LastModifiedDate
  @Field(type = FieldType.Date)
  private LocalDateTime modifiedDate;

  public String getPrettyCreatedDate() {
    if (createdDate == null) {
      return "";
    }
    Date date = Date.from(createdDate.atZone(ZoneId.systemDefault()).toInstant());
    return prettyTime.format(date);
    // return prettyTime.format(createdDate);
  }

  public String getPrettyModifiedDate() {
    if (modifiedDate == null) {
      return "";
    }
    Date date = Date.from(modifiedDate.atZone(ZoneId.systemDefault()).toInstant());
    return prettyTime.format(date);
    // return prettyTime.format(modifiedDate);
  }

}
