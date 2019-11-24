package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest {

	public static WebDriver driver;
	public Properties prop;
	private static String repoPath=System.getProperty("user.dir")+"\\rreports\\result"+timeStamp()+".html";
	public static ExtentReports extent;
	public static ExtentTest test;

	public WebDriver initializeDriver() throws IOException
	{

		prop= new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");

		prop.load(fis);
		String browserName=prop.getProperty("browser");
		System.out.println(browserName);

		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
			driver= new ChromeDriver();
			//execute in chrome driver

		}
		else if (browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
			driver= new FirefoxDriver();
			//firefox code
		}
		else if (browserName.equals("IE"))
		{
			//	IE code
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}



	public void getScreenshot(String result) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\logs\\"+result+"screenshot.png"));
	}

	public static String timeStamp() {
		String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return fileSuffix;
	}

	@BeforeSuite 
	public void setupBeforeTest() throws IOException { 
		extent=new ExtentReports(repoPath);
	}

	@BeforeMethod
	public void setupBeforeMethod(Method method) throws IOException {
		test=extent.startTest(method.getName());
		driver=initializeDriver();
	}

	@AfterMethod
	public void setupAfterMethod() {
		driver.close();
		extent.endTest(test);

	}

	@AfterTest
	public void setupAfterTest() {
		driver.quit();
		extent.flush();
	}


}
