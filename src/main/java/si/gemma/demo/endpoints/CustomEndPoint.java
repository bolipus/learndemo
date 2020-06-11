package si.gemma.demo.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;
import si.gemma.demo.config.LearnDemoProperties;

@Component
@Endpoint(id = "custom")
@Log4j2
public class CustomEndPoint {

  @ReadOperation
  public String get() {
    return "Custom EndPoint";
  }

  @WriteOperation
  public void set(String message) {
    log.info(message);
  }


}
