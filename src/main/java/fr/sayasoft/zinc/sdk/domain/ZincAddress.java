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

/**
 * cf http://docs.zincapi.com/?shell#address-object
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZincAddress implements Serializable {
    @SerializedName(ZincConstants.first_name)
    @JsonProperty(ZincConstants.first_name)
    private String firstName;

    @SerializedName(ZincConstants.last_name)
    @JsonProperty(ZincConstants.last_name)
    private String lastName;

    @SerializedName(ZincConstants.address_line1)
    @JsonProperty(ZincConstants.address_line1)
    private String addressLine1;

    @SerializedName(ZincConstants.address_line2)
    @JsonProperty(ZincConstants.address_line2)
    private String addressLine2;

    @SerializedName(ZincConstants.zip_code)
    @JsonProperty(ZincConstants.zip_code)
    private String zipCode;

    @SerializedName(ZincConstants.city)
    @JsonProperty(ZincConstants.city)
    private String city;

    @SerializedName(ZincConstants.state)
    @JsonProperty(ZincConstants.state)
    private String state;

    @SerializedName(ZincConstants.country)
    @JsonProperty(ZincConstants.country)
    private String country;

    @SerializedName(ZincConstants.phone_number)
    @JsonProperty(ZincConstants.phone_number)
    private String phoneNumber;
}
