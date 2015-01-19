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
    CacheConfig.class,
    PersistenceConfig.class,
    SecurityConfig.class,
    WebConfig.class
})
@ComponentScan(
    basePackageClasses = {
        demo.subpackage.DummyEntity.class
    }
)
public class CoreConfig
{
}
