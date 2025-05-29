package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage extends BasePage {
    private final By emailField = By.id("user_email");
    private final By passwordField = By.id("user_password");
    private final By signInButton = By.cssSelector("button");
    private final By createAccountLink = By.linkText("Create new account");
    private final By signOutButton = By.cssSelector("#crawler-sign-out > span");

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    // Navigate to sign-in page
    public SignInPage navigateTo() {
        driver.get("http://localhost:4000/sign_in");
        waitForPageLoad();
        return this;
    }

    public SignInPage enterEmail(String email) {
        WebElement emailElement = waitForElement(emailField);
        emailElement.clear();
        emailElement.sendKeys(email);
        return this;
    }

    public SignInPage enterPassword(String password) {
        WebElement passwordElement = waitForElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
        return this;
    }

    // Sign in with credentials and navigate to Dashboard
    public DashboardPage clickSignIn() {
        waitForElement(signInButton).click();
        return new DashboardPage(driver);
    }

    // Sign in without entering any credentials
    public DashboardPage signInWithoutCredentials() {
        waitForElement(signInButton).click();
        return new DashboardPage(driver);
    }

    // Used for form validation tests (no redirect expected)
    public SignInPage clickSignInSuppressRedirect() {
        waitForElement(signInButton).click();
        return this;
    }

    // Click "Create new account" link
    public SignUpPage clickCreateAccount() {
        waitForElement(createAccountLink).click();
        return new SignUpPage(driver);
    }



    // Utility methods for test assertions
    public boolean isEmailFieldPresent() {
        try {
            waitForElementVisible(emailField);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordFieldPresent() {
        try {
            waitForElementVisible(passwordField);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailValue() {
        try {
            WebElement emailElement = driver.findElement(emailField);
            return emailElement.getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }


    public String getPasswordValue() {
        try {
            WebElement passwordElement = driver.findElement(passwordField);
            return passwordElement.getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }
}
