package si.gemma.demo.config;

import java.time.Duration;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;
import si.gemma.demo.models.Address;

@ConfigurationProperties(prefix = "learndemo")
@Data
public class LearnDemoProperties {

  private int number;

  private String userName;

  private String phone;

  private List<String> emails;

  private String firstName;

  private String lastName;

  private List<Address> addresses;

  private Duration workingTime;
}
