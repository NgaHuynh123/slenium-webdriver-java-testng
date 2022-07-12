package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown_I {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
Select select;
Random rand;
JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor)driver;
	}

	@Test
	public void TC_01_Register_Nopcommerce() {

		driver.get("https://demo.nopcommerce.com/register");
		
		String firstName = "Nga";
		String lastName = "Huynh";
		String dateOfBirthDay = "15";
		
		String dateOfBirthMonth = "November";
		String dateOfBirthYear = "1940";
		String email = "hn.qn" + getRandoNumber() + "@gmail.com";
		String companyName = "KMS";
		String password = "123456789";
		String confirmPassword = "123456789";	
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		
		select.selectByVisibleText(dateOfBirthDay);
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthDay);

		Assert.assertFalse(select.isMultiple());
		Assert.assertEquals(select.getOptions().size(),32);
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(dateOfBirthMonth);
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthMonth);
		Assert.assertFalse(select.isMultiple());
		Assert.assertEquals(select.getOptions().size(), 13);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(dateOfBirthYear);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthYear);
		Assert.assertFalse(select.isMultiple());
		Assert.assertEquals(select.getOptions().size(), 112);
		
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmPassword);
		driver.findElement(By.id("register-button")).click();
	}
	@Test
	public void TC_02_Multiple() {

		
		
		
		
		
		
		
		
		
		
		
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandoNumber() {
		rand = new Random();
		return rand.nextInt(9999);
		
		
		
	}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	public void scrollIntoView() {
		
		jsExecutor.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//input[@name='Company']")));
	}
}
