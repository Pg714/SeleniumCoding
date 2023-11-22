package SeleniumCodingChallenge;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterHandling {

	static WebDriver driver = new ChromeDriver();
	static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	public static void main(String args[]) throws Exception {

		driver.get("https://www.t-mobile.com/tablets");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

//		SelectFilter("Deals", "New", "Special offer");
		SelectFilter("Brands", "Apple", "Samsung", "TCL");
//		SelectFilter("Operating System", "iPadOS");
//		SelectFilter("Deals", "all");
//		SelectFilter("Brands", "all");
//		SelectFilter("Operating System", "all");
	}

	public static void SelectFilter(String filterCategory, String... filtername) {
		String filterCategoryXpath = "//legend[text()=' " + filterCategory + " ']";
		WebElement filterCategoryId = driver.findElement(By.xpath(filterCategoryXpath));
		wait.until(ExpectedConditions.elementToBeClickable(filterCategoryId));
		filterCategoryId.click();

		String[] dealsFilers = { "New", "Special offer"};
		String[] brandsFilters = {"Alcatel", "Apple", "Samsung", "T-Mobile®", "TCL"};
		String[] osFilters = {"Android", "iPadOS", "Other"};

		if (filtername[0].equals("all")) {
			if (filterCategory.equals("Deals")) {
				for (String s : dealsFilers)
					SelectFilterCheckbox(s);
			}
			else if(filterCategory.equals("Brands")) {
				for (String s : brandsFilters)
					SelectFilterCheckbox(s);
			}
			else if(filterCategory.equals("Operating System")) {
				for (String s : osFilters)
					SelectFilterCheckbox(s);
			}
		} else {
			for (String i : filtername)
				SelectFilterCheckbox(i);
		}
	}

	public static void SelectFilterCheckbox(String valueName) {
		String Xpath = "//*[text()=' " + valueName
				+ " ']/ancestor::label/span[@class = 'mat-checkbox-inner-container']";
		WebElement checkbox = driver.findElement(By.xpath(Xpath));
		wait.until(ExpectedConditions.elementToBeClickable(checkbox));
		checkbox.click();
	}

}
