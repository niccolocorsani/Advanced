package operations.backend.configurations;

import operations.backend.security.AuthorizationFilter;
import operations.domains.user.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
//@EnableWebSecurity //TODO da levare il commento nell'app in produzione
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserService userService;


    private static String[] publicEndpoints = new String[]{
            "/api/test/",
            "/api/user/*",
            "/api/user",

    };

    //Swagger exclusions
    private static String[] swaggerEndpoints = new String[]{
            "/v2/api-docs"
            , "/configuration/ui"
            , "/swagger-resources"
            , "/configuration/security"
            , "/swagger-ui.html"
            , "/webjars/**"
            , "/swagger-resources/configuration/ui"
            , "/docs/**"
            , "/swagger-resources/configuration/security"
    };

    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf()
                .disable()
                //.cors().disable()
                .exceptionHandling()
                // .authenticationEntryPoint(unauthorizedHandler())
                .and()
                .authorizeRequests()
                .antMatchers(publicEndpoints).permitAll()
                .antMatchers(swaggerEndpoints).permitAll()
                .antMatchers("/h2-console/**").permitAll() //Necessario per far funzionare H2 */
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthorizationFilter(authenticationManagerBean(), this.userService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().disable(); //Necessario per far funzionare H2

    }

}
