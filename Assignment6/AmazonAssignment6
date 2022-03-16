package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonAssignment6 {

	public static void main(String[] args) {

		String mobileModel = "oneplus 9 pro";
		// setup driver and prepare browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.navigate().to("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// search for mobile model
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(mobileModel + Keys.ENTER);

		// get price of 1st resulting model
		WebElement mobilePrice = driver.findElement(By.xpath("((//div[@class='sg-row'])[2]//span/span)[4]"));
		String mobPrice = "₹" + mobilePrice.getText();
		System.out.println("Price of 1st mobile model resulting - " + mobPrice);

		// get ratings for 1st resulting model
		System.out.println("Ratings for 1st mobile model resulting - "
				+ driver.findElement(By.xpath("(((//div[@class='sg-row'])/div)[2]//span)[5]//span")).getText());

		// click on starts and find % of 5star rating
		driver.findElement(By.xpath("(((//div[@class='sg-row'])/div)[2]//span)[2]//a")).click();
		System.out.println("5Star rating % of model is - "
				+ driver.findElement(By.xpath("(//table[@id='histogramTable']//tr/td)[3]//a")).getText());

		// click 1st image
		driver.findElement(By.xpath("(//div[@class='sg-row'])[1]//img")).click();

		// switch to new window
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);

		// String parWin = winList.get(0);
		String newWin = winList.get(1);

		driver.switchTo().window(newWin);

		// click add to cart
		driver.findElement(By.id("add-to-cart-button")).click();

		// get cart subtotal
		WebElement subTot = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']"));
		String subTotal = subTot.getText();
		/*
		 * subTotal = subTotal.replaceAll(",", ""); double number1 =
		 * Double.parseDouble(subTotal); int number2 = (int) number1;
		 */
		System.out.println("Cart subtotal is : " + subTotal);

		// int number = Integer.parseInt(mobPrice);
		System.out.println("Mobile price is : " + mobPrice);

		if (mobPrice.equalsIgnoreCase(subTotal)) {
			System.out.println("Mobile Price " + mobPrice + " and Cart Subtotal " + subTotal + " are the same");
		}

		else
			System.out.println("Mobile price and cart Subtotal are different");

		// close new tab
		driver.close();

		// close parent tab
		driver.quit();

	}

}
