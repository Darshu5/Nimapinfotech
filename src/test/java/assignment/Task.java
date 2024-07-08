package assignment;

import java.time.Duration;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Task {

	@Test(dataProvider = "loginData")
	public void Login(String username, String password, String customerName) throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		try {
			// Maximize the window
			driver.manage().window().maximize();

			// Implicit wait
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

			// Enter the URL
			driver.get("https://testffc.nimapinfotech.com/auth/login");

			// Enter username and password
			driver.findElement(By.id("mat-input-0")).sendKeys(username);
			driver.findElement(By.id("mat-input-1")).sendKeys(password);

			// Print instructions for manual CAPTCHA entry
			System.out.println("Please solve the CAPTCHA as displayed on the page.");
			System.out.println("After solving the CAPTCHA, press Enter in the console to continue...");

			// Wait for user to solve the CAPTCHA
			waitForUserToSolveCaptcha();

			// Click on the SignIn button after solving CAPTCHA manually
			driver.findElement(By.id("kt_login_signin_submit")).click();

			// Click on My Customers
			Thread.sleep(3000);
			driver.findElement(By.xpath("//span[text()='My Customers']")).click();

			// Click on New Customers
			Thread.sleep(3000);
			driver.findElement(By.xpath("//span[contains(text(),'New Customer')]")).click();

			// Click on Customer Name
			Thread.sleep(3000);
			WebElement customerNameField = driver.findElement(By.xpath("//input[@formcontrolname='LeadName']"));
			customerNameField.sendKeys("Mahesh");

			// Click on Save Button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[contains(text(),'Save')]")).click();

		} catch (Exception e) {
			System.out.println("An error occurred during the test execution.");
			e.printStackTrace();
		} finally {
			// Close the browser
//			driver.quit();
		}
	}

	// Data provider for login test data
	@DataProvider(name = "loginData")
	public Object[][] provideLoginData() {
		return new Object[][] { { "nakadedarshana28@gmail.com", "Sonu@2001", "Mahesh" },
				// Add more test data sets as needed
		};
	}

	// Method to wait for manual CAPTCHA entry and user confirmation
	private void waitForUserToSolveCaptcha() {
		Scanner scanner = new Scanner(System.in); // Using Scanner to read from the console
		scanner.nextLine(); // Wait for the user to press Enter
	}
}