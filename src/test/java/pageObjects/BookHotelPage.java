package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookHotelPage extends BasePage {
	
	public BookHotelPage(WebDriver driver) {
		super(driver);
	}

	@FindBy (id="first_name")
	WebElement txt_firstName;
	
	@FindBy (id="last_name")
	WebElement txt_lastName;
	
	@FindBy (id="address")
	WebElement txtarea_address;
	
	@FindBy (id="cc_num")
	WebElement txt_ccNumber;
	
	@FindBy (id="cc_type")
	WebElement list_ccType;
	
	@FindBy (id="cc_exp_month")
	WebElement list_expMonth;
	
	@FindBy (id="cc_exp_year")
	WebElement list_expYear;
	
	@FindBy (id="cc_cvv")
	WebElement txt_cvv;
	
	@FindBy (id="book_now")
	WebElement btn_bookNow;
	
	@FindBy (xpath="//label[@id='process_span']")
	WebElement msg_processingConfirmation;
		
	@FindBy (xpath="//td[@class='login_title']")
	WebElement msg_bookingConfirmation;
	
	public void set_FirstName(String fname) {
		txt_firstName.sendKeys(fname);
	}
	
	public void set_LastName(String lname) {
		txt_lastName.sendKeys(lname);
	}
	
	public void set_Address(String address) {
		txtarea_address.sendKeys(address);
	}
	
	public void set_CrdtNumber(String num) {
		txt_ccNumber.sendKeys(num);
	}
	
	public void set_CrdtType(String value) {
		super.doSelectDropDownByVisibleText(list_ccType, value);
	}
	
	public void set_expiryMonth(String value) {
		super.doSelectDropDownByVisibleText(list_expMonth, value);
	}
	
	public void set_expiryYear(String value) {
		super.doSelectDropDownByVisibleText(list_expYear, value);
	}
	
	public void set_cvvNumber(String value) {
		txt_cvv.sendKeys(value);
	}
	
	public void click_BookNow() {
		btn_bookNow.click();
	}
	
	public String get_ProcessingConfirmation() {
		return msg_processingConfirmation.getText();
	}
	
	public String get_BookingConfirmation() {
		return msg_bookingConfirmation.getText();
	}
	
	public boolean IsPageExists() {
		try
		{
		  return (msg_bookingConfirmation.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
