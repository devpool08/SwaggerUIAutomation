package org.example.page_object;

import org.example.page_object.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

@SuppressWarnings("unused")
public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='product_label']")
    private WebElement pageLabel;
    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement dropdown;
    @FindBy(xpath = "//div[@class='inventory_list']//div[1]//div[3]//button[1]")
    private WebElement addProduct;
    @FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
    private WebElement cartBadge;
    @FindBy(xpath = "//a[@class='shopping_cart_link fa-layers fa-fw']")
    private WebElement checkoutCart;

    public String getLabel() {
        wait.until(ExpectedConditions.visibilityOf(pageLabel));
        return pageLabel.getText();
    }

    public boolean sortByPriceLowToHigh() {
        try {
            wait.until(ExpectedConditions.visibilityOf(dropdown));
            dropdown.click();
            Select selectDropDown = new Select(dropdown);
            selectDropDown.selectByContainsVisibleText("Price (low to high)");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addProductToCart() {
        try {
            wait.until(ExpectedConditions.visibilityOf(addProduct));
            addProduct.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyProductCart() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']")));
        return cartBadge.isDisplayed();
    }

    public boolean checkoutCart() {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutCart));
            checkoutCart.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
