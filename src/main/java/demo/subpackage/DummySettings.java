package demo.subpackage;

/**
 * @author Alexander S. Pogrebnyak
 */

///@name Imports
//@{

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@}

/**
 * @author Alexander S. Pogrebnyak
 */
@Component
@ConfigurationProperties(
    prefix = "dummy.settings",
    ignoreUnknownFields = false
)
public class DummySettings
{
    public DummySettings ( )
    {

    }
}
