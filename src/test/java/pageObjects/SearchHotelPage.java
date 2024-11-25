package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchHotelPage extends BasePage{
	
	public WebDriver driver;
 
	public SearchHotelPage(WebDriver driver) {
		super(driver);
	}		
	
	@FindBy (id="location")
	WebElement list_location;
	
	@FindBy (id="hotels")
	WebElement list_hotels;
	
	@FindBy (id="room_type")
	WebElement list_room_type;
	
	@FindBy(id="room_nos")
	WebElement list_room_nos;
	
	@FindBy(xpath="//input[@id='datepick_in']")
	WebElement txt_checkInDate;
	
	@FindBy(xpath="//input[@id='datepick_out']")
	WebElement txt_checkoutDate;
	
	@FindBy(id="adult_room")
	WebElement list_number_of_adults;
	
	@FindBy (id="child_room")
	WebElement list_number_of_children;
	
	@FindBy (id="Submit")
	WebElement btn_search;
	
	@FindBy (id="Reset")
	WebElement btn_reset;	
	
	@FindBy(xpath="//td[normalize-space()='Welcome to Adactin Group of Hotels']")
	WebElement msg_Heading;
	
	@FindBy(id="checkin_span")
	WebElement errormsg_incorrectdate;
	
	@FindBy (id="location_span")
	WebElement errormsg_missinglocation;
	
	public void select_Location(String value) {
		super.doSelectDropDownByVisibleText(list_location, value);
	}
	
	public void select_Hotels(String value) {
		super.doSelectDropDownByValue(list_hotels, value);
	}
	
	public void select_roomType(String value) {
		super.doSelectDropDownByValue(list_room_type, value);
	}
	
	public void select_no_of_rooms(int number) {
		super.doSelectDropDownByIndex(list_room_nos, number);
	}
	
	public void set_checkInDate(String date) {
		txt_checkInDate.clear();
		txt_checkInDate.sendKeys(date);
	}
	
	public void set_checkOutDate(String date) {
		txt_checkoutDate.clear();
		txt_checkoutDate.sendKeys(date);
	}
	
	public void select_numberOfAdults(int number) {
		super.doSelectDropDownByIndex(list_number_of_adults, number);
	}
	
	public void select_numberOfChildren(int number) {
		super.doSelectDropDownByIndex(list_number_of_children, number);
	}
	
	public void click_Search() {
		btn_search.click();
	}
	
	public void click_Reset() {
		btn_reset.click();
	}
	
	public String get_LoginTitle()
	{
		return (msg_Heading.getText());
	}
	
	public boolean ispageExist()
	{
		try
		{
		  return (msg_Heading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
		
	}	
	
	public String get_errorMsgIncorrectDate() {
		return (errormsg_incorrectdate.getText());
	}
	
	public String get_errorMsgMissingLocation() {
		return (errormsg_missinglocation.getText());
	}
	
	
	
	
	
	
}
