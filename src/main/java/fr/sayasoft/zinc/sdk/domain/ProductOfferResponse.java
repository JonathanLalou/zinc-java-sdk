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
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfferResponse implements Serializable {

    @SerializedName(ZincConstants.retailer)
    @JsonProperty(ZincConstants.retailer)
    private String retailer;

    @SerializedName(ZincConstants.status)
    @JsonProperty(ZincConstants.status)
    private String status;

    /**
     * An address object to which the order will be delivered
     */
    @SerializedName(ZincConstants.offers)
    @JsonProperty(ZincConstants.offers)
    private List<ZincOffer> offers;
}
