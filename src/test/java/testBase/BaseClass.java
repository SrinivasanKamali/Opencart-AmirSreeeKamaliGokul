package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Regression", "Sanity", "Master", "datadriven" })

	@Parameters({ "os", "browser" })

	public void setup(String os, String br) throws IOException {

		// loading config
		FileReader file = new FileReader("./src//test//resources//config.properties");

		p = new Properties();
		p.load(file);
		switch (br.toLowerCase()) {

		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid Browser name...!!!");
			return;
		}

		logger = LogManager.getLogger(this.getClass());

		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//driver.get(p.getProperty("appurl1")); // "https://tutorialsninja.com/demo"
		driver.get("https://tutorialsninja.com/demo");
		driver.manage().window().maximize();

	}

	@AfterClass
	public void tearDown() {
		driver.close();

	}

	public String randomString() {
		String generatestring = RandomStringUtils.randomAlphabetic(5);
		return generatestring;
	}

	public String randomNumber() {
		String generatenumber = RandomStringUtils.randomNumeric(10);
		return generatenumber;
	}

	public String randomAlphNumeric() {
		String generatestring = RandomStringUtils.randomAlphabetic(3);
		String generatenumber = RandomStringUtils.randomNumeric(5);

		return (generatestring + "@" + generatenumber);
	}

	public String captureScreen(String tnamee) {
		// TODO Auto-generated method stub
		String timestamp1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourcefile = takeScreenshot.getScreenshotAs(OutputType.FILE);

		String targetfilepath = System.getProperty("user.dir") + "\\screenshots\\" + tnamee + "_" + timestamp1 +".png";
		File targetfile = new File(targetfilepath);

		sourcefile.renameTo(targetfile);

		return targetfilepath;
	}

}
