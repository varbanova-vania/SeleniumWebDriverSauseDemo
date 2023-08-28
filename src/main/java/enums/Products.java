package enums;

public enum Products {
    SAUCE_LAB_BACKPACK("Sauce Labs Backpack"),
    SAUCE_LAB_BIKE_LIGHT("Sauce Labs Bike Light"),
    SAUCE_LAB_BOLD_T_SHIRT("Sauce Labs Bolt T-Shirt"),
    SAUCE_LAB_FLEECE_JACKET("Sauce Labs Fleece Jacket"),
    SAUCE_LAB_ONESIE("Sauce Labs Onesie"),
    RED_T_SHIRT("Test.allTheThings() T-Shirt (Red)");
    private final String value;

    Products(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
