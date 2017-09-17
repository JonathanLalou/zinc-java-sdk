package fr.sayasoft.zinc.sdk.exception;

import lombok.Data;

@Data
public class CannotGetOrderException extends Exception {
    public CannotGetOrderException() {
    }

    public CannotGetOrderException(String e) {
        super(e);
    }

    public CannotGetOrderException(Throwable cause) {
        super(cause);
    }
}
