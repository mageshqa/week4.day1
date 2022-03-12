package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowsHandlingAssignment1 {

	public static void main(String[] args) {

		// setup driver and prepare browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.navigate().to("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// explicit wait driver
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// login to web site
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'CRM/SFA')]")));
		driver.findElement(By.xpath("//a[contains(text(),'CRM/SFA')]")).click();

		// click contacts
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Contacts']")));
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		// click merge contacts
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Merge Contacts']")));
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();

		// click widget from contact
		driver.findElement(By.xpath("//span[text()='From Contact']//following::td//a")).click();

		// secondary window handle
		Set<String> contactHandle = driver.getWindowHandles();
		List<String> contactWindowList = new ArrayList<String>(contactHandle);

		String parentWindow = contactWindowList.get(0);
		String secondWindow = contactWindowList.get(1);
		driver.switchTo().window(secondWindow);

		// select first resulting contact
		driver.findElement(By.xpath("(//a[@class='linktext'])[1]")).click();

		// control back to parent window
		driver.switchTo().window(parentWindow);

		// click widget to contact
		driver.findElement(By.xpath("//span[text()='To Contact']//following::td//a")).click();

		// secondary window handle
		Set<String> newContactHandle = driver.getWindowHandles();
		List<String> newContactWindowList = new ArrayList<String>(newContactHandle);

		String newParentWindow = newContactWindowList.get(0);
		String newSecondWindow = newContactWindowList.get(1);
		driver.switchTo().window(newSecondWindow);

		// select second resulting contact
		driver.findElement(By.xpath("((//div[@class='x-grid3-body']/div)[2]//following::a)[2]")).click();

		// control back to parent window
		driver.switchTo().window(newParentWindow);

		// click merge button
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();

		// accept alert
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert text is " + alert.getText());
		alert.accept();

		// verify page title
		System.out.println("Page title is " + driver.getTitle());

		// close browser
		// driver.quit();

	}

}
