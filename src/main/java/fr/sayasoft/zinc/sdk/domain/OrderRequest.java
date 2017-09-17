package fr.sayasoft.zinc.sdk.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;
import fr.sayasoft.zinc.sdk.enums.ShippingMethod;
import fr.sayasoft.zinc.sdk.enums.ZincWebhookType;
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
/** cf http://docs.zincapi.com/#create-an-order */
public class OrderRequest implements Serializable {
    @SerializedName(ZincConstants.idempotency_key)
    @JsonProperty(ZincConstants.idempotency_key)
    private String idempotencyKey;

    @SerializedName(ZincConstants.retailer)
    @JsonProperty(ZincConstants.retailer)
    /** The retailer code of the supported retailer*/
    private String retailer;

    @SerializedName(ZincConstants.products)
    @JsonProperty(ZincConstants.products)
    /** A list of product objects that should be ordered */
    private List<Product> products;

    // optional
    /**
     * The maximum price in cents for the order. If the final price exceeds this number, the order will not go through and will return a max_price_exceeded error.
     */
    @SerializedName(ZincConstants.max_price)
    @JsonProperty(ZincConstants.max_price)
    private Integer maxPrice = 0;

    /**
     * An address object to which the order will be delivered
     */
    @SerializedName(ZincConstants.shipping_address)
    @JsonProperty(ZincConstants.shipping_address)
    private ZincAddress shippingAddress;

    // optional
    /**
     * Whether or not this order should be placed as a gift. Typically, retailers will exclude the price of the items on the receipt if this is set.
     */
    @SerializedName(ZincConstants.is_gift)
    @JsonProperty(ZincConstants.is_gift)
    private Boolean isGift;

    // optional
    /**
     * A message to include on the packing slip for the recipient. Must be no more than 240 characters, or 9 lines.
     */
    @SerializedName(ZincConstants.gift_message)
    @JsonProperty(ZincConstants.gift_message)
    private String giftMessage;

    /**
     * The desired shipping method for the object. Available methods are cheapest (always select the cheapest method available), fastest (always select the fastest method available), or free (which will fail for items without some sort of free shipping). You must provide either this or the shipping attribute, but not both.
     */
    @SerializedName(ZincConstants.shipping_method)
    @JsonProperty(ZincConstants.shipping_method)
    private ShippingMethod shippingMethod;

    /**
     * A shipping object with information as to which shipping method to use. You must provide either this or the shipping_method attribute, but not both.
     */
    @SerializedName(ZincConstants.shipping)
    @JsonProperty(ZincConstants.shipping)
    private Shipping shipping;

    /**
     * An address object for the person associated with the credit card
     */
    @SerializedName(ZincConstants.billing_address)
    @JsonProperty(ZincConstants.billing_address)
    private ZincAddress billingAddress;

    /**
     * A payment method object containing payment information for the order
     */
    @SerializedName(ZincConstants.payment_method)
    @JsonProperty(ZincConstants.payment_method)
    private PaymentMethod paymentMethod;

    /**
     * A retailer credentials object for logging into the retailer with a preexisting account
     */
    @SerializedName(ZincConstants.retailer_credentials)
    @JsonProperty(ZincConstants.retailer_credentials)
    private RetailerCredentials retailerCredentials;

    // optional
    /**
     * A webhooks object including URLs that will receive POST requests after particular events have finished
     */
    @SerializedName(ZincConstants.webhooks)
    @JsonProperty(ZincConstants.webhooks)
    private Map<ZincWebhookType, String> webhooks = Maps.newHashMap();

    // optional
    /**
     * Any metadata to store on the request for future use. This object will be passed back in the response.
     */
    @SerializedName(ZincConstants.client_notes)
    @JsonProperty(ZincConstants.client_notes)
    private Object clientNotes;

    // optional
    /**
     * A list of promotion codes to use at checkout.
     */
    @SerializedName(ZincConstants.promo_codes)
    @JsonProperty(ZincConstants.promo_codes)
    private List<String> promoCodes;

    // optional
    /**
     * Continue with checkout even if promotion codes are invalid. Default is false.
     */
    @SerializedName(ZincConstants.ignore_invalid_promo_code)
    @JsonProperty(ZincConstants.ignore_invalid_promo_code)
    private Boolean ignoreInvalidPromoCode;

    // optional
    /**
     * (Amazon business accounts only). Adds a purchase order number to the order.
     */
    @SerializedName(ZincConstants.po_number)
    @JsonProperty(ZincConstants.po_number)
    private Integer poNumber;

    // optional
    /**
     * (Amazon only). If enabled, orders will be grouped together into batches and placed together. See the order bundling section for more details.
     */
    @SerializedName(ZincConstants.bundled)
    @JsonProperty(ZincConstants.bundled)
    private Boolean bundled;

}
