
package models;


public class DatoInvalidoException extends RuntimeException{

    public DatoInvalidoException() {
        super();
    }

    public DatoInvalidoException(String message) {
        super(message);
    }

}
