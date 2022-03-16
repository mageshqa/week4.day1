package week4.day1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FunWithFramesAssignment4 {

	public static void main(String[] args) {

		// setup driver and prepare browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.navigate().to("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// find frames by tagname
		List<WebElement> allIFrames = driver.findElements(By.tagName("iframe"));
		System.out.println("Number of Frames in page is " + allIFrames.size());

		// click button inside frame
		driver.switchTo().frame(0);
		driver.findElement(By.id("Click"));

		// click button inside nested frame
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		driver.switchTo().frame(driver.findElement(By.id("frame2")));
		driver.findElement(By.id("Click1")).click();

		// close browser
		// driver.quit();

	}

}
