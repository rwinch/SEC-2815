package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@ComponentScan
public class DemoApplication {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService uds)
    				throws Exception {
    	auth
    		.userDetailsService(uds);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}