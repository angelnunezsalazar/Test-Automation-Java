package webtestingframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ElegirProductoPage {

	private WebDriver driver;

	public ElegirProductoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void Abrir() {
		driver.get("http://localhost:6709/");
	}

	public IngresarOrdenPage comprar(String name) {
		driver.findElement(By.xpath("//h3[contains(text(),'" + name + "')]/../.././/a")).click();
		return PageFactory.initElements(driver, IngresarOrdenPage.class);
	}
}
