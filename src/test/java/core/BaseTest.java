package core;

import enums.PageTitle;
import enums.PageUrls;
import enums.Products;
import models.ShippingDetails;
import org.example.BrowserTypes;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sausedemotests.ProductsTests;
import utils.CredentialsManager;

import java.time.Duration;

import static enums.PageUrls.*;
import static org.junit.jupiter.api.Assertions.*;
import static utils.CredentialsManager.*;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @AfterAll
    public static void afterTest() {
        driver.close();
        driver.quit();
    }

    @BeforeAll
    public static void beforeAll() {
        driver = startBrowser(BrowserTypes.CHROME);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @BeforeEach
    public void beforeEach() {
        navigateToPage(LOGIN_PAGE);
        authenticateWithUser(USERNAME, PASSWORD);
        waitForElementToBePresent(By.id("inventory_container"));
    }

    protected static WebDriver startBrowser(BrowserTypes browserType) {
        switch (browserType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                return new FirefoxDriver(firefoxOptions);
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                return new EdgeDriver(edgeOptions);
        }

        return null;
    }

    protected static void authenticateWithUser(String username, String pass) {
        WebElement usernameInput = driver.findElement(By.xpath("//input[@data-test='username']"));
        usernameInput.sendKeys(username);

        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys(pass);

        WebElement loginButton = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        loginButton.click();

        WebElement inventoryPageTitle = driver.findElement(By.xpath("//div[@class='app_logo']"));
        wait.until(ExpectedConditions.visibilityOf(inventoryPageTitle));
    }

    protected WebElement getProductByTitle(String title) {
        return driver.findElement(By.xpath(String.format("//div[@class='inventory_item' and descendant::div[text()='%s']]", title)));
    }

    protected static void fillShippingDetails(ShippingDetails shippingDetails) {
        driver.findElement(By.id("first-name")).sendKeys(shippingDetails.getFirstName());
        driver.findElement(By.id("last-name")).sendKeys(shippingDetails.getLastName());
        driver.findElement(By.id("postal-code")).sendKeys(shippingDetails.getZipCode());
    }

    protected void waitForElementToBePresent(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void addProductToCartByTitle(Products product) {
        WebElement element = getProductByTitle(product.toString());
        element.findElement(By.className("btn_inventory")).click();
    }

    protected void clickShoppingCartButton() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    protected void assertPageUrlEquals(PageUrls pageUrls) {
        String actualUrl = driver.getCurrentUrl();

        assertEquals(pageUrls.toString(), actualUrl, "Urls are not same");
    }

    protected void navigateToPage(PageUrls pageUrls) {
        driver.get(pageUrls.toString());
    }

    protected void assertProductIsInTheCart(Products product) {
        WebElement element = driver.findElement(By.xpath(String.format("//div[contains(text(), '%s')]", product.toString())));

        assertTrue(element.isDisplayed());
    }

    protected void assertPageTitle(PageTitle expectedPageTitle) {
        WebElement actualPageTitle = driver.findElement(By.className("title"));
        assertTrue(actualPageTitle.isDisplayed());
        Assertions.assertEquals(expectedPageTitle.toString(), actualPageTitle.getText());
    }

    protected void assertProductPrice(Products product, String expectedPrice) {
        WebElement element = driver.findElement(By.xpath(String.format("//div[contains(text(), '%s')]//parent::a//following-sibling::div[@class=\"item_pricebar\"]//div[@class=\"inventory_item_price\"]", product.toString())));

        assertTrue(element.getText().contains(expectedPrice));
    }

    protected void purchaseMultipleProducts(Products... products) {
        for (Products product :
                products) {
            addProductToCartByTitle(product);
        }

        clickShoppingCartButton();
    }

    protected void assertPageTitleEquals(String expectedTitle) {
        String actualTitle = driver.findElement(By.xpath("//span[@class='title']")).getText();

        assertEquals(expectedTitle, actualTitle);
    }

    protected void proceedToCheckout() {
        driver.findElement(By.id("checkout")).click();
    }

    protected void continueToNextStep() {
        driver.findElement(By.id("continue")).click();
    }

    protected void finishOrder() {
        driver.findElement(By.id("finish")).click();
    }
}