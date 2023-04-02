package SeleniumFrameworkDesign.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFrameworkDesign.PageObject.CartPage;
import SeleniumFrameworkDesign.PageObject.CheckOutPage;
import SeleniumFrameworkDesign.PageObject.ConfirmationPage;
import SeleniumFrameworkDesign.PageObject.LandingPage;
import SeleniumFrameworkDesign.PageObject.ProductCatalog;
import SeleniumFrameworkDesign.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String name, String password) {
		// Write code here that turns the phrase above into concrete actions
		productCatalog = landingPage.loginApplication(name, password);

	}

	@When("^I add product (.+) from cart$")
	public void i_add_product_from_cart(String productName) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		// Write code here that turns the phrase above into concrete actions
		CartPage cartPage = productCatalog.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("pakistan");
		confirmationPage = checkOutPage.submitOrder();
	}

	@Then("{string} is display in ConfirmationPage")
	public void is_display_in_confirmation_page(String message) {
		// Write code here that turns the phrase above into concrete actions
		String confirmMessge = confirmationPage.getConfMessage();
		Assert.assertTrue(confirmMessge.equalsIgnoreCase(message));
		driver.close();
	}



	@Then("{string} message is display")
	public void  message_is_display(String message) {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(message, landingPage.getErrorMessage());
		driver.close();

	}


}
