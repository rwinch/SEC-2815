package demo;

/**
 * <p>Created: Jan 15, 2015</p>
 * @author Alexander S. Pogrebnyak
 */

///@name Imports
//@{

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import demo.repositories.DataCenterRepository;

//@}

/**
 * Snapshot of current SecurityConfig
 *
 * @author Alexander S. Pogrebnyak
 */
@EnableWebMvcSecurity
@Configuration
@EnableGlobalMethodSecurity( prePostEnabled=true )
public class SecurityConfig
    extends WebSecurityConfigurerAdapter
{
    ///@name Methods
    //@{

        @Override
        protected void configure (
                final HttpSecurity http
            ) throws Exception
        {
            // Redirect all requests to HTTPS.
            http.requiresChannel( ).anyRequest( ).requiresSecure( );

            // @formatter:off
            http.authorizeRequests( )
                .antMatchers(
                    "/",
                    WebConfig.CssMatcher,
                    WebConfig.ImgMatcher,
                    WebConfig.VendorMatcher
                ).permitAll( )
                .anyRequest( ).authenticated( );
            // @formatter:on

            // @formatter:off
            http.formLogin( )
                    .loginPage( "/login" )
                    .permitAll( )
                    .and( )
                .logout( )
                    .permitAll( );
            // @formatter:on

            // This duplicates the HeaderFilter stuff, but Spring Security operates
            // before Spring Framework, so the HeaderFilter stuff won't even kick in
            // if the response is a Security error.
            http.headers( )
                .addHeaderWriter(
                    new StaticHeadersWriter(
                        "Server",
                        "My Server"
                    )
                ).addHeaderWriter(
                    new StaticHeadersWriter(
                        "Strict-Transport-Security",
                        "max-age=16070400; includeSubDomains"
                    )
                );
        }

    //@}

    @Configuration
    protected static class AuthenticationConfiguration
        extends GlobalAuthenticationConfigurerAdapter
    {
        @Inject
        DataCenterRepository usersRepository;

        @Override
        public void init (
            final AuthenticationManagerBuilder auth )
        {
        }
    }
}
