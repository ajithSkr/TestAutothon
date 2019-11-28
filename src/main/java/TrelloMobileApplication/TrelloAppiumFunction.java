package TrelloMobileApplication;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TrelloAppiumFunction {
	public static AndroidDriver<AndroidElement> mobDriver;
	public static WebDriver webDriver;

	public void preSetUpMobile() throws InterruptedException, ParseException {
		{

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("noReset", "true");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "8.1.0");
			capabilities.setCapability("deviceName", "emulator-5554");
			capabilities.setCapability("app",
					"/Users/300067704/Documents/TrelloAPIAutomation/Trello/src/test/resources/trello.apk");
			capabilities.setCapability("appPackage", "com.trello");
			capabilities.setCapability("appActivity", "com.trello.home.HomeActivity");

			try {
				mobDriver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				mobDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			} catch (MalformedURLException e) {
				e.printStackTrace();

			}
		}
	}

	public void clickElementByXpathMobile(String xpath) {
		mobDriver.findElement(By.xpath(xpath)).click();
	}

	public void clickElementByTextMobile(String xpath, String text) {
		mobDriver.findElement(By.xpath(xpath)).click();
	}

	public MobileElement findElementByTextMobile(String xpath, String text) {
		return mobDriver.findElement(By.xpath(MessageFormat.format(xpath, text)));
	}

	public void sendKeysByXpathMobile(String xpath, String text) {
		mobDriver.findElement(By.xpath(xpath)).clear();
		mobDriver.findElement(By.xpath(xpath)).sendKeys(text);
	}

	public MobileElement scrollElementbyText(String text) {
		return mobDriver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));");
	}

	public MobileElement findElementByXpath(String xpath) {
		return mobDriver.findElement(By.xpath(xpath));
	}

	public void scrollElementByText(String text) {
		mobDriver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));").getLocation();
	}

	public void waitUntilTheElementIsVisible(String xpath) {

		WebDriverWait wait = new WebDriverWait(mobDriver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public void waitUntilTheElementIsVisibleUsingText(String xpath, String text) {

		WebDriverWait wait = new WebDriverWait(mobDriver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MessageFormat.format(xpath, text))));
	}

	public void waitUntilTheElementIsClickable(String xpath) {
		WebDriverWait wait = new WebDriverWait(mobDriver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}

}
