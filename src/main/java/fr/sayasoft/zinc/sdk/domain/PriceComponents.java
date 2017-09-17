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
    @SerializedName("shipping")
    @JsonProperty("shipping")
    private Integer shipping;

    @SerializedName("subtotals")
    @JsonProperty("subtotals")
    private Integer subtotal;

    @SerializedName("tax")
    @JsonProperty("tax")
    private Integer tax;

    @SerializedName("total")
    @JsonProperty("total")
    private Integer total;

    @SerializedName("product_subtotals")
    @JsonProperty("product_subtotals")
    private Map<String, Integer> productSubtotals;
}