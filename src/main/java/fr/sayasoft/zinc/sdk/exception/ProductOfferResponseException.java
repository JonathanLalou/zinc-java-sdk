package fr.sayasoft.zinc.sdk.exception;

import fr.sayasoft.zinc.sdk.domain.ZincError;
import lombok.Data;

@Data
public class ProductOfferResponseException extends CannotGetProductOfferException {
    private final ZincError zincError;

    public ProductOfferResponseException(ZincError zincError) {
        super();
        this.zincError = zincError;
    }
}
