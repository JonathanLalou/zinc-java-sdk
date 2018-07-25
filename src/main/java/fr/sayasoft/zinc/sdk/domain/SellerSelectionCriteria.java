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
/** cf http://docs.zincapi.com/#seller-selection-criteria-object */
public class SellerSelectionCriteria implements Serializable {
    @SerializedName(ZincConstants.addon)
    @JsonProperty(ZincConstants.addon)
    Boolean addon;

    @SerializedName(ZincConstants.buy_box)
    @JsonProperty(ZincConstants.buy_box)
    Boolean buyBox;

    @SerializedName(ZincConstants.condition_in)
    @JsonProperty(ZincConstants.condition_in)
    List<String> conditionIn;

    @SerializedName(ZincConstants.condition_not_in)
    @JsonProperty(ZincConstants.condition_not_in)
    List<String> conditionNotIn;

    @SerializedName(ZincConstants.first_party_seller)
    @JsonProperty(ZincConstants.first_party_seller)
    Boolean firstPartySeller;

    @SerializedName(ZincConstants.handling_days_max)
    @JsonProperty(ZincConstants.handling_days_max)
    Number handlingDaysMax;

    @SerializedName(ZincConstants.international)
    @JsonProperty(ZincConstants.international)
    Boolean international;

    @SerializedName(ZincConstants.max_item_price)
    @JsonProperty(ZincConstants.max_item_price)
    Number maxItemPrice;

    @SerializedName(ZincConstants.merchant_id_in)
    @JsonProperty(ZincConstants.merchant_id_in)
    List<String> merchantIdIn;

    @SerializedName(ZincConstants.merchant_id_not_in)
    @JsonProperty(ZincConstants.merchant_id_not_in)
    List<String> merchantIdNotIn;

    @SerializedName(ZincConstants.min_seller_num_ratings)
    @JsonProperty(ZincConstants.min_seller_num_ratings)
    Number minSellerNumRatings;

    @SerializedName(ZincConstants.min_seller_percent_positive_feedback)
    @JsonProperty(ZincConstants.min_seller_percent_positive_feedback)
    Number minSellerPercentPositiveFeedback;

    @SerializedName(ZincConstants.prime)
    @JsonProperty(ZincConstants.prime)
    Boolean prime;
}
