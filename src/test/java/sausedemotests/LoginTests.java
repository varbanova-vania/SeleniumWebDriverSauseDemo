package sausedemotests;

import core.BaseTest;
import enums.PageTitle;
import models.ShippingDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static enums.PageUrls.*;
import static enums.Products.SAUCE_LAB_BACKPACK;
import static enums.Products.SAUCE_LAB_BOLD_T_SHIRT;

public class LoginTests extends BaseTest {
    @Test
    public void userAuthenticated_when_validCredentialsProvided() {
        assertPageUrlEquals(INVENTORY_PAGE);
        WebElement shoppingCartIcon = driver.findElement(By.id("shopping_cart_container"));
        Assertions.assertTrue(shoppingCartIcon.isDisplayed());
        assertPageTitle(PageTitle.INVENTORY_PAGE_TITLE);
    }

    @Test
    public void productAddedToShoppingCart_when_addToCart() {
        addProductToCartByTitle(SAUCE_LAB_BACKPACK);
        addProductToCartByTitle(SAUCE_LAB_BOLD_T_SHIRT);

        clickShoppingCartButton();

        assertPageUrlEquals(CART_PAGE);
        assertProductIsInTheCart(SAUCE_LAB_BACKPACK);
        assertProductIsInTheCart(SAUCE_LAB_BOLD_T_SHIRT);
        assertProductPrice(SAUCE_LAB_BACKPACK, "29.99");
        assertProductPrice(SAUCE_LAB_BOLD_T_SHIRT, "15.99");
        assertPageTitle(PageTitle.CART_PAGE_TITLE);
    }

    @Test
    public void userDetailsAdded_when_checkoutWithValidInformation() {
        ShippingDetails shippingDetails = new ShippingDetails();
        purchaseMultipleProducts(SAUCE_LAB_BACKPACK, SAUCE_LAB_BOLD_T_SHIRT);
        proceedToCheckout();

        fillShippingDetails(shippingDetails);
        continueToNextStep();

        assertPageUrlEquals(CHECKOUT_OUT_OVERVIEW_PAGE);
        assertPageTitle(PageTitle.CHECKOUT_OVERVIEW_TITLE);
    }

    @Test
    public void orderCompleted_when_addProduct_and_checkout_withConfirm() {
        ShippingDetails shippingDetails = new ShippingDetails();
        purchaseMultipleProducts(SAUCE_LAB_BACKPACK, SAUCE_LAB_BOLD_T_SHIRT);
        proceedToCheckout();
        fillShippingDetails(shippingDetails);
        continueToNextStep();

        finishOrder();

        assertPageTitle(PageTitle.COMPLETE_PAGE_TITLE);
    }
}
