package TestAutothon.Trello;

import org.aspectj.lang.annotation.After;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TrelloAPI.TrelloAPISteps;
import TrelloFunctions.TrelloConstant;
import TrelloFunctions.TrelloData;
import TrelloMobileApplication.TrelloAppiumFunction;
import TrelloMobileApplication.TrelloAppiumSteps;
import TrelloWebApplication.TrelloSeleniumFunction;
import TrelloWebApplication.TrelloSeleniumSteps;
import groovy.util.logging.Slf4j;
import io.appium.java_client.MobileDriver;

@Slf4j
public class TrelloTest {

	TrelloAPISteps trelloApiSteps = new TrelloAPISteps();
	TrelloAppiumSteps trelloAppiumSteps = new TrelloAppiumSteps();
	TrelloSeleniumSteps trelloSeleniumSteps = new TrelloSeleniumSteps();
	TrelloAppiumFunction trelloAppiumFunction = new TrelloAppiumFunction();
	TrelloSeleniumFunction trelloSeleniumFunction = new TrelloSeleniumFunction();
	Logger logger = LoggerFactory.getLogger(TrelloTest.class.getName());

	@Test(alwaysRun = true, description = "Create a card using trello API ", priority = 1)
	public void createCardUsingAPI() throws ParseException, InterruptedException {
		trelloApiSteps.createBoard(TrelloData.BOARDNAME.getData());
		trelloApiSteps.getBoard();
		trelloApiSteps.createCard(TrelloData.CARDNAME.getData());
	}

	@Test(alwaysRun = true, description = "Validate the card and create a label in mobile application ", priority = 2)
	public void validateCardAndCreateALabel() throws InterruptedException, ParseException {
		trelloAppiumSteps.login();
		trelloAppiumSteps.validateCardinMobileApplication();
		trelloAppiumSteps.createLabel();
		trelloAppiumSteps.mobileDriverQuit();
	}

	@Test(alwaysRun = true, description = "Validate the label in web application and drag and drop the card from To Do list to Doing list", priority = 3)
	public void validateLabelAndMoveToDoingList() throws InterruptedException {
		trelloSeleniumSteps.loginIntoWebApplication(TrelloConstant.userName, TrelloConstant.passWord);
		trelloSeleniumSteps.validatedUpdatedLabel();
		trelloSeleniumSteps.moveTheCardFromToDoToDoingList();

	}

	@Test(alwaysRun = true, description = "Validate the card state in API respose and move the card to done state", priority = 4)
	public void validateInAPIAndMoveToDoneList() throws ParseException, InterruptedException {
		trelloApiSteps.validateTheCard();
		trelloApiSteps.movetheCardFromDoingToDoneState();
		trelloSeleniumSteps.webDriverQuit();

	}
}
