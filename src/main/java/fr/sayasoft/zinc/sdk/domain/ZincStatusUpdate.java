package fr.sayasoft.zinc.sdk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.Map;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZincStatusUpdate implements Serializable {
    @SerializedName("type")
    @JsonProperty("type")
    private String type;

    @SerializedName(ZincConstants.message)
    @JsonProperty(ZincConstants.message)
    private String message;

    @SerializedName(ZincConstants.data)
    @JsonProperty(ZincConstants.data)
    private Map<String, String> data;

    @SerializedName(ZincConstants.createdAt)
    @JsonProperty(ZincConstants.createdAt)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ZincConstants.jsonDateFormat)
    private Date createdAt;

}
