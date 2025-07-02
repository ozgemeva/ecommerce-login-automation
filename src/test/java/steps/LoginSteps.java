package steps;

import org.testng.Assert;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.DriverFactory;
import pages.LoginPage;
import testData.dataSet;

public class LoginSteps {

	WebDriver driver = DriverFactory.getDriver();
	LoginPage loginPage = new LoginPage(driver);

	@Given("I am on the login page")
	public void on_the_login_page() {
		loginPage.goPage(testData.dataSet.BASE_URL_STRING);
	}

	// smoke test positive scenerio
	@And("I enter the correct username and password")
	public void enterValid_username_password() {
		loginPage.enterUsername(dataSet.valid_username);
		loginPage.enterPassword(dataSet.valid_password);
	}

	@When("I click the login button")
	public void click_btn() {
		loginPage.clickButton();
	}

	// smoke test positive scenerio
	@Then("I should be redirected to the inventory page")
	public void redirected_inventory() {
		loginPage.isOnInventoryPage(dataSet.currentUrl_inventory);
		Assert.assertTrue(loginPage.isOnInventoryPage(dataSet.currentUrl_inventory), "Not on inventory page!");

	}

	@And("I should see more expected elements")
	public void elements_list() {
		List<String> products = loginPage.getAllProductNames();
		Assert.assertTrue(products.size() > 0, "Product list is empty!");

	}

	// negative test scenerio
	@And("I enter {string} as invalid username")
	public void enterInvalid_username(String username) {
		loginPage.enterUsername(username);
		
	}
	
	@And("I enter {string} as invalid password")
	public void enterInvalid_password(String password) {
		loginPage.enterPassword(password);
	}

	@Then("I should see an {string}")
	public void getErrorMessage(String expectedResult) {
		String actualMessage = loginPage.getErrorMessage();
		Assert.assertEquals(actualMessage, expectedResult);
	}

}
