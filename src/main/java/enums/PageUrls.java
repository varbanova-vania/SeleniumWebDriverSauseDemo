package enums;

public enum PageUrls {
    LOGIN_PAGE("https://www.saucedemo.com/"),
    INVENTORY_PAGE("https://www.saucedemo.com/inventory.html"),
    CART_PAGE("https://www.saucedemo.com/cart.html"),
    CHECKOUT_OUT_OVERVIEW_PAGE("https://www.saucedemo.com/checkout-step-two.html");
    private final String value;

    PageUrls(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
