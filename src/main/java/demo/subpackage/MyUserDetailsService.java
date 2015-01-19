package demo.subpackage;

/**
 * <p>Created: Jan 19, 2015</p>
 * @author Alexander S. Pogrebnyak
 */

///@name Imports
//@{

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//@}

/**
 * @author Alexander S. Pogrebnyak
 */
@Component
public class MyUserDetailsService
    implements UserDetailsService
{
    private final DummyRepository dummyRepo;

    @Inject
    public MyUserDetailsService (
            final DummyRepository dummyRepo
        )
    {
        this.dummyRepo = dummyRepo;
    }


    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername ( final String username ) throws UsernameNotFoundException
    {
        return
            new UserDetails( )
            {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isEnabled ( )
                {
                    return false;
                }

                @Override
                public boolean isCredentialsNonExpired ( )
                {
                    return false;
                }

                @Override
                public boolean isAccountNonLocked ( )
                {
                    return false;
                }

                @Override
                public boolean isAccountNonExpired ( )
                {
                    return false;
                }

                @Override
                public String getUsername ( )
                {
                    return username;
                }

                @Override
                public String getPassword ( )
                {
                    return "password";
                }

                @Override
                public Collection< ? extends GrantedAuthority > getAuthorities ( )
                {
                    return AuthorityUtils.createAuthorityList("ROLE_USER");
                }
            };
    }
}
