package scripts;
import org.testng.Assert;
import org.testng.annotations.Test;
import generic.BaseTest;
import generic.Utilities;
import pages.HomePage;
import pages.LoginPage;

public class Test1 extends BaseTest{
	
	@Test
	public void validLogin()
	{
		String userName = Utilities.getExcelData(EXCEL, "validLogin", 1, 0);
		System.out.println(userName);
		
		String password = Utilities.getExcelData(EXCEL, "validLogin", 1, 1);
		System.out.println(password);
		
		//Enter the UserName
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUN(userName);
		
		//Enter the Password
		loginPage.enterPW(password);
		
		//Click on GO button
		loginPage.clickGOButton();
		
		//Verify Homepage is displayed or not
		HomePage homePage = new HomePage(driver);
		boolean result = homePage.verifyHomePageIsDisplayed(wait);
		//Assert.assertEquals(result, true);
		Assert.assertTrue(result);
	}

}
