package si.gemma.demo;

import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.DiskSpaceHealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationProperty;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import lombok.extern.log4j.Log4j2;
import si.gemma.demo.config.LearnDemoProperties;
import si.gemma.demo.models.Address;

@SpringBootApplication
@EnableConfigurationProperties(LearnDemoProperties.class)
@EnableTransactionManagement
@Log4j2
public class LearnDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnDemoApplication.class, args);
	}



	@Bean
	public ApplicationRunner applicationRunner(ConfigurableEnvironment env,
			LearnDemoProperties prop) {


		List<Address> addresses =
				Binder.get(env).bind("learndemo.addresses", Bindable.listOf(Address.class))
						.orElseThrow(IllegalStateException::new);

		log.info("Working time: " + prop.getWorkingTime());

		for (Address address : addresses) {
			log.info(address);
		}

		return args -> log.info("Hello world!");
	}

}
