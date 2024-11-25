package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (id="username")
	WebElement txt_username;
	
	@FindBy(id="password")
	WebElement txt_password;
	
	@FindBy(id="login")
	WebElement btn_login;
	
	@FindBy(xpath="//a[normalize-space()='Logout']")
	WebElement link_logout;
	
	@FindBy (xpath="//a[normalize-space()='Click here to login again']")
	WebElement link_login;
	
	@FindBy (xpath="//a[normalize-space()='Booked Itinerary']")
    WebElement btn_bookedItinerary;
		
	public void set_username(String uname) {
		txt_username.sendKeys(uname);
	}
	
	public void set_password(String password) {
		txt_password.sendKeys(password);
	}	
		
	public void click_login() {
		btn_login.click();
	}
	
	public void click_logout() {
		link_logout.click();
	}
	
	public void click_loginAgain() {
		link_login.click();
	}
		
	public void click_bookedItinerary() {
		btn_bookedItinerary.click();
	}
	
		
	
	

}
