package pageObjects;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingConfirmtionPage extends BasePage {
	
	public BookingConfirmtionPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (id="order_no")
	WebElement txt_orderNo;
	
	@FindBy (id="search_hotel")
	WebElement btn_searchHotel;
	
	@FindBy (id="my_itinerary")
	WebElement btn_myItinerary;
	
	@FindBy (xpath="//td[normalize-space()='Welcome to Adactin Group of Hotels']//following::table[3]//tr")
	List <WebElement> table_Itinerary;
	
	@FindBy (xpath="/html[1]/body[1]/table[2]/tbody[1]/tr[2]/td[1]/form[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[2]/td[2]/input[1]")
	WebElement table_OrderId;
	
	@FindBy (xpath="//td[normalize-space()='Welcome to Adactin Group of Hotels']//following::table[3]//tr[2]//td[1]")
	WebElement list_checkboxselected;
	
	@FindBy (xpath="//td[normalize-space()='Welcome to Adactin Group of Hotels']//following::table[3]//tr[2]//td[3]")
	WebElement btn_cancel;
	
	@FindBy (xpath="//td[normalize-space()='Welcome to Adactin Group of Hotels']//following::table[2]//input[@id='order_id_text']")
	WebElement txt_orderId;
	
	@FindBy (xpath="//td[normalize-space()='Welcome to Adactin Group of Hotels']//following::table[2]//input[@type='submit']")
	WebElement btn_SearchOrderId;
	
	@FindBy (xpath="//td[normalize-space()='Welcome to Adactin Group of Hotels']//following::table[1]//input[@name='cancelall']")
	WebElement Cancel_selected;
	
	@FindBy (xpath="//td[normalize-space()='Welcome to Adactin Group of Hotels']//following::table[2]//label[@id='search_result_error']")
	WebElement errormsg_cancelled;
	
	
	public String get_OrderNo() {
		return txt_orderNo.getAttribute("value");
	}
	
	public void click_Go() {
		btn_SearchOrderId.click();
	}
	
	public void click_myItinerary() {
		btn_myItinerary.click();
	}
	
	public int get_rowsItinerary() {
		return (table_Itinerary.size());
	}	
	
	public void SearchOrderId(String OrderID) {
		txt_orderId.sendKeys(OrderID);
	}
	
	public String get_listedOrderId() {
		return table_OrderId.getAttribute("value");
	}
	
	public void click_cancelSelected() {
		Cancel_selected.click();
	}
	
	public void click_CancelButton() {
		btn_cancel.click();
	}
	
	public void select_Checkbox() {
		list_checkboxselected.click();
	}
	
	public void acceptAlert() {
		Alert alert=driver.switchTo().alert();
		alert.accept();
	}
	
	public String get_AlertMessage() {
		Alert alert=driver.switchTo().alert();
		return (alert.getText());
	}
	
	public String get_confirmationofCancel() {
		return (errormsg_cancelled.getText());
	
	}	

}
