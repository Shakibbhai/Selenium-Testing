package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardDetailsPage extends BasePage {

    // --- Locators ---
    private final By addMemberButton = By.cssSelector("li > .add-new");  // Invite member button
    private final By memberEmailField = By.id("crawljax_member_email");  // Input field
    private final By submitButton = By.cssSelector("button");            // Submit invite

    public BoardDetailsPage(WebDriver driver) {
        super(driver);
    }

    // 🔹 Open board by static ID (used rarely; fallback)
    public BoardDetailsPage openDefaultBoard() {
        By boardTitle = By.cssSelector("#\\31-ranna h4"); // ID: 1-ranna
        waitForElement(boardTitle).click();
        return this;
    }

    // 🔹 Open board dynamically by board title (display text)
    public BoardDetailsPage openBoardByTitle(String boardName) {
        By dynamicBoardTitle = By.xpath("//h4[contains(normalize-space(), '" + boardName + "')]");

        waitForElement(dynamicBoardTitle).click();
        return this;
    }

    // 🔹 Open "Add Member" modal
    public BoardDetailsPage openAddMemberForm() {
        waitForElement(addMemberButton).click();
        return this;
    }

    public BoardDetailsPage inviteMemberByEmail(String email) {
        WebElement input = waitForElementVisible(memberEmailField); // Wait until visible
        input.clear();
        input.sendKeys(email);
        waitForElementVisible(submitButton).click();
        return this;
    }


    // ✅ Optional: Assert if the member was added (based on visible text)
    public boolean isMemberAddedSuccessfully(String email) {
        try {
            By memberEmailLocator = By.xpath("//li[contains(text(),'" + email + "')]");
            return waitForElement(memberEmailLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
