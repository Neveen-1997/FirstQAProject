import java.time.Duration;
import java.util.Random;

import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

@Test
public class Websitetest {
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();

	String MyWebsiteurl = "https://automationteststore.com/";

	String[] FirstNames = { "neveen", "mohammad", "zaina", "noor", "alaa", "emad", "anas" };
	String[] LastNames = { "salma", "jaber", "eman", "nizar", "alaa" };

	String DomainName = "@gmail.com";

	String GlobalUserName = "";
	String LoginName = "";
	String pass = "Neveen@97";

	@BeforeTest
	public void setup() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(MyWebsiteurl);

	}

	@Test(priority = 1)

	public void testregister() throws InterruptedException {

		int Randomindexfirstname = rand.nextInt(FirstNames.length);
		int Randomindexlasttname = rand.nextInt(LastNames.length);
		String FirstName = FirstNames[Randomindexfirstname];
		String LastName = LastNames[Randomindexlasttname];

		int RandomIndexemail = rand.nextInt(30);
		String Email = FirstName + LastName + RandomIndexemail + DomainName;
		String Finalname = FirstName + LastName + RandomIndexemail;

		WebElement registerbtn = driver.findElement(By.linkText("Login or register"));
		registerbtn.click();

		driver.findElement(By.xpath("//*[@id=\"accountFrm\"]/fieldset/button")).click();
		WebElement FirstNameinput = driver.findElement(By.id("AccountFrm_firstname"));
		FirstNameinput.sendKeys(FirstName);

		GlobalUserName = FirstName;

		WebElement LastNameinput = driver.findElement(By.id("AccountFrm_lastname"));
		LastNameinput.sendKeys(LastName);

		WebElement Emailinput = driver.findElement(By.id("AccountFrm_email"));
		Emailinput.sendKeys(Email);

		WebElement Addressinput = driver.findElement(By.id("AccountFrm_address_1"));
		Addressinput.sendKeys("Jordan-Irbid");

		WebElement Cityinput = driver.findElement(By.id("AccountFrm_city"));
		Cityinput.sendKeys("iRBID");

		WebElement Regioninput = driver.findElement(By.id("AccountFrm_zone_id"));
		Select selector1 = new Select(Regioninput);
		int RandomRegion = rand.nextInt(1, 100);
		selector1.selectByIndex(RandomRegion);
		Thread.sleep(3000);

		WebElement Zipinput = driver.findElement(By.id("AccountFrm_postcode"));
		Zipinput.sendKeys("123456");

		WebElement Countryinput = driver.findElement(By.id("AccountFrm_country_id"));
		Select selector2 = new Select(Countryinput);

		int Randomindexcountry = rand.nextInt(0, 8);

		WebElement LogiNnameinput = driver.findElement(By.id("AccountFrm_loginname"));
		LogiNnameinput.sendKeys(Finalname);

		LoginName = Finalname;

		WebElement Passwordinput = driver.findElement(By.id("AccountFrm_password"));
		Passwordinput.sendKeys(pass);

		WebElement Confirmpassinput = driver.findElement(By.id("AccountFrm_confirm"));
		Confirmpassinput.sendKeys(pass);

		WebElement Checkbox = driver.findElement(By.id("AccountFrm_agree"));
		Checkbox.click();

		driver.findElement(By.xpath("//*[@id=\"AccountFrm\"]/div[5]/div/div/button")).click();

	}

	@Test(priority = 2)
	public void testlogout() throws InterruptedException {

		WebElement Navbar = driver.findElement(By.linkText("Welcome " + "back " + GlobalUserName));

		Actions action = new Actions(driver);

		action.moveToElement(Navbar).perform();

		Thread.sleep(2000);

		WebElement logout = driver.findElement(By.linkText("Not " + GlobalUserName + "? Logoff"));
		Thread.sleep(2000);
		logout.click();
		Thread.sleep(2000);

	}

	@Test(priority = 3)

	public void loginwithsameuser() throws InterruptedException {
		Thread.sleep(2000);
		WebElement Loginbtn = driver.findElement(By.linkText("Login or register"));
		Thread.sleep(2000);
		Loginbtn.click();

		WebElement LogiNnameinput = driver.findElement(By.id("loginFrm_loginname"));
		LogiNnameinput.sendKeys(LoginName);

		WebElement Passwordinput = driver.findElement(By.id("loginFrm_password"));
		Passwordinput.sendKeys(pass);

		driver.findElement(By.xpath("//*[@id=\"loginFrm\"]/fieldset/button")).click();
		Thread.sleep(2000);

	}

	public void AddItemToThecart() throws InterruptedException {

		String[] WebSitesForTheItems = { "https://automationteststore.com/index.php?rt=product/category&path=68",
				"https://automationteststore.com/index.php?rt=product/category&path=36",
				"https://automationteststore.com/index.php?rt=product/category&path=43",
				"https://automationteststore.com/index.php?rt=product/category&path=49",
				"https://automationteststore.com/index.php?rt=product/category&path=58",
				"https://automationteststore.com/index.php?rt=product/category&path=52",
				"https://automationteststore.com/index.php?rt=product/category&path=65" };

		int randomIndex = rand.nextInt(WebSitesForTheItems.length);
		driver.get(WebSitesForTheItems[randomIndex]);

		// define for the webelement which is a UL tag
		WebElement ListOfITem = driver.findElement(By.cssSelector(".thumbnails.row"));

		// each UL tag has always a li items (list items ) here i am getting the total
		// number of li inside the ul
		int totalNumberOfItems = ListOfITem.findElements(By.tagName("li")).size();
		// create a random index based on the total number of items that i got in the
		// previous line
		int RandomIdexForTheItem = rand.nextInt(totalNumberOfItems);

		// sleep just to see the results before click on the sub category
		Thread.sleep(5000);

		// inside the sub category i need selenium to click on a random sub category
		ListOfITem.findElements(By.tagName("li")).get(RandomIdexForTheItem).click();

		// i defined the container of all items in a Container variable to loop over all
		// items inside using the css selector
		WebElement Container = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));
		// we need to see how many items that selenium found inside the container
		int numberOfPRoducts = Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();
		// random index for the item to randomly select one item each time
		int randomIndexForTheproduct = rand.nextInt(numberOfPRoducts);

		// the randomly item that we generated using rand.nextInt we need to click on
		// that item
		Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(randomIndexForTheproduct).click();
		Thread.sleep(5000);

		// here i have an UL tag which contains either the Add to cart or out of the
		// stock
		WebElement ULList = driver.findElement(By.className("productpagecart"));

		// inside the UL that i found in the previous line of code i am searching about
		// the a tag noting:-
		// (a) tag if the item in the stock and span tag if the item out of the stock
		int LiItem = ULList.findElements(By.tagName("li")).get(0).findElements(By.tagName("span")).size();

		// this liItem will give me either 0 or 1 ( 0 if the item out of the stock so it
		// will go back to the home page and print a message says that the item out of
		// the stock , if it gives 1 that means the item inside the stock and i can
		// press on the add to cart button by using it's name which is cart
		if (LiItem > 0) {
			driver.get(MyWebsiteurl);

			System.out.println("sorry the item out of the stock ");
			String ExpectedResult = "https://automationteststore.com/";
			String ActualResult = driver.getCurrentUrl();

			org.testng.Assert.assertEquals(ActualResult, ExpectedResult, "redirected to home page");

		} else {

			driver.findElement(By.className("cart")).click();
			;
			Thread.sleep(2000);
			String ActualResult = driver.findElement(By.className("heading1")).getText();
			String ExpectedResult = "Shopping Cart";

			org.testng.Assert.assertEquals(ActualResult, ExpectedResult.toUpperCase());
			// l hoon kafiii
			boolean ExpectedValueForCheckOut = true;
			boolean ActualValueForCheckOut = driver.findElement(By.id("cart_checkout1")).isDisplayed();
			org.testng.Assert.assertEquals(ActualValueForCheckOut, ExpectedValueForCheckOut,
					"Added to cart Successfully ");
		}
	}
}
