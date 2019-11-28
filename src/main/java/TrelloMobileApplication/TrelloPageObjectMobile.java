package TrelloMobileApplication;


/*
 * Locators for web application --> Trello
 */

public class TrelloPageObjectMobile {

	public static final String loginButton = "//android.widget.Button[@text='Log in']";
	public static final String userName = "//android.widget.EditText[@text='Email or Username']";
	public static final String passWord = "//android.widget.EditText[@text='Password']";
	public static final String logInField = "//*[@text='LOG IN']";
	public static final String boardField = "//android.widget.TextView[@text=''{0}'']";
	public static final String cardField = "//android.widget.TextView[@text=''{0}'']";
	public static final String labelField="(//*[@class='android.widget.TextView'])[2]";
	public static final String createLabel = "//android.widget.TextView[@text='CREATE NEW LABEL']";
	public static final String labelTextField = "//android.widget.EditText[@resource-id='com.trello:id/label_name']";
	public static final String labelColor = " (//android.widget.ImageView[@class='android.widget.ImageView'])[5]";
	public static final String doneButton = "//android.widget.Button[@text='DONE']";
	public static final String confirmButton = "//android.widget.TextView[@content-desc='Done']";
	public static final String goBack="(//*[@class='android.widget.ImageButton'])[2]";
	public static final String backButton="(//*[@class='android.widget.ImageButton'])[1]";
	public static final String openDrawer = "	//android.widget.ImageButton[@content-desc='Open Drawer']";
	public static final String settings ="(//android.widget.TextView[@resource-id='com.trello:id/title'])[4]";
	public static final String logOutField="//*[@text='Log out']";
	public static final String noneOfTheAbove = "//android.widget.Button[@text='NONE OF THE ABOVE']";
}
