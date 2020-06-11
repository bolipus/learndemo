package si.gemma.demo.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.stereotype.Component;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.log4j.Log4j2;
import si.gemma.demo.config.LearnDemoProperties;

@Component
@EndpointWebExtension(endpoint = CustomEndPoint.class)
@Log4j2
public class CustomEndPointExtention {

  public static final String CUSTOM_ENDPOINT_CALLS = "custom.endpoint.calls";

  private LearnDemoProperties prop;

  private MeterRegistry meterRegistry;

  @Autowired
  public CustomEndPointExtention(LearnDemoProperties prop, MeterRegistry meterRegistry) {
    this.prop = prop;
    this.meterRegistry = meterRegistry;
  }


  @ReadOperation
  public WebEndpointResponse<String> getUsername() {
    log.info(prop.getUserName());
    meterRegistry.counter(CUSTOM_ENDPOINT_CALLS).increment();
    return new WebEndpointResponse<>(prop.getUserName(), WebEndpointResponse.STATUS_OK);
  }
}
