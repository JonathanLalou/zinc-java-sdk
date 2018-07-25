package fr.sayasoft.zinc.sdk.exception;

import com.google.gson.JsonSyntaxException;
import fr.sayasoft.zinc.sdk.domain.ZincError;
import lombok.Getter;

@Getter
public class CannotPostOrderRequestException extends Exception {
    private final ZincError zincError;

    public CannotPostOrderRequestException(Throwable e) {
        this(null, null, e);
    }

    public CannotPostOrderRequestException(String message) {
        this(message, null, null);
    }

    public CannotPostOrderRequestException(String message, ZincError _zincError) {
        this(message, _zincError, null);
    }

    public CannotPostOrderRequestException(String message, JsonSyntaxException e) {
        this(message, null, e);
    }

    private CannotPostOrderRequestException(String _message, ZincError _zincError, Throwable jse) {
        super(_message, jse);
        this.zincError = _zincError;
    }
}
