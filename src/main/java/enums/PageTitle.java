package enums;

public enum PageTitle {

    INVENTORY_PAGE_TITLE("Products"),
    CART_PAGE_TITLE("Your Cart"),
    CHECKOUT_PAGE_TITLE("Your Information"),
    CHECKOUT_OVERVIEW_TITLE("Checkout: Overview"),
    COMPLETE_PAGE_TITLE("Checkout: Complete!");
    private final String value;

    PageTitle (String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

}
