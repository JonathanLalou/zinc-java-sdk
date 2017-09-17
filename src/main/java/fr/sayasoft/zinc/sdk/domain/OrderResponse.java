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
public class OrderResponse implements Serializable {
    /*
    {
  "_type" : "order_response",
  "price_components" : {
    "shipping" : 0,
    "subtotal" : 1999,
    "tax" : 0,
    "total" : 1999
  },
  "merchant_order_ids" : [
    {
      "merchant_order_id" : "112-1234567-7272727",
      "merchant" : "amazon",
      "account" : "timbeaver@gmail.com",
      "placed_at" : ISODate("2014-07-02T23:51:08.366Z")
    }
  ],
  "tracking" : [
    {
      "merchant_order_id" : "112-1234567-7272727",
      "carrier" : "Fedex",
      "tracking_number" : "9261290100129790891234",
      "obtained_at" : ISODate("2014-07-03T23:22:48.165Z")
    }
  ]
}
    */
//    @SerializedName(ZincConstants.type)
//    private String type;
    @SerializedName("price_components")
    @JsonProperty("price_components")
    private PriceComponents priceComponents;

    @SerializedName("merchant_order_ids")
    @JsonProperty("merchant_order_ids")
    private List<MerchantOrder> merchantOrders;

    @SerializedName("tracking")
    @JsonProperty("tracking")
    private List<TrackingItem> tracking;

    @SerializedName(ZincConstants.request)
    @JsonProperty(ZincConstants.request)
    private OrderRequest orderRequest;

    @SerializedName(ZincConstants.statusUpdates)
    @JsonProperty(ZincConstants.statusUpdates)
    private List<ZincStatusUpdate> statusUpdates;

}