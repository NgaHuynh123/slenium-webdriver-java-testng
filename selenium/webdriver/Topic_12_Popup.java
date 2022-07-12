package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Fixed_Popup() {

		driver.get("https://ngoaingu24h.vn/");

		By popupLogin = By.xpath("//div[@id='modal-login-v1']");

		Assert.assertFalse(driver.findElement(popupLogin).isDisplayed());

		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(popupLogin).isDisplayed());
		
		driver.findElement(By.xpath("//input[@id='account-input']")).sendKeys("nga@gmail.com");
		sleepInSecond(2);
		driver.findElement(By.xpath("//input[@id='password-input']")).sendKeys("111111");
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[@data-text='Đăng nhập']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Tài khoản không tồn tại!']")).isDisplayed());

	}

	@Test
	public void TC_02_Popup_In_Dom() {
		driver.get("https://blog.testproject.io/");

		By popup = By.xpath("//div[@class='mailch-wrap']");

		if (driver.findElement(popup).isDisplayed()) {
			driver.findElement(By.id("close-mailch")).click();

			Assert.assertFalse(driver.findElement(popup).isDisplayed());

		}

	}

	@Test
	public void TC_03_Popup_Not_In_Dom() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(5);
		By popupby = By.xpath("//div[@class='popup-content']");
		List<WebElement> allPopups = driver.findElements(By.xpath("//div[@class='popup-content']"));
		sleepInSecond(2);
		if (allPopups.size() > 0) {

			driver.findElement(By.xpath("//button[@class='close']")).click();
			sleepInSecond(5);
			Assert.assertEquals(driver.findElements(popupby).size(), 0);

		}

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

}
