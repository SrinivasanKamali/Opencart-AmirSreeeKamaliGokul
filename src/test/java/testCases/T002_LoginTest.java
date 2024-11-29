package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import testBase.BaseClass;

public class T002_LoginTest extends BaseClass {

	@Test(groups={"Sanity"})

	public void verify_login() {

		logger.info("****** Starting T002_LoginTest *****");
		try {
			// Homepage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clicklogin();

			// LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPass(p.getProperty("pass"));
			lp.clicLogin();

			// MyAccount
			MyAccountPage mypage = new MyAccountPage(driver);
			boolean target = mypage.isMyAccountPageExists();
			// Assert.assertEquals(target, true,"Login Failed");
			Assert.assertTrue(target);
		} catch (Exception e) {
			Assert.fail();
		}

	}

}
