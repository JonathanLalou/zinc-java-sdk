package fr.sayasoft.zinc.sdk.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

import java.io.Serializable;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Product implements Serializable {
    public static final Integer DEFAULT_QUANTITY = 1;

    @SerializedName("product_id")
    @JsonProperty("product_id")
    private String productId;

    private Integer quantity;

    public Product(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Product(String productId) {
        this(productId, DEFAULT_QUANTITY);
    }
}
