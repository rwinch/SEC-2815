package demo;

/**
 * <p>Created: Jan 16, 2015</p>
 * @author Alexander S. Pogrebnyak
 */

///@name Imports
//@{

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@}

/**
 * Cache configuration
 *
 * @author Alexander S. Pogrebnyak
 */
@Configuration
@EnableCaching
public class CacheConfig
{
    ///@name Logger
    //@{

        private static final Logger _LOGGER = LoggerFactory.getLogger( CacheConfig.class );

    //@}

    ///@name Beans
    //@{

        @Bean
        public CacheManager cacheManager ( )
        {
            _LOGGER.debug( "In {}.cacheManager", this.getClass( ) );

            return new ConcurrentMapCacheManager( );
        }

    //@}
}
