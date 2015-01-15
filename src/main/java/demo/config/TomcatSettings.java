package demo.config;

/**
 * <p>Company: Reflexion Networks, Inc.
 * <p>Created: Oct 12, 2014
 *
 * @since Monitor v. 1.0
 * @copyright 2014 Reflexion Networks
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.AbstractHttp11JsseProtocol;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(
    prefix = "tomcat.config",
    ignoreUnknownFields = false
)
public class TomcatSettings
{
    ///@name Constants
    //@{

        private static final String COMPRESSION_ON_VALUE = "on";

        public static final List<MediaType> DEFAULT_COMPRESSABLE_TYPES =
            Collections.unmodifiableList(
                Arrays.asList(
                    MediaType.TEXT_HTML,
                    MediaType.TEXT_PLAIN,
                    MediaType.TEXT_XML,
                    MediaType.valueOf( "text/css" ),
                    MediaType.valueOf( "text/javascript" ),
                    MediaType.valueOf( "application/javascript" )
                )
            );

        public static final int DEFAULT_MIMIMUM_BYTES_TO_COMPRESS = 2048;

    //@}

    ///@name Fields
    //@{

        long connectionTimeoutMillis;

        Compression compression;

        UnsecureConnector unsecureConnector;

        SecureConnector secureConnector;

    //@}

    ///@name Lifetime Management
    //@{

        public TomcatSettings ( )
        {
            unsecureConnector = new UnsecureConnector( );

            secureConnector = new SecureConnector( );

            compression = new Compression( );

            connectionTimeoutMillis = 20000L;
        }

    //@}

    ///@name Accessors
    //@{


        public long getConnectionTimeoutMillis ( )
        {
            return connectionTimeoutMillis;
        }

        public UnsecureConnector getUnsecureConnector ( )
        {
            return unsecureConnector;
        }

        public SecureConnector getSecureConnector ( )
        {
            return secureConnector;
        }

        public Compression getCompression ( )
        {
            return compression;
        }

    //@}

    ///@name Mutators
    //@{

        /**
         * @param connectionTimeoutMillis the connectionTimeoutMillis to set
         */
        public void setConnectionTimeoutMillis ( final long connectionTimeoutMillis )
        {
            this.connectionTimeoutMillis = connectionTimeoutMillis;
        }

        /**
         * @param unsecureConnector the unsecureConnector to set
         */
        public void setUnsecureConnector ( final UnsecureConnector unsecureConnector )
        {
            this.unsecureConnector = unsecureConnector;
        }

        /**
         * @param secureConnector the secureConnector to set
         */
        public void setSecureConnector ( final SecureConnector secureConnector )
        {
            this.secureConnector = secureConnector;
        }

        /**
         * @param compression the compression to set
         */
        public void setCompression ( final Compression compression )
        {
            this.compression = compression;
        }

    //@}

    ///@name Methods
    //@{

        public void customizeContainer (
                final TomcatEmbeddedServletContainerFactory tomcat
            )
        {
        }

        public void customizeConnector (
                final Connector connector
            )
        {
            final ProtocolHandler baseProtocol = connector.getProtocolHandler( );

            final AbstractHttp11JsseProtocol<?> protocol =
                (AbstractHttp11JsseProtocol<?>) baseProtocol;

            protocol.setConnectionTimeout( (int) connectionTimeoutMillis );

            if ( compression != null )
            {
                compression.customizeConnector( protocol );
            }

            if ( connector.getSecure( ) )
            {
                secureConnector.customizeConnector( connector, protocol );
            }
            else
            {
                unsecureConnector.customizeConnector( connector, protocol );
            }
        }

    //@}


    public static final class Compression
    {
        ///@name Fields
        //@{

            List<MediaType> compressableMimeTypes;

            int minimumBytesToCompress;

        //@}

        ///@name Lifetime Management
        //@{

            public Compression ( )
            {
                compressableMimeTypes = DEFAULT_COMPRESSABLE_TYPES;

                minimumBytesToCompress = DEFAULT_MIMIMUM_BYTES_TO_COMPRESS;
            }

        //@}

        ///@name Accessors
        //@{

            /**
             * @return the compressableMimeTypes
             */
            public List< MediaType > getCompressableMimeTypes ( )
            {
                return compressableMimeTypes;
            }

            /**
             * @return the minimumBytesToCompress
             */
            public int getMinimumBytesToCompress ( )
            {
                return minimumBytesToCompress;
            }

        //@}

        ///@name Mutators
        //@{

            /**
             * @param compressableMimeTypes the compressableMimeTypes to set
             */
            public void setCompressableMimeTypes ( final String compressableMimeTypes )
            {
                try
                {
                    this.compressableMimeTypes =
                        Collections.unmodifiableList(
                            MediaType.parseMediaTypes( compressableMimeTypes )
                        );

                    if ( compressableMimeTypes.isEmpty( ) )
                    {
                        throw
                            new IllegalArgumentException(
                                String.format(
                                    "Empty or null compressableMimeTypes"
                                )
                            );
                    }
                }
                catch ( final RuntimeException ex )
                {
                    throw
                        new IllegalArgumentException(
                            String.format(
                                "Invalid compressableMimeTypes <%s>",
                                compressableMimeTypes
                            ),
                            ex
                        );
                }
            }

            /**
             * @param minimumBytesToCompress the minimumBytesToCompress to set
             */
            public void setMinimumBytesToCompress ( final int minimumBytesToCompress )
            {
                this.minimumBytesToCompress = minimumBytesToCompress;
            }

            /**
             * @return the proxyPort
             */
            final void customizeConnector (
                    final AbstractHttp11JsseProtocol< ? > protocol
                )
            {
                protocol.setCompression( COMPRESSION_ON_VALUE );
                final StringBuilder sb = new StringBuilder( );
                boolean isFirst = true;

                for ( final MediaType cur : compressableMimeTypes )
                {
                    if ( ! isFirst )
                    {
                        sb.append( "," );
                    }
                    sb.append( cur );

                    isFirst = false;
                }

                protocol.setCompressableMimeTypes( sb.toString( ) );
                protocol.setCompressionMinSize( minimumBytesToCompress );
            }

        //@}
    }

    public static abstract class AbstractConnectorProperties
    {
        ///@name Fields
        //@{

            int proxyPort;

        //@}

        ///@name Lifetime Management
        //@{

            AbstractConnectorProperties (
                    final int proxyPort
                )
            {
                this.proxyPort = proxyPort;
            }

        //@}

        ///@name Accessors
        //@{

            /**
             * @return the proxyPort
             */
            public int getProxyPort ( )
            {
                return proxyPort;
            }

        //@}

        ///@name Mutators
        //@{

            /**
             * @param proxyPort the proxyPort to set
             */
            public void setProxyPort ( final int proxyPort )
            {
                this.proxyPort = proxyPort;
            }

        //@}

        ///@name Methods
        //@{

            /**
             * @return the proxyPort
             */
            final void customizeConnector (
                    final Connector connector,
                    final AbstractHttp11JsseProtocol< ? > protocol
                )
            {
                connector.setProxyPort( proxyPort );

                applyConnectorCustomizations( connector, protocol );
            }

            abstract void applyConnectorCustomizations (
                    final Connector connector,
                    final AbstractHttp11JsseProtocol< ? > protocol
                );

        //@}
    }

    public static final class UnsecureConnector
        extends AbstractConnectorProperties
    {
        ///@name Fields
        //@{

            int port;

            int secureRedirectPort;

        //@}

        ///@name Lifetime Management
        //@{

            public UnsecureConnector ( )
            {
                super(
                    80
                );

                this.port = 7080;

                this.secureRedirectPort = 443;
            }

        //@}

        ///@name Accessors
        //@{

            /**
             * @return the port
             */
            public int getPort ( )
            {
                return port;
            }

            /**
             * @return the secureRedirectPort
             */
            public int getSecureRedirectPort ( )
            {
                return secureRedirectPort;
            }

        //@}

        ///@name Mutators
        //@{

            /**
             * @param port the port to set
             */
            public void setPort ( final int port )
            {
                this.port = port;
            }

            /**
             * @param secureRedirectPort the secureRedirectPort to set
             */
            public void setSecureRedirectPort ( final int secureRedirectPort )
            {
                this.secureRedirectPort = secureRedirectPort;
            }

        //@}

        ///@name Methods
        //@{

            @Override
            final void applyConnectorCustomizations (
                    final Connector connector,
                    final AbstractHttp11JsseProtocol< ? > protocol
                )
            {
                connector.setPort( port );

                connector.setRedirectPort( secureRedirectPort );

                connector.setURIEncoding( "utf-8" );
            }

        //@}
    }


    public static final class SecureConnector
        extends AbstractConnectorProperties
    {
        ///@name Fields
        //@{


        //@}

        ///@name Lifetime Management
        //@{

            public SecureConnector ( )
            {
                super( 443 );
            }

        //@}

        ///@name Accessors
        //@{

        //@}

        ///@name Mutators
        //@{

        //@}

        ///@name Methods
        //@{

            @Override
            final void applyConnectorCustomizations (
                    final Connector connector,
                    final AbstractHttp11JsseProtocol< ? > protocol
                )
            {
            }

        //@}
    }
}
