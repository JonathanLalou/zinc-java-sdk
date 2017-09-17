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
public class ZincWebhooks implements Serializable {
    @SerializedName("request_succeeded")
    @JsonProperty("request_succeeded")
    private String requestSucceeded;
    @SerializedName("request_failed")
    @JsonProperty("request_failed")
    private String requestFailed;
    @SerializedName("tracking_obtained")
    @JsonProperty("tracking_obtained")
    private String trackingObtained;
    @SerializedName("status_updated")
    @JsonProperty("status_updated")
    private String statusUpdated;
}
