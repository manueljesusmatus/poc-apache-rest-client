package cl.mjmh.ws.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Serie implements Serializable {

    private static final long serialVersionUID = 8134504198613762432L;

    private Long id;
    private String tipo;
    private String nombre;
    private String urlImagen;
    private String url;

}
