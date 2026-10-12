package cl.mjmh.ws.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.Header;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpClientConfiguration {

    private String url;

    private Header[] headers;

}
