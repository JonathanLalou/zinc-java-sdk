package fr.sayasoft.zinc.sdk.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import fr.sayasoft.zinc.sdk.enums.ShippingMethod;
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
public class Shipping implements Serializable {
    @SerializedName("order_by")
    @JsonProperty("order_by")
    ShippingMethod orderBy;

    @SerializedName("maxDays")
    @JsonProperty("maxDays")
    private Integer maxDays;

    @SerializedName("maxPrice")
    @JsonProperty("maxPrice")
    private Integer maxPrice;
}
