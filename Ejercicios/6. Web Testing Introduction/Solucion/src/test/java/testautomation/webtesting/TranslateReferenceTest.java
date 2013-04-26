package testautomation.webtesting;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TranslateReferenceTest {
	@Test
	public void FirstTest() {
		WebDriver driver = new FirefoxDriver();
		// WebDriver driver = new InternetExplorerDriver();
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "./drivers/chromedriver.exe"); WebDriver driver = new ChromeDriver();
		 */
		driver.get("http://www.google.com");

		assertEquals("Google", driver.getTitle());

		driver.quit();
	}

	@Test
	public void TranslateWord() {
		WebDriver driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://translate.reference.com/");
		new Select(driver.findElement(By.id("src"))).selectByVisibleText("Spanish");
		new Select(driver.findElement(By.id("dst"))).selectByVisibleText("English");
		driver.findElement(By.id("query")).click();
		driver.findElement(By.id("query")).sendKeys("hola mundo");

		driver.findElement(By.cssSelector("button.trans_image")).click();

//		WebElement element = new WebDriverWait(driver, 10)
//				  .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.translateTxt")));

		String translateText = driver.findElement(By.cssSelector("div.translateTxt")).getText();
		assertEquals("Hello World", translateText);

		driver.quit();
	}

	@Test
	public void InsertSymbol() {
		FirefoxDriver driver = new FirefoxDriver();

		driver.get("http://translate.reference.com/");
		driver.findElement(By.id("query")).click();
		driver.findElement(By.linkText("Symbols & accents")).click();
		// driver.findElement(By.XPath("//div[@id='tooltip_keyboard']/p[2]/button[32]")).click();
		driver.findElement(By.xpath("//button[text()='ñ']")).click();
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-closethick")).click();

		String symbol = driver.findElement(By.id("query")).getAttribute("value");
		assertEquals("ñ", symbol);

		driver.quit();
	}
}
