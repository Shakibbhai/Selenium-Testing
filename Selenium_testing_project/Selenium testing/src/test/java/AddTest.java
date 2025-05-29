

import org.example.pages.SignInPage;
import org.example.pages.BoardDetailsPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;

public class AddTest {
    private WebDriver driver;
    private SignInPage signInPage;
    private BoardDetailsPage boardDetailsPage;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(949, 1063));
        signInPage = new SignInPage(driver);
        boardDetailsPage = new BoardDetailsPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testAddMemberToBoard() {
        signInPage.navigateTo()
                .signInWithoutCredentials(); // assumes login without creds is allowed

        boardDetailsPage
                .openBoardByTitle("ji")
                .openAddMemberForm()
                .inviteMemberByEmail("shakibislam599@gmail.com");

    }

}