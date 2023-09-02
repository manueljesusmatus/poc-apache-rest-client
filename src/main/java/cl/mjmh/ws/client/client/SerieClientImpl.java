package cl.mjmh.ws.client.client;

import cl.mjmh.ws.client.dto.HttpClientConfiguration;
import cl.mjmh.ws.client.dto.Serie;
import cl.mjmh.ws.client.exception.HttpClientException;
import cl.mjmh.ws.client.util.Util;
import lombok.Data;
import lombok.NonNull;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

@Data
public class SerieClientImpl implements SerieClient {

    private static final Logger LOGGER = LogManager.getLogger(SerieClientImpl.class);

    @NonNull
    private final HttpClientConfiguration httpClientConfiguration;

    @NonNull
    private final HttpClient httpClient;

    @Override
    public Serie findById(@NonNull Integer id) {
        MDC.put("id", UUID.randomUUID().toString());
        LOGGER.trace("findById(): Consultando por serie con id <{}>", id);
        try {
            URI uri = new URIBuilder(httpClientConfiguration.getUrl())
                    .addParameter("id", Integer.toString(id))
                    .build();

            Serie response = new ClientBuilder()
                    .doExecute(uri, httpClientConfiguration.getHeaders(), httpClient, Serie.class);
            MDC.clear();
            return response;
        } catch (URISyntaxException uriSyntaxException) {
            LOGGER.error(Util.ERROR_MSG_URI_DEFAULT);
            MDC.clear();
            throw new HttpClientException(uriSyntaxException);
        } catch (Exception exception) {
            LOGGER.error(Util.ERROR_MSG_OP_DEFAULT);
            MDC.clear();
            throw new HttpClientException(exception);
        }
    }

    @Override
    public Collection<Serie> findAll() {
        MDC.put("id", UUID.randomUUID().toString());
        LOGGER.trace("findAll(): Consultando por todas las series");
        try {
            URI uri = new URIBuilder(httpClientConfiguration.getUrl())
                    .build();

            Serie[] response = new ClientBuilder()
                    .doExecute(uri, httpClientConfiguration.getHeaders(), httpClient, Serie[].class);
            Collection<Serie> series = Arrays.asList(response);
            MDC.clear();
            return series;
        } catch (URISyntaxException uriSyntaxException) {
            LOGGER.error(Util.ERROR_MSG_URI_DEFAULT);
            MDC.clear();
            throw new HttpClientException(uriSyntaxException);
        } catch (Exception exception) {
            LOGGER.error(Util.ERROR_MSG_OP_DEFAULT);
            MDC.clear();
            throw new HttpClientException(exception);
        }
    }

    @Override
    public Collection<Serie> findByLabel(@NonNull String tipo) {
        MDC.put("id", UUID.randomUUID().toString());
        LOGGER.trace("findByLabel(): Consultando por todas las series de tipo {}", tipo);
        try {
            URI uri = new URIBuilder(httpClientConfiguration.getUrl())
                    .addParameter("tipo", tipo)
                    .build();

            Serie[] response = new ClientBuilder()
                    .doExecute(uri, httpClientConfiguration.getHeaders(), httpClient, Serie[].class);
            Collection<Serie> series = Arrays.asList(response);
            MDC.clear();
            return series;
        } catch (URISyntaxException uriSyntaxException) {
            LOGGER.error(Util.ERROR_MSG_URI_DEFAULT);
            MDC.clear();
            throw new HttpClientException(uriSyntaxException);
        } catch (Exception exception) {
            LOGGER.error(Util.ERROR_MSG_OP_DEFAULT);
            MDC.clear();
            throw new HttpClientException(exception);
        }
    }

}
