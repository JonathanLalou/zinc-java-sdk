package fr.sayasoft.zinc.sdk.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.sayasoft.zinc.sdk.domain.OrderResponse;
import fr.sayasoft.zinc.sdk.domain.ZincConstants;
import fr.sayasoft.zinc.sdk.domain.ZincError;
import fr.sayasoft.zinc.sdk.enums.ZincResponseType;
import fr.sayasoft.zinc.sdk.exception.OrderResponseException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

import static fr.sayasoft.zinc.sdk.enums.ZincResponseType.order_response;

@Getter
@Setter
@ToString
@Log4j
public class ZincHelper {

    // UNITTESTME
    public OrderResponse parseOrderResponse(String responseBody) throws OrderResponseException {
        final Gson gson = new GsonBuilder().setDateFormat(ZincConstants.jsonDateFormat).create();
        final Map<String, Object> responseMap = gson.fromJson(responseBody, ZincConstants.TYPE_MAP_STRING_OBJECT);

        final ZincResponseType zincResponseType;
        final String szType = (String) responseMap.get(ZincConstants.type);
        if (StringUtils.isBlank(szType)) {
            // by default, we assume that if no 'type' is provided, then this means there is no error and the returned message *is* an order_response
            zincResponseType = order_response;
        } else {
            zincResponseType = ZincResponseType.valueOf(szType);
        }
        switch (zincResponseType) {
            case error:
                final ZincError zincError = gson.fromJson(responseBody, ZincError.class);
                throw new OrderResponseException(zincError);
            case order_response:
                return gson.fromJson(responseBody, OrderResponse.class);
            default:
                throw new IllegalStateException("Unknown ZincResponseType: " + zincResponseType);
        }
    }


}
