package si.gemma.demo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Document(indexName = "learndemo_article", type = "article")
@RequiredArgsConstructor
@NoArgsConstructor
public class Article extends AuditableElastic {

  @Id
  private String id;

  @NonNull
  private String title;

  private String link;

  @NonNull
  private String summary;

  @NonNull
  private String body;

  private String user;

}
