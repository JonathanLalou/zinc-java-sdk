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
import java.util.Date;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackingItem implements Serializable {
    @SerializedName(ZincConstants.productId)
    @JsonProperty(ZincConstants.productId)
    private String productId;

    @SerializedName(ZincConstants.obtainedAt)
    @JsonProperty(ZincConstants.obtainedAt)
    private Date obtainedAt;

    @SerializedName(ZincConstants.merchantOrderId)
    @JsonProperty(ZincConstants.merchantOrderId)
    private String merchantOrderId;

    @SerializedName(ZincConstants.carrier)
    @JsonProperty(ZincConstants.carrier)
    private String carrier;

    @SerializedName(ZincConstants.trackingNumber)
    @JsonProperty(ZincConstants.trackingNumber)
    private String trackingNumber;
}
