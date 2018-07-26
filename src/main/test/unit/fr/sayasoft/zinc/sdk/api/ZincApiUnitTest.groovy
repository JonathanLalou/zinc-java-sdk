package fr.sayasoft.zinc.sdk.api

import fr.sayasoft.zinc.sdk.domain.OrderRequest
import fr.sayasoft.zinc.sdk.domain.OrderResponse
import fr.sayasoft.zinc.sdk.domain.Product
import fr.sayasoft.zinc.sdk.domain.Shipping
import fr.sayasoft.zinc.sdk.domain.ZincConstants
import fr.sayasoft.zinc.sdk.domain.ZincError
import fr.sayasoft.zinc.sdk.domain.ZincStatusUpdate
import fr.sayasoft.zinc.sdk.enums.ShippingMethod
import fr.sayasoft.zinc.sdk.enums.SupportedRetailer
import fr.sayasoft.zinc.sdk.enums.ZincErrorCode
import fr.sayasoft.zinc.sdk.exception.CannotGetOrderException
import fr.sayasoft.zinc.sdk.exception.CannotPostOrderRequestException
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import org.easymock.EasyMockRunner
import org.easymock.Mock
import org.easymock.MockType
import org.easymock.TestSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

import java.nio.file.Files
import java.nio.file.Paths

import static org.easymock.EasyMock.expect
import static org.easymock.EasyMock.replay
import static org.easymock.EasyMock.verify
import static org.springframework.http.HttpStatus.FORBIDDEN
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNAUTHORIZED

@RunWith(EasyMockRunner.class)
class ZincApiUnitTest {
    @Mock(type = MockType.STRICT)
    RestTemplate restTemplate
    @Mock(type = MockType.STRICT)
    ZincHelper zincHelper

    String baseUrl = "http://Rukbat-Alpha-Sagittarii/"
    String baseEncodedClientToken = "Schedar-Alpha-Cassiopeiae"
    HttpHeaders headers
    HttpEntity<String> entity

    @TestSubject
    ZincApi zincApi = new ZincApi(baseUrl: baseUrl, base64EncodedClientToken: baseEncodedClientToken)

    @Before
    void setUp() {
        zincApi.zincHelper = zincHelper

        headers = new HttpHeaders()
        headers.set("Authorization", "Basic $baseEncodedClientToken")
        headers.setContentType(MediaType.APPLICATION_JSON)
        this.entity = new HttpEntity<>(headers)
    }

    private replayAll() {
        replay(restTemplate, zincHelper)
    }

    private verifyAll() {
        verify(restTemplate, zincHelper)
    }

    @Test(expected = CannotGetOrderException.class)
    void "getOrder with HttpClientErrorException"() {
        def requestId = "Sinistra-Nu-Ophiuchi"
        def url = "http://Rukbat-Alpha-Sagittarii/orders/$requestId"

        expect(restTemplate.exchange(url, HttpMethod.GET, entity, String.class)).andThrow(new HttpClientErrorException(UNAUTHORIZED))
        replayAll()
        zincApi.getOrder(requestId)
        verifyAll()
    }

    @Test(expected = CannotGetOrderException.class)
    void "getOrder with returned status not OK"() {
        def requestId = "Sinistra-Nu-Ophiuchi"
        def url = "http://Rukbat-Alpha-Sagittarii/orders/$requestId"

        expect(restTemplate.exchange(url, HttpMethod.GET, entity, String.class)).andReturn(new ResponseEntity<String>(FORBIDDEN))
        replayAll()
        zincApi.getOrder(requestId)
        verifyAll()
    }

    @Test
    void "getOrder with returned status OK"() {
        def requestId = "Sinistra-Nu-Ophiuchi"
        def url = "http://Rukbat-Alpha-Sagittarii/orders/$requestId"
        def body = "Tejat Prior Eta Geminorum"
        def orderResponse = new OrderResponse()

        expect(restTemplate.exchange(url, HttpMethod.GET, entity, String.class)).andReturn(new ResponseEntity<String>(body, OK))
        expect(zincHelper.parseOrderResponse(body)).andReturn(orderResponse)

        replayAll()
        assert orderResponse == zincApi.getOrder(requestId)
        verifyAll()
    }

    @Test(expected = CannotPostOrderRequestException.class)
    void "postOrder with RestClientException"() {
        OrderRequest orderRequest = new OrderRequest(idempotencyKey: "Zubra-Delta Leonis", shippingMethod: ShippingMethod.cheapest)
        def url = "http://Rukbat-Alpha-Sagittarii/orders"
        def data = "{\"idempotency_key\":\"Zubra-Delta Leonis\",\"max_price\":0,\"shipping_method\":\"cheapest\",\"webhooks\":{}}"

        entity = new HttpEntity<>(data, headers)

        expect(restTemplate.exchange(url, HttpMethod.POST, entity, String.class)).andThrow(new HttpClientErrorException(FORBIDDEN))

        replayAll()
        zincApi.postOrder(orderRequest)
        verifyAll()
    }

    @Test
    void "postOrder with ZincError"() {
        OrderRequest orderRequest = new OrderRequest(
                idempotencyKey: "Torcularis Septentrionalis Omicron Piscium",
                retailer: "amazon",
                products: [new Product("Sheddi-Delta Capricorni", 159)],
                shipping: new Shipping(orderBy: ShippingMethod.fasted, maxDays: 21, maxPrice: 6543)
        )
        def url = "http://Rukbat-Alpha-Sagittarii/orders"
        def data = "{\"idempotency_key\":\"Torcularis Septentrionalis Omicron Piscium\",\"retailer\":\"amazon\",\"products\":[{\"product_id\":\"Sheddi-Delta Capricorni\",\"quantity\":159}],\"max_price\":0,\"shipping\":{\"order_by\":\"fasted\",\"maxDays\":21,\"maxPrice\":6543},\"webhooks\":{}}"
        ZincError zincError = new ZincError(
                type: ZincConstants.error,
                orderRequest: orderRequest,
                code: ZincErrorCode.invalid_payment_method,
                message: "Sualocin-Alpha Delphini",
                data: ["Rasalas": "Mu Leonis"],
                createdAt: Date.parse(ZincConstants.jsonDateFormat, "2017-09-17T16:05:48.250Z"),
                statusUpdates: [new ZincStatusUpdate(type: "status_updated", data: [hello: "world"], createdAt: Date.parse(ZincConstants.jsonDateFormat, "2017-09-17T16:08:54.250Z"))]
        )
        def expectedJson = "{" +
                "\"_type\":\"error\"," +
                "\"request\":$data," +
                "\"code\":\"invalid_payment_method\"," +
                "\"message\":\"Sualocin-Alpha Delphini\"," +
                "\"data\":{\"Rasalas\": \"Mu Leonis\"}," +
                "\"_created_at\":\"2017-09-17T16:05:48.250Z\"," +
                "\"status_updates\":[{\"type\":\"status_updated\",\"data\":{\"hello\":\"world\"},\"_created_at\":\"2017-09-17T16:08:54.250Z\"}]" +
                "}"

        entity = new HttpEntity<>(data, headers)
        def responseEntity = new ResponseEntity<String>(expectedJson, OK)
        expect(restTemplate.exchange(url, HttpMethod.POST, entity, String.class)).andReturn(responseEntity)

        replayAll()
        try {
            zincApi.postOrder(orderRequest)
        } catch (CannotPostOrderRequestException e) {
            assert e.message == "Did not receive requestId but received ZincError"
            assert ToStringBuilder.reflectionToString(zincError, ToStringStyle.JSON_STYLE) == ToStringBuilder.reflectionToString(e.zincError, ToStringStyle.JSON_STYLE)
        }
        verifyAll()
    }

    @Test(expected = CannotPostOrderRequestException.class)
    void "postOrder cannot extract requestId"() {
        OrderRequest orderRequest = new OrderRequest(
                idempotencyKey: "Torcularis Septentrionalis Omicron Piscium",
                retailer: "amazon",
                products: [new Product("Sheddi-Delta Capricorni", 159)],
                shipping: new Shipping(orderBy: ShippingMethod.fasted, maxDays: 21, maxPrice: 6543)
        )
        def url = "http://Rukbat-Alpha-Sagittarii/orders"
        def data = "{\"idempotency_key\":\"Torcularis Septentrionalis Omicron Piscium\",\"retailer\":\"amazon\",\"products\":[{\"product_id\":\"Sheddi-Delta Capricorni\",\"quantity\":159}],\"max_price\":0,\"shipping\":{\"order_by\":\"fasted\",\"maxDays\":21,\"maxPrice\":6543},\"webhooks\":{}}"
        def requestId = "Phact-Alpha-Columbae"
        def responseEntity = new ResponseEntity<String>("{request_id: \"\"}", OK)

        entity = new HttpEntity<>(data, headers)
        expect(restTemplate.exchange(url, HttpMethod.POST, entity, String.class)).andReturn(responseEntity)

        replayAll()
        assert requestId == zincApi.postOrder(orderRequest)
        verifyAll()
    }

    @Test
    void "postOrder OK"() {
        OrderRequest orderRequest = new OrderRequest(
                idempotencyKey: "Torcularis Septentrionalis Omicron Piscium",
                retailer: "amazon",
                products: [new Product("Sheddi-Delta Capricorni", 159)],
                shipping: new Shipping(orderBy: ShippingMethod.fasted, maxDays: 21, maxPrice: 6543)
        )
        def url = "http://Rukbat-Alpha-Sagittarii/orders"
        def data = "{\"idempotency_key\":\"Torcularis Septentrionalis Omicron Piscium\",\"retailer\":\"amazon\",\"products\":[{\"product_id\":\"Sheddi-Delta Capricorni\",\"quantity\":159}],\"max_price\":0,\"shipping\":{\"order_by\":\"fasted\",\"maxDays\":21,\"maxPrice\":6543},\"webhooks\":{}}"
        def requestId = "Phact-Alpha-Columbae"
        def responseEntity = new ResponseEntity<String>("{request_id: \"" +requestId + "\"}", OK)

        entity = new HttpEntity<>(data, headers);
        expect(restTemplate.exchange(url, HttpMethod.POST, entity, String.class)).andReturn(responseEntity)

        replayAll()
        assert requestId == zincApi.postOrder(orderRequest)
        verifyAll()
    }

    @Test
    void getProductDetails() {
        // don't mock helper for this test (reproduces spotted a bug IRL)
        zincApi.zincHelper = new ZincHelper()
        def productId = "B00KFP6NHO", supportedRetailer = SupportedRetailer.amazon
        def json = new String(Files.readAllBytes(Paths.get("./src/main/test/unit-resources/" +
            "ZincApiUnitTest-getProductDetails.json")))

        def url = "${baseUrl}products/${productId}?retailer=${supportedRetailer.name()}&async=false"
        def responseEntity = new ResponseEntity<String>(json, OK)

        entity = new HttpEntity<>(headers)
        expect(restTemplate.exchange(url, HttpMethod.GET, entity, String.class)).andReturn(responseEntity)

        replayAll()
        def actual = zincApi.getProductDetails(supportedRetailer, productId, null, null, false)
        verifyAll()
        assert null != actual
        assert "completed" == actual.status
        assert productId == actual.productId
        assert "1515775557" == actual.timestamp
        assert "Nuby Garden Fresh Fruitsicle Frozen Pop Tray" == actual.title
        assert "Nuby" == actual.brand
    }
}
