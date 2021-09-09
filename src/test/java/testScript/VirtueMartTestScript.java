package testScript;

import pages.VirtueMartPageHelper;
import basePage.BasePage;
import org.junit.Test;
import org.junit.Assert;

/**
 * Unit test for simple App.
 */
public class VirtueMartTestScript extends BasePage 
{


	 
	  VirtueMartPageHelper virtueMart = new VirtueMartPageHelper();


    @Test
    public void onlineShopping() {
    	
    	try 
		{
			virtueMart.getLaunch();
			
			Assert.assertEquals("WebSite Title Is Not Matched",
					VirtueMartPageHelper.webSiteTitle,VirtueMartPageHelper.verifyTitle());
			
			virtueMart.loginIntoTheApplication();
			
			Assert.assertEquals("Greeting Message Is Not Matched",
					"Hi demo,",VirtueMartPageHelper.greetingMessage());
			
			virtueMart.searchProduct();
			
			Assert.assertEquals("Product Price Is Not Matched",VirtueMartPageHelper.cowBoyHatPrice,
					VirtueMartPageHelper.verifyProductPrice()
					);
			
			virtueMart.addProductToCart();
			
			virtueMart.checkProductDetails();
			
			virtueMart.checkOutNow();
			
			virtueMart.logOut();
			

			
		} catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
      }
   




