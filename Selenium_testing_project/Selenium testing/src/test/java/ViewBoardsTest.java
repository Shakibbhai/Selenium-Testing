import org.example.pages.SignInPage;
import org.example.pages.BoardsPage;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ViewBoardsTest {
    private WebDriver driver;
    private SignInPage signInPage;
    private BoardsPage boardsPage;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1053, 1064));
        signInPage = new SignInPage(driver);
        boardsPage = new BoardsPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testViewBoardsNavigationHandlesEmptyState() {


        signInPage.navigateTo()
                .enterEmail("shakibislam599@gmail.com")
                .enterPassword("12345678")
                .clickSignIn();


        boardsPage.openBoardsNav()
                .clickViewAllBoards();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        By boardListContainer = By.cssSelector(".boards-wrapper");
        By emptyMessage = By.xpath("//*[contains(text(), 'no boards') or contains(text(), 'No boards')]");


        boolean boardListVisible = false;
        boolean emptyTextVisible = false;

        try {
            boardListVisible = wait.until(ExpectedConditions.presenceOfElementLocated(boardListContainer)).isDisplayed();
        } catch (Exception e) {
            System.out.println("Board list not found — possibly empty.");
        }

        if (!boardListVisible) {
            try {
                emptyTextVisible = wait.until(ExpectedConditions.presenceOfElementLocated(emptyMessage)).isDisplayed();
            } catch (Exception e) {
                System.out.println("No empty state message found either.");
            }
        }

        Assert.assertTrue("Expected either board list or empty message to be visible.",
                boardListVisible || emptyTextVisible);
    }
}
