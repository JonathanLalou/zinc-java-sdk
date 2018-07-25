package fr.sayasoft.zinc.sdk.exception;

import lombok.Data;

@Data
public class CannotGetProductDetailsException extends Exception {
    public CannotGetProductDetailsException() {
    }

    public CannotGetProductDetailsException(String e) {
        super(e);
    }

    public CannotGetProductDetailsException(Throwable cause) {
        super(cause);
    }
}
