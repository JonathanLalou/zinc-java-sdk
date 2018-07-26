package fr.sayasoft.zinc.sdk.api

import com.google.gson.JsonSyntaxException
import fr.sayasoft.zinc.sdk.domain.ZincConstants
import fr.sayasoft.zinc.sdk.domain.ZincError
import fr.sayasoft.zinc.sdk.enums.ZincErrorCode
import fr.sayasoft.zinc.sdk.exception.OrderResponseException
import org.apache.commons.lang3.StringUtils
import org.junit.Test

import java.nio.file.Files
import java.nio.file.Paths

import static fr.sayasoft.zinc.sdk.enums.SupportedRetailer.amazon
import static org.junit.Assert.fail

class ZincHelperUnitTest {

    ZincHelper zincHelper = new ZincHelper()

    @Test
    void "parse OrderResponse type=error"() {
        final String responseBody = new String(Files.readAllBytes(Paths.get("./src/main/test/unit-resources/ZincHelperUnitTest-withError.json")));
        assert StringUtils.isNotBlank(responseBody)

        def zincError
        try {
            zincHelper.parseOrderResponse(responseBody)
            fail()
            return
        } catch (OrderResponseException e) {
            zincError = e.zincError
        }
        assert ZincConstants.error == zincError.type
        assert "Torcularis Septentrionalis Omicron Piscium" == zincError.orderRequest.idempotencyKey
        assert amazon.name() == zincError.orderRequest.retailer
        assert 0 == zincError.orderRequest.maxPrice
        assert [:] == zincError.orderRequest.webhooks
        assert ZincErrorCode.invalid_payment_method == zincError.code
        assert "Sualocin-Alpha Delphini" == zincError.message
        assert ["Rasalas": "Mu Leonis"] == zincError.data
        assert Date.parse(ZincConstants.jsonDateFormat, "2017-09-17T16:05:48.250Z") == zincError.createdAt
        assert 1 == zincError.statusUpdates.size()
    }

    @Test
    void "parse OrderResponse no explicit type"() {
        final String responseBody = new String(Files.readAllBytes(Paths.get("./src/main/test/unit-resources/ZincHelperUnitTest-orderResponse-withoutExplicitType.json")));

        'parse OrderResponse'(responseBody)
    }

    @Test
    void "parse OrderResponse type=order_response"() {
        final String responseBody = new String(Files.readAllBytes(Paths.get("./src/main/test/unit-resources/ZincHelperUnitTest-orderResponse-withExplicitType.json")));

        'parse OrderResponse'(responseBody)
    }
    private void 'parse OrderResponse'(String responseBody) {
        assert StringUtils.isNotBlank(responseBody)
        def orderResponse
        try {
            orderResponse = zincHelper.parseOrderResponse(responseBody)
        } catch (OrderResponseException e) {
            fail()
            return
        }
        assert orderResponse.priceComponents.shipping == 0
        assert orderResponse.priceComponents.subtotal == 1999
        assert orderResponse.priceComponents.tax == 0
        assert orderResponse.priceComponents.total == 1999

        assert orderResponse.merchantOrders.size() == 1
        assert orderResponse.merchantOrders[0].merchantOrderId == "112-1234567-7272727"
        assert orderResponse.merchantOrders[0].merchant == amazon.name()
        assert orderResponse.merchantOrders[0].account == "timbeaver@gmail.com"

        assert orderResponse.tracking.size() == 1
        assert orderResponse.tracking[0].productId == "0923568964"
        assert orderResponse.tracking[0].merchantOrderId == "112-1234567-7272727"
        assert orderResponse.tracking[0].carrier == "Fedex"
        assert orderResponse.tracking[0].trackingNumber == "9261290100129790891234"
    }

    /** As of 20171016, ZincError.data was typed as String, and unmarshalling raised:
     * <code>com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at line 1 column 1547 path $.data
        ...
        Caused by: java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at line 1 column 1547 path $.data
     </code>
     * */
    @Test
    void "parse actual ZincError (one that was actually returned after calling ZincAPI)"() {
        final String responseBody = new String(Files.readAllBytes(Paths.get("./src/main/test/unit-resources/zincError.json")));

        def ZincError zincError
        try {
            zincHelper.parseOrderResponse(responseBody)
        } catch (JsonSyntaxException jse) {
            fail(jse.message)
            return
        } catch (OrderResponseException ore){
            // test OK
            zincError = ore.zincError
        }
        assert null != zincError
        assert null != zincError.data
        assert zincError.data instanceof Map
    }
}
