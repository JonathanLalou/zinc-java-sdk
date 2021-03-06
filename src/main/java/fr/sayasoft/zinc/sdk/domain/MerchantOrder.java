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


@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantOrder implements Serializable {
    @SerializedName(ZincConstants.merchantOrderId)
    @JsonProperty(ZincConstants.merchantOrderId)
    private String merchantOrderId;

    @SerializedName("merchant")
    @JsonProperty("merchant")
    private String merchant;

    @SerializedName("account")
    @JsonProperty("account")
    private String account;

    @SerializedName(ZincConstants.placedAt)
    @JsonProperty(ZincConstants.placedAt)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ZincConstants.jsonDateFormat)
    private Date placedAt;
}
