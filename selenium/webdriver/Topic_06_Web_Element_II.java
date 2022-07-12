package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_II {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, middleName, lastName, emailAddress, password, confirmPassword, fullName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
	firstName = "Nga";
	middleName = "Hong";
	lastName = "Huynh";
	emailAddress = "hn.qn" + getRandomNumber() + "@gmail.com";
	password = "123123";
	confirmPassword = "123123";
	fullName = firstName + " " + middleName + " " + lastName;
	}
	@Test
	public void TC_01_Validate_SignUp() {

driver.get("https://login.mailchimp.com/signup/");
		
		
WebElement emailTextbox = driver.findElement(By.id("email"));
WebElement userNameTextbox = driver.findElement(By.id("new_username"));
WebElement password = driver.findElement(By.id("new_password"));


emailTextbox.sendKeys("hongnga.qn93@gmail.com");
userNameTextbox.clear();
userNameTextbox.sendKeys("NgaHuynh");
		
password.sendKeys("nga");
Assert.assertEquals(driver.findElement(By.xpath("//span[text()='One lowercase character']/parent::li")).getAttribute("class"), "lowercase-char completed");

password.clear();
password.sendKeys("NGA");
	Assert.assertEquals(driver.findElement(By.xpath("//span[text()='One uppercase character']/parent::li")).getAttribute("class"), "uppercase-char completed");
	
	password.clear();
	password.sendKeys("123");
	
	Assert.assertEquals(driver.findElement(By.xpath("//span[text()='One number']/parent::li")).getAttribute("class"), "number-char completed");

	password.clear();
	password.sendKeys("@");
	
	Assert.assertEquals(driver.findElement(By.xpath("//span[text()='One special character']/parent::li")).getAttribute("class"), "special-char completed");
	
	
	password.clear();
	password.sendKeys("111111111");
	
	Assert.assertEquals(driver.findElement(By.xpath("//span[text()='8 characters minimum']/parent::li")).getAttribute("class"), "8-char completed");
	Assert.assertEquals(driver.findElement(By.xpath("//span[text()='One number']/parent::li")).getAttribute("class"), "number-char completed");

	password.clear();
	password.sendKeys("Nga@123455555");	
		
		Assert.assertFalse(driver.findElement(By.className("line password-requirements signup always-open show-errors")).isDisplayed());
		WebElement confirmCheckbox = driver.findElement(By.id("marketing_newsletter"));
		Assert.assertFalse(confirmCheckbox.isSelected());
		confirmCheckbox.click();
		Assert.assertTrue(confirmCheckbox.isSelected());
		driver.findElement(By.id("create-account-enabled")).click();
	}
	
	@Test
	public void TC_05_Create_A_New_Account() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("middlename")).sendKeys(middleName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(confirmPassword);
		driver.findElement(By.xpath("//span[text()='Register']")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']"))
						.isDisplayed());
		String getFullName = driver.findElement(By.xpath("//h3[text()='Contact Information']//"
				+ "parent::div//following-sibling::div//p")).getText();
		Assert.assertTrue(getFullName.contains(fullName));
		String getEmail = driver.findElement(By.xpath("//h3[text()='Contact Information']//"
				+ "parent::div//following-sibling::div//p")).getText();
		Assert.assertTrue(getEmail.contains(emailAddress));
		//cach 2
		//Assert.assertTrue(driver.findElement(By.xpath("//p[contains(.,'"+fullName+"')]")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[ contains(., '"+ emailAddress+"')]")).isDisplayed());
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

	}

	public int getRandomNumber() {

		Random rand = new Random();
		return rand.nextInt(999999);

	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
