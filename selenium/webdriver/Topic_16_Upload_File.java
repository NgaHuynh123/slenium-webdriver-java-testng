package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_File {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String cityImage = "City.jpg";
	String huongRiverImage = "Huong River.jpg";
	String riceFieldImage = "Rice Field.jpg";

	String cityPath = "D:\\02-Selenium WebDriver\\uploadFile\\City.jpg";
	String huongRiverPath = "D:\\02-Selenium WebDriver\\uploadFile\\Huong River.jpg";
	String riceFieldPath = "D:\\02-Selenium WebDriver\\uploadFile\\Rice Field.jpg";
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 20);
	}

	@Test
	public void TC_01_Upload_Single_File() {

		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(cityPath);
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(huongRiverPath);
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(riceFieldPath);

		List<WebElement> allStartButton = driver
				.findElements(By.cssSelector("table.table.table-striped button.btn.btn-primary.start"));
		for (WebElement startButton : allStartButton) {
			startButton.click();
			sleepInSecond(3);

		}

		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + cityImage + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.cssSelector("p.name>a[title='" + huongRiverImage + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + riceFieldImage + "']")).isDisplayed());

	}

	@Test
	public void TC_02_Upload_Multiple_Files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input[name='files[]']"))
				.sendKeys(cityPath + "\n" + huongRiverPath + "\n" + riceFieldPath);
		sleepInSecond(3);

		List<WebElement> allStartButton = driver.findElements(
				By.xpath("//table[@class='table table-striped']//button[@class='btn btn-primary start']"));
		for (WebElement startButton : allStartButton) {
			startButton.click();
			sleepInSecond(3);

		}

		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + cityImage + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.cssSelector("p.name>a[title='" + huongRiverImage + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + riceFieldImage + "']")).isDisplayed());

	}

	@Test
	public void TC_03_Upload_File() {
		driver.get("https://gofile.io/?t=uploadFiles");
		explicitWait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@class='animation__wobble']")));
		driver.findElement(By.xpath("//input[@type='file']"))
				.sendKeys(cityPath + "\n" + huongRiverPath + "\n" + riceFieldPath);
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#rowLoading>div.col-xs-auto")));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#rowUploadProgress")));

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.cssSelector("//i[@class='nav-icon']/i[@class='fas fa-spinner fa-spin']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//h5[contains(.,'Your files have been successfully uploaded')]")));
		Assert.assertTrue(driver.findElement(By.xpath("//h5[contains(.,'Your files have been successfully uploaded')]"))
				.isDisplayed());

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
