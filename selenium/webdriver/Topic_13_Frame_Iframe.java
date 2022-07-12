package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Frame_Iframe {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		

		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage ']//iframe")));

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='_1drq']")).getText(), "166K likes");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
		driver.findElement(By.cssSelector("div.border_overlay ")).click();
		sleepInSecond(3);
		Assert.assertTrue(
				driver.findElement(By.cssSelector("input.submit.meshim_widget_widgets_ConnAwareSubmit")).isDisplayed());
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		driver.switchTo().frame(driver.findElement(By.name("login_page")));

		driver.findElement(By.name("fldLoginUserId")).sendKeys("11111111");
		driver.findElement(By.xpath("//a[@class='btn btn-primary login-btn']")).click();
		Assert.assertTrue(driver.findElement(By.name("fldPassword")).isDisplayed());

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
