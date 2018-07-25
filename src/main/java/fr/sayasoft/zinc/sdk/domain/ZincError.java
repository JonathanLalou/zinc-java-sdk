package fr.sayasoft.zinc.sdk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import fr.sayasoft.zinc.sdk.enums.ZincErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Builder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created on 31/07/2017.
 */
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZincError implements Serializable {
    @SerializedName(ZincConstants._type)
    @JsonProperty(ZincConstants._type)
    private String type = ZincConstants.error;

    @SerializedName(ZincConstants.request)
    @JsonProperty(ZincConstants.request)
    private OrderRequest orderRequest;

    @SerializedName(ZincConstants.code)
    @JsonProperty(ZincConstants.code)
    private ZincErrorCode code;

    @SerializedName(ZincConstants.message)
    @JsonProperty(ZincConstants.message)
    private String message;

    @SuppressWarnings({"squid:S1948", "squid:S1165"})
    @SerializedName(ZincConstants.data)
    @JsonProperty(ZincConstants.data)
    private Map<String, ?> data;

    @SerializedName(ZincConstants.host)
    @JsonProperty(ZincConstants.host)
    private String host;

    @SerializedName(ZincConstants.createdAt)
    @JsonProperty(ZincConstants.createdAt)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ZincConstants.jsonDateFormat)
    private Date createdAt;

    @SerializedName(ZincConstants.requestId)
    @JsonProperty(ZincConstants.requestId)
    private String requestId;

    @SerializedName(ZincConstants.statusUpdates)
    @JsonProperty(ZincConstants.statusUpdates)
    private List<ZincStatusUpdate> statusUpdates;

}
