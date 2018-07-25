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
public class ZincOffer implements Serializable {

    @SerializedName(ZincConstants.addon)
    @JsonProperty(ZincConstants.addon)
    private Boolean addon;

    @SerializedName(ZincConstants.condition)
    @JsonProperty(ZincConstants.condition)
    private String condition;

    @SerializedName(ZincConstants.greytext)
    @JsonProperty(ZincConstants.greytext)
    private String greytext;

    @SerializedName(ZincConstants.handling_days_max)
    @JsonProperty(ZincConstants.handling_days_max)
    private Number handlingDaysMax;

    @SerializedName(ZincConstants.handling_days_min)
    @JsonProperty(ZincConstants.handling_days_min)
    private Number handlingDaysMin;

    @SerializedName(ZincConstants.international)
    @JsonProperty(ZincConstants.international)
    private Boolean international;

    @SerializedName(ZincConstants.member_only)
    @JsonProperty(ZincConstants.member_only)
    private Boolean memberOnly;

    @SerializedName(ZincConstants.merchant_id)
    @JsonProperty(ZincConstants.merchant_id)
    private String merchantId;

    @SerializedName(ZincConstants.offerlisting_id)
    @JsonProperty(ZincConstants.offerlisting_id)
    private String offerlistingId;

    @SerializedName(ZincConstants.price)
    @JsonProperty(ZincConstants.price)
    private Number price;

    @SerializedName(ZincConstants.ship_price)
    @JsonProperty(ZincConstants.ship_price)
    private Number shipPrice;

    @SerializedName(ZincConstants.prime)
    @JsonProperty(ZincConstants.prime)
    private Boolean prime;

    @SerializedName(ZincConstants.prime_only)
    @JsonProperty(ZincConstants.prime_only)
    private Boolean primeOnly;

    @SerializedName(ZincConstants.seller_name)
    @JsonProperty(ZincConstants.seller_name)
    private String sellerName;

    @SerializedName(ZincConstants.seller_num_ratings)
    @JsonProperty(ZincConstants.seller_num_ratings)
    private Number sellerNumRatings;

    @SerializedName(ZincConstants.seller_percent_positive)
    @JsonProperty(ZincConstants.seller_percent_positive)
    private Number sellerPercentPositive;
}
