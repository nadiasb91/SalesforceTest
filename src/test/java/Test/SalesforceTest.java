package Test;

import Page.SalesforcePage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class SalesforceTest {
    WebDriver driver;
    SalesforcePage page;
    Properties properties;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        page = new SalesforcePage(driver);
        driver.get("https://nadiasanchezuy-trainingorg--qa.my.salesforce.com/?ec=302&startURL=%2Fvisualforce%2Fsession%3Furl%3Dhttps%253A%252F%252Fnadiasanchezuy-trainingorg--qa.lightning.force.com%252Flightning%252Fpage%252Fhome");

    }

    @Given("user is on salesforce page")
    public void user_is_on_salesforce_page() {
        try {
            Assert.assertEquals(driver.getTitle(), "Login | Salesforce");
        } catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    @And("user enter user and password")
    public void use_enter_user_and_password() {
        page.login("nadia.sanchez@oktana.io.qa", "Tradition123");
    }

    @When("user submits")
    public void user_submits() {
        page.clickLoginButton();
    }

    @Then("homepage is be display")
    public void homepage_is_be_display() {
        page.untilTitleIs("Home | Salesforce");
        try {
            Assert.assertEquals(driver.getTitle(), "Home | Salesforce");
        } catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    @And("user click setup")
    public void user_click_setup() {
        page.untilTitleIs("Home | Salesforce");
        page.clickSetUpMenu();
    }

    @And("click custom object")
    public void click_custom_object() {
        page.switchTab();
        page.createNewCustomObject();
    }

    @When("user fill the form data")
    public void user_fill_the_form_data(){
        page.fillObjectForm("Coffee","Auto Number");



    }


    @Then("a custom object is created")
    public void a_custom_object_is_created(){
        page.untilPageLoadComplete(driver,16);
        Assert.assertEquals(driver.getTitle(),"Coffee | Salesforce");
    }


}
