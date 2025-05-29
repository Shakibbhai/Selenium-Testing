

import org.example.pages.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;

public class UserRegistrationTest {
    private WebDriver driver;
    private SignInPage signInPage;
    private SignUpPage signUpPage;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(897, 1063));
        signInPage = new SignInPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testUserRegistrationAndSignIn() {

        signInPage.navigateTo()
                .clickCreateAccount()
                .enterFirstName("Shakib")
                .enterLastName("Islam")
                .enterEmail("shakibislam599211@gmail.com")
                .enterPassword("12345678")
                .enterConfirmPassword("12345678")
                .clickSignUp()
                .signOut();


        signInPage.navigateTo()
                .enterEmail("shakibislam599211@gmail.com")
                .enterPassword("12345678")
                .clickSignIn()
                .signOut();
    }


    @Test
    public void testDuplicateEmailRegistration() {
        signUpPage = signInPage.navigateTo()
                .clickCreateAccount()
                .enterFirstName("Shakib")
                .enterLastName("Islam")
                .enterEmail("shakibislam599@gmail.com") // already registered
                .enterPassword("12345678")
                .enterConfirmPassword("12345678");

        String beforeClickURL = driver.getCurrentUrl();

        signUpPage.clickSignUpExpectingFailure();

        String afterClickURL = driver.getCurrentUrl();

        Assert.assertEquals("Expected to stay on sign-up page due to duplicate email", beforeClickURL, afterClickURL);
        Assert.assertTrue("Expected email field to still contain value", !signUpPage.getEmailValue().isEmpty());
    }


    @Test
    public void testInvalidEmailRegistration() {
        signUpPage = signInPage.navigateTo()
                .clickCreateAccount()
                .enterFirstName("Shakib")
                .enterLastName("Islam")
                .enterEmail("shakibislam") // invalid format
                .enterPassword("12345678")
                .enterConfirmPassword("12345678");

        String beforeClickURL = driver.getCurrentUrl();

        signUpPage.clickSignUpExpectingFailure();

        String afterClickURL = driver.getCurrentUrl();

        Assert.assertEquals("Expected to stay on sign-up page due to invalid email format", beforeClickURL, afterClickURL);
        Assert.assertTrue("Expected email field to still contain invalid value", !signUpPage.getEmailValue().isEmpty());
    }


}
