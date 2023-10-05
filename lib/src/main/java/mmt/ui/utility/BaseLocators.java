package mmt.ui.utility;

public class BaseLocators {

	public static String frameName = "//iframe[contains(@name,'notification')]";
	public static String closeBtn = "//a[@class='%s']";
	public static String classContians = "//span[contains(@class,'%s')]";
	public static String clickanywhere = "//div[contains(@class,'image')]";
	public static String inputPlaceholder ="//input[@placeholder='%s']";
	public static String inputid ="//input[@id='%s']";
	public static String continuebtn = "//button[@data-cy='continueBtn']";
	public static String tabSelected="//span[text()='%s']/preceding-sibling::span//*[contains(@class,'active')]";
	public static String radiobutton = "//li[@data-cy='%s' and @class='%s']";
}
