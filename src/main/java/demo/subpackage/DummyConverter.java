package demo.subpackage;

/**
 * <p>Copyright: Copyright (c) 2002-2015</p>
 * <p>Company: Reflexion Networks, Inc.</p>
 * <p>Created: Jan 19, 2015</p>
 * @author Alexander S. Pogrebnyak
 */

///@name Imports
//@{

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//@}

/**
 * TODO: brief class description
 * <p>
 * TODO: full description
 * </p>
 *
 * @author Alexander S. Pogrebnyak
 * @since Reflexion v. 7.50
 *
 */
@Component
public class DummyConverter
    implements Converter< String, DummyEntity >
{

    public DummyConverter ( )
    {
    }

    /* (non-Javadoc)
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public DummyEntity convert ( final String source )
    {
        return new DummyEntity( source );
    }
}
