package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert_And_Authentication {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;

	}

	@Test
	public void TC_01_Acept_Alert() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		By acceptAlertButton = By.xpath("//button[text()='Click for JS Alert']");

		jsExecutor.executeScript("arguments[0].scrollIntoView()", driver.findElement(acceptAlertButton));
		driver.findElement(acceptAlertButton).click();

		explicitWait.until(ExpectedConditions.alertIsPresent());

		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		alert.accept();
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='You clicked an alert successfully ']")).isDisplayed());

	}

	@Test
	public void TC_02_Acept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		By acceptAlertButton = By.xpath("//button[text()='Click for JS Alert']");

		jsExecutor.executeScript("arguments[0].scrollIntoView()", driver.findElement(acceptAlertButton));
		driver.findElement(acceptAlertButton).click();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		alert.accept();
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='You clicked an alert successfully ']")).isDisplayed());

	}

	@Test
	public void TC_03_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		jsExecutor.executeScript("arguments[0].scrollIntoView()",
				driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")));

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='You clicked: Cancel']")).isDisplayed());
	}

	@Test
	public void TC_04_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		jsExecutor.executeScript("arguments[0].scrollIntoView()",
				driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")));

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		String promptValue = "I know";
		alert.sendKeys(promptValue);
		alert.accept();
		timeInSecon(3);
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='You entered: " + promptValue + "']")).isDisplayed());
	}

	@Test
	public void TC_05_Authentication_Alert() {
		String user = "admin";
		String passord = "admin";
		
	    driver.get("http://" + user + ":" + passord + "@" + "the-internet.herokuapp.com/basic_auth");

		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(.,'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());

	}

	@Test
	public void TC_01() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void timeInSecon(long timeoutInsecond) {
		try {
			Thread.sleep(timeoutInsecond * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}
