package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_I {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//legend[contains(., 'Your basic info')]//following-sibling::input[@id='mail']");
	By under18RadioButton = By
			.xpath("//legend[contains(., 'Your basic info')]//following-sibling::input[@id='under_18']");
	By educationTextarea = By.xpath("//textarea[@id='edu']");
	By nameUser5img = By.xpath("//h5[text()='Name: User5']");
	By jobTitle3 = By.id("job3");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.manage().window().maximize();

	}

	 @Test
	public void TC_01_Verify_isDisplayed() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTexbox = driver.findElement(
				By.xpath("//legend[contains(., 'Your basic info')]//following-sibling::input[@id='mail']"));
		WebElement under18RadioButton = driver.findElement(
				By.xpath("//legend[contains(., 'Your basic info')]//following-sibling::input[@id='under_18']"));
		WebElement educationTextarea = driver.findElement(By.xpath("//textarea[@id='edu']"));
		WebElement nameUser5img = driver.findElement(By.xpath("//h5[text()='Name: User5']"));

		if (emailTexbox.isDisplayed()) {

			emailTexbox.sendKeys("hongnga.qn93@gmail.com");
			System.out.println("email is displayed");
		} else {
			System.out.println("email is not displayed");

		}
		if (under18RadioButton.isDisplayed()) {
			under18RadioButton.click();
			System.out.println("under 18 radio button is displayed");

		} else {

			System.out.println("under 18 radio button is not displayed");
		}

		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("automation testing");
			System.out.println("education is displayed");

		} else {
			System.out.println("education is not displayed");

		}

		if (nameUser5img.isDisplayed()) {
			System.out.println("user 5 image is displayed");
		} else {
			System.out.println("user 5 image is not displayed");
		}
	}

	@Test
	public void TC_02_isDisplayed_Refactor() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		By emailTextbox = By.xpath("//legend[contains(., 'Your basic info')]//following-sibling::input[@id='mail']");
		By under18RadioButton = By
				.xpath("//legend[contains(., 'Your basic info')]//following-sibling::input[@id='under_18']");
		By educationTextarea = By.xpath("//textarea[@id='edu']");
		By nameUser5img = By.xpath("//h5[text()='Name: User5']");

		if (isElementDisplayed(emailTextbox)) {
			senKeys(emailTextbox, "hongnga.qn93@gmail.com");
		}
		if (isElementDisplayed(under18RadioButton)) {
			clickOnAnElement(under18RadioButton);
		}
		if (isElementDisplayed(educationTextarea)) {
			senKeys(educationTextarea, "automation testing aaa");
		}
		Assert.assertFalse(isElementDisplayed(nameUser5img));
	}

	public boolean isElementDisplayed(By by) {

		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("the element " + by + " is displayed ");
			return true;
		} else {
			System.out.println("the element" + by + "is displayed ");
			return false;

		}

	}

	public void senKeys(By by, String value) {

		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);

	}

	public void clickOnAnElement(By by) {

		WebElement element = driver.findElement(by);
		element.click();

	}

	 @Test
	public void TC_03_Verify_isEnable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		Assert.assertTrue(isEnable(emailTextbox));
		Assert.assertTrue(isEnable(under18RadioButton));
		Assert.assertTrue(isEnable(educationTextarea));
		Assert.assertFalse(isEnable(jobTitle3));

	}

	public boolean isEnable(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("the element " + by + " is enable");
			return true;
		} else {
			System.out.println("the element " + by + " is disable");
			return false;
		}

	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
