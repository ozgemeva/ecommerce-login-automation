package steps;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.InventoryPage;
import testData.dataSet;
import utils.DriverFactory;

public class InventorySteps {

    WebDriver driver = DriverFactory.getDriver();
    InventoryPage inventoryPage = new InventoryPage(driver);

    @Given("I am on the inventory page")
    public void I_am_on_the_inventory_page() {
        Assert.assertTrue(inventoryPage.isOnInventoryPage(dataSet.currentUrl_inventory), "Not on inventory page!");
    }

    @Given("the inventory page has fully loaded")
    public void the_inventory_page_has_fully_loaded() {
        List<String> products = inventoryPage.getAllProductNames();
        Assert.assertFalse(products.isEmpty(), "Inventory page loaded but no products found!");
        System.out.println("Inventory loaded successfully with " + products.size() + " products.");
    }

    @Then("all products should be visible")
    public void all_products_should_be_visible() {
        List<WebElement> productElementList = inventoryPage.getProductElements();
        for (WebElement productElement : productElementList) {
            Assert.assertTrue(productElement.isDisplayed(), "A product element is not visible!");
        }
    }

    @And("the number of products should be greater than 0")
    public void the_number_of_products_should_be_greater() {
        int count = inventoryPage.getProductElementsNumber();
        Assert.assertTrue(count > 0, "No products found on the inventory page!");
    }

    @When("I click the Add to cart button for the {string} product")
    public void is_ClickTheAddToCartButtonForTheProduct(String productName) {
        inventoryPage.click_AddToCart_btn(productName);
    }

    @And("I navigate to the cart page")
    public void and_I_navigate_to_the_cart_page() {
        inventoryPage.goToCartPage();
        Assert.assertTrue(inventoryPage.isOnCartPage(dataSet.currentUrl_cart), "Not on cart page!");
    }

    @Then("the product {string} should be added to the basket")
    public void is_added_to_the_basket(String expectedProductName) {
        List<String> cartProducts = inventoryPage.getProductNamesInCart();
        Assert.assertTrue(
            cartProducts.stream().anyMatch(name -> name.equalsIgnoreCase(expectedProductName.trim())),
            "Product not found in the basket: " + expectedProductName
        );
    }

    @And("the Add to cart button should change to Remove button")
    public void changetoRemoveButton() {
        try {
            WebElement removeBtn = driver.findElement(By.xpath("//*[contains(text(),'Remove')]"));
            Assert.assertTrue(removeBtn.isDisplayed(), "Remove button is not displayed.");
        } catch (Exception e) {
            Assert.fail("Remove button not found: " + e.getMessage());
        }
    }
}
