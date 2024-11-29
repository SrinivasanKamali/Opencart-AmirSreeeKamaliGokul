package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.BasePage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import Utilities.DataProviders;
import testBase.BaseClass;

public class T003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups= {"datadriven"})

	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException {

		logger.info("***** Started T003_LoginDDT *****");
		try {
			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clicklogin();
			hp.clickMyAccount();

			// Login
			LoginPage lp = new LoginPage(driver);
			lp.setPass(email);
			lp.setPass(pwd);
			lp.clicklogin();

			// MyAccount
			MyAccountPage maxx = new MyAccountPage(driver);
			boolean target = maxx.isMyAccountPageExists();

			if (exp.equalsIgnoreCase("Valid")) {
				if (target == true) {
					maxx.clicklogout();
					Assert.assertTrue(true);

				} else {
					Assert.assertFalse(false);
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) {
				if (target == true) {
					maxx.clicklogout();
					Assert.assertTrue(false);

				} else {
					Assert.assertFalse(true);
				}
			}

		} catch (Exception e) {
			Assert.fail();
		}
		Thread.sleep(3000);

		logger.info("***** Finished TC003_LoginDDT  *****");
	}
}
