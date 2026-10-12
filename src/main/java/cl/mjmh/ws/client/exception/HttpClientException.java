package cl.mjmh.ws.client.exception;

public class HttpClientException extends RuntimeException {

    public HttpClientException(){
        super();
    }

    public HttpClientException(String message){
        super(message);
    }

    public HttpClientException(Throwable cause){
        super(cause);
    }

    public HttpClientException(String message, Throwable cause){
        super(message);
    }

}
