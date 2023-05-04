package es.tresdigits.worklogminer.configuration;

import es.tresdigits.worklogminer.common.auditor.AuditorAwareImpl;
import es.tresdigits.worklogminer.features.authentication.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableWebSecurity
public class WebSecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired
  public WebSecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //Deshabilita las CORS y la protección CSRF
    http.cors().and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .exceptionHandling().authenticationEntryPoint(
            ((request, response, authException) -> response.sendError(
                HttpServletResponse.SC_UNAUTHORIZED,
                authException.getMessage()))
        )
        .and()
        .authorizeHttpRequests()
        //Permite que todas las rutas pertenecientes a /login y las rutas del swagger pasen
        .requestMatchers("/authentication/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/language/getTranslations/**").permitAll()
        //Protege el resto de rutas
        .anyRequest().authenticated()
        .and()
        //Añade el filtro de JWT a la cadena de filtros de Spring Security después del UsernamePasswordAuthenticationFilter
        .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  AuditorAware<String> auditorProvider() {
    return new AuditorAwareImpl();
  }

}
