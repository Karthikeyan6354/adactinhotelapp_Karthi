package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
	
	WebDriver driver;
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public WebElement getElement (By locator) {
		return driver.findElement(locator);
	}
	
	public void doSelectDropDownByVisibleText(WebElement locator, String value) {
		Select select=new Select (locator);
		select.selectByVisibleText(value);		
	}
	
	public void doSelectDropDownByIndex(WebElement locator, int index) {
		Select select=new Select (locator);
		select.selectByIndex(index);	
	}
	
	public void doSelectDropDownByValue(WebElement locator, String value) {
		Select select=new Select (locator);
		select.selectByValue(value);		
	}
	
	public double exactPrice(String pricestring) {
		try 
		   {
            String numericString = pricestring.replaceAll("[^\\d.]", "");
            return Double.parseDouble(numericString);
            } 
		catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid price format: " + pricestring);
	        }
     }
	
	public int exactNumber(String NumberString) {
		try 
		   {
         String numericString = NumberString.replaceAll("[^\\d.]", "");
         return Integer.parseInt(numericString);
         } 
		catch (NumberFormatException e) {
         throw new NumberFormatException("Invalid number format: " + NumberString);
	        }
	}
}
