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
public class RetailerCredentials implements Serializable {
    @SerializedName(ZincConstants.email)
    @JsonProperty(ZincConstants.email)
    private String email;

    @SerializedName(ZincConstants.password)
    @JsonProperty(ZincConstants.password)
    private String password;

    /** (Optional) The verification code required by the retailer for logging in. Only required in cases where the retailer prevents a login with an account_locked_verification_required error code.
     * cf. http://docs.zincapi.com/#amazon-email-verification*/
    @SerializedName(ZincConstants.verificationCode)
    @JsonProperty(ZincConstants.verificationCode)
    private String verificationCode;
}
