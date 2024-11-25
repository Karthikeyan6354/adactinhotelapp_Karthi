package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BookHotelPage;
import pageObjects.LoginPage;
import pageObjects.SearchHotelPage;
import pageObjects.SelectHotelPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is Valid - login Success - test pass  - logout
 *                 login unSuccess - test fail
 *                 
 * Data is Invalid - login success - test fail - logout
 *                  login unSuccess - test pass		  
 */
public class TC104_BookHotelDTT extends BaseClass {
	
	@Test (dataProvider="BookHotelData", dataProviderClass=DataProviders.class, groups= "DataDriven")
	public void verify_BookHotelDDT(String fname, String lname, String ccNumber, String crType, String ExpiryMonth, String ExpiryYear, String cvv, String result) throws InterruptedException
	{
		try
		{
		
		logger.info("**********TC104_BookHotelDTT*******");
		logger.info("login page.....");
		LoginPage lp=new LoginPage(driver);
		lp.set_username(prop.getProperty("username"));
		lp.set_password(prop.getProperty("password"));
		lp.click_login();
		logger.info("clicked login button........");
		
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
		
		logger.info("book Hotel page.....");
		BookHotelPage bhp=new BookHotelPage(driver);
		bhp.set_FirstName(fname);
		bhp.set_LastName(lname);
		bhp.set_Address(getalphanumberic(10));
		bhp.set_CrdtNumber(ccNumber);
		bhp.set_CrdtType(crType);
		bhp.set_expiryMonth(ExpiryMonth);
		bhp.set_expiryYear(ExpiryYear);
		bhp.set_cvvNumber(cvv);
		bhp.click_BookNow();
		logger.info("provided necessary information and clicked book now button........");
		System.out.println(bhp.get_ProcessingConfirmation());
		Thread.sleep(5000);
		boolean targetpage=bhp.IsPageExists();
							
		if(result.equalsIgnoreCase("Valid"))
		{
			if (targetpage==true)
			{   
				lp.click_logout();
				lp.click_loginAgain();
				Assert.assertTrue(true);
				logger.info("Valid test case passed and functions are working well...");
			}
			else
			{
				Assert.assertTrue(false);
				logger.info("Valid test case failed and functions are not working well...");
			}
		}
		if (result.equalsIgnoreCase("Invalid"))
		{
			if (targetpage==true)
			{   
				lp.click_logout();
				lp.click_loginAgain();
				Assert.assertTrue(false);
				logger.info("In Valid test case failed and functions are not working well...");
			}
			else
			{
				Assert.assertTrue(true);
				logger.info("In Valid test case passed and functions are working well...");
			}
		}			
	 }
	  catch(Exception e)
		 {
		  Assert.fail();
		  logger.error("Test Failed....");
		  logger.debug("debug loggs.....");
		  
		 }
	  Thread.sleep(3000);
	  
     logger.info("*****TC104_BookHotelDTT Finished*********");
	}


}

