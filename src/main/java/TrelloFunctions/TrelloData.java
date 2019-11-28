package TrelloFunctions;

public enum TrelloData {

	BOARDNAME("Qapitol QA"), LISTNAME("Testing"), CARDNAME("Automation"), LABELNAME("API Automation"),
	TODOLIST("To Do"), DOINGLIST(" Doing");

	private String data;

	TrelloData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

}
