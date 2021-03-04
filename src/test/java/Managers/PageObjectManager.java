package Managers;

import Page.SalesforcePage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private WebDriver driver;
    private SalesforcePage salesforcePage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public SalesforcePage getSalesforcePage(){
        return (salesforcePage==null) ? salesforcePage= new SalesforcePage(driver) : salesforcePage;
    }
}
