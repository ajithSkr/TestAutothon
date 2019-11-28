package TrelloWebApplication;

import static org.testng.Assert.assertEquals;

import java.text.MessageFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import TrelloFunctions.TrelloData;
import io.qameta.allure.Step;

public class TrelloSeleniumSteps extends TrelloSeleniumFunction {

	@Step("Login into web application")
	public void loginIntoWebApplication(String username, String password) throws InterruptedException {
		preSetupWeb();
		waitUntilTheElementIsVisible(TrelloPageObjectWeb.logInButton);
		clickElementByXpathWeb(TrelloPageObjectWeb.logInButton);
		waitUntilTheElementIsVisible(TrelloPageObjectWeb.userNameField);
		sendKeysByXpathWeb(TrelloPageObjectWeb.userNameField, username);
		clickElementByXpathWeb(TrelloPageObjectWeb.passWordField);
		sendKeysByXpathWeb(TrelloPageObjectWeb.passWordField, password);
		waitUntilTheElementIsVisible(TrelloPageObjectWeb.logInField);
		clickElementByXpathWeb(TrelloPageObjectWeb.logInField);

	}

	@Step("Validate the updated label in web application")
	public void validatedUpdatedLabel() throws InterruptedException {
		waitUntilTheElementIsVisibleUsingText(TrelloPageObjectWeb.boardField, TrelloData.BOARDNAME.getData());
		findElementByTextWeb(TrelloPageObjectWeb.boardField, TrelloData.BOARDNAME.getData());
		WebElement element = (WebElement) webDriver.findElement(By.xpath(TrelloPageObjectWeb.cardField));
		boolean isDisplayed = element.isDisplayed();
		assertEquals(isDisplayed, true);

	}

	@Step("Move the card from the To Do list to Doing list ")
	public void moveTheCardFromToDoToDoingList() throws InterruptedException {
		Actions move = new Actions(webDriver);
		WebElement toDoList = webDriver.findElement(By.xpath(TrelloPageObjectWeb.cardField));
		WebElement doingList = webDriver.findElement(
				By.xpath(MessageFormat.format(TrelloPageObjectWeb.doingList, TrelloData.DOINGLIST.getData())));
		move.dragAndDrop(toDoList, doingList).perform();

	}

	@Step("Close the web application")
	public void webDriverQuit() {
		webDriver.quit();
	}

}
