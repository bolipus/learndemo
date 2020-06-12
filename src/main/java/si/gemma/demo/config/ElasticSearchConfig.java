package si.gemma.demo.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

@Configuration
public class ElasticSearchConfig extends ElasticsearchConfigurationSupport {

  @Bean
  @Override
  public ElasticsearchCustomConversions elasticsearchCustomConversions() {
    return new ElasticsearchCustomConversions(Arrays.asList(new LongToLocalDate()));
  }

  @ReadingConverter
  static class LongToLocalDate implements Converter<Long, LocalDateTime> {

    @Override
    public LocalDateTime convert(Long source) {
      return LocalDateTime.ofInstant(Instant.ofEpochMilli(source), ZoneId.systemDefault());
    }
  }
}
