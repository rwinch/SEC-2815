package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    CoreConfig.class,
    PersistenceConfig.class
})
@EnableAutoConfiguration
@EnableConfigurationProperties
public class DemoApplication
    extends SpringBootServletInitializer
{
    public static void main(final String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}