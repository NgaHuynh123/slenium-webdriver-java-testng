package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Random rand;
	JavascriptExecutor jsExecutor;

	String emailLogin, custromerName, dateOfBirthInput, dateOfBirthOutput, address, city, state, pin, mobileNumber,
			emailAddress, password;
	String addressEditTextbox, cityEditTextbox;
	By customerNameTextbox = By.name("name");
	By genderRadioButton = By.xpath("//input[@value='f']");
	By dobInputTextbox = By.name("dob");
	By dobOutputTextbox = By.name("dob");
	By addressTextArea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By mobilePhoneNumber = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
		driver.get("http://demo.guru99.com/v4");
		emailLogin = "dam@gmai.com";
		custromerName = "Nga";
		dateOfBirthInput = "01/12/2021";
		dateOfBirthOutput = "2021-01-12";
		address = "257 Dong Da";
		city = "Da Nang";
		state = "Texas";
		pin = "123456";

		mobileNumber = "09051111111";
		emailAddress = "hongnga.qn" + getRandomNumber() + "@gmail.com";
		password = "11111111";
		addressEditTextbox = "111 Dong Da";
		cityEditTextbox = "Ha Noi";

	}

	@Test
	public void TC_01_Login() {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(emailLogin);
		driver.findElement(By.name("btnLogin")).click();
		String userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		String password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		driver.navigate().back();
		driver.navigate().back();

		driver.findElement(By.name("uid")).sendKeys(userID);

		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());

	}

	@Test
	public void TC_02_Create_New_Users() {

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(customerNameTextbox).sendKeys(custromerName);
		driver.findElement(genderRadioButton).click();
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(By.name("dob")));
		sleepInsecond(3);
		driver.findElement(dobInputTextbox).sendKeys(dateOfBirthInput);
		driver.findElement(addressTextArea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(mobilePhoneNumber).sendKeys(mobileNumber);
		driver.findElement(emailTextbox).sendKeys(emailAddress);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(By.name("sub")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),
				custromerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				"female");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				emailAddress);

	}

	@Test
	public void TC_03_Edit_Customer() {
		String customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='City']/following-sibling::td/input")).getAttribute("value"),
				city);
		driver.findElement(addressTextArea).clear();
		driver.findElement(addressTextArea).sendKeys(addressEditTextbox);

		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(cityEditTextbox);
		driver.findElement(By.name("sub")).click();
		sleepInsecond(3);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}

	public void sleepInsecond(long timeoutInsecond) {

		try {
			Thread.sleep(timeoutInsecond);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

}
