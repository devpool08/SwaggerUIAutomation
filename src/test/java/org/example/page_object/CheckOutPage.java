package org.example.page_object;


import org.example.page_object.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
@SuppressWarnings("unused")
public class CheckOutPage extends BasePage {

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private WebElement inventoryItem;
    @FindBy(xpath = "//a[normalize-space()='CHECKOUT']")
    private WebElement checkoutButton;

    public String isItemPresent() {
        wait.until(ExpectedConditions.visibilityOf(inventoryItem));
        return inventoryItem.getText();
    }

    public boolean clickOnCheckoutButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
            checkoutButton.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

