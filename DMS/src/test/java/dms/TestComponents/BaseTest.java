package dms.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import dms.PageObjects.LoginPage;




public class BaseTest {
	
	public WebDriver driver;
	public LoginPage loginpg;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop =new Properties();

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/dms/Resources/Globaldata.properties");
		prop.load(fis);
		String browserName= System.getProperty("browser")!=null ?System.getProperty("browser"):prop.getProperty("browser");

		///String browserName = prop.getProperty("browser");

		if(browserName.contains("chrome"))
		{
			ChromeOptions options= new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			options.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(options);
			//driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.contains("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browserName.contains("edge"))
		{
			driver=new EdgeDriver();
		}
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://dmsdev.datamatica.uk");
		return driver;	
	}
	
	@BeforeMethod(alwaysRun = true)
	public void LoginPage() throws Exception
	{
		driver=initializeDriver();
		loginpg = new LoginPage(driver);
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.close();
	}

}
