package webtestingframework;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import webtestingframework.infraestructure.DataLoader;
import webtestingframework.model.Product;

public class ComprarProductosTest {

	static WebDriver driver;
	static DataLoader dataLoader;

	@BeforeClass
	public static void InitDriver(){
		driver=new FirefoxDriver();
		dataLoader=new DataLoader();
	}
	
	@AfterClass
	public static void CloseDriver(){
		driver.close();
	}
	
	@Before
	public void LoadData() throws Exception{
		dataLoader.loadData(new Product("Orange Muffin",null,6.99,null));
	}
	
	@After 
	public void CleanData() throws Exception{
		dataLoader.clean();
	}
	
	@Test
	public void MuestraLosDetallesDelProductoAlIngresarUnaOrden() {
		driver.get("http://localhost:8080/bakery");
		driver.findElement(By.xpath("//h3[contains(text(),'Orange Muffin')]/../.././/a")).click();
		
		String productName = driver.findElement(By.tagName("h1")).getText();
	    assertTrue(productName.contains("Orange Muffin"));
	    String price = driver.findElement(By.id("orderPrice")).getText();
	    assertTrue(price.contains("6.99"));
	}
	
	@Test
	public void ConfirmaLaCompraDeUnProducto() {
		
	}
}
