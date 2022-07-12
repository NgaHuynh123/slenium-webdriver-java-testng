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

public class Topic_09_Custom_RadioButton_And_Checkbox {

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
	public void TC_01_Click_On_A_Radio_Button() {
		driver.get("https://material.angular.io/components/radio/examples");

		By summerRadioButton = By.xpath("//input[@value='Summer']");

		jsExecutor.executeScript("arguments[0].click()", driver.findElement(summerRadioButton));
		Assert.assertTrue(driver.findElement(summerRadioButton).isSelected());

	}

	@Test
	public void TC_02_Click_On_A_Checkbox() {

		driver.get("https://material.angular.io/components/checkbox/examples");
		By checkedCheckbox = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::span/input");
		By indeterminateCheckbox = By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::span/input");

		check_To_A_Custom_Checkbox(checkedCheckbox);
		check_To_A_Custom_Checkbox(indeterminateCheckbox);

	}

	@Test
	public void TC_03_Click_On_A_Radio_Button_Without_InPut_TagName() {

		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By canThoRadioButton = By.xpath("//div[@data-value='Cần Thơ']");
		Assert.assertEquals(driver.findElement(canThoRadioButton).getAttribute("aria-checked"), "false");

		driver.findElement(canThoRadioButton).click();

		Assert.assertEquals(driver.findElement(canThoRadioButton).getAttribute("aria-checked"), "true");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void check_To_A_Custom_Checkbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));
		}
		Assert.assertTrue(driver.findElement(by).isSelected());
	}

	public void uncheck_To_A_Custom_Checkbox(By by) {
		if (driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0]", driver.findElement(by));
		}
		Assert.assertFalse(driver.findElement(by).isSelected());
	}

}
