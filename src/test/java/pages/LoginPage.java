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

public class LoginPage {

	WebDriver loginDriver;
	WebDriverWait wait;

	@FindBy(id = "user-name")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "login-button")
	WebElement loginBtn;

	@FindBy(className = "inventory_item")
	List<WebElement> productList;

	@FindBy(css = "[data-test='error']")
	WebElement errorActualMessage;
	
	@FindBy(className = "inventory_item")
	List<WebElement> productLocator;

	public LoginPage(WebDriver loginDriver) {
		this.loginDriver = loginDriver;
		PageFactory.initElements(loginDriver, this);
		wait = new WebDriverWait(loginDriver, Duration.ofSeconds(10));

	}

	public List<String> getAllProductNames() {

		List<String> names = new ArrayList<>();
		try {
	        By nameLocator = By.className("inventory_item_name");
			for (WebElement item : productList) {
				String name = item.findElement(nameLocator).getText();
				names.add(name);
				System.out.println("number of name: " + names.size());
			}
		} catch (Exception e) {
			System.out.println("An occured error while getting product list : " + e);
		}

		return names;
	}

	public void goPage(String url) {
		try {
			loginDriver.get(url);
		} catch (Exception e) {
			System.out.println("An occured error while loading new url : " + e);
		}
	}

	public boolean isOnInventoryPage(String currentUrl) {
		return loginDriver.getCurrentUrl().contains(currentUrl);

	}

	public void enterUsername(String user) {
		try {
			wait.until(ExpectedConditions.visibilityOf(username));
			username.sendKeys(user);
		} catch (Exception e) {
			System.out.println("An occured error while writing the username : " + e);
		}
	}

	public void enterPassword(String psw) {
		try {
			wait.until(ExpectedConditions.visibilityOf(password));
			password.sendKeys(psw);

		} catch (Exception e) {
			System.out.println("An occured error while writing the password : " + e);
		}
	}

	public String getErrorMessage() {
		return errorActualMessage.getText().trim();
	}

	public void clickButton() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
			loginBtn.click();
		} catch (Exception e) {
			System.out.println("An occured error while clicking login button : " + e);
		}

	}
}