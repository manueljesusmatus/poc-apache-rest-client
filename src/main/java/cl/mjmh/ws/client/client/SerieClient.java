package cl.mjmh.ws.client.client;

import cl.mjmh.ws.client.dto.Serie;
import lombok.NonNull;

import java.util.Collection;

public interface SerieClient {

    Serie findById(@NonNull Integer id);

    Collection<Serie> findAll();

    Collection<Serie> findByLabel(@NonNull String tipo);

}
