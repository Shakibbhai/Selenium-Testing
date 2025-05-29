package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class CardPage extends BasePage {
    private final By cardContent = By.cssSelector(".card-content");
    private final By editLink = By.linkText("Edit");
    private final By titleField = By.cssSelector("textarea:nth-child(2)");
    private final By saveTitleButton = By.cssSelector("button:nth-child(3)");
    private final By descriptionField = By.cssSelector("textarea");
    private final By saveDescriptionButton = By.cssSelector("button");
    private final By tagsLink = By.linkText("Tags");
    private final By yellowTag = By.cssSelector(".yellow");
    private final By deleteIcon = By.cssSelector(".fa-trash-o");

    public CardPage(WebDriver driver) {
        super(driver);
    }

    public void editCard(String title, String description) {
        waitForElement(cardContent).click();
        waitForElement(editLink).click();
        WebElement titleElem = waitForElement(titleField);
        titleElem.clear();
        titleElem.sendKeys(title);
        waitForElement(saveTitleButton).click();
        waitForElement(descriptionField).sendKeys(description);
        waitForElement(saveDescriptionButton).click();
    }

    public void addTagAndDeleteCard() {
        waitForElement(tagsLink).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(waitForElement(tagsLink)).perform();
        actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();
        waitForElement(yellowTag).click();
        waitForElement(deleteIcon).click();
    }
}
