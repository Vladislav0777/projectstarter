package ru.vzaytsev.studythumeleaf.config;

import com.atlassian.crowd.exception.UserNotFoundException;
import com.atlassian.crowd.integration.http.CrowdHttpAuthenticator;
import com.atlassian.crowd.integration.springsecurity.RemoteCrowdAuthenticationProvider;
import com.atlassian.crowd.integration.springsecurity.user.CrowdUserDetails;
import com.atlassian.crowd.integration.springsecurity.user.CrowdUserDetailsService;
import com.atlassian.crowd.model.group.Group;
import com.atlassian.crowd.model.user.User;
import com.atlassian.crowd.service.client.CrowdClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Рабочий пример
 */
@Service
public class CrowdAuthenticationProviderImpl extends RemoteCrowdAuthenticationProvider {
    private final static List<String> roles = List.of("developers", "users");
    private static Logger logger = LoggerFactory.getLogger(CrowdAuthenticationProviderImpl.class);

    public CrowdAuthenticationProviderImpl(CrowdClient authenticationManager, CrowdHttpAuthenticator httpAuthenticator, CrowdUserDetailsService userDetailsService) {
        super(authenticationManager, httpAuthenticator, userDetailsService);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            User crowdUser  = authenticationManager.authenticateUser(username, password);

            List<Group> groupList = authenticationManager.getGroupsForUser(crowdUser.getName(), 0, 200)
                    .stream()
                    .filter(group -> roles.contains(group.getName().toLowerCase(Locale.ROOT)))
                    .toList();
            List<GrantedAuthority> authorities = groupList.stream()
                    .map(group -> new SimpleGrantedAuthority(group.getName().toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
            UserDetails userDetails = new CrowdUserDetails(crowdUser, authorities);

            return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);

        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("Пользователь не найден", e);
        } catch (Exception e) {
            throw new AuthenticationException("Ошибка аутентификации", e) {};
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
