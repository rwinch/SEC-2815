package demo;

/**
 * <p>Created: Jan 15, 2015</p>
 * @author Alexander S. Pogrebnyak
 */

///@name Imports
//@{

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

//@}

/**
 * Security config to reproduce SEC-2815
 *
 * @author Alexander S. Pogrebnyak
 */
@EnableWebMvcSecurity
@Configuration
@EnableGlobalMethodSecurity( prePostEnabled=true )
public class SecurityConfig
    extends WebSecurityConfigurerAdapter
{
    @Configuration
    protected static class AuthenticationConfiguration
        extends GlobalAuthenticationConfigurerAdapter
    {
        @Inject
        DummyRepository repoInjectCauses2815;
    }
}
