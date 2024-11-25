package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.BookHotelPage;
import pageObjects.BookingConfirmtionPage;
import pageObjects.LoginPage;
import pageObjects.SearchHotelPage;
import pageObjects.SelectHotelPage;
import testBase.BaseClass;

public class TC105_EndToEndTest extends BaseClass {

	    @Test (groups="Master")
		public void BookingConfirmation() throws InterruptedException {
			try
			{
	    	logger.info("********Started TC105_EndToEndTest*******");
	    	logger.info("Login Page...");
			LoginPage lp=new LoginPage(driver);
			lp.set_username(prop.getProperty("username"));
			lp.set_password(prop.getProperty("password"));
			logger.info("Provided user name and password......");
			lp.click_login();
			logger.info("clicked login button......");
			
			logger.info("Search Hotel page........");
			SearchHotelPage shp=new SearchHotelPage(driver);
			shp.select_Location("London");
			shp.select_Hotels("Hotel Creek");
			shp.select_roomType("Double");
			shp.select_no_of_rooms(2);
			shp.set_checkInDate("10/12/2025");
			shp.set_checkOutDate("12/12/2025");
			logger.info("provided the necessary informations.........");
			shp.click_Search();
			logger.info("clicked search button......");
			
			logger.info("Select Hotel Page........");
			SelectHotelPage selctHotPage=new SelectHotelPage(driver);
			selctHotPage.click_radionButton();	
			selctHotPage.click_Continue();
			logger.info("Selected the hotel and clicked contine buttone.........");
			
			logger.info("Book Hotel Page..........");
			BookHotelPage bhp=new BookHotelPage(driver);
			bhp.set_FirstName(getRandomAlphabetic(5));
			bhp.set_LastName(getRandomAlphabetic(5));
			bhp.set_Address(getalphanumberic(10));
			bhp.set_CrdtNumber(getRandomNumeric(16));
			bhp.set_CrdtType("Master Card");
			bhp.set_expiryMonth("February");
			bhp.set_expiryYear("2025");
			bhp.set_cvvNumber(getRandomNumeric(3));
			logger.info("provided the information to book the hotel.......");
			bhp.click_BookNow();
			logger.info("Clicked book now button.......");
			Thread.sleep(5000);
			
			System.out.println(bhp.get_BookingConfirmation());
			Assert.assertEquals("Booking Confirmation", bhp.get_BookingConfirmation());
			logger.info("booking confirmed and order ID generated.......");
				 
			BookingConfirmtionPage bcp=new BookingConfirmtionPage(driver);
			String OrderID=bcp.get_OrderNo();
			Thread.sleep(3000);
			bcp.click_myItinerary();
            System.out.println("Booking Confirmed and New Order Id: "+OrderID);
            
            bcp.SearchOrderId(OrderID);
            bcp.click_Go();
            Thread.sleep(3000);
            logger.info("Entered the search ID and clicked GO button to cancel the booking......");
            
            String listedOrderId=bcp.get_listedOrderId();
            System.out.println("ListedOrder Id: "+listedOrderId);
            System.out.println(bcp.get_confirmationofCancel());
            Assert.assertEquals(OrderID, listedOrderId);
               	
            bcp.select_Checkbox();
            
           	bcp.click_CancelButton();
           	Thread.sleep(8000);
           	System.out.println("Clicked cancel button");
           	
           	Thread.sleep(4000);
            bcp.acceptAlert();
            System.out.println(bcp.get_confirmationofCancel());
            
            logger.info("Cancelled the order successfully.......");
            lp.click_logout();
            lp.click_loginAgain();
	       }
	    
	    catch(Exception e) 
		{
	    	System.out.println(e.getStackTrace());
			Assert.fail();
			logger.error("Test Failed....");
			logger.debug("debug loggs.....");
			  
		}
		Thread.sleep(3000);
		  
	    logger.info("*****TC105_EndToEndTest Finished*********");
    }
	
	
		
	
}




