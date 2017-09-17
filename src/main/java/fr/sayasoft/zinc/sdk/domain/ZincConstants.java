package fr.sayasoft.zinc.sdk.domain;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public interface ZincConstants {
    String type = "_type";
    String createdAt = "_created_at";
    String data = "data";
    String message = "message";
    String request = "request";
    String requestId = "request_id";
    String statusUpdates = "status_updates";
    String placedAt = "placed_at";
    String merchantOrderId = "merchant_order_id";
    String name_on_card = "name_on_card";
    String card_number = "number";
    String security_code = "security_code";
    String expiration_month = "expiration_month";
    String expiration_year = "expiration_year";
    String use_gift = "use_gift";
    String first_name = "first_name";
    String last_name = "last_name";
    String address_line1 = "address_line1";
    String address_line2 = "address_line2";
    String zip_code = "zip_code";
    String city = "city";
    String state = "state";
    String country = "country";
    String phone_number = "phone_number";
    String email = "email";
    String password = "password";
    String error = "error";
    String code = "code";

    String jsonDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    // @formatter:off
    Type TYPE_MAP_STRING_STRING = new TypeToken<Map<String, String>>() {}.getType();
    Type TYPE_MAP_STRING_OBJECT = new TypeToken<Map<String, Object>>() {}.getType();
    // @formatter:on


    String idempotency_key = "idempotency_key";
    String retailer = "retailer";
    String products = "products";
    String max_price = "max_price";
    String shipping_address = "shipping_address";
    String is_gift = "is_gift";
    String gift_message = "gift_message";
    String shipping_method = "shipping_method";
    String shipping = "shipping";
    String billing_address = "billing_address";
    String payment_method = "payment_method";
    String retailer_credentials = "retailer_credentials";
    String webhooks = "webhooks";
    String client_notes = "client_notes";
    String promo_codes = "promo_codes";
    String ignore_invalid_promo_code = "ignore_invalid_promo_code";
    String po_number = "po_number";
    String bundled = "bundled";
}
