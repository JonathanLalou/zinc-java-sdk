package fr.sayasoft.zinc.sdk.exception;

import fr.sayasoft.zinc.sdk.domain.ZincError;
import lombok.Getter;

@Getter
public class CannotPostOrderRequestException extends Exception {
    private ZincError zincError;

    public CannotPostOrderRequestException(Throwable e) {
        super(e);
    }

    public CannotPostOrderRequestException(String message) {
        super(message);
    }

    public CannotPostOrderRequestException(String message, ZincError _zincError) {
        this(message);
        this.zincError = _zincError;
    }
}
