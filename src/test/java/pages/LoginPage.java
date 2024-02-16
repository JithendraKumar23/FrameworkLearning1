package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
	
	//Declaration
	@FindBy(id="input-username")
	private WebElement unTB; 
	
	@FindBy(id="input-password")
	private WebElement pwTB; 
	
	@FindBy(name="login-button")
	private WebElement goBtn; 
	
	//Initialization
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public void enterUN(String un) 
	{
		unTB.sendKeys(un);
	}
	
	public void enterPW(String pw) 
	{
		pwTB.sendKeys(pw);
	}
	
	public void clickGOButton() 
	{
		goBtn.click();
	}

}
