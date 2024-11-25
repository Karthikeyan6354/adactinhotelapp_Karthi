package testCases;

import org.testng.annotations.Test;
import org.testng.Assert; 

import pageObjects.LoginPage;
import pageObjects.SearchHotelPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is Valid - login Success - test pass  - logout
 *                 login unSuccess - test fail
 *                 
 * Data is Invalid - login success - test fail - logout
 *                  login unSuccess - test pass		  
 */

public class TC101_LoginTestDDT extends BaseClass{
	
	@Test (dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= "DataDriven")
	public void verify_loginDDT(String username, String password, String result) throws InterruptedException
	{
		try
		{
		driver.navigate().refresh();
		logger.info("******TC101_LoginTestDDT has started*******");
		//Login Page
		LoginPage lp=new LoginPage(driver);  
		lp.set_username(username);
		lp.set_password(password);
		logger.info("Provided user name and password");
		lp.click_login();
		logger.info("clicked login button");
		
		//Search Hotel Page
		SearchHotelPage shp=new SearchHotelPage(driver);  
		boolean targetpage=shp.ispageExist();
		logger.info("Checking for target page .....");	
		System.out.println("Is page displayed: "+targetpage);
					
		if(result.equalsIgnoreCase("Valid"))
		{
			if (targetpage==true)
			{   
				lp.click_logout();
				lp.click_loginAgain();
				Thread.sleep(3000);
				Assert.assertTrue(true);
				logger.info("Valid test case passed....");
			}
			else
			{
				Assert.assertTrue(false);
				logger.info("Valid test case failed....");
			}
		}
		if (result.equalsIgnoreCase("Invalid"))
		{
			if (targetpage==true)
			{   
				lp.click_logout();
				lp.click_loginAgain();
				Thread.sleep(3000);
				Assert.assertTrue(false);
				logger.info("Invalid test case failed and redirected to login page....");
			}
			else
			{
				Assert.assertTrue(true);	
				logger.info("Invalid test case passed");
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
	  logger.info("*******Finished TC101_LoginTestDDT*******");

	}


}
