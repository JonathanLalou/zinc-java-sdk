package fr.sayasoft.zinc.sdk.domain

import com.google.common.collect.Lists
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.sayasoft.zinc.sdk.enums.ShippingMethod
import fr.sayasoft.zinc.sdk.enums.SupportedRetailer
import fr.sayasoft.zinc.sdk.enums.ZincErrorCode
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import org.junit.Ignore
import org.junit.Test

class GsonUnitTest {
    @Test
    @Ignore
    void 'serialize OrderRequest'() {
        def orderRequest = OrderRequest.builder()
                .idempotencyKey("myIdempotencyKey")
                .retailer("amazon")
                .products(Lists.newArrayList(new Product("0923568964")))
                .shippingAddress(
                ZincAddress.builder()
                        .firstName("John Hannibal")
                        .lastName("Smith")
                        .addressLine1("1234 Main Street")
                        .addressLine2("above the bar")
                        .zipCode("11907")
                        .city("Brooklyn")
                        .state("NY")
                        .country("US")
                        .phoneNumber("123-123-1234")
                        .build()
        ).shippingMethod(ShippingMethod.cheapest) // TODO
                .billingAddress( // TODO parametrize
                ZincAddress.builder()
                        .firstName("John Hannibal")
                        .lastName("Smith")
                        .addressLine1("1234 Main Street")
                        .addressLine2("above the bar")
                        .zipCode("11907")
                        .city("Brooklyn")
                        .state("NY")
                        .country("US")
                        .phoneNumber("123-123-1234")
                        .build()
        )
                .paymentMethod(
                PaymentMethod.builder()
                        .nameOnCard("SAS SayaSoft")
                        .number("0000000000000000")
                        .securityCode("000")
                        .expirationMonth(12)
                        .expirationYear(2020)
                        .useGift(false)
                        .build()
        )
                .retailerCredentials(new RetailerCredentials("test@test.fr", "password")) // TODO
                .maxPrice(0) // TODO
                .giftMessage("Here is your package") // TODO
                .isGift(true)
                .build()
//        final Gson gson = new Gson()
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String jsonOutput = gson.toJson(someObject);
        def actual = gson.toJson(orderRequest)
        println actual
        assert "{\n" +
                "  \"idempotency_key\": \"myIdempotencyKey\",\n" +
                "  \"retailer\": \"amazon\",\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"product_id\": \"0923568964\",\n" +
                "      \"quantity\": 1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"max_price\": 0,\n" +
                "  \"shipping_address\": {\n" +
                "    \"first_name\": \"John Hannibal\",\n" +
                "    \"last_name\": \"Smith\",\n" +
                "    \"address_line1\": \"1234 Main Street\",\n" +
                "    \"address_line2\": \"above the bar\",\n" +
                "    \"zip_code\": \"11907\",\n" +
                "    \"city\": \"Brooklyn\",\n" +
                "    \"state\": \"NY\",\n" +
                "    \"country\": \"US\",\n" +
                "    \"phone_number\": \"123-123-1234\"\n" +
                "  },\n" +
                "  \"is_gift\": true,\n" +
                "  \"gift_message\": \"Here is your package\",\n" +
                "  \"shipping_method\": \"cheapest\",\n" +
                "  \"billing_address\": {\n" +
                "    \"first_name\": \"John Hannibal\",\n" +
                "    \"last_name\": \"Smith\",\n" +
                "    \"address_line1\": \"1234 Main Street\",\n" +
                "    \"address_line2\": \"above the bar\",\n" +
                "    \"zip_code\": \"11907\",\n" +
                "    \"city\": \"Brooklyn\",\n" +
                "    \"state\": \"NY\",\n" +
                "    \"country\": \"US\",\n" +
                "    \"phone_number\": \"123-123-1234\"\n" +
                "  },\n" +
                "  \"payment_method\": {\n" +
                "    \"expiration_month\": 12,\n" +
                "    \"expiration_year\": 2020,\n" +
                "    \"use_gift\": false\n" +
                "  },\n" +
                "  \"retailer_credentials\": {\n" +
                "    \"email\": \"test@test.fr\",\n" +
                "    \"password\": \"password\"\n" +
                "  }\n" +
                "}" == actual
    }

    @Test
    void 'deserialize Json'() {
        def json = "{\n" +
                "  \"idempotencyKey\": \"myIdempotencyKey\",\n" +
                "  \"client_token\": \"public\",\n" +
                "  \"retailer\": \"amazon\",\n" +
                "  \"products\": [{\"product_id\": \"0923568964\", \"quantity\": 1}],\n" +
                "  \"max_price\": 2300,\n" +
                "  \"shipping_address\": {\n" +
                "    \"first_name\": \"Tim\",\n" +
                "    \"last_name\": \"Beaver\",\n" +
                "    \"address_line1\": \"77 Massachusetts Avenue\",\n" +
                "    \"address_line2\": \"\",\n" +
                "    \"zip_code\": \"02139\",\n" +
                "    \"city\": \"Cambridge\", \n" +
                "    \"state\": \"MA\",\n" +
                "    \"country\": \"US\",\n" +
                "    \"phone_number\": \"5551230101\"\n" +
                "  },\n" +
                "  \"is_gift\": true,\n" +
                "  \"gift_message\": \"Here's your package, Tim! Enjoy!\",\n" +
                "  \"shipping_method\": \"cheapest\",\n" +
                "  \"payment_method\": {\n" +
                "    \"name_on_card\": \"Ben Bitdiddle\",\n" +
                "    \"number\": \"5555555555554444\",\n" +
                "    \"security_code\": \"123\",\n" +
                "    \"expiration_month\": 1,\n" +
                "    \"expiration_year\": 2015,\n" +
                "    \"use_gift\": false\n" +
                "  },\n" +
                "  \"billing_address\": {\n" +
                "    \"first_name\": \"William\", \n" +
                "    \"last_name\": \"Rogers\",\n" +
                "    \"address_line1\": \"84 Massachusetts Ave\",\n" +
                "    \"address_line2\": \"\",\n" +
                "    \"zip_code\": \"02139\",\n" +
                "    \"city\": \"Cambridge\", \n" +
                "    \"state\": \"MA\",\n" +
                "    \"country\": \"US\",\n" +
                "    \"phone_number\": \"5551234567\"\n" +
                "  },\n" +
                "  \"retailer_credentials\": {\n" +
                "    \"email\": \"timbeaver@gmail.com\",\n" +
                "    \"password\": \"myAmazonPassword\"\n" +
                "  },\n" +
                "  \"webhooks\": {\n" +
                "    \"order_placed\": \"http://mywebsite.com/zinc/order_placed\",\n" +
                "    \"order_failed\": \"http://mywebsite.com/zinc/order_failed\",\n" +
                "    \"tracking_obtained\": \"http://mywebsite.com/zinc/tracking_obtained\"\n" +
                "  },\n" +
                "  \"client_notes\": {\n" +
                "    \"our_internal_order_id\": \"abc123\"\n" +
                "  }\n" +
                "}"
        def Gson gson = new Gson()
        def OrderRequest orderRequest = gson.fromJson(json, OrderRequest.class)
        println ToStringBuilder.reflectionToString(orderRequest, ToStringStyle.MULTI_LINE_STYLE)
        assert null != orderRequest
        assert SupportedRetailer.amazon != orderRequest.retailer
        assert 2300 == orderRequest.maxPrice
        assert 3 == orderRequest.webhooks.size()
        assert "0923568964" == orderRequest.products[0].productId
    }

    @Test
    void "zincError marshalling and unmarshalling"() {
        ZincError zincError = new ZincError(
                type: ZincConstants.error,
                orderRequest: new OrderRequest(
                        idempotencyKey: "Torcularis Septentrionalis Omicron Piscium",
                        retailer: "amazon",
                        products: [new Product("Sheddi-Delta Capricorni", 159)],
                        shipping: new Shipping(orderBy: ShippingMethod.fasted, maxDays: 21, maxPrice: 6543)
                ),
                code: ZincErrorCode.invalid_payment_method,
                message: "Sualocin-Alpha Delphini",
                data: "{Rasalas: Mu Leonis}",
                createdAt: Date.parse(ZincConstants.jsonDateFormat, "2017-09-17T16:05:48.250Z"),
                statusUpdates: [new ZincStatusUpdate(type: "statusUpdated", data: [hello: "world"], createdAt: Date.parse(ZincConstants.jsonDateFormat, "2017-09-17T16:08:54.250Z"))]
        )
        Gson gson = new GsonBuilder().setDateFormat(ZincConstants.jsonDateFormat).create();
        def expectedJson = "{" +
                "\"_type\":\"error\"," +
                "\"request\":{\"idempotency_key\":\"Torcularis Septentrionalis Omicron Piscium\",\"retailer\":\"amazon\",\"products\":[{\"product_id\":\"Sheddi-Delta Capricorni\",\"quantity\":159}],\"max_price\":0,\"shipping\":{\"order_by\":\"fasted\",\"maxDays\":21,\"maxPrice\":6543},\"webhooks\":{}}," +
                "\"code\":\"invalid_payment_method\"," +
                "\"message\":\"Sualocin-Alpha Delphini\"," +
                "\"data\":\"{Rasalas: Mu Leonis}\",\"_created_at\":\"2017-09-17T16:05:48.250Z\"," +
                "\"status_updates\":[{\"type\":\"statusUpdated\",\"data\":{\"hello\":\"world\"},\"_created_at\":\"2017-09-17T16:08:54.250Z\"}]" +
                "}"
        assert expectedJson == gson.toJson(zincError)

        // test unmarshalling
        def unmarshalled = gson.fromJson(expectedJson, ZincError.class)
        assert null != unmarshalled
        assert ToStringBuilder.reflectionToString(unmarshalled, ToStringStyle.JSON_STYLE) == ToStringBuilder.reflectionToString(zincError, ToStringStyle.JSON_STYLE)
    }
}
