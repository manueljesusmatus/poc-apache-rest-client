package cl.mjmh.ws.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Label {

    MANWHA("manhwa"),
    MANHUA("manhua"),
    MANGA("manga"),
    NOVELA("novela"),
    OEL("oel"),
    ONE_SHOT("one shot");
    
    private String description;

}
