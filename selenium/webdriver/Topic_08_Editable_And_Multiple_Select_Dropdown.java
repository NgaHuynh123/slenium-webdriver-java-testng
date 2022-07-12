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

public class Topic_08_Editable_And_Multiple_Select_Dropdown {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor)driver;
	}

	
	
	
	
	
	//@Test
	public void TC_01_Editable_Dropdown_I(){
		
		
		
		selectItemEditableDropdown(By.xpath("//div[@id='default-place']/input"), By.xpath("//ul[@class='es-list' and @style]/li"),"Fiat");
		sleepInSecond(3);
		selectItemEditableDropdown(By.xpath("//div[@id='default-place']/input"), By.xpath("//ul[@class='es-list' and @style]/li"),"BMW");
		sleepInSecond(3);
		selectItemEditableDropdown(By.xpath("//div[@id='default-place']/input"), By.xpath("//ul[@class='es-list' and @style]/li"),"Audi");
		sleepInSecond(3);

	}
	
	//@Test
	public void TC_02_Editable_Dropdown_II() {
		
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		driver.findElement(By.cssSelector("input.search")).click();
		By parent = By.cssSelector("input.search");
		By child = By.xpath("//div[@role='listbox']//span");
		selectItemEditableDropdown(parent, child, "Algeria");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='alert' and contains(., 'Algeria')]")).isDisplayed());

		selectItemEditableDropdown(parent, child, "Afghanistan");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='alert' and contains(., 'Afghanistan')]")).isDisplayed());
	}
	
	@Test
	public void TC_03_a() {
		driver.get("https://indrimuska.github.io/jquery-editable-select/");

		driver.findElement(By.xpath("//div[@id='default-place']/input")).clear();
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys("BMW");
		
		driver.findElement(By.xpath("//ul[@class='es-list' and @style]/li")).click();
		sleepInSecond(3);
		
		System.out.println((driver.findElement(By.xpath("//ul[@class='es-list' and @style]/li")).getText().trim()));
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
public void selectItemEditableDropdown(By parentBy, By childBy, String expectedItem) {
	driver.findElement(parentBy).clear();
	driver.findElement(parentBy).sendKeys(expectedItem);

	
	
	explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
	List<WebElement> allItems = driver.findElements(childBy);
	
	for(WebElement item:allItems) {
		System.out.println((item.getText().trim()));
		if(item.getText().trim().equals(expectedItem)) {
			if(item.isDisplayed()) {
				item.click();
				
				
			}else {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
			item.click();
			}
		}break;
	}
	
	
	
}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
}
