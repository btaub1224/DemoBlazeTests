package Steps;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	
	String user = null;
	String pass = null;
	WebDriver driver = null;
	WebDriverWait wait = null;
	
	@Given("On demoblaze webpage")
	public void OpenWebPage() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.navigate().to("https://www.demoblaze.com/");
	}
	
	@And("Username and password that have not been used before")
	public void GenerateUserPass() {
		user = RandomStringUtils.randomAlphanumeric(10);
		pass = RandomStringUtils.randomAlphanumeric(10);
	}

	@When("I sign up using those credentials")
	public void SignUp() {
		driver.findElement(By.linkText("Sign up")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));
		driver.findElement(By.id("sign-username")).sendKeys(user);
		driver.findElement(By.id("sign-password")).sendKeys(pass);
		driver.findElement(By.xpath("//button[.='Sign up']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

	@And("Login to verify user creation")
	public void Login() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='Log in']")));
		driver.findElement(By.xpath("//a[.='Log in']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
		driver.findElement(By.id("loginusername")).sendKeys(user);
		driver.findElement(By.id("loginpassword")).sendKeys(pass);
		driver.findElement(By.xpath("//button[.='Log in']")).click();
	}
	
	@Then("Login will be successful")
	public void LoginVerify() throws Exception{
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Welcome " + user)));
		} catch (Exception TimeoutException) {
			throw new Exception("\nERROR: User not logged in\n");
		} finally {
			driver.quit();
		}
	}

}
