package TrelloWebApplication;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TrelloSeleniumFunction {
	public static WebDriver webDriver;
	public void preSetupWeb() {
		System.setProperty("webdriver.chrome.driver", "/Users/300067704/Downloads/Apps/chromedriver");
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		webDriver.get("https://trello.com");
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public void clickElementByXpathWeb(String xpath) {
		webDriver.findElement(By.xpath(xpath)).click();
	}

	public void findElementByTextWeb(String xpath, String text) {
		webDriver.findElement(By.xpath(MessageFormat.format(xpath, text))).click();
	}

	public void sendKeysByXpathWeb(String xpath, String text) {
		webDriver.findElement(By.xpath(xpath)).sendKeys(text);
	}
	public void waitUntilTheElementIsVisible(String xpath) {

		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	public void waitUntilTheElementIsVisibleUsingText(String xpath,String text) {

		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MessageFormat.format(xpath, text))));
	}
	public void waitUntilTheElementIsClickable(String xpath) {
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}



}
