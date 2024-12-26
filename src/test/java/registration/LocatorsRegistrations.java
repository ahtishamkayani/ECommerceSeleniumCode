package registration;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.github.javafaker.Faker;
public class LocatorsRegistrations {
	
	WebDriver driver;

	public String webUrl = "https://automationexercise.com/";
	public String loginPage = "https://automationexercise.com/login";
	public String AccountCreated = "https://automationexercise.com/account_created";
	public String deleteAccUrl = "https://automationexercise.com/delete_account";
	public String SignInTextVisibilty = "New User Signup!";
	public String EmptyFieldCh;
	public String randomname = "Ahtisham";
	public String Passwordtxt1 = "GVG&77pk";
	Faker faker = new Faker();
	String randomEmail = faker.internet().emailAddress();
	WebElement emailField;
	JavascriptExecutor js;
	WebElement dayDropdown,SelectCountry,yearDropdown,monthDropdown;
	WebDriverWait wait;
	Select daySelect;
	String filePath=System.getProperty("user.dir")+"\\ConfigData\\configValue.properties";
	Properties prop= new Properties();

	public LocatorsRegistrations(WebDriver driver) {
		this.driver = driver;
	}

	By Login1 = By.linkText("Signup / Login");
	By VerifyHomeText = By.linkText("Home");
	By VerifyContactUsText = By.linkText("Contact us");
	By VerifyCartText = By.linkText("Cart");
	By VerifySignupText = By.xpath("//h2[text()='New User Signup!']");
	By NameText = By.name("name");
	By SubmitBtn = By.xpath("//button[contains(@data-qa,'signup-button')]");
	By Title = By.id("id_gender1");
	By PasswordTxt = By.id("password");
	By Newsletter = By.id("newsletter");
	By OptiosnMenu = By.id("optin");
	By FirstName = By.id("first_name");
	By lastName = By.id("last_name");
	By Company = By.name("company");
	By Address = By.id("address1");
	By Address2 = By.id("address2");
	By State = By.id("state");
	By City = By.id("city");
	By ZipCode = By.id("zipcode");
	By MobilePh = By.id("mobile_number");
	By CreateAcc = By.xpath("//button[contains(@data-qa,'create-account')]");
	By CreateBtn = By.xpath("//a[contains(@data-qa,'continue-button')]");
	By DelAccount = By.linkText("Delete Account");

	public void enterServiceId() {
		driver.findElement(Login1).click();
	}
	
	public String setupCofig() throws IOException {
		// TODO Auto-generated method stub
			FileInputStream file=new FileInputStream(filePath);
			prop.load(file);
			file.close();
			return prop.getProperty("WebUrl");
	}	
	public void FillSignInForm() {
		System.out.println("In 1");
		driver.findElement(NameText).sendKeys(prop.getProperty("Name"));
		System.out.println("In 2");
		emailField = driver.findElement(By.xpath("//input[contains(@data-qa,'signup-email')]"));
		emailField.sendKeys(randomEmail);
		driver.findElement(SubmitBtn).click();
	}

	public void AssertElementsOnHomePage() {
		driver.findElement(Login1).isDisplayed();
		driver.findElement(VerifyHomeText).isDisplayed();
		driver.findElement(VerifyContactUsText).isDisplayed();
		driver.findElement(VerifyCartText).isDisplayed();
	}
 
	public void AssertElementsOnSignPage() {
		driver.findElement(Login1).isDisplayed();
		driver.findElement(VerifyHomeText).isDisplayed();
		driver.findElement(VerifyContactUsText).isDisplayed();
		driver.findElement(VerifyCartText).isDisplayed();
		driver.findElement(VerifySignupText).isDisplayed();
	}
	
	public void EnterAccountInfo() {
		driver.findElement(Title).click();
		if ((NameText != null) & (emailField != null))
			;
		driver.findElement(PasswordTxt).sendKeys(Passwordtxt1);
		if ((PasswordTxt != null))
			;
		driver.findElement(Newsletter);
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 1500);");
		driver.findElement(OptiosnMenu);
		selectDOB();
		SelectCountry = driver.findElement(By.id("country"));
		daySelect = new Select(SelectCountry);
		daySelect.selectByVisibleText("Canada");
		driver.findElement(FirstName).sendKeys(prop.getProperty("Name"));
		driver.findElement(lastName).sendKeys(prop.getProperty("LastName"));	
		driver.findElement(Company).sendKeys(prop.getProperty("CompanyText"));
		driver.findElement(Address).sendKeys(prop.getProperty("Address1"));
		driver.findElement(Address2).sendKeys(prop.getProperty("Address3"));
		js.executeScript("window.scrollBy(0, 1500);");
		driver.findElement(State).sendKeys(prop.getProperty("State1"));
		driver.findElement(City).sendKeys(prop.getProperty("Cityadd"));
		driver.findElement(ZipCode).sendKeys(prop.getProperty("Zip"));
		driver.findElement(MobilePh).sendKeys(prop.getProperty("Mobi"));
		driver.findElement(CreateAcc).click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(@data-qa,'account-created')]")));
		driver.findElement(CreateBtn).isDisplayed();
		Assert.assertEquals(driver.getCurrentUrl(), AccountCreated);
		driver.findElement(CreateBtn).click();
		Assert.assertEquals(driver.getCurrentUrl(), webUrl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Logged in as')]")));
	}

	public void selectDOB() {
		dayDropdown = driver.findElement(By.id("days"));
		monthDropdown = driver.findElement(By.id("months"));
		yearDropdown = driver.findElement(By.id("years"));

		Select daySelect = new Select(dayDropdown);
		Select monthSelect = new Select(monthDropdown);
		Select yearSelect = new Select(yearDropdown);

		daySelect.selectByVisibleText("28"); // Select day by visible text
		monthSelect.selectByVisibleText("December"); // Select month by visible text
		yearSelect.selectByVisibleText("1993"); // Select year by visible text

		if ((daySelect != null) & (monthSelect != null) & (yearSelect != null));
	}
	
	public void deleteAccountD() {
		driver.findElement(DelAccount).isDisplayed();
		driver.findElement(DelAccount).click();
		Assert.assertEquals(driver.getCurrentUrl(), deleteAccUrl);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement AccountDelText1 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Account Deleted!')]")));
		if (AccountDelText1.isDisplayed()) {
			driver.findElement(CreateBtn).click();
		}
		Assert.assertEquals(driver.getCurrentUrl(), webUrl);
	}

}
