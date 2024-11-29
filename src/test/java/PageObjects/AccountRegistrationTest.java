package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationTest extends BasePage {

	public AccountRegistrationTest(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(xpath = "(//input[@id='input-lastname'])")
	WebElement txtltname;
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement txtPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//*[@id=\"content\"]/h1")
	WebElement msgConfirmation;

	public void setFirstname(String fname) {
		txtFirstname.sendKeys(fname);
	}

	public void setLastname(String lname) {
		txtltname.sendKeys(lname);
	}

	public void setEmal(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String tele) {
		txtTelephone.sendKeys(tele);
	}

	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}

	public void setConfirmpassword(String cpass) {
		txtConfirmPassword.sendKeys(cpass);
	}

	public void setPolicy() {
		txtPolicy.click();;
	}

	public void setbtnContinue() {
		btnContinue.click();
	}

	public String setmsgconfirm() {
		
		try {
			return (msgConfirmation.getText());
		}catch (Exception e) {
			return(e.getMessage());
		}
	}

}
