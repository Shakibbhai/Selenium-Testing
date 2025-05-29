package org.example.pages;

import org.openqa.selenium.*;

public class DashboardPage extends BasePage {
    private final By addBoardButton = By.cssSelector(".add-new > .inner");
    private final By boardNameField = By.id("board_name");
    private final By createBoardButton = By.cssSelector("button");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    private final By signOutButton = By.cssSelector("#crawler-sign-out > span");


    public SignInPage signOut() {
        waitForElement(signOutButton).click();
        return new SignInPage(driver);
    }

    public BoardPage createBoard(String name) {
        waitForElement(addBoardButton).click();
        waitForElement(boardNameField).sendKeys(name);
        waitForElement(createBoardButton).click();
        return new BoardPage(driver);
    }

    public BoardDetailsPage openBoardById(String boardCssId) {
        driver.findElement(By.cssSelector(boardCssId + " h4")).click();
        return new BoardDetailsPage(driver);
    }




}
