package basePage;


import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasePage
{
	public static WebDriver driver= new ChromeDriver();
	
	
	
	public static void getURL(String url) throws Exception
	{
	   
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		
		//Maximize current window
		driver.manage().window().maximize();

		//Delay execution for 5 seconds to view the maximize operation
		Thread.sleep(2000);
		
		
	}

	@After
	  public void tearDown() {
	    driver.quit();
	  }
	
}

