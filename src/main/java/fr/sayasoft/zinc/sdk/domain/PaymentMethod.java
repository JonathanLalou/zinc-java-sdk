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
// TODO add validation constraints
public class PaymentMethod implements Serializable {
    @SerializedName(ZincConstants.name_on_card)
    @JsonProperty(ZincConstants.name_on_card)
    private String nameOnCard;

    @SerializedName(ZincConstants.card_number)
    @JsonProperty(ZincConstants.card_number)
    private String number;

    @SerializedName(ZincConstants.security_code)
    @JsonProperty(ZincConstants.security_code)
    private String securityCode;

    @SerializedName(ZincConstants.expiration_month)
    @JsonProperty(ZincConstants.expiration_month)
    private Number expirationMonth;

    @SerializedName(ZincConstants.expiration_year)
    @JsonProperty(ZincConstants.expiration_year)
    private Number expirationYear;

    @SerializedName(ZincConstants.use_gift)
    @JsonProperty(ZincConstants.use_gift)
    private Boolean useGift = false;
}
