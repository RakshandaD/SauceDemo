package resuableMethods;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.PageObjects;

public class ResuableFunctions {
	    
	public static WebDriver driver = new EdgeDriver();
	PageObjects po= new PageObjects(driver);

	public void launchApp() {
		Reporter.log("=====Browser Session Started=====", true);
		WebDriverManager.edgedriver().setup();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		Reporter.log("=====Application Started=====", true);
	}
	
	public void loginToApp() {
		po.userName.sendKeys("standard_user");
		po.pass.sendKeys("secret_sauce");
		po.login.click();
	}
	
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//Scroll down till the bottom of the page
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	// Method to verify the title is equal to expected title
	public void titleEquals(String expectedTitle) {
		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle, "Actual Title is equal to the expected Title(" + expectedTitle + ")");
		System.out.println(actualTitle);
	}

	// Method to verify the innerHtmlText of the single webelement is equal to the
	// expected text
	public void innerTextEquals(WebElement element, String expectedText) {
		String actualText = element.getText();
		assertEquals(actualText, expectedText);
		System.out.println(actualText);
	}
	
	public void handleMultiWindows(WebElement ele) {
		String originalWindow = driver.getWindowHandle();
		ele.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		//Loop through until we find a new window handle
		for (String windowHandle : driver.getWindowHandles()) {
		    if(!originalWindow.contentEquals(windowHandle)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
		//Wait for the new tab to finish loading content
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		//Close the tab or window
		driver.close();

		//Switch back to the old tab or window
		driver.switchTo().window(originalWindow);
	}
	
	public void submitCheckOutDetails() {
		po.Fname.sendKeys("Rakshanda");
		po.Lname.sendKeys("Dharmik");
		po.PostalCode.sendKeys("400018");
	}

	// Method to check whether the url equals to specific string or not
	public void urlEquals(String expectedUrl) {
		String actualUrl = driver.getCurrentUrl();
		assertEquals(actualUrl, expectedUrl, "Actual Url is equal to the expected URL(" + expectedUrl + ")Url");
	}

	// Method to verify the innerHtmlText of the multiple webelement is equal to the
	// expected text
	public void multipleInnerTextEquals(List<WebElement> element, String expectedText) {
		for (WebElement data : element) {
			String actualText = data.getText();
			assertEquals(actualText, expectedText);
		}

	}

	// Method to verify whether the element is available in the application or not
	public void elementAvailable(WebElement element, boolean expectedValue) {
		boolean actualValue = element.isDisplayed();
		assertEquals(actualValue, expectedValue);
		System.out.println(actualValue);
	}

	// Method to verify whether the element is enabled in the application or not
	public void elementEnabled(WebElement element, boolean expectedValue) {
		boolean actualValue = element.isEnabled();
		assertEquals(actualValue, expectedValue);
	}

	// Method to verify whether the element is selected in the application or not
	public void elementSelected(WebElement element, boolean expectedValue) {
		boolean actualValue = element.isEnabled();
		assertEquals(actualValue, expectedValue);
	}
	
	public void selectElementByVal(WebElement ele, String val) {
		Select s= new Select(ele);
		s.selectByValue(val);
	}

	// Import workbook to get the data from the excel
	public String readDataFromExcel(int rowcount, int columncount, String filepath, String Sheetname) {
		String data = null;
		try {
			FileInputStream input = new FileInputStream(filepath);
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(input);
			XSSFSheet sh = wb.getSheet(Sheetname);
			XSSFRow row = sh.getRow(rowcount);
			DataFormatter df = new DataFormatter();
			data = df.formatCellValue(row.getCell(columncount));
		} catch (Exception e) {
			System.out.println(e);
		}
		return data;
	}

	public void closeApp() {
		driver.close();
		Reporter.log("=====Browser Session End=====", true);
	}

}
