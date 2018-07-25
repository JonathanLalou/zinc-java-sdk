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
import java.util.Map;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceComponents implements Serializable {
    @SerializedName(ZincConstants.shipping)
    @JsonProperty(ZincConstants.shipping)
    private Integer shipping;

    @SerializedName(ZincConstants.subtotal)
    @JsonProperty(ZincConstants.subtotal)
    private Integer subtotal;

    @SerializedName(ZincConstants.tax)
    @JsonProperty(ZincConstants.tax)
    private Integer tax;

    @SerializedName(ZincConstants.total)
    @JsonProperty(ZincConstants.total)
    private Integer total;

    @SerializedName(ZincConstants.product_subtotals)
    @JsonProperty(ZincConstants.product_subtotals)
    private Map<String, Integer> productSubtotals;
}