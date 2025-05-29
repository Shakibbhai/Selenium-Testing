package org.example.pages;

import org.openqa.selenium.*;

public class BoardPage extends BasePage {
    private final By addListButton = By.cssSelector(".inner");
    private final By listNameField = By.id("list_name");
    private final By createListButton = By.cssSelector("button");

    public BoardPage(WebDriver driver) {
        super(driver);
    }

    public ListPage createList(String listName) {
        waitForElement(addListButton).click();
        waitForElement(listNameField).sendKeys(listName);
        waitForElement(createListButton).click();
        return new ListPage(driver);
    }
}
