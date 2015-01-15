package demo.web.services;

/**
 * Copyright: Copyright (c) 2002-2014
 * Company: Reflexion Networks, Inc.
 * Created: August 2014
 *
 * @author droyer@reflexion.net
 */

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import demo.entities.DataCenter;
import demo.repositories.DataCenterRepository;

@Component
public class DataCenterService
{
    @Inject
    DataCenterRepository dataCenterRepository;

    public Iterable< DataCenter > getAll ( )
    {
        return dataCenterRepository.findAll( );
    }
}
