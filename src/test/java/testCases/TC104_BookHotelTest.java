package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.BookHotelPage;
import pageObjects.LoginPage;
import pageObjects.SearchHotelPage;
import pageObjects.SelectHotelPage;
import testBase.BaseClass;

public class TC104_BookHotelTest extends BaseClass {
	
	@Test (priority=1, groups="Functional")
	public void validlogin () 
	{
		logger.info("******* Started TC104_BookHotelTest**********");
		logger.info("Login page.........");
		LoginPage lp=new LoginPage(driver);
		lp.set_username(prop.getProperty("username"));
		lp.set_password(prop.getProperty("password"));
		lp.click_login();
		
		logger.info("SearchHotel page.....");
		SearchHotelPage shp=new SearchHotelPage(driver);
		shp.select_Location("London");
		shp.click_Search();
		logger.info("clicked search button........");
		
		logger.info("Select Hotel page.....");
		SelectHotelPage selctHotPage=new SelectHotelPage(driver);
		selctHotPage.click_radioButton1();
		selctHotPage.click_Continue();
		logger.info("clicked Continue button........");
		
	}
	
    @Test (priority=2, groups="Functional")	
	public void bookhotel () throws InterruptedException {
		try
		{
		logger.info("book Hotel page.....");
		BookHotelPage bhp=new BookHotelPage(driver);
		bhp.set_FirstName(" ");
		bhp.set_LastName(" ");
		bhp.set_Address(" ");
		bhp.set_CrdtNumber(getRandomNumeric(16));
		bhp.set_CrdtType("Master Card");
		bhp.set_expiryMonth("February");
		bhp.set_expiryYear("2024");
		bhp.set_cvvNumber(getRandomNumeric(3));
		bhp.click_BookNow();
		logger.info("provided necessary information and clicked book now button........");
		System.out.println(bhp.get_ProcessingConfirmation());
		Thread.sleep(5000);
		
		System.out.println(bhp.get_BookingConfirmation());
		Assert.assertEquals("Booking Confirmation", bhp.get_BookingConfirmation());
		System.out.println("Success, its verified");
		
		LoginPage lp=new LoginPage(driver);
		lp.click_logout();
		lp.click_loginAgain();
		}
   
	   catch(Exception e)
		 {
		   Assert.fail();
		   logger.error("Test Failed....");
		   logger.debug("debug loggs.....");
		  
		 }
	  Thread.sleep(3000);
	  
    logger.info("*****TC104_BookHotelTest Finished*********");
	}
	
}	























