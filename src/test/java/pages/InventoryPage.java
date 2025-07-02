package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {

    WebDriver inventoryDriver;
    WebDriverWait wait;

    @FindBy(className = "inventory_item")
    List<WebElement> productList;

    @FindBy(className = "cart_item")
    List<WebElement> cartItems;

    public InventoryPage(WebDriver inventoryDriver) {
        this.inventoryDriver = inventoryDriver;
        PageFactory.initElements(inventoryDriver, this);
        wait = new WebDriverWait(inventoryDriver, Duration.ofSeconds(10));
    }

    public List<WebElement> getProductElements() {
        return productList;
    }

    public int getProductElementsNumber() {
        return productList.size();
    }

    public boolean isOnInventoryPage(String currentUrl) {
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        return inventoryDriver.getCurrentUrl().contains(currentUrl);
    }

    public List<String> getAllProductNames() {
        List<String> names = new ArrayList<>();
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(productList));
            for (WebElement item : productList) {
                String productName = item.findElement(By.className("inventory_item_name")).getText();
                names.add(productName);
            }
        } catch (Exception e) {
            System.out.println("Error getting product names: " + e.getMessage());
        }
        return names;
    }

    public void click_AddToCart_btn(String productName) {
        try {
            String formattedProductName = productName.toLowerCase().replaceAll(" ", "-");
            String xpath_product = "//*[@id='add-to-cart-" + formattedProductName + "']";
            WebElement addBtn = inventoryDriver.findElement(By.xpath(xpath_product));
            wait.until(ExpectedConditions.elementToBeClickable(addBtn));
            addBtn.click();
        } catch (Exception e) {
            System.out.println("Error clicking Add to Cart button: " + e.getMessage());
        }
    }

    public void goToCartPage() {
        WebElement cartIcon = inventoryDriver.findElement(By.className("shopping_cart_link"));
        cartIcon.click();
        wait.until(ExpectedConditions.urlContains("cart.html"));
    }

    public boolean isOnCartPage(String expectedUrlPart) {
        return inventoryDriver.getCurrentUrl().contains(expectedUrlPart);
    }

    public List<String> getProductNamesInCart() {
        List<String> namesInTheCarts = new ArrayList<>();
        try {
            for (WebElement item : cartItems) {
                try {
                    String name = item.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText();
                    namesInTheCarts.add(name.trim());
                } catch (Exception inner) {
                    System.out.println("Error reading product name: " + inner.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error while fetching cart items: " + e.getMessage());
        }
        return namesInTheCarts;
    }
}
