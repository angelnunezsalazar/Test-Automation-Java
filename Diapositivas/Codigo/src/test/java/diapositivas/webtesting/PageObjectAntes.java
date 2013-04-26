package diapositivas.webtesting;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PageObjectAntes {

	public void Antes(){
		
		WebDriver driver=new FirefoxDriver();
		driver.get("http://www.google.com");
		
		WebElement textbox=driver.findElement(By.name("q"));
		textbox.sendKeys("cats");
		textbox.submit();
		
		assertTrue(driver.getTitle().contains("cats"));
		
	}
	
	public void Despues(){
		
		WebDriver driver=new FirefoxDriver();
		GoogleSearchPage googleSearch=new GoogleSearchPage(driver);
		
		googleSearch.open();
		googleSearch.searchFor("cats");
		
		assertTrue(googleSearch.getTitle().contains("cat"));
		
	}
	
	public class GoogleSearchPage{

		public GoogleSearchPage(WebDriver driver) {
			// TODO Auto-generated constructor stub
		}

		public void open() {
			// TODO Auto-generated method stub
			
		}

		public String getTitle() {
			// TODO Auto-generated method stub
			return null;
		}

		public void searchFor(String string) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
