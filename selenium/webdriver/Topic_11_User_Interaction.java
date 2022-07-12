

package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Hover_To_Element_Textbox() {

		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC_02_Hover_To_Element_Menu() {
		driver.get("https://shopee.vn/");
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Tiếng Việt']"))).perform();
		driver.findElement(By.xpath("//span[text()='English']")).click();
	}

	@Test
	public void TC_03_Click_And_Hold_Element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumberts = driver.findElements(By.xpath("//ol/li"));

		action.clickAndHold(allNumberts.get(0)).moveToElement(allNumberts.get(3)).release().perform();
		Assert.assertEquals(
				driver.findElements(By.xpath("//ol/li[@class='ui-state-default ui-selectee ui-selected']")).size(), 4);

	}

	@Test
	public void TC_04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		By doubleClickMe = By.xpath("//button[@ondblclick='doubleClickMe()']");
		jsExecutor.executeScript("arguments[0].scrollIntoView()", driver.findElement(doubleClickMe));
		action.doubleClick(driver.findElement(doubleClickMe)).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
