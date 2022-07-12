package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Default_RadioButton_And_Checkbox {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Verify_BackgroundColor() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)",
				driver.findElement(By.xpath("//button[@class='fhs-btn-login']")));
		sleepInSecond(3);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",
				driver.findElement(By.xpath("//button[@class='fhs-btn-login']")));
		sleepInSecond(3);
		String grbaColor = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"))
				.getCssValue("background-color");
		System.out.println(grbaColor);
		String hexaColor = Color.fromString(grbaColor).asHex().toUpperCase();
		System.out.println(hexaColor);
		Assert.assertEquals(hexaColor, "#C92127");

	}

	@Test
	public void TC_02_click_On_A_Checkbox() {

		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='kd-loader']")));
		jsExecutor.executeScript("window.scrollBy(0, 300)",
				driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		By duaZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");

		Assert.assertFalse(driver.findElement(duaZoneCheckbox).isSelected());

		driver.findElement(duaZoneCheckbox).click();
		Assert.assertTrue(driver.findElement(duaZoneCheckbox).isSelected());
		driver.findElement(duaZoneCheckbox).click();
		Assert.assertFalse(driver.findElement(duaZoneCheckbox).isSelected());

	}

	@Test
	public void TC_03_click_On_A_Radio_Button() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='kd-loader']")));

		jsExecutor.executeScript("window.scrollBy(0, 300)",
				driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
		By petrol147KwRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		driver.findElement(petrol147KwRadio).click();
		Assert.assertTrue(driver.findElement(petrol147KwRadio).isSelected());

	}

	@Test
	public void TC_04_Check_To_A_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='kd-loader']")));
		jsExecutor.executeScript("window.scrollBy(0, 300)",
				driver.findElement(By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input")));

		// By.xpath("//label[text()='Heated front and rear
		// seats']/preceding-sibling::input");

		By duaZoneCheckbox = By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input");

		checkToCheckbox(duaZoneCheckbox);
		Assert.assertTrue(driver.findElement(duaZoneCheckbox).isSelected());
	}

	@Test
	public void TC_05_Uncheck_To_A_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='kd-loader']")));
		jsExecutor.executeScript("window.scrollBy(0, 300)",
				driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input")));

		By rearCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");

		checkToCheckbox(rearCheckbox);
		Assert.assertTrue(driver.findElement(rearCheckbox).isSelected());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void checkToCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();

		}

	}

	public void uncheckToCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}

	}
}
