package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends HomePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtemail;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtpass;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnlogin;

	public void setEmail(String email) {
		txtemail.sendKeys(email);
	}

	public void setPass(String pass) {
		txtpass.sendKeys(pass);
	}

	public void clicLogin() {
		btnlogin.click();
	}

}
