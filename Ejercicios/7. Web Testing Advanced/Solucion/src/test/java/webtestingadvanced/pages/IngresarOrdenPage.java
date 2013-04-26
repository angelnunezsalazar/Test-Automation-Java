package webtestingadvanced.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class IngresarOrdenPage {
	private WebDriver driver;

	@FindBy(how = How.TAG_NAME, using = "h1")
	private WebElement producto;

	@FindBy(how = How.ID, using = "orderPrice")
	private WebElement precio;

	public IngresarOrdenPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getProducto() {
		return producto.getText();
	}

	public String getPrecio() {
		return precio.getText();
	}
}
