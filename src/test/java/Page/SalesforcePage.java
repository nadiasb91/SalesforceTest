package Page;

import io.cucumber.java.eo.Se;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class SalesforcePage {
    WebDriver driver;


    public SalesforcePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    WebElement userInput;
    @FindBy(id = "password")
    WebElement passwordInput;
    @FindBy(id = "Login")
    WebElement loginButton;
    @FindBy(xpath = "//a[@class=\"menuTriggerLink slds-button slds-button_icon slds-button_icon slds-button_icon-container slds-button_icon-small slds-global-actions__setup slds-global-actions__item-action\"]")
    WebElement setUpMenu;
    @FindBy(id = "all_setup_home")
    WebElement setupOption;
    @FindBy(xpath = "//a[@class=\"slds-button slds-button--icon\"]")
    WebElement objectManagerMenu;
    @FindBy(xpath = "//a[@title=\"New Object\"]")
    WebElement newObjectOption;
    @FindBy(id = "MasterLabel")
    WebElement objectLabelInput;
    @FindBy(id = "PluralLabel")
    WebElement pluralObjectNameInput;
    @FindBy(id = "AutoNo")
    WebElement dataTypeObject;
    @FindBy(id = "CreateTab")
    WebElement checkTabForObject;
    @FindBy(xpath = "//td[@id=\"bottomButtonRow\"]/input[@name=\"save\"]")
    WebElement saveButton;
    @FindBy(xpath = "//iframe[@title=\"New Custom Object ~ Salesforce - Enterprise Edition\"]")
    WebElement newObjectFrame;
    @FindBy(id = "NameAutoNumberMask")
    WebElement autoNumberInput;
    @FindBy(id = "StartingNo")
    WebElement startNumber;
    @FindBy(name = "goNext")
    WebElement goNext;
    @FindBy(xpath = "//*[@id=\"p2\"]/a[2]")
    WebElement tabStyles;
    @FindBy(id = "Custom38")
    WebElement styleCameraBlue;


    public void navigateToUrl(String url) {
        driver.navigate().to(url);
    }

    public void login(String user, String pass) {
        userInput.sendKeys(user);
        passwordInput.sendKeys(pass);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickSetUpMenu() {
        try {
            setUpMenu.click();
            try {
                setupOption.findElement(By.tagName("a")).click();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

    }

    private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Integer timeoutInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
        webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
        try {
            webDriverWait.until(waitCondition);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void untilJqueryIsDone(WebDriver driver, Integer timeoutInSeconds) {
        until(driver, (d) ->
        {
            Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
            if (!isJqueryCallDone) System.out.println("JQuery call is in Progress");
            return isJqueryCallDone;
        }, timeoutInSeconds);
    }

    public void untilPageLoadComplete(WebDriver driver, Integer timeoutInSeconds) {
        until(driver, (d) ->
        {
            Boolean isPageLoaded = (Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            if (!isPageLoaded) System.out.println("Document is loading");
            return isPageLoaded;
        }, timeoutInSeconds);
    }

    public void untilTitleIs(String title) {
        FluentWait wait = new FluentWait(driver).
                withTimeout(Duration.ofSeconds(20)).
                pollingEvery(Duration.ofSeconds(2));
        wait.until(ExpectedConditions.titleIs(title));
    }

    public void untilClickable(WebElement e) {
        FluentWait wait = new FluentWait(driver).
                withTimeout(Duration.ofSeconds(20)).
                pollingEvery(Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void switchTab() {
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        String setupTab = tab.get(1);
        driver.switchTo().window(setupTab);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void createNewCustomObject() {
        try {
            objectManagerMenu.click();
            try {
                newObjectOption.click();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void fillObjectForm(String objectName, String dataType) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        untilClickable(newObjectFrame);
        driver.switchTo().frame(newObjectFrame);
        objectLabelInput.sendKeys(objectName);
        pluralObjectNameInput.sendKeys(objectName + "s");
        Select objectDataType = new Select(dataTypeObject);
        objectDataType.selectByVisibleText(dataType);
        autoNumberInput.sendKeys(objectName.substring(0,1)+"{0000}");
        startNumber.sendKeys("0");
        checkTabForObject.click();
        saveButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        tabStyles.click();
        driver.switchTo().activeElement();
        styleCameraBlue.click();
        goNext.click();
    }

    public void clickSaveButton() {

    }

    public  void styleTab(){
        driver.switchTo().frame(newObjectFrame);

    }

}
