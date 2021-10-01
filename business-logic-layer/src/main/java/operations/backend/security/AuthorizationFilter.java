package operations.backend.security;

import operations.domains.user.entity.User;
import operations.domains.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);
    private UserService userService;

    public AuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {


        logger.debug("filter in action...");
        String headerXauthSubject = req.getHeader("x-auth-subject");
        String headerXauthRole = req.getHeader("x-auth-roles");
        String eMail = req.getHeader("x-auth-email");
        String username = req.getHeader("x-auth-username");
        String userid = req.getHeader("x-auth-userid");
        logger.debug("x-auth-subject {}", headerXauthSubject);
        logger.debug("x-auth-roles {}", headerXauthRole);

        if (headerXauthSubject == null || headerXauthRole == null) {
            chain.doFilter(req, res);
            return;
        }
        List<String> listRoles = Arrays.stream(headerXauthRole.split(","))
                .collect(Collectors.toList());
        Collection<? extends GrantedAuthority> authorities = listRoles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        if (logger.isDebugEnabled()) {
            for (GrantedAuthority auth : authorities) {
                logger.debug(auth.getAuthority());
            }
        }
        User user = null;
        try {
            user = this.userService.findBySubject(headerXauthSubject);

           /* if (user.isActive()) {
                user = this.saveUser(user, username, eMail, listRoles, userid);
            } else if (!user.isActive()) {
                user.setActive(true);
                user = this.saveUser(user, username, eMail, listRoles, userid);
            }*/
        } catch (Exception exception) {
            ////TODO riguardare se funziona con il fatto che qui sopra ho messo exception piuttosto che EntityNotFoundException
            user = new User();
            user.setSubject(headerXauthSubject);
            user = this.saveUser(user, username, eMail, listRoles, userid);
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(headerXauthSubject, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.debug(String.valueOf(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()));
        chain.doFilter(req, res);
    }

    private User saveUser(User user, String username, String email, List<String> roles, String userid) {
        user.setUsername(username);
        user.setEmail(email);
        user.setRoles(roles);
        user.setExternalId(userid);

        return this.userService.save(user);
    }

}
