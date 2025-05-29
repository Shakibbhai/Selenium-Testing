

import org.example.pages.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;

public class BoardAndCardTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(949, 1063));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCardCreationEditingAndDeletion() {
        SignInPage signInPage = new SignInPage(driver);
        DashboardPage dashboard = signInPage.navigateTo()
                .signInWithoutCredentials();

        BoardPage boardPage = dashboard.createBoard("shei");
        ListPage listPage = boardPage.createList("hei");
        CardPage cardPage = listPage.createCard("nice");

        cardPage.editCard("hh", "ff");
        cardPage.addTagAndDeleteCard();
    }
}
