package demo.repositories;

import javax.persistence.OrderBy;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import demo.entities.DataCenter;

public interface DataCenterRepository extends CrudRepository< DataCenter, Long >
{
    DataCenter findOneByName ( String name );

    @Override
    @OrderBy ( "name ASC" )
    @Cacheable("datacenters")
    Iterable< DataCenter > findAll ( );
}
