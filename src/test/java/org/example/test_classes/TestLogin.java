package org.example.test_classes;

import lombok.extern.log4j.Log4j2;
import org.example.page_object.InventoryPage;
import org.example.page_object.LoginPage;
import org.example.test_bases.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
@SuppressWarnings("All")
public class TestLogin extends BaseTestClass {
    @Test(groups = {"Sanitty","Main"})
    public void testOpenPage() {
        openPage(properties.getProperty("SWAGGER_LOGIN_URL"));
        System.out.println("Inside testOpenPage");
    }

    @Test(dependsOnMethods = "testOpenPage",groups = {"Sanitty","Main"})
    public void testInputCredentials() {
        System.out.println("Inside testInputCredential");
        log.info("input credential section");
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.inputUserName(properties.getProperty("VALID_USER_NAME")), "Error While Entering User Name");
        Assert.assertTrue(loginPage.inputPassword(properties.getProperty("VALID_PASSWORD")), "Error While Entering Password");
        Assert.assertTrue(loginPage.clickEnter(), "Error while clicking Login Button");
        log.info("Successfully entered credentials");
    }

    @Test(dependsOnMethods = "testInputCredentials",groups = {"Sanitty","Main"})
    public void testVerifyLogin() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getLabel(), "Products");
        log.info("Successfully logged in and navigated to the inventory page");
    }
    @Test(dependsOnMethods = "testVerifyLogin",groups = {"Sanitty","Main"})
    public void testDemoForFailing() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getLabel(), "Products ");
        log.error("Test failed due to incorrect label intentionally");
    }
    @Test(dependsOnMethods = "testDemoForFailing",groups = {"Sanitty","Main"})
    public void testDemoForSkip() {
        log.info("This Part Never be Execute");
    }
    @Test(dependsOnMethods = "testDemoForFailing",groups = {"Sanitty","Main"})
    public void testDemoForSkip2() {
        log.info("This Part Never be Execute");
    }
}
