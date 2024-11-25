package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.SearchHotelPage;
import testBase.BaseClass;

public class TC102_SearchHotelsTest extends BaseClass{

		
	@BeforeMethod
	public void Login() {
		
		LoginPage lp=new LoginPage(driver);
		lp.set_username(prop.getProperty("username"));
		lp.set_password(prop.getProperty("password"));
		lp.click_login();		
		logger.info("*******Started TC102_SearchHotelsTest********");
	}
	
	@AfterMethod
	public void logout() {
		LoginPage lp=new LoginPage(driver);
		lp.click_logout();
		logger.info("Logout done and loging again.....");
		lp.click_loginAgain();
	}
	
	@Test (priority=1, groups="Regression")
	public void Valid_Search() throws InterruptedException {
				
		SearchHotelPage shp=new SearchHotelPage(driver);
		shp.select_Location("Sydney");
		shp.select_Hotels("Hotel Creek");
		shp.select_roomType("Standard");
		shp.select_no_of_rooms(2);
		shp.select_numberOfAdults(2);
		logger.info("provided all the necessary informations....... ");
		shp.click_Search();
		logger.info("clicked search button and verifying the target page:");
		Assert.assertTrue(driver.getCurrentUrl().contains("SelectHotel.php"));
		System.out.println("Login is successful with valid credentials");			
		
	}
	
	@Test (priority=2, groups="Regression")
	public void MandatoryFields_Only() {
		SearchHotelPage shp=new SearchHotelPage(driver);
		shp.select_Location("Melbourne");
		shp.select_no_of_rooms(1);
		shp.select_numberOfAdults(1);
		shp.click_Search();
		
		logger.info("provided all the necessary informations....... ");
		logger.info("clicked search button and verifying the target page...");
		Assert.assertTrue(driver.getCurrentUrl().contains("SelectHotel.php"));
		System.out.println("Login is successful Mandatory credentials");		
	}
	
	@Test (priority=3, groups="Regression")
	public void Past_CheckInDate() {
		SearchHotelPage shp=new SearchHotelPage(driver);
		shp.select_Location("Sydney");
		shp.set_checkInDate("20/11/2024");
		shp.click_Search();
		
		logger.info("provided all the necessary informations....... ");
		logger.info("clicked search button and verifying the check-in date...");
		Assert.assertEquals(shp.get_errorMsgIncorrectDate(), "Check-In Date should be either Today or Later Date");
		System.out.println("validated with invalid date, getting error message");
	}
	
	@Test (priority=4,groups="Regression")
	public void CheckOut_Before() {
		SearchHotelPage shp=new SearchHotelPage(driver);
		shp.select_Location("Sydney");
		shp.set_checkInDate("27/11/2024");
		shp.set_checkOutDate("25/11/2024");
		shp.click_Search();
		
		logger.info("provided all the necessary informations....... ");
		logger.info("clicked search button and verifying check-out date...");
		Assert.assertEquals("Check-In Date shall be before than Check-Out Date", shp.get_errorMsgIncorrectDate());
		System.out.println(shp.get_errorMsgIncorrectDate());		
	}
	
	@Test (priority=5, groups="Regression")
	public void InvalidDate() {
		SearchHotelPage shp=new SearchHotelPage(driver);
		shp.select_Location("Sydney");
		shp.set_checkInDate("45/11/2024");
		shp.set_checkOutDate("47/11/2024");
		shp.click_Search();
		
		logger.info("provided all the necessary informations....... ");
		logger.info("clicked search button and verifying the date format...");
		Assert.assertEquals("Date should be in correct format", shp.get_errorMsgIncorrectDate());
		System.out.println("Date should be entered as per the calender date");		
	}
	
	@Test (priority=6, groups="Regression")
	public void InvalidDateFormat() {
		SearchHotelPage shp=new SearchHotelPage(driver);
		shp.select_Location("Sydney");
		shp.set_checkInDate("Today");
		shp.set_checkOutDate("Tomorrow");
		shp.click_Search();
		
		logger.info("provided all the necessary informations....... ");
		logger.info("clicked search button and verifying the date format");
		Assert.assertEquals("Date should be in correct format", shp.get_errorMsgIncorrectDate());
		System.out.println("Date format should be in calender format");
	}
	
	@Test (priority=7, groups="Regression")
	public void withoutLocation() {
		SearchHotelPage shp=new SearchHotelPage(driver);
		shp.select_Hotels("Hotel Creek");
		shp.select_roomType("Standard");
		shp.select_no_of_rooms(2);
		shp.select_numberOfAdults(2);
		shp.click_Search();
		
		logger.info("provided all the necessary informations....... ");
		logger.info("clicked search button and verifying the missing location...");
		Assert.assertEquals("Please Select a Location", shp.get_errorMsgMissingLocation());
		System.out.println("Location should be selected");
		
	}
 	
				
}
	

