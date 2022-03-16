package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IrctcAssignment5b {

	public static void main(String[] args) {

		// setup driver and prepare browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.navigate().to("https://www.irctc.co.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// handle alert pop up => turned off notification in chrome settings
		/*
		 * WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		 * wait.until(ExpectedConditions.alertIsPresent()); Alert alert =
		 * driver.switchTo().alert(); System.out.println("Pop Up Alert message is - " +
		 * alert.getText()); alert.dismiss();
		 */

		// click ok in lightbox window
		driver.findElement(By.xpath("//button[text()='OK']")).click();

		// select flights
		driver.findElement(By.xpath("(//label[text()='FLIGHTS'])[2]/preceding-sibling::span")).click();

		// switch to next tab
		Set<String> windowSet = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windowSet);

		// String parrentWindow = windowList.get(0);
		String flightsWindow = windowList.get(1);

		driver.switchTo().window(flightsWindow);

		// handle pop up in new window
		driver.findElement(By.xpath("//button[text()='Later']")).click();

		// select contactus link
		driver.findElement(By.id("dropdown10")).click();

		// get customer care email
		System.out.println("Customer Care email id is - "
				+ driver.findElement(By.xpath("(//a[@id='dropdown10']/following::div/a)[3]")).getText());

		// close current tab
		driver.close();

		// close parent tab
		driver.quit();
	}

}
