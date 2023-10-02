package testcases;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.PageObjects;
import resuableMethods.ResuableFunctions;

public class EndToEndProductTest extends ResuableFunctions {

	PageObjects po = new PageObjects(driver);

	@BeforeTest
	public void start() {
		launchApp();
		loginToApp();
	}

	@Test(priority = 1)
	public void addSingleProdAndCheckOut() {
		po.AddbackPack.click();
		po.cartbtn.click();
		innerTextEquals(po.backPackProdName, "Sauce Labs Backpack");
		String desc = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
		innerTextEquals(po.backPackProdDesc, desc);
		innerTextEquals(po.backPackProdprice, "$29.99");
		po.checkOutBtn.click();
		submitCheckOutDetails();
		scrollDown();
		po.continueBtn.click();
		scrollDown();
		po.finishBtn.click();
		po.BackToProds.click();
	}

	@Test(priority = 2)
	public void addMultiProdAndCheckOut() {
		po.Addbikelight.click();
		po.AddboltTshirt.click();
		po.Addfleecejacket.click();
		po.cartbtn.click();
		po.checkOutBtn.click();
		submitCheckOutDetails();
		scrollDown();
		po.continueBtn.click();
		scrollDown();
		po.finishBtn.click();
		po.BackToProds.click();
	}

	@Test(priority = 3)
	public void sortProducts() {
		po.productSort.click();
		selectElementByVal(po.productSort, "lohi");
		po.productSort.click();
		selectElementByVal(po.productSort, "hilo");
		po.productSort.click();
		selectElementByVal(po.productSort, "za");
		po.productSort.click();
		selectElementByVal(po.productSort, "az");
	}

	@Test(priority = 4)
	public void singleAddedProdRemovefromCart() throws Exception {
		po.Addbikelight.click();
		po.cartbtn.click();
		po.removeBtn.click();
		po.continueBtn.click();
	}

	@Test(priority = 5)
	public void multiAddedProdRemovefromCart() {
		po.Addbikelight.click();
		po.Addfleecejacket.click();
		po.redTshirt.click();
		po.cartbtn.click();
		int count = po.removeProdBtn.size();
		System.out.println(count);
		for (int i = 0; i < count; i++) {
			po.removeBtn.click();
		}
		po.continueBtn.click();
	}

	@Test(priority = 6)
	public void verifyTwitterNavigation() {
		handleMultiWindows(po.twitterLink);
	}

	@Test(priority = 7)
	public void verifyFBNavigation() {
		handleMultiWindows(po.fbLink);
	}
	
	@Test(priority = 8)
	public void verifyLinkedInNavigation() {
		handleMultiWindows(po.linkedInLink);
	}
	
	@Test(priority = 9)
	public void verifyOpenMenu() {
		po.openMenu.click();
		po.AllItems.click();
		po.about.click();
		driver.navigate().back();
		po.Logout.click();
		loginToApp();
		po.openMenu.click();
		po.resetApp.click();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(po.closeMenu));
		po.closeMenu.click();
	}

	@AfterSuite
	public void close() {
		closeApp();
	}
}
