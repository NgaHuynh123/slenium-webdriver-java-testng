package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 15);
	}

	@Test
	public void TC_01_jQuery() {

		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		driver.findElement(By.id("number-button")).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='number-menu']/li")));

		List<WebElement> allitems = driver.findElements(By.xpath("//ul[@id='number-menu']/li"));

		for (WebElement item : allitems) {
			if (item.getText().equals("15")) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView", item);
					item.click();
				}

			}

		}

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				"15");

	}

	@Test
	public void TC_02_jQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		By parent = By.id("number-button");
		By child = By.xpath("//ul[@id='number-menu']/li");
		selectItemInDropdown(parent, child, "19");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				"19");

		selectItemInDropdown(parent, child, "17");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				"17");

		selectItemInDropdown(parent, child, "10");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				"10");
	}

	@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		By parentBy = By.xpath("//i[@class='dropdown icon']");
		By childBy = By.xpath("//div[@class='ui active visible fluid selection dropdown']//span");
		selectItemInDropdown(parentBy, childBy, "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Jenny Hess");

		selectItemInDropdown(parentBy, childBy, "Matt");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Matt");
	}

	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		By parentBy = By.cssSelector("li.dropdown-toggle");
		By childBy = By.cssSelector("ul.dropdown-menu>li");
		selectItemInDropdown(parentBy, childBy, "First Option");
		Assert.assertEquals(driver
				.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).getText(),
				"First Option");
		selectItemInDropdown(parentBy, childBy, "Second Option");
		Assert.assertEquals(driver
				.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).getText(),
				"Second Option");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void selectItemInDropdown(By parentBy, By childBy, String expectedItem) {

		driver.findElement(parentBy).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		List<WebElement> allItems = driver.findElements(childBy);
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				if (item.isDisplayed()) {

					item.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView()", item);
				}
				break;
			}
			
		}

	}

}
