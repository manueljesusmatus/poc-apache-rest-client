package cl.mjmh.ws.client;


import cl.mjmh.ws.client.client.SerieClient;
import cl.mjmh.ws.client.client.SerieClientImpl;
import cl.mjmh.ws.client.dto.HttpClientConfiguration;
import cl.mjmh.ws.client.dto.Serie;
import org.apache.http.Header;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String URL = System.getenv("URL_TEST");
    private static final String API_KEY = System.getenv("API_KEY");

    public static void main(String[] args) {
        Header[] headers = {new BasicHeader("x-api-key", API_KEY)};
        SerieClient serieClient = new SerieClientImpl(
                HttpClientConfiguration.builder()
                        .url(URL)
                        .headers(headers)
                        .build(),
                HttpClients.createDefault()
        );
        try {
            LOGGER.info("findNyId(:id)");
            Serie serie = serieClient.findById(1);
            LOGGER.info(serie);
        } catch (Exception exception) {
            LOGGER.error("Error fatal", exception);
        }

    }
}
