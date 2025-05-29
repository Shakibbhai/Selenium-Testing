package org.example.pages;

import org.openqa.selenium.*;

public class ListPage extends BasePage {
    private final By addCardLink = By.linkText("Add a new card...");
    private final By cardNameField = By.id("card_name");
    private final By addCardButton = By.cssSelector("button");

    public ListPage(WebDriver driver) {
        super(driver);
    }

    public CardPage createCard(String cardName) {
        waitForElement(addCardLink).click();
        waitForElement(cardNameField).sendKeys(cardName);
        waitForElement(addCardButton).click();
        return new CardPage(driver);
    }
}
