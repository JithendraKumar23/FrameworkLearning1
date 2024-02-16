package generic;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest implements IAutoConst{
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	@Parameters({"Grid","GridURL","Browser","Env"})
	
	@BeforeMethod
	public void launchApp(@Optional(GRID) String grid, @Optional(GRIDURL) String gridURL, @Optional(BROWSER) String br, @Optional(ENV) String env) throws MalformedURLException 
	{
		if(grid.equalsIgnoreCase("Yes"))
		{
			URL url = new URL(gridURL);
			if(br.equalsIgnoreCase("Chrome"))
			{
				ChromeOptions options = new ChromeOptions();
				driver = new RemoteWebDriver(url, options);
				Reporter.log("Server : Chrome Browser Lauched",true);
			}
			else if(br.equalsIgnoreCase("Fireox"))
			{
				FirefoxOptions options = new FirefoxOptions();
				driver = new RemoteWebDriver(url, options);
				Reporter.log("Server : Firefox Browser Lauched",true);
			}
			else
			{
				EdgeOptions options = new EdgeOptions();
				driver = new RemoteWebDriver(url, options);
				Reporter.log("Server : Edge Browser Lauched",true);
			}
		}
		else
	{
		if(br.equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver();
			Reporter.log("Local : Chrome Browser Lauched",true);
		}
		else if (br.equalsIgnoreCase("Firefox")) 
		{
			driver = new FirefoxDriver();
			Reporter.log("Local : Firefox Browser Lauched",true);
		}
		else
		{
			driver = new EdgeDriver();
			Reporter.log("Local : Edge Browser Lauched",true);
		}
	}
		
		driver.manage().window().maximize();
		Reporter.log("Maximize the browser",true);
		
		String appURL = Utilities.getProperties(env, "APPURL");
		driver.get(appURL);
		Reporter.log("Enter the URL : "+appURL ,true);
		
		String converToIntITO = Utilities.getProperties(env, "ITO");
		int ITO = Integer.parseInt(converToIntITO);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ITO));
		Reporter.log("Set ITO : "+ITO ,true);
		
		String converToIntETO = Utilities.getProperties(env, "ETO");
		int ETO = Integer.parseInt(converToIntETO);
		wait = new WebDriverWait(driver, Duration.ofSeconds(ETO));
		Reporter.log("Set ETO : "+ETO ,true);
		
	}
	
	@AfterMethod
	public void closeApp(ITestResult testResult) throws IOException
	{
		String testName = testResult.getName();
		Reporter.log(testName , true);
		
		int testStatus = testResult.getStatus();
		System.out.println(testStatus);
		
		if(testStatus == 2)
		{
			TakesScreenshot tscreen = (TakesScreenshot)driver;
			File scrFile = tscreen.getScreenshotAs(OutputType.FILE);
			
			String path = SCREENSHOT+testName+".png";
			File dstFile = new File(path);
			
			FileUtils.copyFile(scrFile, dstFile);
			Reporter.log(testName + "TestCase is Failed , Screenshot is taken in " + path , true);
		}
		else
		{
			Reporter.log(testName + "TestCase is Passed , Screenshot is NOT taken" , true);
		}
		
		driver.quit();
		Reporter.log("Browser is closed",true);
	}

}
