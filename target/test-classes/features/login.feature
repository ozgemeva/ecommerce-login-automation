Feature: Login Functionality

  Background:
    Given I am on the login page

  @smoke 
  Scenario: Valid login with correct credentials
    And  I enter the correct username and password
    When I click the login button
    Then I should be redirected to the inventory page
    And  I should see more expected elements

  @negative 
  Scenario Outline: Login with invalid credentials
    And I enter "<username>" as invalid username
  	And I enter "<password>" as invalid password
    When I click the login button
    Then I should see an "<expectedResult>"

  Examples:
    | username         | password      | expectedResult                                                    				 |
    | locked_out_user  | secret_sauce  | Epic sadface: Sorry, this user has been locked out.                       |
    | wrong_user       | secret_sauce  | Epic sadface: Username and password do not match any user in this service |
    