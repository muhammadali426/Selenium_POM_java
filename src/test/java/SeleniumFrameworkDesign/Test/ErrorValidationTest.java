package SeleniumFrameworkDesign.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.PageObject.CartPage;
import SeleniumFrameworkDesign.PageObject.ProductCatalog;
import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginErrorValidationTest() throws IOException, InterruptedException {

		landingPage.loginApplication("postman123@gmail.com", "Ab123456");

		Assert.assertEquals("Incorrect email and password.", landingPage.getErrorMessage());
	}

	@Test
	public void productErrorValidationTest() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";

		ProductCatalog productCatalog = landingPage.loginApplication("postman12@gmail.com", "Ab123456");

		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
