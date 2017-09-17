package fr.sayasoft.zinc.sdk.exception;

import fr.sayasoft.zinc.sdk.domain.ZincError;
import lombok.Data;

@Data
public class OrderResponseException extends CannotGetOrderException{
    private final ZincError zincError;

    public OrderResponseException(ZincError zincError) {
        super();
        this.zincError = zincError;
    }
}
