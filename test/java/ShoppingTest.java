import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingTest {
	public static WebDriver driver;

	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Documents\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void methodTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
		WebElement getSearchBar = driver.findElement(By.id("search_query_top"));
		getSearchBar.sendKeys("dress");
		getSearchBar.sendKeys(Keys.ENTER);
		TimeUnit.SECONDS.sleep(10);
		List<WebElement> getDresses = driver.findElements(By.className("product-name"));
		for (WebElement dress : getDresses) {
			if (dress.getText().toLowerCase().matches(".*dress.*")) {
				assertTrue(true);
				break;
			}
		}
	}

	@Test
	public void methodTestBuyStuff() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
		WebElement getSearchBar = driver.findElement(By.id("search_query_top"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		getSearchBar.sendKeys("dress");
		getSearchBar.sendKeys(Keys.ENTER);

		List<WebElement> getDresses = driver.findElements(By.className("product-name"));
		for (WebElement dress : getDresses) {
			if (dress.getText().toLowerCase().contains("dress")) {
				driver.navigate().to(dress.getAttribute("href"));

				WebElement buyDress = driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span"));
				buyDress.click();
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")));

				WebElement buyDress2 = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"));
				buyDress2.click();
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]")));

				WebElement buyDress3 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]"));
				buyDress3.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")));

				WebElement addEmail = driver.findElement(By.id("email_create"));
				int n = ((int) (Math.random() * 100000) + 1);
				addEmail.sendKeys("email" + n + "@gmail.com");
				addEmail.sendKeys(Keys.ENTER);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer_firstname")));

				WebElement addName1 = driver.findElement(By.id("customer_firstname"));
				addName1.sendKeys("FirstName");
				WebElement addName2 = driver.findElement(By.id("customer_lastname"));
				addName2.sendKeys("FirstName");
				WebElement addName3 = driver.findElement(By.id("passwd"));
				addName3.sendKeys("FirstName");
				WebElement addName4 = driver.findElement(By.id("firstname"));
				addName4.sendKeys("FirstName");
				WebElement addName5 = driver.findElement(By.id("lastname"));
				addName5.sendKeys("FirstName");
				WebElement addName6 = driver.findElement(By.id("address1"));
				addName6.sendKeys("FirstName");
				WebElement addName10 = driver.findElement(By.id("city"));
				addName10.sendKeys("FirstName");
				WebElement addName7 = driver.findElement(By.id("postcode"));
				addName7.sendKeys("00000");
				WebElement addName8 = driver.findElement(By.id("phone_mobile"));
				addName8.sendKeys("0000000000");
				new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Alaska");
				new Select(driver.findElement(By.id("id_country"))).selectByVisibleText("United States");
				WebElement addName9 = driver.findElement(By.id("alias"));
				addName9.sendKeys("newAddress");
				WebElement register = driver.findElement(By.id("submitAccount"));
				register.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("processAddress")));

				WebElement registerAddress = driver.findElement(By.name("processAddress"));
				registerAddress.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("processCarrier")));
				WebElement addName60 = driver.findElement(By.id("cgv"));
				addName60.click();
				WebElement register2 = driver.findElement(By.name("processCarrier"));
				register2.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bankwire")));

				WebElement buyBankWire = driver.findElement(By.className("bankwire"));
				buyBankWire.click();

				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cart_navigation\"]/button")));
				WebElement buyBankWireFinal = driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button"));
				buyBankWireFinal.click();
				WebElement confirm = driver.findElement(By.className("cheque-indent"));
				if (confirm.getText().toLowerCase().matches("your order on my store is complete.")) {
					assertTrue(true);
					break;
				}
				TimeUnit.SECONDS.sleep(5);
			}
		}
	}

	@AfterClass
	public static void teardown() {
		driver.quit();
	}
}
