package testCases;

import java.time.Duration;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.AccountRegistrationTest;
import PageObjects.HomePage;
import testBase.BaseClass;

public class T001_AccountRegistration extends BaseClass {

	// public WebDriver driver;

	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
		logger.info("****** Starting T001_AccountRegistration ******");
		try {
			HomePage hm = new HomePage(driver);
			hm.clickMyAccount();
			logger.info("Clicked on MyAccount Link..");
			hm.clickRegisterpage();
			logger.info("Clicked on Register Link..");
			AccountRegistrationTest regpage = new AccountRegistrationTest(driver);
			// regpage.setFirstname(RandomString().toUpperCase()); //common use from excel
			logger.info("Providing Customer Details...");
			regpage.setFirstname(randomString().toUpperCase());
			regpage.setLastname(randomString().toUpperCase());
			regpage.setEmal(randomString() + "@gmail.com");
			regpage.setTelephone(randomNumber());
			String password = randomAlphNumeric();
			regpage.setPassword(password);
			regpage.setConfirmpassword(password);
			regpage.setPolicy();
			regpage.setbtnContinue();
			logger.info("Validating Expected Message..");
			String conmsg = regpage.setmsgconfirm();
			Assert.assertEquals(conmsg, "Your Account Has Been Created!");
		} catch (Exception e) {
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.fail();
		}

	}

}
