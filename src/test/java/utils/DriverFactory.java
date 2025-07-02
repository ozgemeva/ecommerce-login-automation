package utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	private static WebDriver browserType;

	public static void startingDriver(String driverBrowser) {
		try {
			if (driverBrowser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);

				browserType = new ChromeDriver(options);
				browserType.manage().window().maximize();
			}

			else if (driverBrowser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				browserType = new FirefoxDriver();
				browserType.manage().window().maximize();
			}

			else {
				throw new RuntimeException("Unsupported browser: " + driverBrowser);
			}

		} catch (Exception e) {
			System.out.println("An occurred error while opening driver: " + e);
		}
	}

	public static WebDriver getDriver() {
		return browserType;
	}
}
