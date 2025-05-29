package org.example.pages;

import org.openqa.selenium.*;

public class SignUpPage extends BasePage {
    private final By firstNameField = By.id("user_first_name");
    private final By lastNameField = By.id("user_last_name");
    private final By emailField = By.id("user_email");
    private final By passwordField = By.id("user_password");
    private final By confirmPasswordField = By.id("user_password_confirmation");
    private final By signUpButton = By.cssSelector("button");

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public SignUpPage enterFirstName(String firstName) {
        waitForElement(firstNameField).sendKeys(firstName);
        return this;
    }

    public SignUpPage enterLastName(String lastName) {
        waitForElement(lastNameField).sendKeys(lastName);
        return this;
    }

    public SignUpPage enterEmail(String email) {
        waitForElement(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage enterPassword(String password) {
        waitForElement(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage enterConfirmPassword(String password) {
        waitForElement(confirmPasswordField).sendKeys(password);
        return this;
    }

    public DashboardPage clickSignUp() {
        waitForElement(signUpButton).click();
        return new DashboardPage(driver);
    }

    public String getEmailValue() {
        try {
            WebElement element = driver.findElement(By.id("user_email"));
            return element.getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }

    public SignUpPage clickSignUpExpectingFailure() {
        waitForElement(signUpButton).click();
        return this;
    }


}
