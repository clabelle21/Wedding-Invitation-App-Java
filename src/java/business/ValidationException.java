/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author chris
 */
public class ValidationException extends Exception {
    public ValidationException() {
        super("Data not in valid format");
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ValidationException(Throwable throwable) {
        super(throwable);
    }
}
