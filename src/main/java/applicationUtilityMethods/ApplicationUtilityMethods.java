package applicationUtilityMethods;

import java.util.ArrayList;
import java.util.List;
//import java.util.Set;
//import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import basePage.BasePage;



public class ApplicationUtilityMethods extends BasePage
{

	
	public static void getScrollByElements(WebElement ele)
	{
		try 
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("arguments[0].scrollIntoView(true)", ele);
			 
		} catch (Exception e)
		{
			System.out.println("Issue in GetScrollbyElements "+e);
		}
	}
	
	
	public static void Doubleclick(WebElement ele)
	{
		try 
		{
			Actions act=new Actions(driver);
			act.doubleClick(ele).perform();
			
		} catch (Exception e)
		{
			System.out.println("Issue in Doubleclick method "+e);
		}
	}
	public static void Rightclick(WebElement ele)
	{
		try 
		{
			Actions act=new Actions(driver);
			act.contextClick(ele).perform();
			
		} catch (Exception e)
		{
			System.out.println("Issue in Doubleclick method "+e);
		}
	}
	
	public static void SwitchTotab(int tabno)
	{
		try
		{
			ArrayList<String>tab=new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tab.get(tabno));
			
		} catch (Exception e) 
		{
			System.out.println("Issue in Switchtab method "+e);
		}
	}
	
	public static void Dropdownbyvalue(WebElement ele, String value)
	{
		try 
		{
			Select sel=new Select(ele);
			sel.selectByVisibleText(value);
		} catch (Exception e)
		{
			System.out.println("Issue in Dropdownvalue method "+e);
		}
	}
	public static void Dropdownbyindex(WebElement ele, int index)
	{
		try 
		{
			Select sel=new Select(ele);
			sel.selectByIndex(index);
		} catch (Exception e)
		{
			System.out.println("Issue in Dropdownvalue method "+e);
		}
	}
	public static void Dropdownbycontainsvalue(WebElement ele, String value)
	{
		try 
		{
			Select sel=new Select(ele);
			List<WebElement> list = sel.getOptions();
			for(WebElement dd:list)
			{
				String data = dd.getText();
				if(data.contains(value))
				{
					sel.selectByVisibleText(data);
					break;
				}
			}
		} catch (Exception e)
		{
			System.out.println("Issue in Dropdownvalue method "+e);
		}
	}
	
	public static void Slider(WebElement ele, int range)
	{
		try 
		{
			Actions act= new  Actions(driver);
			
			act.clickAndHold(ele).moveByOffset((60), 0).release().perform();
		} catch (Exception e) 
		{
			System.out.println("Issue in slider method "+e);
		}
	}
	

	
	
	
	
}

