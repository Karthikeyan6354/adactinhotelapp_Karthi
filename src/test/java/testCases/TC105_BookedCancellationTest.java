package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.BookingConfirmtionPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC105_BookedCancellationTest extends BaseClass {
	
	
	@Test (groups="Functional")
	public void BookedItinerary() throws InterruptedException 
	{
	try 
	    {
		logger.info("********Started TC105_BookedCancellationTest*******");
    	logger.info("Login Page...");
		LoginPage lp=new LoginPage(driver);
		lp.set_username(prop.getProperty("username"));
		lp.set_password(prop.getProperty("password"));
		logger.info("Provided user name and password......");
		lp.click_login();
		logger.info("clicked login button......");
		
		lp.click_bookedItinerary();
		logger.info("clicked Booked Itinerary button");
		logger.info("Booked Itinerary ......");
		
		BookingConfirmtionPage bcp=new BookingConfirmtionPage(driver);
		System.out.println("Total no of orders in booked Itinerary: "+bcp.get_rowsItinerary());
		String OrderID=prop.getProperty("searchOrderId");
		bcp.SearchOrderId(OrderID);
        bcp.click_Go();
        Thread.sleep(3000);
        logger.info("Entered the search ID and clicked GO button to cancel the booking......");
        
        String listedOrderId=bcp.get_listedOrderId();
        System.out.println("ListedOrder Id: "+listedOrderId);
        System.out.println(bcp.get_confirmationofCancel());
        Assert.assertEquals(OrderID, listedOrderId);
           	
        bcp.select_Checkbox();
        Thread.sleep(5000);
       	bcp.click_CancelButton();
       	
       	Thread.sleep(4000);
        bcp.acceptAlert();
        System.out.println(bcp.get_confirmationofCancel());
        
        logger.info("Cancelled the order successfully.......");
        
        lp.click_logout();
        lp.click_loginAgain();    
        	
		}
		catch(Exception e) {
			Assert.fail();
			logger.error("Test Failed....");
			logger.debug("debug loggs.....");
			  
		}
		Thread.sleep(3000);
		logger.info("*****TC105_BookedCancellationTest Finished*********");
			
		}

}
