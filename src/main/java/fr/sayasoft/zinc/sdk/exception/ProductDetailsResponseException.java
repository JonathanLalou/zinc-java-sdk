package fr.sayasoft.zinc.sdk.exception;

import fr.sayasoft.zinc.sdk.domain.ZincError;
import lombok.Data;

@Data
public class ProductDetailsResponseException extends CannotGetProductDetailsException {
    private final ZincError zincError;

    public ProductDetailsResponseException(ZincError zincError) {
        super();
        this.zincError = zincError;
    }
}
