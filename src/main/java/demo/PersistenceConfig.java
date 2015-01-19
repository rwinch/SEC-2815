package demo;

/**
 * Copyright: Copyright (c) 2002-2014
 * Company: Reflexion Networks, Inc.
 * Created: August 2014
 *
 * @author droyer@reflexion.net
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author droyer@reflexion.net
 */
@Configuration
@EnableJpaRepositories(
    basePackageClasses = {
        demo.subpackage.DummyRepository.class
    }
)
@EnableTransactionManagement
public class PersistenceConfig
{
}
