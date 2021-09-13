package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import applicationUtilityMethods.ApplicationUtilityMethods;
import basePage.BasePage;

public class VirtueMartPageHelper extends BasePage {
	public static String webSiteTitle = "Welcome to VirtueMart 3 Sample store";
	public static String cowBoyHatPrice = "12,00 €";
	public VirtueMartPageHelper() {
		super();
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath = "(//div[@class='accept'])")
	private WebElement iUnderStandButton;

	@FindBy(xpath = "(//input[@id='modlgn-username'])")
	private WebElement username;

	@FindBy(xpath = "(//input[@id='modlgn-passwd'])")
	private WebElement password;

	@FindBy(xpath = "(//input[@name='Submit'])")
	private WebElement logIn;

	@FindBy(xpath = "(//div[@class='login-greeting'])")
	public static WebElement greetingMessage;

	@FindBy(xpath = "(//input[@name='keyword' and @size='40'])")
	private WebElement searchTextBox;

	@FindBy(xpath = "(//input[@value='Search in shop'])")
	private WebElement searchButton;

	@FindBy(xpath = "(//div[@class='products-view'])//h2//a[text()='Cowboy Hat']")
	private static WebElement cowboyhatProduct;

	@FindBy(xpath = "(//span[@class='PricesalesPrice'])[4]")
	private static WebElement cowboyHatPrice;

	@FindBy(xpath = "(//span[@class='addtocart-button'])[4]")
	private WebElement addToCartButton;

	@FindBy(xpath = "(//a[@class='vm-btn vm-btn-primary showcart floatright'])")
	private WebElement showCartButton;

	@FindBy(xpath = "(//table[@class='cart-summary ui-sortable'])")
	private WebElement cartItemTable;

	@FindBy(xpath = "(//span[@class='PricebillTotal'])")
	private static WebElement productTotalPrice;

	@FindBy(xpath = "(//input[@id='tos'])")
	private WebElement termsAndConditionsCheckBox;

	@FindBy(xpath = "(//button[@id='checkoutFormSubmit'])")
	private WebElement checkOutNow;

	@FindBy(xpath = "(//div[@class='vm-wrap vm-order-done'])//h3")
	private static WebElement thankyouMessage;
	
	public String readproperty(String key) throws IOException
	{ 
	String St=null;
	Properties pr= new Properties();
	InputStream file= new FileInputStream("config.properties");
	pr.load(file);
	St=pr.getProperty(key);
	file.close();
	return St;
		
	}

	public void getLaunch() throws Exception {
		String url = readproperty("applicationurl");
		BasePage.getURL(url);

	}

	public static String verifyTitle() throws Exception {
		String title = driver.getTitle();
		System.out.println(title);
		return title;
		
	}

	public void loginIntoTheApplication() throws Exception {
		PageFactory.initElements(driver, this);
		String userID = readproperty("userID");
		String pwd =readproperty("password");
		iUnderStandButton.click();
		username.click();
		username.sendKeys(userID);
		password.click();
		password.sendKeys(pwd);
		ApplicationUtilityMethods.getScrollByElements(logIn);
		logIn.click();
     }

	public static String greetingMessage() throws Exception {
		System.out.println(greetingMessage.getText());
		return greetingMessage.getText();
	}

	public void searchProduct() throws Exception {
		ApplicationUtilityMethods.getScrollByElements(searchTextBox);
		searchTextBox.click();
		searchTextBox.sendKeys("cowboy hat");
		searchButton.click();

	}

	public static String verifyProductPrice() throws Exception {

		ApplicationUtilityMethods.getScrollByElements(cowboyhatProduct);
		cowboyhatProduct.click();
		System.out.println(cowboyHatPrice.getText());
		return cowboyHatPrice.getText();

	}

	public void addProductToCart() throws Exception {
		ApplicationUtilityMethods.getScrollByElements(addToCartButton);
		addToCartButton.click();
		TimeUnit.SECONDS.sleep(2);
		showCartButton.click();

	}

	public void checkProductDetails() throws Exception {

		ApplicationUtilityMethods.getScrollByElements(cartItemTable);
		List<WebElement> trCount = driver.findElements(By.tagName("tr"));
		System.out.println(trCount.size());
		for (int i = 4; i==4; i++) {
			List<WebElement> tdCount = trCount.get(i).findElements(By.tagName("td"));
			System.out.println("First TR Contains: " + tdCount.size());

			// Fetch text from individual '<td>' tags
			int j=0;
				System.out.println(tdCount.get(j).getText().length());
				int strlen = tdCount.get(j).getText().length();
				if (strlen==59) {
					System.out.println("#########################");
					ApplicationUtilityMethods.getScrollByElements(productTotalPrice);
					
					if (productTotalPrice.getText().equals(cowBoyHatPrice)) {
						System.out.println("#########################1if");
						ApplicationUtilityMethods.getScrollByElements(termsAndConditionsCheckBox);
						TimeUnit.SECONDS.sleep(2);
						termsAndConditionsCheckBox.click();
						break;
					} 
					
					else {
						System.out.println("###########1else##############");
						WebElement productQuantity = driver.findElement(By.xpath(
								"(//input[@class='quantity-input js-recalculate'and @name='quantity[" + j + "]'])"));
						ApplicationUtilityMethods.getScrollByElements(productQuantity);
						System.out.println(productQuantity.getAttribute("value"));
						if (productQuantity.getAttribute("value").equals("2")) {
							System.out.println("###########2if##############");
							productQuantity.click();
							productQuantity.clear();
							productQuantity.sendKeys("1");
							WebElement updateQuantityInCart = driver.findElement(
									By.xpath("(//button[@class='vmicon vm2-add_quantity_cart'and @name='updatecart." + j
											+ "'])"));
							updateQuantityInCart.click();
							ApplicationUtilityMethods.getScrollByElements(productTotalPrice);
							if (productTotalPrice.getText().equals(cowBoyHatPrice)) {
								System.out.println("###########3if##############");
								ApplicationUtilityMethods.getScrollByElements(termsAndConditionsCheckBox);
								TimeUnit.SECONDS.sleep(2);
								termsAndConditionsCheckBox.click();
								break;
							}
						}
					}
				}
				
				else {
					System.out.println("************************");
					WebElement deleteExtraItem = driver.findElement(By.xpath("(//button[@name='delete." + j + "'])"));
					deleteExtraItem.click();
					ApplicationUtilityMethods.getScrollByElements(cartItemTable);
						ApplicationUtilityMethods.getScrollByElements(productTotalPrice);
						
						if (productTotalPrice.getText().equals(cowBoyHatPrice)) {
							System.out.println("*************************1if");
							ApplicationUtilityMethods.getScrollByElements(termsAndConditionsCheckBox);
							TimeUnit.SECONDS.sleep(2);
							termsAndConditionsCheckBox.click();
							break;
						}else {
							System.out.println("****************1else**************");
							WebElement productQuantity = driver.findElement(By.xpath(
									"(//input[@class='quantity-input js-recalculate'and @name='quantity[" + j + "]'])"));
							ApplicationUtilityMethods.getScrollByElements(productQuantity);
							System.out.println(productQuantity.getAttribute("value"));
							if (productQuantity.getAttribute("value").equals("2")) {
								System.out.println("***************2if***************");
								productQuantity.click();
								productQuantity.clear();
								productQuantity.sendKeys("1");
								WebElement updateQuantityInCart = driver.findElement(
										By.xpath("(//button[@class='vmicon vm2-add_quantity_cart'and @name='updatecart." + j
												+ "'])"));
								updateQuantityInCart.click();
								ApplicationUtilityMethods.getScrollByElements(productTotalPrice);
								if (productTotalPrice.getText().equals(cowBoyHatPrice)) {
									System.out.println("*************3if***************");
									ApplicationUtilityMethods.getScrollByElements(termsAndConditionsCheckBox);
									TimeUnit.SECONDS.sleep(2);
									termsAndConditionsCheckBox.click();
									break;
								}
							}
						}

					}
				}
			}

		


	public void checkOutNow() throws Exception {
		TimeUnit.SECONDS.sleep(3);
		if(termsAndConditionsCheckBox.isSelected()) {
			System.out.println(termsAndConditionsCheckBox.isSelected());
			}else {
			System.out.println(termsAndConditionsCheckBox.isSelected());
			termsAndConditionsCheckBox.click();
		}
		ApplicationUtilityMethods.getScrollByElements(checkOutNow);
		if(checkOutNow.getAttribute("name").equals("confirm")) {
		TimeUnit.SECONDS.sleep(1);
		System.out.println(checkOutNow.getAttribute("name"));
		checkOutNow.click();
		TimeUnit.SECONDS.sleep(3);
		verifyMessage();
		}
		else {
			TimeUnit.SECONDS.sleep(1);
			System.out.println(checkOutNow.getAttribute("name"));
			checkOutNow.click();
		}
	}

	public void verifyMessage() throws Exception {
		String message = thankyouMessage.getText();
		if(message.equals("Thank you for your order!")) {
			System.out.println(message);
		}
     }

	public void logOut() throws Exception {

		logIn.click();
	}
}
