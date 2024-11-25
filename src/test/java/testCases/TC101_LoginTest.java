package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC101_LoginTest extends BaseClass{
	
	@Test (priority=1, groups="Sanity")
	public void Validlogin()
	{
	  try {
	  logger.info("*****Login Test has Started ....");
	  LoginPage lp=new LoginPage(driver);
	  lp.set_username(prop.getProperty("username"));
	  lp.set_password(prop.getProperty("password"));
	  
	  logger.info("Provided the username and password");
	  lp.click_login();		
	  logger.info("clicked login button");
	  
	  Assert.assertTrue(driver.getCurrentUrl().contains("SearchHotel.php"));
	  System.out.println("Login is successful with valid credentials");
	  logger.info("Validation Done: Login successful");
	  
	  lp.click_logout();
	  logger.info("clicked log out button");
	  lp.click_loginAgain();
	  }
	  catch (Exception e) {
		  logger.error("Test Failed....");
		  logger.debug("debug loggs.....");
		  Assert.fail();	  
	  }
	  logger.info("*******Finished TC101_LoginTest*******");
	}
	
	@Test (enabled=false, groups="Sanity")
	public void InvalidLogin() 
	{
	  LoginPage lp=new LoginPage(driver);
	  lp.set_username("Sherman123");
	  lp.set_password("Dausses63*");
	  lp.click_login();	
	  
	  Assert.assertTrue(driver.getCurrentUrl().contains("SearchHotel.php"));
	  System.out.println("Login is unsuccessful when invlalid credentials");;
		
	}

}
