package fr.sayasoft.zinc.sdk.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

import java.io.Serializable;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Caution! requests are of the form "https://api.zinc.io/v1/products/0923568964/offers?retailer=amazon" ! Fields are not to be serialized in JSON!
/** The same entity is used for both product price and product details */
public class ProductRequest implements Serializable {

    private String retailer;

    private Number maxAge; // max_age;
    private Number newerThan; // newer_than;
    private Boolean async;
}
