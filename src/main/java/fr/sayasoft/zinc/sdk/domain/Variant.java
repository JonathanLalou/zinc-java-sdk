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
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Variant implements Serializable {
    @SerializedName(ZincConstants.variant_specifics)
    @JsonProperty(ZincConstants.variant_specifics)
    private List<VariantSpecific> variantSpecifics;

    @SerializedName(ZincConstants.product_id)
    @JsonProperty(ZincConstants.product_id)
    private String productId;
}
