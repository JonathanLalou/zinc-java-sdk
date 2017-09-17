package fr.sayasoft.zinc.sdk.enums;

import com.google.common.collect.Lists;
import fr.sayasoft.zinc.sdk.exception.UnsupportedRetailerException;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

@Getter
@ToString
public enum SupportedRetailer {

    // @formatter:off
    aliexpress  ( "AliExpress",           true,     true,  true  ,Lists.newArrayList("aliexpress.com" )),
    amazon      ( "Amazon",               true,     true,  true  ,Lists.newArrayList("amazon.com")),
    amazon_uk   ( "Amazon United Kingdom",true,     true,  true  ,Lists.newArrayList("amazon.co.uk"  )),
    amazon_ca   ( "Amazon Canada",        true,     true,  true  ,Lists.newArrayList("amazon.ca"  )),
    amazon_mx   ( "Amazon Mexico",        true,     true,  true  ,Lists.newArrayList("amazon.com.mx"  )),
    costco      ( "Costco",               true,     true,  true  ,Lists.newArrayList("costco.com","costco.ca" )),
    eastdane    ( "East Dane",            true,     false, false ,Lists.newArrayList("eastdane.com" )),
    google_shopping( "Google Shopping",   false,    true,  true  ,Lists.newArrayList("google.com/shopping?hl=en" )), // not sure of the domain nam)e
    newegg      ( "Newegg",               true,     false, false ,Lists.newArrayList("newegg.com" )),
    nordstrom   ( "Nordstrom",            true,     false, false ,Lists.newArrayList("nordstrom.com" )),
    overstock   ( "Overstock",            false,    true,  true  ,Lists.newArrayList("overstock.com" )),
    shopbop     ( "Shopbop",              true,     false, false ,Lists.newArrayList("shopbop.com" )),
    walmart     ( "Walmart",              true,     true,  true  ,Lists.newArrayList("walmart.com", "walmart.ca" ))
    ;
    // @formatter:on

    SupportedRetailer(String _fullname, Boolean _orders, Boolean _productDetails, Boolean _productPrices, Collection<String> domainNames) {
        this.fullname = _fullname;
        this.orders = _orders;
        this.productDetails = _productDetails;
        this.productPrices = _productPrices;
        this.domainNames = domainNames;
    }

    /**
     * Fullname of the retailer
     */
    private final String fullname;
    /**
     * Support order placing API
     */
    private final Boolean orders;
    /**
     * Support order product details API
     */
    private final Boolean productDetails;
    /**
     * Support order product price API
     */
    private final Boolean productPrices;
    /**
     * Suitable domain names for this retailer: this is a list, because some retailers may use several domain names and shortened URLs
     */
    private final Collection<String> domainNames;

    public static SupportedRetailer domainName2SupportedRetailer(String host) throws UnsupportedRetailerException {
        for (SupportedRetailer retailer : values()) {
            for (String domainName : retailer.domainNames) {
                if (host.endsWith(domainName)) {
                    return retailer;
                }
            }
        }
        throw new UnsupportedRetailerException("Non-supported domain name retailer: " + host);
    }
}