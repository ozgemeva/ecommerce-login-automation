# ecommerce-login-automation
Automated login flow test using Selenium, Java, TestNG, and Cucumber for an e-commerce website.
# 🛍️ Ecommerce Login Automation

This is a test automation project for validating the login functionality 
and inventory page of an e-commerce site using:

- **Selenium WebDriver**
- **Java**
- **TestNG**
- **Cucumber (Gherkin)**
- **Page Object Model (POM)**

---

## 💻 Technologies Used

- Java 17  
- Selenium 4.x  
- TestNG  
- Cucumber  
- Maven  

---

## 📁 Project Structure

src/test/java/pages/ -> Page Object classes (Login, Inventory)
src/test/java/steps/ -> Step definitions
src/test/java/runner/ -> Cucumber Test Runner
src/test/resources/features/ -> Gherkin feature files


---

## 🧪 Sample Scenario

```gherkin
Scenario: Successful login
  Given I am on the login page  
  And I enter the correct username and password  
  When I click the login button  
  Then I should be redirected to the inventory page  

▶️ How to Run
Clone the repository

Open with any Java IDE (e.g., IntelliJ, Eclipse)

Use TestNG or Cucumber runner to run tests
