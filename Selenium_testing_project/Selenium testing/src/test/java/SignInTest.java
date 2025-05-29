import org.example.pages.SignInPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;

public class SignInTest {
    private WebDriver driver;
    private SignInPage signInPage;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(897, 1063));
        signInPage = new SignInPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBasicSignIn() {
        signInPage.navigateTo()
                .signInWithoutCredentials()
                .signOut();
    }

    @Test
    public void testSignInWithCredentials() {
        signInPage.navigateTo();

        Assert.assertTrue("Email field should be visible", signInPage.isEmailFieldPresent());
        Assert.assertTrue("Password field should be visible", signInPage.isPasswordFieldPresent());

        signInPage.enterEmail("shakibislam599@gmail.com")
                .enterPassword("12345678")
                .clickSignIn()
                .signOut();
    }

    @Test
    public void testSignInWithOnlyEmail() {
        signInPage.navigateTo();

        Assert.assertTrue("Email field should be visible", signInPage.isEmailFieldPresent());
        Assert.assertTrue("Password field should be visible", signInPage.isPasswordFieldPresent());

        signInPage.enterPassword("");
        signInPage.enterEmail("shakibislam1599@gmail.com");

        String beforeClickURL = driver.getCurrentUrl();
        signInPage.clickSignInSuppressRedirect();
        String afterClickURL = driver.getCurrentUrl();

        Assert.assertEquals("Expected to stay on the same page due to form validation.", beforeClickURL, afterClickURL);
        Assert.assertTrue("Expected password field to remain empty after click.", signInPage.getPasswordValue().isEmpty());
    }

    @Test
    public void testSignInWithOnlyPassword() {
        signInPage.navigateTo();

        Assert.assertTrue("Email field should be visible", signInPage.isEmailFieldPresent());
        Assert.assertTrue("Password field should be visible", signInPage.isPasswordFieldPresent());

        signInPage.enterEmail("");
        signInPage.enterPassword("12345678");

        String beforeClickURL = driver.getCurrentUrl();
        signInPage.clickSignInSuppressRedirect();
        String afterClickURL = driver.getCurrentUrl();

        Assert.assertEquals("Expected to stay on the same page due to form validation.", beforeClickURL, afterClickURL);
        Assert.assertTrue("Expected email field to remain empty after click.", signInPage.getEmailValue().isEmpty());
    }

    @Test
    public void testSignInWithEmptyFields() {
        signInPage.navigateTo();

        Assert.assertTrue("Email field should be visible", signInPage.isEmailFieldPresent());
        Assert.assertTrue("Password field should be visible", signInPage.isPasswordFieldPresent());

        signInPage.enterEmail("");
        signInPage.enterPassword("");

        String beforeClickURL = driver.getCurrentUrl();
        signInPage.clickSignInSuppressRedirect();
        String afterClickURL = driver.getCurrentUrl();

        Assert.assertEquals("Expected to stay on the same page due to empty fields.", beforeClickURL, afterClickURL);
        Assert.assertTrue("Expected email field to remain empty.", signInPage.getEmailValue().isEmpty());
        Assert.assertTrue("Expected password field to remain empty.", signInPage.getPasswordValue().isEmpty());
    }
}
