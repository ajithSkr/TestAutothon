package TrelloMobileApplication;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import TrelloAPI.TrelloAPISteps;
import TrelloFunctions.TrelloConstant;
import TrelloFunctions.TrelloData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;

public class TrelloAppiumSteps extends TrelloAppiumFunction {

	Logger log = LoggerFactory.getLogger(TrelloAPISteps.class);

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

	@Step("login to mobile application")
	public void login() throws InterruptedException, ParseException {
		preSetUpMobile();
		{
			waitUntilTheElementIsClickable(TrelloPageObjectMobile.loginButton);
			clickElementByXpathMobile(TrelloPageObjectMobile.loginButton);
			sendKeysByXpathMobile(TrelloPageObjectMobile.userName, TrelloConstant.userName);
			sendKeysByXpathMobile(TrelloPageObjectMobile.passWord, TrelloConstant.passWord);
			clickElementByXpathMobile(TrelloPageObjectMobile.logInField);

		}
	}

	@Step("Validating card in mobile application")
	public void validateCardinMobileApplication() throws InterruptedException, ParseException {
		preSetUpMobile();
		scrollElementbyText(TrelloData.BOARDNAME.getData());
		waitUntilTheElementIsVisibleUsingText(TrelloPageObjectMobile.boardField, TrelloData.BOARDNAME.getData());
		log.info(findElementByTextMobile(TrelloPageObjectMobile.boardField, TrelloData.BOARDNAME.getData()).getId());
		findElementByTextMobile(TrelloPageObjectMobile.boardField, TrelloData.BOARDNAME.getData()).click();
		waitUntilTheElementIsVisibleUsingText(TrelloPageObjectMobile.cardField, TrelloData.CARDNAME.getData());
		MobileElement element = findElementByTextMobile(TrelloPageObjectMobile.cardField,
				TrelloData.CARDNAME.getData());

		boolean isDisplayed = element.isDisplayed();
		assertEquals(isDisplayed, true);
	}

	@Step("create a label in Mobile application")
	public void createLabel() throws InterruptedException {
		findElementByTextMobile(TrelloPageObjectMobile.cardField, TrelloData.CARDNAME.getData()).click();
		waitUntilTheElementIsVisible(TrelloPageObjectMobile.labelField);
		clickElementByXpathMobile(TrelloPageObjectMobile.labelField);
		waitUntilTheElementIsVisible(TrelloPageObjectMobile.createLabel);
		clickElementByXpathMobile(TrelloPageObjectMobile.createLabel);
		waitUntilTheElementIsVisible(TrelloPageObjectMobile.labelTextField);
		sendKeysByXpathMobile(TrelloPageObjectMobile.labelTextField, TrelloData.LABELNAME.getData());
		waitUntilTheElementIsVisible(TrelloPageObjectMobile.labelColor);
		clickElementByXpathMobile(TrelloPageObjectMobile.labelColor);
		waitUntilTheElementIsVisible(TrelloPageObjectMobile.doneButton);
		clickElementByXpathMobile(TrelloPageObjectMobile.doneButton);
		waitUntilTheElementIsVisible(TrelloPageObjectMobile.confirmButton);
		clickElementByXpathMobile(TrelloPageObjectMobile.confirmButton);
		waitUntilTheElementIsVisible(TrelloPageObjectMobile.goBack);
		clickElementByXpathMobile(TrelloPageObjectMobile.goBack);
		waitUntilTheElementIsVisible(TrelloPageObjectMobile.backButton);
		clickElementByXpathMobile(TrelloPageObjectMobile.backButton);

	}

	@Step("Closing mobile application")
	public void mobileDriverQuit() {
		waitUntilTheElementIsVisible(TrelloPageObjectMobile.openDrawer);
		clickElementByXpathMobile(TrelloPageObjectMobile.openDrawer);
		waitUntilTheElementIsVisible(TrelloPageObjectMobile.settings);
		clickElementByXpathMobile(TrelloPageObjectMobile.settings);
		scrollElementbyText("Log out");
		waitUntilTheElementIsVisible(TrelloPageObjectMobile.logOutField);
		clickElementByXpathMobile(TrelloPageObjectMobile.logOutField);
		mobDriver.quit();

	}
}
