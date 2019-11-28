package TrelloAPI;

import static org.testng.Assert.assertEquals;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import TrelloFunctions.TrelloConstant;
import io.qameta.allure.Step;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrelloAPISteps {
	private static String boardId;
	private static String cardId;
	private static String toDoListId;
	private static String doingListId;
	private static String doneListId;

	
	@Step("Create a Board using Trello API")
	public void createBoard(String boardName) throws ParseException, InterruptedException {
		HttpResponse<String> response = Unirest.post(TrelloConstant.URL + "boards").header("accept", "application/json")
				.queryString("key", TrelloConstant.key).queryString("token", TrelloConstant.token)
				.queryString("name", boardName).asString();
		Thread.sleep(3000);
		JSONObject boardData = (JSONObject) new JSONParser().parse(response.getBody().toString());
		log.info("Response -->" + response.getBody());
		boardId = boardData.get("id").toString();
		assertEquals(response.getStatus(), 200);
	}

	@Step("Get Board Data")
	public void getBoard() throws ParseException, InterruptedException {
		HttpResponse<String> response = Unirest.get(TrelloConstant.URL + "boards/{id}")
				.header("accept", "application/json").routeParam("id", boardId).queryString("key", TrelloConstant.key)
				.queryString("token", TrelloConstant.token).queryString("lists", "all").asString();
		Thread.sleep(3000);
		log.info("Response -->" + response.getBody().toString());
		JSONObject boardResponse = (JSONObject) new JSONParser().parse(response.getBody().toString());
		JSONArray jsonArray = (JSONArray) boardResponse.get("lists");
		JSONObject toDoList = (JSONObject) jsonArray.get(0);
		JSONObject doingList = (JSONObject) jsonArray.get(1);
		JSONObject doneList = (JSONObject) jsonArray.get(2);
		toDoListId = toDoList.get("id").toString();
		doingListId = doingList.get("id").toString();
		doneListId = doneList.get("id").toString();
	}

	@Step("Create a card using Trello API")
	public void createCard(String cardName) throws ParseException, InterruptedException {
		HttpResponse<String> response = Unirest.post(TrelloConstant.URL + "cards").header("accept", "application/json")
				.queryString("key", TrelloConstant.key).queryString("token", TrelloConstant.token)
				.queryString("idList", toDoListId).queryString("name", cardName).asString();
		Thread.sleep(3000);
		log.info("Response -->" + response.getBody().toString());
		JSONObject cardData = (JSONObject) new JSONParser().parse(response.getBody().toString());
		cardId = cardData.get("id").toString();
		assertEquals(response.getStatus(), 200);
	}

	@Step("validate the card in API")
	public void validateTheCard() throws ParseException, InterruptedException {
		Thread.sleep(10000);
		HttpResponse<String> response = Unirest.get(TrelloConstant.URL + "cards/{id}")
				.header("accept", "application/json").routeParam("id", cardId).queryString("key", TrelloConstant.key)
				.queryString("token", TrelloConstant.token).queryString("idList", toDoListId).asString();
		log.info("Response -->" + response.getBody().toString());
		JSONObject cardResponse = (JSONObject) new JSONParser().parse(response.getBody().toString());
		assertEquals(doingListId, cardResponse.get("idList"));

	}

	@Step("Move the card from doing state to done state")
	public void movetheCardFromDoingToDoneState() throws ParseException, InterruptedException {
		Thread.sleep(10000);
		HttpResponse<String> response = Unirest.put(TrelloConstant.URL + "cards/{id}")
				.header("accept", "application/json").routeParam("id", cardId).queryString("key", TrelloConstant.key)
				.queryString("idList", doneListId).queryString("token", TrelloConstant.token).asString();
		Thread.sleep(3000);
		log.info("Response -->" + response.getBody().toString());
		JSONObject cardResponse = (JSONObject) new JSONParser().parse(response.getBody().toString());
		assertEquals(doneListId, cardResponse.get("idList"));

	}
}
