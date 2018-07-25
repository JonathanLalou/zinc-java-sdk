package fr.sayasoft.zinc.sdk.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.sayasoft.zinc.sdk.domain.OrderResponse;
import fr.sayasoft.zinc.sdk.domain.ProductDetailsResponse;
import fr.sayasoft.zinc.sdk.domain.ProductOfferResponse;
import fr.sayasoft.zinc.sdk.domain.ZincConstants;
import fr.sayasoft.zinc.sdk.domain.ZincError;
import fr.sayasoft.zinc.sdk.enums.ZincResponseType;
import fr.sayasoft.zinc.sdk.exception.OrderResponseException;
import fr.sayasoft.zinc.sdk.exception.ProductDetailsResponseException;
import fr.sayasoft.zinc.sdk.exception.ProductOfferResponseException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Map;

import static fr.sayasoft.zinc.sdk.enums.ZincResponseType.offer_response;
import static fr.sayasoft.zinc.sdk.enums.ZincResponseType.order_response;
import static fr.sayasoft.zinc.sdk.enums.ZincResponseType.product_response;

@Getter
@Setter
@ToString
@Log4j
public class ZincHelper implements Serializable {

    public static String encodeToken(String token){
        if ((null == token) || (0 == token.length())){
            throw new IllegalArgumentException("token cannot be null or blank");
        }
        if (!token.trim().endsWith(":")){
            log.warn("Token should ends with a colon ':'");
        }
        return Base64.encodeBase64String(token.getBytes());
    }

    public OrderResponse parseOrderResponse(String responseBody) throws OrderResponseException {
        final Gson gson = new GsonBuilder().setDateFormat(ZincConstants.jsonDateFormat).create();
        final Map<String, Object> responseMap = gson.fromJson(responseBody, ZincConstants.TYPE_MAP_STRING_OBJECT);

        final ZincResponseType zincResponseType;
        final String szType = (String) responseMap.get(ZincConstants._type);
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

    public ProductOfferResponse parseProductOfferResponse(String responseBody) throws ProductOfferResponseException {
        final Gson gson = new GsonBuilder().setDateFormat(ZincConstants.jsonDateFormat).create();
        final Map<String, Object> responseMap = gson.fromJson(responseBody, ZincConstants.TYPE_MAP_STRING_OBJECT);

        final ZincResponseType zincResponseType;
        final String szType = (String) responseMap.get(ZincConstants._type);
        if (StringUtils.isBlank(szType)) {
            // by default, we assume that if no 'type' is provided, then this means there is no error and the returned message *is* an offer_response
            zincResponseType = offer_response;
        } else {
            zincResponseType = ZincResponseType.valueOf(szType);
        }
        switch (zincResponseType) {
            case error:
                final ZincError zincError = gson.fromJson(responseBody, ZincError.class);
                throw new ProductOfferResponseException(zincError);
            case offer_response:
                return gson.fromJson(responseBody, ProductOfferResponse.class);
            default:
                throw new IllegalStateException("Unknown ZincResponseType: " + zincResponseType);
        }
    }

    public ProductDetailsResponse parseProductDetailsResponse(String responseBody) throws ProductDetailsResponseException {
        final Gson gson = new GsonBuilder().setDateFormat(ZincConstants.jsonDateFormat).create();
        final Map<String, Object> responseMap = gson.fromJson(responseBody, ZincConstants.TYPE_MAP_STRING_OBJECT);

        final ZincResponseType zincResponseType;
        final String szType = (String) responseMap.get(ZincConstants._type);
        if (StringUtils.isBlank(szType)) {
            // by default, we assume that if no 'type' is provided, then this means there is no error and the returned message *is* an offer_response
            zincResponseType = product_response;
        } else {
            zincResponseType = ZincResponseType.valueOf(szType);
        }
        switch (zincResponseType) {
            case error:
                final ZincError zincError = gson.fromJson(responseBody, ZincError.class);
                throw new ProductDetailsResponseException(zincError);
            case product_response:
                return gson.fromJson(responseBody, ProductDetailsResponse.class);
            default:
                throw new IllegalStateException("Unknown ZincResponseType: " + zincResponseType);
        }
    }


}
