package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChercherExampleFramesAssignment3 {

	public static void main(String[] args) throws IOException {

		// setup driver and prepare browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.navigate().to("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// enter frame topic
		driver.switchTo().frame("frame1");
		driver.findElement(By.xpath("//b[@id='topic']//following-sibling::input")).sendKeys("Frames Topic");

		// checbox in nest frame
		driver.switchTo().frame("frame3");
		driver.findElement(By.id("a")).click();

		// return back to parent page frame
		driver.switchTo().defaultContent();

		// select animals droplist
		driver.switchTo().frame("frame2");
		WebElement animalList = driver.findElement(By.id("animals"));
		Select animals = new Select(animalList);
		animals.selectByVisibleText("Baby Cat");

		// screenshot
		File framesScreenshot = driver.getScreenshotAs(OutputType.FILE);
		File img = new File("./Screenshot/ChercherFrames.jpg");
		FileUtils.copyFile(framesScreenshot, img);

		// close browser
		// driver.quit();

	}

}
