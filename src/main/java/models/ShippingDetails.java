package models;

public class ShippingDetails {
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public ShippingDetails() {
        this("Vania", "Varbanova", "5800");
    }

    public ShippingDetails(String firstName, String lastName, String zipCode) {
        setFirstName(firstName);
        setLastName(lastName);
        setZipCode(zipCode);
    }

    private String firstName;
    private String lastName;
    private String zipCode;
}
