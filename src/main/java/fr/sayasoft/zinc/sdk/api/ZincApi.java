package fr.sayasoft.zinc.sdk.api;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import fr.sayasoft.zinc.sdk.domain.OrderRequest;
import fr.sayasoft.zinc.sdk.domain.OrderResponse;
import fr.sayasoft.zinc.sdk.domain.ZincConstants;
import fr.sayasoft.zinc.sdk.domain.ZincError;
import fr.sayasoft.zinc.sdk.exception.CannotGetOrderException;
import fr.sayasoft.zinc.sdk.exception.CannotPostOrderRequestException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Getter
@Setter
@ToString
@Log4j
public class ZincApi {
    public static final String DEFAULT_BASE_URL = "http://localhost:9090/v1/";

    private ZincHelper zincHelper = new ZincHelper();

    /*baseUrl in Production: https://api.zinc.io/v1/*/
    private String baseUrl = DEFAULT_BASE_URL;

    /**
     * Should already be encoded in Base64
     * NB: if the token is "123456789", then <code>base64EncodedClientToken = org.apache.commons.codec.binary.Base64.encodeBase64String("123456789:".getBytes())</code>. Note the ending colon (':') ; without it, it won't work!
     */
    private transient String base64EncodedClientToken;

    private RestTemplate restTemplate = new RestTemplate();

    private Gson gson = new GsonBuilder().setDateFormat(ZincConstants.jsonDateFormat).create();

    public ZincApi() {
        this(DEFAULT_BASE_URL);
    }

    public ZincApi(String baseUrl) {
        this(baseUrl, null);
    }

    public ZincApi(String _baseUrl, String _clientToken) {
        this.baseUrl = _baseUrl;
        this.base64EncodedClientToken = _clientToken;
    }

    public OrderResponse getOrder(String requestId) throws CannotGetOrderException {
        final String url = baseUrl + "orders/" + requestId;

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + base64EncodedClientToken);
        headers.setContentType(MediaType.APPLICATION_JSON); // optional

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        try {

            final ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode().equals(HttpStatus.OK)) {
                final String responseBody = response.getBody();
                return zincHelper.parseOrderResponse(responseBody);
            } else {
                throw new CannotGetOrderException("Cannot get order, received HttpStatus: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            log.error("Could not get Order", e);
            throw new CannotGetOrderException(e);
        }
    }

    // UNITTESTME

    /**
     * Posts an order to Zinc API.
     *
     * @param orderRequest the order to post
     * @return the requestId that is returned by Zinc API. Caution! a simple requestId does not mean the order was successful. It only says: the API
     * was called with this id. Then the application has either to query the API again, thanks to GET method, or to wait for webhooks to be called by
     * Zinc server.
     * @throws CannotPostOrderRequestException
     */
    public String postOrder(OrderRequest orderRequest) throws CannotPostOrderRequestException {
        final String url = baseUrl + "orders";

        // Authentication method is based on http://techqa.info/programming/question/28390662/Translate-authorized-curl--u-post-request-with-JSON-data-to-RestTemplate-equivalent
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + base64EncodedClientToken);
        headers.setContentType(MediaType.APPLICATION_JSON); // optional

        String data = gson.toJson(orderRequest);

        final HttpEntity<String> entity = new HttpEntity<>(data, headers);
        try {
            final ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            if (log.isDebugEnabled()) {
                log.debug("Posted order with idempotencyKey: " + orderRequest.getIdempotencyKey());
                log.debug("Received response: " + response);
            }
            final String responseBody = response.getBody();
            final Map<String, String> responseMap = gson.fromJson(responseBody, ZincConstants.TYPE_MAP_STRING_OBJECT);

            final String type = responseMap.get(ZincConstants.type);
            if (null != type && type.equalsIgnoreCase(ZincConstants.error)) {
                final ZincError zincError;
                try {
                    zincError = gson.fromJson(responseBody, ZincError.class);
                    throw new CannotPostOrderRequestException("Did not receive requestId but received ZincError", zincError);
                } catch (JsonSyntaxException e) {
                    throw new CannotPostOrderRequestException("Did not receive requestId and could not parse ZincError");
                }
            }

            final String requestId = responseMap.get(ZincConstants.requestId);
            if (Strings.isNullOrEmpty(requestId)) {
                throw new CannotPostOrderRequestException("Could not retrieve requestId");
            } else {
                return requestId;
            }
        } catch (RestClientException e) {
            log.error("Could not post OrderRequest", e);
            throw new CannotPostOrderRequestException(e);
        }
    }
}
