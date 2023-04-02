package SeleniumFrameworkDesign.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

   WebDriver driver;
   
   public LandingPage(WebDriver driver)
   {
	   super(driver);
	   this.driver = driver;
	   PageFactory.initElements(driver, this);
   }
	
	
   //WebElement userEmail = driver.findElement(By.id("userEmail"));
   //pagefactory
   
   @FindBy(id="userEmail")
   WebElement userEmail;

   @FindBy(id="userPassword")
   WebElement userPassword;
   
   @FindBy(id="login")
   WebElement submit;
   
   @FindBy(css="[class*='flyInOut']")
   WebElement errorMessage;
   
   
   public String getErrorMessage()
   {
	   waitForWebElementToAppear(errorMessage);
	   return errorMessage.getText();
   }
   
   public ProductCatalog loginApplication(String email,String Password)
   {
	   userEmail.sendKeys(email);
	   userPassword.sendKeys(Password);
	   submit.click();
	   
	   ProductCatalog productCatalog =new ProductCatalog(driver);
	   return productCatalog;
   }
   
   
   public void goTo()
   {
	   driver.get("https://rahulshettyacademy.com/client");
   
   }
}
