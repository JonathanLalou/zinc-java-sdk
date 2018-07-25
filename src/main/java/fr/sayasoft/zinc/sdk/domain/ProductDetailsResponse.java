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
import java.util.Map;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsResponse implements Serializable {

    @SerializedName(ZincConstants.status)
    @JsonProperty(ZincConstants.status)
    private String status;

    @SerializedName(ZincConstants.retailer)
    @JsonProperty(ZincConstants.retailer)
    private String retailer;

    @SerializedName(ZincConstants.product_id)
    @JsonProperty(ZincConstants.product_id)
    private String productId;

    @SerializedName(ZincConstants.timestamp)
    @JsonProperty(ZincConstants.timestamp)
    private String timestamp;

    @SerializedName(ZincConstants.title)
    @JsonProperty(ZincConstants.title)
    private String title;

    @SerializedName(ZincConstants.product_details)
    @JsonProperty(ZincConstants.product_details)
    private List<String> productDetails;

    @SerializedName(ZincConstants.feature_bullets)
    @JsonProperty(ZincConstants.feature_bullets)
    private List<String> featureBullets;

    @SerializedName(ZincConstants.brand)
    @JsonProperty(ZincConstants.brand)
    private String brand;

    @SerializedName(ZincConstants.main_image)
    @JsonProperty(ZincConstants.main_image)
    private String mainImage;

    @SerializedName(ZincConstants.images)
    @JsonProperty(ZincConstants.images)
    private List<String> images;

    @SerializedName(ZincConstants.variant_specifics)
    @JsonProperty(ZincConstants.variant_specifics)
    private List<VariantSpecific> variantSpecifics;

    @SerializedName(ZincConstants.all_variants)
    @JsonProperty(ZincConstants.all_variants)
    private List<Variant> allVariants;

    @SerializedName(ZincConstants.categories)
    @JsonProperty(ZincConstants.categories)
    private List<String> categories;

    @SerializedName(ZincConstants.authors)
    @JsonProperty(ZincConstants.authors)
    private List<String> authors;

    @SerializedName(ZincConstants.product_description)
    @JsonProperty(ZincConstants.product_description)
    private String productDescription;

    @SerializedName(ZincConstants.epids)
    @JsonProperty(ZincConstants.epids)
    private List<Epid> epids;

    @SerializedName(ZincConstants.epids_map)
    @JsonProperty(ZincConstants.epids_map)
    private Map<String, String> epidsMap;

    @SerializedName(ZincConstants.package_dimensions)
    @JsonProperty(ZincConstants.package_dimensions)
    private Map<String, Object> packageDimensions; // TODO improve

    @SerializedName(ZincConstants.item_location)
    @JsonProperty(ZincConstants.item_location)
    private String itemLocation;

    @SerializedName(ZincConstants.original_retail_price)
    @JsonProperty(ZincConstants.original_retail_price)
    private Number originalRetailPrice;

    @SerializedName(ZincConstants.price)
    @JsonProperty(ZincConstants.price)
    private Number price;

    @SerializedName(ZincConstants.review_count)
    @JsonProperty(ZincConstants.review_count)
    private Number reviewCount;

    @SerializedName(ZincConstants.stars)
    @JsonProperty(ZincConstants.stars)
    private Double stars;

    @SerializedName(ZincConstants.question_count)
    @JsonProperty(ZincConstants.question_count)
    private Number questionCount;

    @SerializedName(ZincConstants.asin)
    @JsonProperty(ZincConstants.asin)
    private String asin;

    @SerializedName(ZincConstants.item_number)
    @JsonProperty(ZincConstants.item_number)
    private String itemNumber;
}
