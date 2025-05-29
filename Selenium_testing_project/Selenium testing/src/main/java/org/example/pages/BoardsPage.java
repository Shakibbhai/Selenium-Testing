package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class BoardsPage extends BasePage {
    private final By boardsNav = By.cssSelector("#boards_nav span");
    private final By viewAllBoardsLink = By.linkText("View all boards");

    public BoardsPage(WebDriver driver) {
        super(driver);
    }

    public BoardsPage openBoardsNav() {
        WebElement nav = waitForElement(boardsNav);
        Actions actions = new Actions(driver);
        actions.moveToElement(nav).perform(); // hover
        nav.click();
        return this;
    }

    public BoardsPage clickViewAllBoards() {
        WebElement viewAll = waitForElement(viewAllBoardsLink);
        Actions actions = new Actions(driver);
        actions.moveToElement(viewAll).perform();
        viewAll.click();
        return this;
    }
}
