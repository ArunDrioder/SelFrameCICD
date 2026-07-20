package org.example.Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends AbstractComponent
{
    WebDriver driver;


    @FindBy(css = ".totalRow Button")
    WebElement checkOutBtn;

    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartProductTitle;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    public Boolean verifyProductName (String productName)
    {
        Boolean match =  cartProductTitle.stream().anyMatch(nameOfTheProduct->nameOfTheProduct.getText().equalsIgnoreCase(productName));
        return match;

    }

    public CheckoutPage checkOut()
    {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
// Wait until the button is actually clickable (not covered)
        wait.until(ExpectedConditions.elementToBeClickable(checkOutBtn));
        checkOutBtn.click();

        return new CheckoutPage(driver);
    }


}
