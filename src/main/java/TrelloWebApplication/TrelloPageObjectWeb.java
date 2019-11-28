package TrelloWebApplication;

/*
 * Locators for web application --> Trello
 */

public class TrelloPageObjectWeb {
	public static final String logInButton = "//a[@class='btn btn-sm btn-link text-white']";
	public static final String loginWithEmail = "//a[@id='use-password']";
	public static final String userNameField = "//input[@type='email']";
	public static final String passWordField = "//input[@type='password']";
	public static final String logInField = "//input[@id='login']";
	public static final String boardField = "//div[@class=''board-tile-details-name''][@title=''{0}'']";
	public static final String cardField = "(//div[@class='list-cards u-fancy-scrollbar u-clearfix js-list-cards js-sortable ui-sortable'])[1]";
	public static final String labelField = "//span[@title=''{0}'']//span[@class='label-text']";
	public static final String toDoList = "//div[@class='list-cards u-fancy-scrollbar u-clearfix js-list-cards js-sortable ui-sortable']//*[text()=''{0}'']";
	public static final String doingList = "//textarea[@aria-label=''Doing'']";

}
