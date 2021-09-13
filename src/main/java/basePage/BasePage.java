package basePage;


import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasePage
{
	
	public static WebDriver driver;
	
	/*
	 * Generate The Random Value Using Some Range.
	 */
	public int getRandomInteger(int min, int max) throws Exception {
		int indexValue = ThreadLocalRandom.current().nextInt(min, max);
		return indexValue;
	}
	
    @Before
    public void startBrowser() throws Exception {
    	int value=getRandomInteger(1,4);
    	if(value==1) {
    		System.out.println(value);
    		System.setProperty("webdriver.chrome.driver", "D:\\Assignment\\MavenProject\\chromedriver.exe");
    		driver= new ChromeDriver();
    	}else if(value==2) {
    		System.out.println(value);
    		System.setProperty("webdriver.edge.driver","D:\\Assignment\\MavenProject\\msedgedriver.exe");
    	    driver= new EdgeDriver();
    		}
    	else {
    		 System.out.println(value);
             System.setProperty("webdriver.firefox.driver", "D:\\Assignment\\MavenProject\\geckodriver.exe");
        	 driver= new FirefoxDriver();
        	 }
    }
        

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

