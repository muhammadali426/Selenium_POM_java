package SeleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesign.PageObject.CartPage;
import SeleniumFrameworkDesign.PageObject.OrderPage;

public class AbstractComponent {

	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartPage =  new CartPage(driver);
		return cartPage;
	}
	

	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage orderPage =  new OrderPage(driver);
		return orderPage;
	}
	
	public void waitForElementToAppear(By findBy)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		   	
	}
	
	public void waitForWebElementToAppear(WebElement ele)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.visibilityOf(ele));
		   	
	}

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		 Thread.sleep(1000);
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		// wait.until(ExpectedConditions.invisibilityOf(ele));
  	
	}

}
