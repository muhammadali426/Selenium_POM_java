package SeleniumFrameworkDesign.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import SeleniumFrameworkDesign.PageObject.CartPage;
import SeleniumFrameworkDesign.PageObject.CheckOutPage;
import SeleniumFrameworkDesign.PageObject.ConfirmationPage;
import SeleniumFrameworkDesign.PageObject.OrderPage;
import SeleniumFrameworkDesign.PageObject.ProductCatalog;
import SeleniumFrameworkDesign.TestComponents.BaseTest;

public class SubmitOrderedTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitTest(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("pakistan");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String confirmMessge = confirmationPage.getConfMessage();
		Assert.assertTrue(confirmMessge.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitTest" })
	public void orderHistoryTest() {
		// ZARA COAT 3

		ProductCatalog productCatalog = landingPage.loginApplication("postman12@gmail.com", "Ab123456");
		OrderPage orderPage = productCatalog.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

	}

	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonToMap(
				System.getProperty("user.dir") +"\\src\\main\\java\\SeleniumFrameworkDesign\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	
	/*@DataProvider
	public Object[][] getData() throws IOException {
		
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
		 * "postman12@gmail.com"); map.put("password", "Ab123456"); map.put("product",
		 * "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "anshika@gmail.com"); map1.put("password", "Iamking@000");
		 * map1.put("product", "ADIDAS ORIGINAL");
		 * 
		 * return new Object[][] { { map }, { map1 } };
		 
	}*/
	
}
