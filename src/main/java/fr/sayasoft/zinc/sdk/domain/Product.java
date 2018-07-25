package fr.sayasoft.zinc.sdk.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Product implements Serializable {
    public static final Integer DEFAULT_QUANTITY = 1;

    @SerializedName(ZincConstants.product_id)
    @JsonProperty(ZincConstants.product_id)
    private String productId;

    @SerializedName(ZincConstants.quantity)
    @JsonProperty(ZincConstants.quantity)
    private Integer quantity;

    /**
     * An address object to which the order will be delivered
     */
    @SerializedName(ZincConstants.seller_selection_criteria)
    @JsonProperty(ZincConstants.seller_selection_criteria)
    private SellerSelectionCriteria sellerSelectionCriteria;

    public Product(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Product(String productId) {
        this(productId, DEFAULT_QUANTITY);
    }
}
