package org.example.test_classes;


import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.page_object.CheckOutPage;
import org.example.page_object.CheckoutInfoFillFormPage;
import org.example.page_object.InventoryPage;
import org.example.test_bases.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
@SuppressWarnings("All")
public class TestAddToCartAndCheckOut extends BaseTestClass {
    private InventoryPage inventoryPage;
    private CheckOutPage checkOutPage;
    private CheckoutInfoFillFormPage checkoutInfoFillFormPage;

    @Test(groups = {"Regression","Main"})
    public void openInventoryPage() {
        openPage(properties.getProperty("SWAGGER_INVENTORY_PAGE_URL"));
    }

    @Test(dependsOnMethods = "openInventoryPage",groups = {"Regression","Main"})
    public void sortedByPrice() {
        inventoryPage = new InventoryPage(driver, wait);
        Assert.assertTrue(inventoryPage.sortByPriceLowToHigh(), "Error While sorting by price");
        log.info("Sorted by price completed");
    }

    @Test(dependsOnMethods = "sortedByPrice",groups = {"Regression","Main"})
    public void addToCart() {
        Assert.assertTrue(inventoryPage.addProductToCart(), "Error adding product to cart");
        log.info("Product added to cart completed");
    }

    @Test(dependsOnMethods = "addToCart",groups = {"Regression","Main"})
    public void validateCartItems() {
        Assert.assertTrue(inventoryPage.verifyProductCart(), "Product is not in cart");
        log.info("Product added to cart verify completed");
    }

    @Test(dependsOnMethods = "validateCartItems",groups = {"Regression","Main"})
    public void checkOutItem() {
        Assert.assertTrue(inventoryPage.checkoutCart(), "Error While Checking Out Item");
        log.info("Checkout item completed");
    }

    @Test(dependsOnMethods = "checkOutItem",groups = {"Regression","Main"})
    public void completeCheckout() {
        checkOutPage = new CheckOutPage(driver, wait);
        Assert.assertEquals(checkOutPage.isItemPresent(), "Sauce Labs Onesie");
        if (log.isInfoEnabled()) {
            log.info("Checkout page displayed");
        }
    }

    @Test(dependsOnMethods = "completeCheckout",groups = {"Regression","Main"})
    public void clickCheckOutButton() {
        Assert.assertTrue(checkOutPage.clickOnCheckoutButton(), "Error while clicking check out button");
        log.info("Checkout button clicked");
    }

    @Test(dependsOnMethods = "clickCheckOutButton",groups = {"Regression","Main"})
    public void verifyCheckoutPage() {
        checkoutInfoFillFormPage = new CheckoutInfoFillFormPage(driver, wait);
        Assert.assertEquals(checkoutInfoFillFormPage.isCheckoutInfoPresent(), "Checkout: Your Information");
        log.info("Checkout page displayed");
    }


    @SuppressWarnings("deprecation")
    @Test(dependsOnMethods = "verifyCheckoutPage",groups = {"Regression","Main"})
    public void validateInputCredentials() {
        Assert.assertTrue(checkoutInfoFillFormPage.
                inputFirstName(RandomStringUtils.randomAlphabetic(6)), "Error in Filling first name");
        Assert.assertTrue(checkoutInfoFillFormPage.
                inputLastName(RandomStringUtils.randomAlphabetic(6)), "Error in Filling last name");
        Assert.assertTrue(checkoutInfoFillFormPage.
                inputPostalCode(RandomStringUtils.randomNumeric(5)), "Error in Filling postal code");
        log.info("Checkout credentials validated successfully");
    }
    @SuppressWarnings("java.lang.AssertionError")
    @Test(dependsOnMethods = "validateInputCredentials",groups = {"Regression","Main"})
    public void demoTestForFailing() {
        Assert.assertTrue(checkoutInfoFillFormPage.
                inputFirstName(null), "Error intentionally");
        log.info("Form submit");
    }
    @Test(dependsOnMethods = "demoTestForFailing",groups = {"Regression","Main"})
    public void demoTestForSkip() {
        Assert.assertTrue(checkoutInfoFillFormPage.
                inputFirstName("John"), "Skip intentionally");
        log.info("Will Never Execute");
    }
    @Test(dependsOnMethods = "demoTestForFailing",groups = {"Regression","Main"})
    public void demoTestForSkip2() {
        Assert.assertTrue(checkoutInfoFillFormPage.
                inputFirstName("John"), "Skip intentionally");
        log.info("Will Never Execute");
    }

}
