package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css_I {
	WebDriver driver;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("http://live.techpanda.org/");

	}

	@Test
	public void TC_01_Login_With_Empty_Email_And_Password() {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.id("send2")).click();

		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");

		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
				"This is a required field.");
	}

	@Test
	public void TC_02_Login_With_InValid_Email() {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("123@123");

		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC_03_Login_With_Password_Less_Than_6_Characters() {

		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");

		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_Login_With_Invalid_Email_And_Password() {

		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");

		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),
				"Invalid login or password.");

	}

	@Test
	public void TC_05_Login_With_Valid_Email_Password() {

		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("hongnga.qn2810@gmail.com");

		driver.findElement(By.id("pass")).sendKeys("123123");
		driver.findElement(By.id("send2")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, hh hh hh!']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='box-account box-info']//p[contains(text(),'hh hh hh')]"))
						.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='box-content']/p[contains(., \"hongnga.qn2810@gmail.com\")]"))
						.isDisplayed());
	}

	@AfterClass
	public void afterClass() {

		driver.quit();

	}

}
