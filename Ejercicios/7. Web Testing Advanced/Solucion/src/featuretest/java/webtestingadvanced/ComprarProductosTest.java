package webtestingadvanced;
import static org.junit.Assert.*;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import webtestingadvanced.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("dataaccess-ctx.xml")
public class ComprarProductosTest {

	static WebDriver driver;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@BeforeClass
	public static void InitDriver(){
		driver=new FirefoxDriver();
	}
	
	@AfterClass
	public static void CloseDriver(){
		driver.close();
	}
	
	@Before
	public void LoadData(){
		InsertData(new Product("Orange Muffin",null,6.99,null));
	}
	
	@After 
	public void CleanData(){
		DeleteData();
	}
	
	@Test
	public void MuestraLosDetallesDelProductoAlIngresarUnaOrden() {
		driver.get("http://localhost:8080");
		driver.findElement(By.xpath("//h3[contains(text(),'Orange Muffin')]/../.././/a")).click();
		
		String productName = driver.findElement(By.tagName("h1")).getText();
	    assertTrue(productName.contains("Orange Muffin"));
	    String price = driver.findElement(By.id("orderPrice")).getText();
	    assertTrue(price.contains("6.99"));
	}
	
	@Test
	public void ConfirmaLaCompraDeUnProducto() {
		
	}
	
	Object loadedData;
	public void InsertData(Object object){
		Session session = sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.save(object);
		loadedData=object;
		transaction.commit();
		session.close();
	}
	
	public void DeleteData(){
		Session session = sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.delete(loadedData);
		transaction.commit();
		session.close();
	}
}
