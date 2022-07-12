package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css_II {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String name, email, password, phoneNumber;

	By fullNameTextboxBy = By.id("txtFirstname");
	By emailAddressTextboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirmPasswordTextboxBy = By.id("txtCPassword");
	By phoneNumberTextboxBy = By.id("txtPhone");
	By registerButtonBy = By.xpath("//button[@class='btn_pink_sm fs16']");

	By fullNameErrorMsgBy = By.id("txtFirstname-error");
	By emailAdderessErrorMsgBy = By.id("txtEmail-error");
	By confirmEmailAddressErrorMsgBy = By.id("txtCEmail-error");
	By passwordErrorMsgBy = By.id("txtPassword-error");
	By confirmpasswordErrorMsgBy = By.id("txtCPassword-error");
	By phoneNumberErrorMsgBy = By.id("txtPhone-error");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		name = "Nga Huynh";
		email = "hongnga.qn93@gmail.com";
		password = "1234567";
		phoneNumber = "0905123111";


	}

	@Test
	public void TC_01_Register_With_Empty_Data() {

		driver.findElement(By.xpath("//button[@class=\"btn_pink_sm fs16\"]")).click();

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(fullNameErrorMsgBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailAdderessErrorMsgBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailAddressErrorMsgBy).getText(),
				"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmpasswordErrorMsgBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneNumberErrorMsgBy).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_Register_With_Invalid_Data() {

		driver.findElement(fullNameTextboxBy).sendKeys(name);
		driver.findElement(emailAddressTextboxBy).sendKeys("123@123");
		driver.findElement(confirmEmailTextboxBy).sendKeys("123@123");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneNumberTextboxBy).sendKeys(phoneNumber);
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(emailAdderessErrorMsgBy).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailAddressErrorMsgBy).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_03_Register_With_Incorrect_Confirm_Email() {
		driver.navigate().refresh();
		driver.findElement(fullNameTextboxBy).sendKeys(name);
		driver.findElement(emailAddressTextboxBy).sendKeys(email);
		driver.findElement(confirmEmailTextboxBy).sendKeys("123@123");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneNumberTextboxBy).sendKeys(phoneNumber);
		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(confirmEmailAddressErrorMsgBy).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_04_Register_With_Password_Less_Than_6_Character() {

		driver.navigate().refresh();
		driver.findElement(fullNameTextboxBy).sendKeys(name);
		driver.findElement(emailAddressTextboxBy).sendKeys(email);
		driver.findElement(confirmEmailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys("12345");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("12345");
		driver.findElement(phoneNumberTextboxBy).sendKeys(phoneNumber);
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmpasswordErrorMsgBy).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test

	public void TC_05_Register_With_Incorrect_Confirm_Password() {
		driver.navigate().refresh();

		driver.findElement(fullNameTextboxBy).sendKeys(name);
		driver.findElement(emailAddressTextboxBy).sendKeys(email);
		driver.findElement(confirmEmailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123456789");
		driver.findElement(phoneNumberTextboxBy).sendKeys(phoneNumber);
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(confirmpasswordErrorMsgBy).getText(), "Mật khẩu bạn nhập không khớp");
	}

	@Test
	public void TC_06_Register_With_Invalid_Phonenumber() {
		driver.navigate().refresh();

		driver.findElement(fullNameTextboxBy).sendKeys(name);
		driver.findElement(emailAddressTextboxBy).sendKeys(email);
		driver.findElement(confirmEmailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneNumberTextboxBy).sendKeys("0205123444");
		driver.findElement(registerButtonBy).click();

		Assert.assertTrue(driver.findElement(phoneNumberErrorMsgBy).getText()
				.contains("Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019"));
	}

	@AfterTest
	public void afterTest() {
		driver.quit();

	}

}
