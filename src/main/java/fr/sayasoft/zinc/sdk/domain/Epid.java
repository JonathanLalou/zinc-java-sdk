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
/** "Epid" stands for "External Product IDentifier" */
public class Epid implements Serializable {

    @SerializedName(ZincConstants.type)
    @JsonProperty(ZincConstants.type)
    private String type; // TODO should be an enum

    @SerializedName(ZincConstants.value)
    @JsonProperty(ZincConstants.value)
    private String value;
}
