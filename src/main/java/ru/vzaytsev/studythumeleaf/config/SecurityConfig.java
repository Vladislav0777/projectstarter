package ru.vzaytsev.studythumeleaf.config;

import com.atlassian.crowd.integration.http.CrowdHttpAuthenticatorImpl;
import com.atlassian.crowd.integration.http.util.CrowdHttpTokenHelper;
import com.atlassian.crowd.integration.http.util.CrowdHttpTokenHelperImpl;
import com.atlassian.crowd.integration.http.util.CrowdHttpValidationFactorExtractor;
import com.atlassian.crowd.integration.http.util.CrowdHttpValidationFactorExtractorImpl;
import com.atlassian.crowd.integration.rest.service.factory.RestCrowdClientFactory;
import com.atlassian.crowd.integration.springsecurity.RemoteCrowdAuthenticationProvider;
import com.atlassian.crowd.integration.springsecurity.user.CrowdUserDetailsServiceImpl;
import com.atlassian.crowd.service.client.ClientProperties;
import com.atlassian.crowd.service.client.ClientPropertiesImpl;
import com.atlassian.crowd.service.client.CrowdClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Properties;

/**
 * Рабочий пример
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private Environment environment;

    @Bean
    public CrowdClient crowdClient() {
        return new RestCrowdClientFactory().newInstance(clientProperties());
    }

    @Bean
    public RestCrowdClientFactory crowdClientFactory () {
        return new RestCrowdClientFactory();
    }

    @Bean
    public CrowdHttpValidationFactorExtractor validationFactorExtractor () {
        return CrowdHttpValidationFactorExtractorImpl.getInstance();
    }

    @Bean
    public CrowdHttpTokenHelper tokenHelper () {
        return CrowdHttpTokenHelperImpl.getInstance(CrowdHttpValidationFactorExtractorImpl.getInstance());
    }

    @Bean
    public ClientProperties clientProperties() {
        Properties crowdProperties = new Properties();
        crowdProperties.setProperty("application.name", environment.getProperty("application.name"));
        crowdProperties.setProperty("application.password", environment.getProperty("application.password"));
        crowdProperties.setProperty("application.login.url", environment.getProperty("application.login.url"));
        crowdProperties.setProperty("crowd.server.url", environment.getProperty("crowd.server.url"));
        crowdProperties.setProperty("session.isauthenticated", environment.getProperty("session.isauthenticated"));
        crowdProperties.setProperty("session.tokenkey", environment.getProperty("session.tokenkey"));
        crowdProperties.setProperty("session.validationinterval", environment.getProperty("session.validationinterval"));
        crowdProperties.setProperty("session.lastvalidation", environment.getProperty("session.lastvalidation"));
        return ClientPropertiesImpl.newInstanceFromProperties(crowdProperties);
    }

    @Bean
    public CrowdHttpAuthenticatorImpl crowdHttpAuthenticator () {
        return new CrowdHttpAuthenticatorImpl(crowdClient(), clientProperties(), tokenHelper());
    }

    @Bean
    public CrowdUserDetailsServiceImpl crowdUserDetailsService () {
        CrowdUserDetailsServiceImpl cuds = new CrowdUserDetailsServiceImpl();
        cuds.setCrowdClient(crowdClient());
        return cuds;
    }

    @Bean
    public RemoteCrowdAuthenticationProvider crowdAuthenticationProvider() {
        return new CrowdAuthenticationProviderImpl(crowdClient(), crowdHttpAuthenticator(), crowdUserDetailsService());
    }

    @Bean
    public AuthenticationManager authProviderManager() throws Exception {
        return new ProviderManager(List.of(crowdAuthenticationProvider()));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authenticationManager(authProviderManager())
                .authenticationProvider(crowdAuthenticationProvider())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/login", "/oauth2/**", "/home",
                                "/css/**", "/webjars/**", "/js/**", "/image/**", "/upload/").permitAll()
                        .requestMatchers("/categories").hasAuthority("developers")
                        .requestMatchers("/brands").hasAuthority("users")
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer.defaultSuccessUrl("/", true);
                })
                .logout(httpSecurityLogoutConfigurer -> {
                    httpSecurityLogoutConfigurer
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login?logout")
                            .deleteCookies("JSESSIONID")
                            .invalidateHttpSession(true)
                            .clearAuthentication(true)
                            .permitAll();
                });
        return http.build();
    }
}
