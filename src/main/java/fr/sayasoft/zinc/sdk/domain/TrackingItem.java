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
    @SerializedName("obtained_at")
    @JsonProperty("obtained_at")
    Date obtainedAt;

    @SerializedName("merchant_order_id")
    @JsonProperty("merchant_order_id")
    private String merchantOrderId;

    @SerializedName("carrier")
    @JsonProperty("carrier")
    private String carrier;

    @SerializedName("tracking_number")
    @JsonProperty("tracking_number")
    private String trackingNumber;
}
