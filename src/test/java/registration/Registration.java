package registration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;

import org.junit.Assert;

public class Registration {
	WebDriver driver = null;
	LocatorsRegistrations locators;

	@Before
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Given("user is on registeration page")
	public void VerifyRegisterationPage() throws IOException {
		locators = new LocatorsRegistrations(driver);
		driver.navigate().to(locators.setupCofig());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		System.out.println(driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), locators.webUrl);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}
			
	@When("user enter email and password")
	public void Signup() {
		locators.enterServiceId();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		locators.AssertElementsOnHomePage();
		Assert.assertEquals(driver.getCurrentUrl(), locators.loginPage);
	}

	@And("check all fields filled")
	public void FillForm() {
		locators.AssertElementsOnSignPage();
		locators.FillSignInForm();
		locators.EnterAccountInfo();
	}

	@Then("Delete Account")
	public void deleteRegaccount() {
		locators.deleteAccountD();
	}
}