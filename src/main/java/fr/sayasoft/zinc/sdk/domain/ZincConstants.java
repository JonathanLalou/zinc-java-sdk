package fr.sayasoft.zinc.sdk.domain;

import com.google.common.reflect.TypeToken;
import fr.sayasoft.zinc.sdk.api.ZincApi;

import java.lang.reflect.Type;
import java.util.Map;

public interface ZincConstants {

    /**
     * Character 'colon' (ie ':') must be present at the end of the token given before hashing
     *
     * @see ZincApi#base64EncodedClientToken
     */
    String COLON = ":";

    String _type = "_type";
    String type = "type";
    String createdAt = "_created_at";
    String data = "data";
    String host = "host";
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
    @SuppressWarnings("squid:S2068")
    String password = "password";
    String verificationCode = "verification_code";
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
    String seller_selection_criteria = "seller_selection_criteria";
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
    String productId = "product_id";
    String obtainedAt = "obtained_at";
    String trackingNumber = "tracking_number";
    String carrier = "carrier";
    String subtotal = "subtotal";
    String tax = "tax";
    String total = "total";
    String product_subtotals = "product_subtotals";

    String addon = "addon";
    String buy_box = "buy_box";
    String condition_in = "condition_in";
    String condition_not_in = "condition_not_in";
    String first_party_seller = "first_party_seller";
    String handling_days_max = "handling_days_max";
    String international = "international";
    String max_item_price  = "max_item_price";
    String merchant_id_in  = "merchant_id_in";
    String merchant_id_not_in  = "merchant_id_not_in";
    String min_seller_num_ratings  = "min_seller_num_ratings";
    String min_seller_percent_positive_feedback  = "min_seller_percent_positive_feedback";
    String prime = "prime";
    String product_id = "product_id";
    String quantity = "quantity";
    String tag = "tag";
    String affiliate_info = "affiliate_info";
    String status = "status";
    String offers = "offers";

    String condition = "condition";
    String greytext = "greytext";
    String handling_days_min = "handling_days_min";
    String member_only = "member_only";
    String merchant_id = "merchant_id";
    String offerlisting_id = "offerlisting_id";
    String price = "price";
    String ship_price = "ship_price";
    String prime_only = "prime_only";
    String seller_name = "seller_name";
    String seller_num_ratings = "seller_num_ratings";
    String seller_percent_positive = "seller_percent_positive";

    String max_age = "max_age";
    String newer_than = "newer_than";
    String async = "async";

    String timestamp = "timestamp";
    String title = "title";
    String product_details = "product_details";
    String feature_bullets = "feature_bullets";
    String brand = "brand";
    String main_image = "main_image";
    String images = "images";
    String variant_specifics = "variant_specifics";
    String all_variants = "all_variants";
    String categories = "categories";
    String authors = "authors";
    String product_description = "product_description";
    String epids = "epids";
    String epids_map = "epids_map";
    String package_dimensions = "package_dimensions";
    String item_location = "item_location";
    String original_retail_price = "original_retail_price";
    String review_count = "review_count";
    String stars = "stars";
    String question_count = "question_count";
    String asin = "asin";
    String item_number = "item_number";
    String dimension  = "dimension";
    String value = "value";
}
