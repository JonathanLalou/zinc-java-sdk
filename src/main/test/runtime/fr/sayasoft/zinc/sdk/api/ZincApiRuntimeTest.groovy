package fr.sayasoft.zinc.sdk.api

import com.google.common.collect.Lists
import fr.sayasoft.zinc.sdk.domain.OrderRequest
import fr.sayasoft.zinc.sdk.domain.OrderResponse
import fr.sayasoft.zinc.sdk.domain.PaymentMethod
import fr.sayasoft.zinc.sdk.domain.Product
import fr.sayasoft.zinc.sdk.domain.ProductDetailsResponse
import fr.sayasoft.zinc.sdk.domain.ProductOfferResponse
import fr.sayasoft.zinc.sdk.domain.RetailerCredentials
import fr.sayasoft.zinc.sdk.domain.ZincAddress
import fr.sayasoft.zinc.sdk.domain.ZincError
import fr.sayasoft.zinc.sdk.enums.ShippingMethod
import fr.sayasoft.zinc.sdk.enums.SupportedRetailer
import fr.sayasoft.zinc.sdk.enums.ZincErrorCode
import fr.sayasoft.zinc.sdk.exception.CannotGetOrderException
import fr.sayasoft.zinc.sdk.exception.CannotPostOrderRequestException
import fr.sayasoft.zinc.sdk.exception.OrderResponseException
import org.apache.commons.collections4.MapUtils
import org.apache.commons.collections4.keyvalue.DefaultMapEntry
import org.junit.Before
import org.junit.Test

import static fr.sayasoft.zinc.sdk.api.ZincHelper.encodeToken
import static fr.sayasoft.zinc.sdk.enums.ZincErrorCode.expired_product_id
import static fr.sayasoft.zinc.sdk.enums.ZincWebhookType.request_failed
import static fr.sayasoft.zinc.sdk.enums.ZincWebhookType.request_succeeded
import static fr.sayasoft.zinc.sdk.enums.ZincWebhookType.status_updated
import static org.junit.Assert.fail

class ZincApiRuntimeTest {

    @SuppressWarnings("UnnecessaryQualifiedReference")
    ZincApi zincApi = new ZincApi(
//            "https://api.zinc.io/v1/",
//            org.apache.commons.codec.binary.Base64.encodeBase64String("ABCDEFG".getBytes())
    )

    OrderRequest orderRequest
    String myIdempotencyKey = "20170831-2329"

    @Before
    public void setUp() throws Exception {
        orderRequest = OrderRequest.builder()
                .idempotencyKey(myIdempotencyKey)
                .retailer(SupportedRetailer.amazon.name())
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
                // fake card id was generated on http://credit-card-generator.2-ee.com/
                        .nameOnCard("Hello World")
                        .number("4916831307592488")
                        .securityCode("258")
                        .expirationMonth(06)
                        .expirationYear(2019)
                        .useGift(false)
                        .build()
        )
                // TODO
                .retailerCredentials(new RetailerCredentials("EMAIL@AMAZON.COM", "PASSWORD_AT_AMAZON"))
                .maxPrice(12345) // TODO
                .giftMessage("Here is your package") // TODO
                .isGift(true)
                .webhooks(MapUtils.putAll(new HashMap(), [new DefaultMapEntry(request_succeeded, "http://localhost:8080/hook/zinc?eventType=request_succeeded&uuid=abcdefghij"),
                                                          new DefaultMapEntry(request_failed, "http://localhost:8080/hook/zinc?eventType=request_failed&uuid=abcdefghij"),
                                                          new DefaultMapEntry(status_updated, "http://localhost:8080/hook/zinc?eventType=status_updated&uuid=abcdefghij")]))
                .build()
    }

    @Test
    void 'getOrder with an invalid requestId'() {
        try {
            zincApi.getOrder("0000000000000000")
            fail()
        } catch (OrderResponseException e) {
            assert ZincErrorCode.invalid_request_id == e.zincError.code
        } catch (CannotGetOrderException e) {
            fail()
        }

    }

    @Test
    void 'getOrder with max_price_exceeded'() {
        try {
            zincApi.getOrder("7387cfa103883c0eb517af09602bfa74")
            fail()
        } catch (OrderResponseException e) {
            assert ZincErrorCode.max_price_exceeded == e.zincError.code
        } catch (CannotGetOrderException e) {
            fail(e.message)
        }
    }

    @Test
    void 'getOrder with success'() {
        assert zincApi.getOrder("8f32fb635f9a9b01a9ff7d0efe953f5a") instanceof OrderResponse
    }

    @Test
    void "postOrder and expect a success status"() {
        def response = zincApi.postOrder(this.orderRequest)
        println response
        assert response.matches("[0-9a-fA-F]{32}")
    }

    @Test
    void 'postOrder and expect an error'() {
        orderRequest.clientNotes = expired_product_id
        ZincError zincError
        try {
            def response
            zincApi.postOrder(this.orderRequest)
            fail()
        } catch (CannotPostOrderRequestException e) {
            zincError = e.zincError
        }
        //noinspection GroovyVariableNotAssigned
        assert expired_product_id == zincError.code
        assert expired_product_id.meaning == zincError.code.meaning
        assert ["fakeField": "$myIdempotencyKey"] == zincError.data
    }

    @Test
    void 'generate base64-encoded token'() {
        def token = "ABCDEFGH"
        println encodeToken(token)
    }

    @Test
    void 'getProductOffer with success'() {
        def actual = zincApi.getProductOffer(SupportedRetailer.amazon, "xxxxxxxxx", null, null, null)
        println actual
        assert actual instanceof ProductOfferResponse
    }

    @Test
    void 'getProductDetails with success'() {
        def actual = zincApi.getProductDetails(SupportedRetailer.amazon, "xxxxxxxxx", null, null, null)
        println actual
        assert actual instanceof ProductDetailsResponse
    }
}
