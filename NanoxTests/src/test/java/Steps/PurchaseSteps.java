package Steps;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PurchaseSteps {
	
	WebDriver driver = null;
	WebDriverWait wait = null;
	Actions action = null;
	
	@Given("On demoblaze homepage")
	public void on_demoblaze_homepage() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.navigate().to("https://www.demoblaze.com/");
	}
	
	@When("I add item to cart")
	public void AddItemToCart() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Samsung galaxy s6")));
		driver.findElement(By.linkText("Samsung galaxy s6")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add to cart")));
		driver.findElement(By.linkText("Add to cart")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

	@And("Place order by adding necessary credentials")
	public void PlaceOrder() {
	    driver.findElement(By.id("cartur")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.='Place Order']")));
	    driver.findElement(By.xpath("//button[.='Place Order']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
	    driver.findElement(By.id("name")).sendKeys("John Smith");
	    driver.findElement(By.id("country")).sendKeys("USA");
	    driver.findElement(By.id("city")).sendKeys("Manhatten");
	    driver.findElement(By.id("card")).sendKeys("1234567890");
	    driver.findElement(By.id("month")).sendKeys("07");
	    driver.findElement(By.id("year")).sendKeys("2028");
	    driver.findElement(By.xpath("//button[.='Purchase']")).click();
	}

	@Then("Order will be processed successfully")
	public void VerifyOrder() throws Exception {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[.='Thank you for your purchase!']")));
		} catch (Exception TimeoutException) {
			throw new Exception("\nERROR: Purchase not successful\n");
		} finally {
			driver.quit();
		}
	}
	
	@And("Place order without credentials")
	public void PlaceOrderInvalid() {
	    driver.findElement(By.id("cartur")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.='Place Order']")));
	    driver.findElement(By.xpath("//button[.='Place Order']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
	    driver.findElement(By.xpath("//button[.='Purchase']")).click();
	}
	
	@Then("Order will not be placed")
	public void VerifyNoOrder() throws Exception {
		try {
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception TimeoutException) {
			throw new Exception("\nERROR: Purchase was successful\n");
		} finally {
			driver.quit();
		}
	}
}