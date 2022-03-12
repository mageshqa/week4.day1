package week4.day1;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesHandlingClassWork {

	public static void main(String[] args) {
		String name = "Magesh";

		// driver setup and prepare
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.navigate().to("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

		// get into iframe
		driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("//button[text()='Try it']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

		try {
			// handle alert
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(name);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
			alert.accept();

			// confirm text entered is visible
			WebElement visibleText = driver.findElement(By.id("demo"));

			if (visibleText.getText().contains(name)) {
				System.out.println("Entered text " + name + " is displayed in page as " + visibleText.getText());
			}

		}

		catch (Exception e) {
			System.out.println("NoSuchAlertPresent Exception met");
		}

	}

}
