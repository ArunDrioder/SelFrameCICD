package org.example.Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage extends AbstractComponent
{

    WebDriver driver;

    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "userEmail")

    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement loginBtn;

    @FindBy(css ="[class*='flyInOut']")
    WebElement errorMessage;

    @FindBy(xpath = "//div[contains(text(),'*Email is required')]")
    WebElement emailValidationMessage;

    @FindBy (xpath = "//div[contains(text(),'*Password is required')]")
    WebElement passwordValidationMessage;


    public ProductsPage loginToApp(String loginEmail, String loginPassword)
    {
        userEmail.sendKeys(loginEmail);
        userPassword.sendKeys(loginPassword);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
// Wait until the button is actually clickable (not covered)
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();
        ProductsPage productsPage = new ProductsPage(driver);
        return productsPage;
    }

    public String getErrorMessage() throws InterruptedException {
        Thread.sleep(1000);

        waitFoWebElementToAppear(errorMessage);

        return errorMessage.getText();
    }

    public String getFrontendValidationMessage(String fieldType) throws InterruptedException {
        Thread.sleep(500); // Adjust sleep if necessary
        if (fieldType.equals("email")) {
            waitFoWebElementToAppear(emailValidationMessage);
            return emailValidationMessage.getText();
        } else if (fieldType.equals("password")) {
            waitFoWebElementToAppear(passwordValidationMessage);
            return passwordValidationMessage.getText();
        }
        return "";
    }

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
