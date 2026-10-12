package cl.mjmh.ws.client.client;

import cl.mjmh.ws.client.exception.HttpClientException;
import cl.mjmh.ws.client.util.Util;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class ClientBuilder {

    private static final Logger LOGGER = LogManager.getLogger(ClientBuilder.class);

    public <T> T doExecute(URI uri, Header[] headers, HttpClient httpClient, Class<T> classResponseType) {
        HttpRequestBase httpGet = doGet(uri, headers);

        LOGGER.info(Util.MSG_FORMAT_REQUEST, httpGet.getRequestLine(), httpGet.getAllHeaders());
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            final int httpStatusCode = httpResponse.getStatusLine().getStatusCode();

            if(!Util.isOk(httpStatusCode)){
                String message = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
                LOGGER.error(Util.MSG_FORMAT_RESPONSE,
                        httpResponse.getStatusLine().getStatusCode(),
                        httpResponse.getStatusLine().getReasonPhrase());
                throw new HttpClientException(httpResponse.getStatusLine() + "\n" + message);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            T response = objectMapper.readValue(httpResponse.getEntity().getContent(), classResponseType);
            LOGGER.info(Util.MSG_FORMAT_RESPONSE, httpStatusCode, response);
            return response;
        } catch(JsonParseException | JsonMappingException jsonProcessingException) {
            LOGGER.error("No se ha podido realizar una copia correcta del Json al objeto");
            throw new HttpClientException(jsonProcessingException);
        } catch(ClientProtocolException clientProtocolException) {
            LOGGER.error("No se ha podido ejecutar correctamente el protocolo");
            throw new HttpClientException(clientProtocolException);
        } catch (IOException ioException) {
            LOGGER.error("No se ha podido recuperar el recurso solicitado");
            throw new HttpClientException(ioException);
        }
    }

    private HttpGet doGet(URI uri, Header[] headers){
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeaders(headers);
        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        return httpGet;
    }

}
