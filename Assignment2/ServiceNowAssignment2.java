package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowAssignment2 {

	public static void main(String[] args) throws IOException {

		// setup driver and browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.navigate().to("https://dev81154.service-now.com/navpage.do");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

		// login service now
		driver.switchTo().frame(0);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Magi@123");
		driver.findElement(By.id("sysverb_login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// search for incident
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filter")));
		driver.findElement(By.id("filter")).sendKeys("incident");

		// click all
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();

		// click new button
		driver.switchTo().frame(0);
		Actions action = new Actions(driver);
		WebElement newButton = driver.findElement(By.xpath("//div[@class='navbar-header']/button[text()='New']"));
		action.moveToElement(newButton).click().perform();

		// select caller name from lookup
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lookup.incident.caller_id")));
		driver.findElement(By.id("lookup.incident.caller_id")).click();

		Set<String> allWindowSet = driver.getWindowHandles();
		List<String> allWinList = new ArrayList<String>(allWindowSet);
		String parentWindow = allWinList.get(0);
		String callerListWindow = allWinList.get(1);

		driver.switchTo().window(callerListWindow);
		driver.findElement(By.xpath("(//tbody[@class='list2_body']/tr)[1]//a")).click();

		driver.switchTo().window(parentWindow);
		driver.switchTo().frame(0);

		// read incident number
		WebElement incNum = driver.findElement(By.id("incident.number"));
		String incidentNumber = incNum.getAttribute("value");
		System.out.println("Incident Number created is " + incidentNumber);

		// enter short desc
		String shortDesc = "This is caller short description";
		driver.findElement(By.id("incident.short_description")).sendKeys(shortDesc);

		// click submit
		driver.findElement(By.id("sysverb_insert")).click();

		// search incident number
		driver.findElement(By.xpath("//label[text()='Search']//following-sibling::input"))
				.sendKeys(incidentNumber + Keys.ENTER);

		// verify created incident
		WebElement foundInc = driver.findElement(By.xpath("(//tbody[@class='list2_body']/tr/td)[3]/a"));
		String foundIncident = foundInc.getText();

		if (foundIncident.equals(incidentNumber)) {
			System.out.println("Created Incident Number " + incidentNumber + " is found successfully");
		}

		// take screenshot
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File img = new File("./Screenshot/servicenow.jpg");
		FileUtils.copyFile(screenshotAs, img);

		// close browser
		// driver.quit();

	}

}
