package demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
    name = "datacenters" )
public class DataCenter
    implements
        Comparable< DataCenter >
{
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO )
    public long datacenter_id;

    public String name;

    public String location;

    protected DataCenter ( )
    {
    }

    public DataCenter (
            final String name,
            final String location
        )
    {
        super( );
        this.name = name;
        this.location = location;
    }

    @Override
    public String toString ( )
    {
        return "DataCenter [datacenter_id=" + this.datacenter_id + ", name=" + this.name
                + ", location=" + this.location
                + "]";
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode ( )
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( datacenter_id ^ ( datacenter_id >>> 32 ) );
        result = prime * result + ( ( location == null ) ? 0 : location.hashCode( ) );
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode( ) );
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals (
        final Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass( ) != obj.getClass( ) )
            return false;
        final DataCenter other = (DataCenter) obj;
        if ( datacenter_id != other.datacenter_id )
            return false;
        if ( location == null )
        {
            if ( other.location != null )
                return false;
        }
        else if ( !location.equals( other.location ) )
            return false;
        if ( name == null )
        {
            if ( other.name != null )
                return false;
        }
        else if ( !name.equals( other.name ) )
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo (
        final DataCenter other )
    {
        return name.compareTo( other.name );
    }

}
