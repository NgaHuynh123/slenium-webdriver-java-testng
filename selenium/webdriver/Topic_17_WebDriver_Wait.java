package webdriver;

import java.util.concurrent.TimeUnit;

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

public class Topic_17_WebDriver_Wait {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;

	}

	@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='start']/button"))));
		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));

		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());

	}

	@Test
	public void TC_02() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		jsExecutor.executeScript("window.scrollBy(0, 300)");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='25']")));
		driver.findElement(By.xpath("//a[text()='25']")).click();
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='RadAjax RadAjax_Silk']")));
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Monday, July 25, 2022']")).isDisplayed());
	}

	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
