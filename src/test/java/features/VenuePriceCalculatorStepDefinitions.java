package features;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.MainPage;
import runners.DriverSetup;

import java.net.MalformedURLException;

public class VenuePriceCalculatorStepDefinitions {

	private MainPage mainPage;

	@Before
	public void setup() throws MalformedURLException {
		AppiumDriver driver = DriverSetup.initializeDriver();
		mainPage = new MainPage(driver);
	}

	@After
	public void tearDown() {
		DriverSetup.quitDriver();
	}

	@Given("^Jim has opened the venue application$")
	public void jimHasOpenedTheVenueApplication() {
		Assertions.assertEquals(mainPage.getTitle(), "Venue Calculator");
	}

	@When("^Jim clicks on the venue picker$")
	public void jimClicksOnTheVenuePicker() {
		mainPage.clickVenueDropDown();
	}

	@Then("^the (.*) option should be present$")
	public void theCityHallOptionShouldBePresent(String venue) {
		Assertions.assertTrue(mainPage.getVenueItemsLabels().contains(venue));
	}

	@When("Jim enters {int} for the number of guests")
	public void jimEntersForTheNumberOfGuests(int arg0) {
		mainPage.fillNumberOfGuests(arg0);
	}

	@And("clicks on the calculate button")
	public void clicksOnTheCalculateButton() {
		mainPage.clickCalculate();
	}

	@Then("the result {string} is displayed")
	public void theResultIsDisplayed(String arg0) {
		Assertions.assertTrue(mainPage.getMessage().startsWith(arg0));
	}

	@And("selects <venue> option for the Venue")
	public void selectsVenueOptionForTheVenue(String venue) {
		mainPage.selectVenueOption(venue);
	}

	@And("selects {string} option for the Service level")
	public void selectsLevelOfServiceOptionForTheServiceLevel(String levelOfService) {
		if (levelOfService.equalsIgnoreCase("Basic")) {
			mainPage.selectBasicLevelOfService();
		} else if (levelOfService.equalsIgnoreCase("Premium")) {
			mainPage.selectPremiumLevelOfService();
		} else {
			throw new IllegalArgumentException("Invalid service level: " + levelOfService);
		}
	}

	@Then("the validation message {string} is displayed")
	public void theValidationMessageIsDisplayed(String arg0) {
		Assertions.assertEquals(mainPage.getMessage(), arg0);
	}


	@And("^the (.*) option is selected$")
	public void theVenueOptionIsSelected(String arg0) {
		mainPage.selectVenueOption(arg0);
	}
}
