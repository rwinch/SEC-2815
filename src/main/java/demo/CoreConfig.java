package demo;

/**
 * Copyright: Copyright (c) 2002-2014
 * Company: Reflexion Networks, Inc.
 * Created: August 2014
 *
 * @author droyer@reflexion.net
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author droyer@reflexion.net
 */
@Configuration
@Import({
    SecurityConfig.class,
    WebConfig.class
})
@ComponentScan(
    basePackageClasses = {
        demo.config.TomcatSettings.class,
        demo.web.services.DataCenterService.class
    }
)
public class CoreConfig
{
}
