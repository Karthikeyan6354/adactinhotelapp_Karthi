package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectHotelPage extends BasePage {
	
	public SelectHotelPage(WebDriver driver) {
		super(driver);
	}	
	
	@FindBy (id="radiobutton_0")
	WebElement RadioButton;
	
	@FindBy (id="radiobutton_1")
	WebElement RadionButton1;
	
	@FindBy (id="hotel_name_0")
	WebElement Hotel_Name;
	
	@FindBy (id="location_0")
	WebElement Location;
	
	@FindBy (id="rooms_0")
	WebElement no_rooms;
	
	@FindBy (id="arr_date_0")
	WebElement arr_date;
	
	@FindBy (id="dep_date_0")
	WebElement dep_date;
	
	@FindBy (id="no_days_0")
	WebElement no_days;
	
	@FindBy (id="room_type_0")
	WebElement room_type;
	
	@FindBy (id="price_night_0")
	WebElement price_per_night;
	
	@FindBy (id="total_price_0")
	WebElement total_price;
	
	@FindBy (id="continue")
	WebElement btn_continue;
	
	@FindBy (id="cancel")
	WebElement btn_cancel;
	
	@FindBy (xpath="//td[normalize-space()='Book A Hotel']")
	WebElement msg_bookHotel;
	
	@FindBy (xpath="//form[@name='select_form']//td[@class='login_title']")
	WebElement msg_heading;
	
	@FindBy (id="radiobutton_span")
	WebElement errormsg_radiobutton;
	
	public void click_radionButton() {
		RadioButton.click();
	}
	
	public void click_radioButton1() {
		RadionButton1.click();
	}
	public String get_hotelName() {
		return Hotel_Name.getAttribute("value");
	}
	
	public String get_Location() {
		return Location.getAttribute("value");
	}
	
	public int get_noOfRooms() {
		int expectedNumber=super.exactNumber(no_rooms.getAttribute("value"));
		return expectedNumber;
	}
	
	public String get_CheckInDate() {
		return arr_date.getAttribute("value");
	}
	
	public String get_CheckOutDate() {
		return dep_date.getAttribute("value");
	} 
	
	public int get_noOfDays() {
		int days=super.exactNumber(no_days.getAttribute("value"));
		return days;
	}
	
	public String get_roomType() {
		return room_type.getAttribute("value");
	}
	
	public double get_pricePerNight() {
		double price=super.exactPrice(price_per_night.getAttribute("value"));
		return price;
	}
	
	public double get_totalPrice() {
	    double totalprice=super.exactPrice(total_price.getAttribute("value"));   
	    return totalprice;
	}
	
	public void click_Continue() {
		btn_continue.click();
	}
	
	public void click_Cancel() {
		btn_cancel.click();
	}
	
	public String get_bookHotel() {
		return msg_bookHotel.getText();
	}
	
	public String get_msgHeading() {
		return msg_heading.getText();
	}
	
	public String get_errorMessage() {
		return errormsg_radiobutton.getText();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
