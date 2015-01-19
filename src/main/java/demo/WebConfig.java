package demo;

/**
 * Copyright: Copyright (c) 2002-2014
 * Company: Reflexion Networks, Inc.
 * Created: August 2014
 *
 * @author droyer@reflexion.net
 */

import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


/**
 * @author droyer@reflexion.net
 */
@EnableWebMvc
@Configuration
public class WebConfig
    extends
        WebMvcConfigurerAdapter
{

    @Bean
    public LocaleResolver localeResolver ( )
    {
        final SessionLocaleResolver retVal = new SessionLocaleResolver( );
        retVal.setDefaultLocale( Locale.US );

        return retVal;
    }

    @Override
    public void addViewControllers ( final ViewControllerRegistry registry )
    {
        registry.addViewController(
            "/login" )
            .setViewName(
                "login" );

    }

    @Override
    public void addResourceHandlers ( final ResourceHandlerRegistry registry )
    {
    }

    @Override
    public void addInterceptors ( final InterceptorRegistry registry )
    {
    }

    @Override
    public void addArgumentResolvers ( final List< HandlerMethodArgumentResolver > argumentResolvers )
    {
    }
}
