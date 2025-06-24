package com.org.happyfox;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


/*
Overall Improvements required:
1. Project Structure and Organization.
2. Locators and Element handling.
3. Synchronisation and Waits.
4. Test Data and Security.
5. Assertions and Validations.
6. Framework Usage.
7. Code Quality.

This class does too many things (MONOLITHIC SCRIPT)
Should be broken into smaller classes for maintainability (PAGE OBJECT MODEL)
Class should Maintain PascalCase and Should have meaningful name.
 */

public class Testcase101 {

    public static void main(String[] args) throws InterruptedException, AWTException {
		/*
		 Problem: The hardcoded path for driver.exe won't work on other machines.
		 Solution: Use WebDriverManager
		 */
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Johny\\Downloads\\geckodriver-v0.33.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver(); // Could use the Factory pattern to leverage multi-browser testing.
        driver.get("https://interview.supporthive.com/staff/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // Also needs explicit wait for specific elements
        driver.manage().window().maximize();
        /*
          Problem: Hardcoded login credentials
          Solution: They should come from environment/config
         */
        driver.findElement(By.id("id_username")).sendKeys("Agent");
        driver.findElement(By.id("id_password")).sendKeys("Agent@123");
        driver.findElement(By.id("btn-submit")).click(); // Assertion should be added here to check the on click behaviour
        // Should be wrapped in some Navigation/Functional class - Here it is haphazard.
        WebElement tickets = driver.findElement(By.id("ember29"));
        Actions action = new Actions(driver);
        action.moveToElement(tickets).build().perform();
        WebElement statuses = driver.findElement(By.linkText("Statuses"));
        statuses.click();
        /*Problem: Absolute XPath is fragile. This will break the UI if there are path changes.
          Solution: Prefer relative XPath/ Dynamic XPaths
         */
        driver.findElement(By.xpath("/html/body/div[3]/div/section/section/div/header/button")).click();
        driver.findElement(By.tagName("input")).sendKeys("Issue Created");
        WebElement statusColourSelect = driver.findElement(By.xpath("//div[@class='sp-replacer sp-light']"));
        statusColourSelect.click();

        WebElement statusColourEnter = driver.findElement(By.xpath("//input[@class='sp-input']"));
        statusColourEnter.clear();
        statusColourEnter.sendKeys("#47963f");
        /*
        Problem: Robot Library is platform dependent library and may lead to flaky test cases.
        Solution: We should use WebDriver
         */
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ESCAPE);

         /*
        Problem: The id used for locating element is not descriptive and might lead to maintenance issues.
        Solution: prefer proper names like "successStatusLink"
         */
        WebElement firstElement = driver.findElement(By.xpath("//a[@id='first-link']"));
        firstElement.click();
        WebElement secondElement = driver.findElement(By.xpath("//a[@id='second-link']"));
        secondElement.click();

        /*
        Problem: At multiple places we are directly sending keys without validation
        Solution: Add a check for ElementVisibility/ElementNotFound/ElementNotInteractable Exceptions.
         */
        driver.findElement(By.tagName("textarea")).sendKeys("Status when a new ticket is created in HappyFox");
        WebElement addCreate = driver.findElement(By.xpath("//button[@class ='hf-entity-footer_primary hf-primary-action ember-view']"));
        addCreate.click();

           /*
        Problem: Thread.sleep is used multiple time across files which will lead to flakiness
        Solution: Either wrap it in try-catch block or Use WebDriverWait for stable test execution
         */
        Thread.sleep(3000);
        //Could use css locators or axes locator strategy as well instead of having fragile locators
        WebElement moveTo = driver.findElement(By.xpath("//td[@class ='lt-cell align-center hf-mod-no-padding ember-view']"));
        action.moveToElement(moveTo).build().perform();
        moveTo.click();

        Thread.sleep(9000);
        // can select multiple divs with same text
        WebElement issue = driver.findElement(By.xpath("//div[contains(text(),'Issue Created')]"));
        action.moveToElement(issue).build().perform();   //adds unnecessary complexity and can slow down your tests.


        WebElement make = driver.findElement(By.linkText("Make Default"));
        make.click();
        driver.findElement(By.linkText("Priorities")).click();
        driver.findElement(By.xpath("//header/button[1]")).click();
        driver.findElement(By.tagName("input")).sendKeys("Assistance required");
        driver.findElement(By.tagName("textarea")).sendKeys("Priority of the newly created tickets");
        WebElement button = driver.findElement(By.cssSelector("button[data-test-id='add-priority']"));
        button.click();

        Thread.sleep(9000);

        WebElement tickets2 = driver.findElement(By.id("ember29"));
        action.moveToElement(tickets2).build().perform();
        WebElement priorities2 = driver.findElement(By.linkText("Priorities"));
        priorities2.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
// Absolute XPath – risky and unreadable. Prefer meaningful locators wherever the absolute XPaths are used.
        driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[9]/td[2]")).click();
        driver.findElement(By.linkText("Delete")).click();
        WebElement delete = driver.findElement(By.cssSelector("button[data-test-id='delete-dependants-primary-action']"));
        delete.click();

        Thread.sleep(9000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/header[1]/div[2]/nav[1]/div[7]/div[1]/div[1]")).click();
        driver.findElement(By.linkText("Logout")).click();
// Closing Parenthesis Missing here.

// Entire POM classes are nested inside a test class. They should be separate .java files for better reuse and readability.
        public class PagesforAutomationAssignment {

            public static void main(String[] args) {
                ChromeDriver driver = new ChromeDriver();
                driver.get("https://www.happyfox.com");

                LoginPage loginPage = new LoginPage(driver);
                loginPage.login("username", "password");

                HomePage homePage = new HomePage(driver);
                homePage.verifyHomePage();

                driver.quit();
            }

            //This should be in its own BasePage.java file
            static class BasePage {
                protected final WebDriver driver;

                public BasePage(WebDriver driver) {
                    this.driver = driver;
                }
            }

            static class LoginPage extends BasePage {
                public LoginPage(WebDriver driver) {
                    super(driver);
                }

                public void login(String username, String password) {

                    driver.findElement(By.id("username")).sendKeys(username);
                    driver.findElement(By.id("password")).sendKeys(password);
                    driver.findElement(By.id("loginButton")).click();
                }

                public void forgotPassword() {
                    driver.findElement(By.linkText("Forgot password?")).click();
                }
            }

            //This should be in its own HomePage.java file
            static class HomePage extends BasePage {
                public HomePage(WebDriver driver) {
                    super(driver);
                }

                public void verifyHomePage() {
                    if (!driver.getCurrentUrl().equals("https://www.happyfox.com/home")) {
                        throw new IllegalStateException("Not on the home page");
                    }
                }

                public void navigateToProfile() {
                    driver.findElement(By.id("profileLink")).click();
                }

                //This should be in its own TablePage.java file
                public class TablePage extends BasePage {

                    private final By rowLocator = By.xpath("//table[@id='dataTable']/tbody/tr");

                    public TablePage(WebDriver driver) {
                        super(driver);
                    }

                    public void retrieveRowTexts() {
                        List<WebElement> rows = driver.findElements(rowLocator);

                        for (int i = 0; i < rows.size(); i++) {
                            WebElement row = rows.get(i);
                            String rowText = row.getText();
                            System.out.println("Row " + i + " Text: " + rowText);
                        }
                    }

                }
            }
