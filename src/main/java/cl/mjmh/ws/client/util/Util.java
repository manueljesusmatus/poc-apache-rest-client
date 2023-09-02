package cl.mjmh.ws.client.util;

import org.apache.http.HttpStatus;

public class Util {

    public static final String ERROR_MSG_OP_DEFAULT = "La operacion ha fallado";
    public static final String ERROR_MSG_URI_DEFAULT = "Error en la sintaxis de la Url a consultar";
    public static final String MSG_FORMAT_RESPONSE = "Response {} <{}>";
    public static final String MSG_FORMAT_REQUEST = "Request <{}>";

    private Util() {}

    public static boolean isOk(int httpStatusCode){
        return HttpStatus.SC_OK == httpStatusCode;
    }
}
