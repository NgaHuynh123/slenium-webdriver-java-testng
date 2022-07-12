package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_JavascriptExecutor {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	 @Test
	public void TC_01_JavaScriptExecutor() {
		jsExecutor.executeScript("window.location = 'http://live.techpanda.org/'");
		sleepInSecond(5);
		String domain = (String) jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(domain, "live.techpanda.org");
		String url = (String) jsExecutor.executeScript("return document.URL");
		Assert.assertEquals(url, "http://live.techpanda.org/");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[text()='Mobile']")));
		sleepInSecond(5);
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(
				By.xpath("//a[text()='Samsung Galaxy']/parent::h2/parent::div//span[text()='Add to Cart']")));
		sleepInSecond(5);
		String innerText = (String) jsExecutor.executeScript("return document.documentElement.innerText");

		Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));
		jsExecutor.executeScript("arguments[0].click()",
				driver.findElement(By.xpath("//a[text()='Customer Service']")));
		sleepInSecond(5);
		String customerTitle = (String) jsExecutor.executeScript("return document.title");
		Assert.assertEquals(customerTitle, "Customer Service");
		jsExecutor.executeScript("arguments[0].scrollIntoView(false)",
				driver.findElement(By.cssSelector("#newsletter")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'ngakute@gmail.com')",
				driver.findElement(By.cssSelector("#newsletter")));
		sleepInSecond(5);
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//span[text()='Subscribe']")));
		sleepInSecond(5);
		String getcustomerTitle = (String) jsExecutor.executeScript("return document.documentElement.innerText");
		Assert.assertTrue(getcustomerTitle.contains("Thank you for your subscription."));
		jsExecutor.executeScript("window.location = 'http://demo.guru99.com/v4/'");
		sleepInSecond(5);
		String demogurudomain = (String) jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(demogurudomain, "demo.guru99.com");

	}

	 @Test
	public void TC_02_Verify_Validation_Message() {
		jsExecutor.executeScript("window.location = 'https://automationfc.github.io/html5/index.html'");
		sleepInSecond(3);
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//input[@name='submit-btn']")));
		sleepInSecond(3);
		String nameValidationMessage = (String) jsExecutor.executeScript(" return arguments[0].validationMessage",
				driver.findElement(By.cssSelector("#fname")));
		Assert.assertEquals(nameValidationMessage, "Please fill out this field.");
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'Nga Huynh')",
				driver.findElement(By.cssSelector("#fname")));

		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//input[@name='submit-btn']")));
		sleepInSecond(3);
		String passwordValidationMessage = (String) jsExecutor.executeScript(" return arguments[0].validationMessage",
				driver.findElement(By.cssSelector("#pass")));
		Assert.assertEquals(passwordValidationMessage, "Please fill out this field.");

		jsExecutor.executeScript("arguments[0].setAttribute('value', '123@456')",
				driver.findElement(By.cssSelector("#pass")));
		sleepInSecond(3);
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//input[@name='submit-btn']")));
		String emailValidationMessage = (String) jsExecutor.executeScript(" return arguments[0].validationMessage",
				driver.findElement(By.cssSelector("#em")));
		Assert.assertEquals(emailValidationMessage, "Please fill out this field.");
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'nga@gmail.com')",
				driver.findElement(By.cssSelector("#em")));
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//input[@name='submit-btn']")));

	}

	@Test
	public void TC_03_Remove_Attribute() {
		jsExecutor.executeScript("window.location= 'https://www.facebook.com/'");
		sleepInSecond(3);

		jsExecutor.executeScript("arguments[0].removeAttribute('placeholder')",
				driver.findElement(By.cssSelector("#email")));
		sleepInSecond(3);

	}

	@Test
	public void TC_04() {

	}

	@Test
	public void TC_05() {

	}

	@Test
	public void TC_06() {

	}

	@Test
	public void TC_07() {

	}

	@Test
	public void TC_08() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeoutSecond) {
		try {
			Thread.sleep(timeoutSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
