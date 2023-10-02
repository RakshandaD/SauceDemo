package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjects {

	@FindBy (id="user-name") public WebElement userName;
	@FindBy (id="password") public WebElement pass;
	@FindBy (id="login-button") public WebElement login;
	@FindBy (xpath="//a[contains(@class,'cart_link')]") public WebElement cartbtn;
	@FindBy (xpath="//button[contains(@id,'labs-backpack')]") public WebElement AddbackPack;
	@FindBy (xpath="//button[contains(@id,'bike-light')]") public WebElement Addbikelight;
	@FindBy (xpath="//button[contains(@id,'fleece-jacket')]") public WebElement Addfleecejacket;
	@FindBy (xpath="//button[contains(@id,'bolt-t-shirt')]") public WebElement AddboltTshirt;//
	@FindBy (xpath="//select[contains(@data-test,'product_sort')]") public WebElement productSort;
	@FindBy (xpath="//button[contains(@id,'t-shirt-(red)')]") public WebElement redTshirt;
	@FindBy (xpath="//button[text()='Remove']") public List<WebElement> removeProdBtn;
	@FindBy (xpath="//button[text()='Remove']") public WebElement removeBtn;
	@FindBy (id="checkout") public WebElement checkOutBtn;
	@FindBy (xpath="//div[contains(text(),'Backpack')]") public WebElement backPackProdName;
	@FindBy (xpath="//div[contains(text(),'style with unequaled laptop')]") public WebElement backPackProdDesc;
	@FindBy (xpath="//*[contains(@class,'tory_item_price')]") public WebElement backPackProdprice;
	@FindBy (xpath="//a[text()='Twitter']") public WebElement twitterLink;
	@FindBy (xpath="//a[text()='Facebook']") public WebElement fbLink;
	@FindBy (xpath="//a[text()='LinkedIn']") public WebElement linkedInLink;
	@FindBy (id="first-name") public WebElement Fname;
	@FindBy (id="last-name") public WebElement Lname;
	@FindBy (id="postal-code") public WebElement PostalCode;
	@FindBy (xpath ="//*[contains(@id,'continue')]") public WebElement continueBtn;
	@FindBy (id="finish") public WebElement finishBtn;
	@FindBy (id="back-to-products") public WebElement BackToProds;
	@FindBy (xpath ="//button[text()='Open Menu']") public WebElement openMenu;
	@FindBy (xpath ="//a[text()='All Items']") public WebElement AllItems;
	@FindBy (xpath ="//a[text()='About']") public WebElement about;
	@FindBy (xpath ="//a[text()='Logout']") public WebElement Logout;
	@FindBy (xpath ="//a[text()='Reset App State']") public WebElement resetApp;
	@FindBy (xpath ="//button[text()='Close Menu']") public WebElement closeMenu;
	
	WebDriver driver;
	public PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
