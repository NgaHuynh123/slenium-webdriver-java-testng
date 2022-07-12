package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Window_Tab {

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
	public void TC_01_Handle_Windown() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(5);

		By googleBy = By.xpath("//a[text()='GOOGLE']");
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(googleBy));
		sleepInSecond(3);

		String windowPageID = driver.getWindowHandle();
		driver.findElement(googleBy).click();
		sleepInSecond(3);
		Set<String> allwindows = driver.getWindowHandles();
		for (String window : allwindows) {

			if (!window.equals(windowPageID)) {
				driver.switchTo().window(window);
				System.out.println("title:" + driver.getTitle());
				System.out.println("ID:" + window);

				break;
			}

		}

		Assert.assertEquals(driver.getTitle().trim(), "Google");
		driver.close();
		for (String window : allwindows) {
			if (window.equals(windowPageID)) {
				driver.switchTo().window(window);
				sleepInSecond(3);
				System.out.println("title:" + driver.getTitle());
				System.out.println("ID:" + window);

				break;
			}
		}

		Assert.assertEquals(driver.getTitle().trim(), "SELENIUM WEBDRIVER FORM DEMO");

	}

	@Test
	public void TC_02_Handle_Windown_By_ID() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		String seleniumWindowID = driver.getWindowHandle();
		By googleBy = By.xpath("//a[text()='GOOGLE']");
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(googleBy));
		sleepInSecond(3);

		driver.findElement(googleBy).click();

		switchToWindowPageByID(seleniumWindowID);
		Assert.assertEquals(driver.getTitle().trim(), "Google");

		String googleWindowID = driver.getWindowHandle();
		switchToWindowPageByID(googleWindowID);
		Assert.assertEquals(driver.getTitle().trim(), "SELENIUM WEBDRIVER FORM DEMO");

	}

	@Test
	public void TC_03_Handle_Window_By_Title() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);

		By googleBy = By.xpath("//a[text()='GOOGLE']");
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)",
				driver.findElement(By.xpath("//label[text()='Opening a new window:']")));
		sleepInSecond(3);

		driver.findElement(googleBy).click();
		sleepInSecond(3);

		switchToWindowPageByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");

		switchToWindowPageByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)",
				driver.findElement(By.xpath("//label[text()='Opening a new window:']")));
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(5);

		switchToWindowPageByTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

		switchToWindowPageByTitle("SELENIUM WEBDRIVER FORM DEMO");
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)",
				driver.findElement(By.xpath("//label[text()='Opening a new window:']")));
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();

		sleepInSecond(3);

		switchToWindowPageByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		switchToWindowPageByTitle("SELENIUM WEBDRIVER FORM DEMO");
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)",
				driver.findElement(By.xpath("//label[text()='Opening a new window:']")));
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();

		sleepInSecond(3);

		switchToWindowPageByTitle("Shopping online - Buy online on Lazada.vn");
		Assert.assertEquals(driver.getTitle(), "Shopping online - Buy online on Lazada.vn");
		driver.close();
		sleepInSecond(3);
		switchToWindowPageByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

	}

	@Test
	public void TC_04() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void switchToWindowPageByID(String expectedWindowID) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String window : allWindows) {
			if (!window.equals(expectedWindowID)) {
				driver.switchTo().window(window);
				System.out.println("title:" + driver.getTitle());
				sleepInSecond(2);
				System.out.println("ID:" + window);

				break;
			}
		}

	}

	public void switchToWindowPageByTitle(String expectedTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				System.out.println("title: " + actualTitle);
				System.out.println("ID: " + window);
				break;
			}
		}
	}

	public void sleepInSecond(long timeoutSecond) {
		try {
			Thread.sleep(timeoutSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
