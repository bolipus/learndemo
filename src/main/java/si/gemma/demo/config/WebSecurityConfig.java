package si.gemma.demo.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().and().formLogin().and().authorizeRequests()
        .requestMatchers(EndpointRequest.to("info", "health")).permitAll()
        .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("SYSTEM").antMatchers("/**")
        .hasRole("USER");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user").password("user").roles("USER").and()
        .withUser("admin").password("admin").roles("USER", "SYSTEM");
  }

}
