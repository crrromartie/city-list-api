package pl.solberg.cities.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ServerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String ALL = "/**";

    @Value("${auth.enabled}")
    private boolean authEnabled;

    private final JwtAuthenticationConverter jwtAuthenticationConverter;

    protected void configure(HttpSecurity http) throws Exception {
        if (authEnabled) {
            configureAuthenticated(http);
        } else {
            configureUnauthenticated(http);
        }
    }

    protected void configureAuthenticated(HttpSecurity httpSecurity) throws Exception {
        var security = httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests();

        security.antMatchers(HttpMethod.PUT, "/cities").hasRole("allowed_edit");

        security.and()
                .oauth2ResourceServer()
                .bearerTokenResolver(bearerTokenResolver())
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);
    }

    protected void configureUnauthenticated(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(ALL).permitAll();
    }

    @Bean
    public BearerTokenResolver bearerTokenResolver() {
        return new DefaultBearerTokenResolver();
    }
}
