package fr.sayasoft.zinc.sdk.exception;

import lombok.Data;

@Data
public class CannotGetProductOfferException extends Exception {
    public CannotGetProductOfferException() {
    }

    public CannotGetProductOfferException(String e) {
        super(e);
    }

    public CannotGetProductOfferException(Throwable cause) {
        super(cause);
    }
}
