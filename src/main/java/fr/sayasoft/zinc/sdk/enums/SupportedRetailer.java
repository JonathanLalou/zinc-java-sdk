package fr.sayasoft.zinc.sdk.enums;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import fr.sayasoft.zinc.sdk.exception.UnsupportedRetailerException;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

@Getter
@ToString
public enum SupportedRetailer {

    // @formatter:off
    aliexpress  ( "AliExpress",           true,     true,  true  , "CN", "USD", "dollar", Lists.newArrayList("aliexpress.com" )),
    amazon      ( "Amazon",               true,     true,  true  , "US", "USD", "dollar", Lists.newArrayList("amazon.com")),
    amazon_uk   ( "Amazon United Kingdom",true,     true,  true  , "GB", "GBP", "gbp"   , Lists.newArrayList("amazon.co.uk"  )),
    amazon_ca   ( "Amazon Canada",        true,     true,  true  , "CA", "CAD", "dollar", Lists.newArrayList("amazon.ca"  )),
    amazon_mx   ( "Amazon Mexico",        true,     true,  true  , "MX", "MXN", "dollar", Lists.newArrayList("amazon.com.mx"  )),
    amazon_fr   ( "Amazon France",        true,     true,  true  , "FR", "EUR", "euro"  , Lists.newArrayList("amazon.fr"  )),
    amazon_de   ( "Amazon Germany",       true,     true,  true  , "DE", "EUR", "euro"  , Lists.newArrayList("amazon.de"  )),
    costco      ( "Costco",               true,     true,  true  , "US", "USD", "dollar", Lists.newArrayList("costco.com")),
    costco_ca   ( "Costco Canada",        true,     true,  true  , "CA", "CAD", "dollar", Lists.newArrayList("costco.ca" )),
    eastdane    ( "East Dane",            true,     false, false , "US", "USD", "dollar", Lists.newArrayList("eastdane.com" )),
    google_shopping( "Google Shopping",   false,    true,  true  , "US", "USD", "dollar", Lists.newArrayList("google.com/shopping?hl=en" )), // not sure of the domain name
    newegg      ( "Newegg",               true,     false, false , "US", "USD", "dollar", Lists.newArrayList("newegg.com" )),
    nordstrom   ( "Nordstrom",            true,     false, false , "US", "USD", "dollar", Lists.newArrayList("nordstrom.com" )),
    overstock   ( "Overstock",            false,    true,  true  , "US", "USD", "dollar", Lists.newArrayList("overstock.com" )),
    shopbop     ( "Shopbop",              true,     false, false , "US", "USD", "dollar", Lists.newArrayList("shopbop.com" )),
    walmart     ( "Walmart",              true,     true,  true  , "US", "USD", "dollar", Lists.newArrayList("walmart.com")),
    walmart_ca  ( "Walmart Canada",       true,     true,  true  , "CA", "CAD", "dollar", Lists.newArrayList("walmart.ca" ))
    ;
    // @formatter:on
    // owing to script: https://zinc.io/docs/assets/js/bookmarklet.js, are also supported: bestbuy.com, sears.com
    // and owing to doc, 6 retailers out of 13 are no more supported

    SupportedRetailer(String _fullname, Boolean _orders, Boolean _productDetails, Boolean _productPrices, String _baseCountry, String _baseCurrency, String _fontAwesomeCurrencyIcon, Collection<String> domainNames) {
        this.fullname = _fullname;
        this.orders = _orders;
        this.productDetails = _productDetails;
        this.productPrices = _productPrices;
        this.baseCountry = _baseCountry;
        this.baseCurrency = _baseCurrency;
        this.fontAwesomeCurrencyIcon = _fontAwesomeCurrencyIcon;
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
     * 2-letter ISO-code for base country: ex: US for Amazon.com and Walmart.com, CA for Amazon.ca, DE for Amazon.de, etc.
     * @deprecated
     */
    @Deprecated // DELETEME
    private final String baseCountry;
    /**
     * 3-letter ISO-code for base currency : ex: USD for Amazon.com and Walmart.com, CAD for Amazon.ca, EUR for Amazon.de, etc.
     * @deprecated
     */
    @Deprecated // DELETEME
    private final String baseCurrency;
    /**
     * FontAwesome icon for the currency used by this retailer
     * @deprecated
     */
    @Deprecated // DELETEME
    private final String fontAwesomeCurrencyIcon;
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

    final static public List<SupportedRetailer> ACTUALLY_SUPPORTED_RETAILERS_TODAY = ImmutableList.of(amazon, amazon_ca, amazon_mx, amazon_uk, amazon_de, amazon_fr, walmart, costco);
}