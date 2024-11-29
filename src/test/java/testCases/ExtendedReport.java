package testCases;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtendedReport implements ITestListener {

	public ExtentSparkReporter sparkreporter;
	public ExtentReports extend;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext context) {

		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt =
		 * new Date(); String currentdatetime = df.format(dt);
		 */

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report" + timestamp + ".html";
		sparkreporter = new ExtentSparkReporter(".\\reports\\" + repName);

		sparkreporter.config().setDocumentTitle("Automation Report");
		sparkreporter.config().setReportName("Functional Testing");
		sparkreporter.config().setTheme(Theme.DARK);

		extend = new ExtentReports();
		extend.attachReporter(sparkreporter);
		extend.setSystemInfo("computer name", "localhost");
		extend.setSystemInfo("Environment", "QA");
		extend.setSystemInfo("Tester Name", "Amirsreekamaligokul");
		extend.setSystemInfo("os", "Windows10");
		extend.setSystemInfo("Browser Name", "Chrome");

		String os = context.getCurrentXmlTest().getParameter("os");
		extend.setSystemInfo("Operating System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extend.setSystemInfo("Browser", browser);

		List<String> inlcudeGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!inlcudeGroups.isEmpty()) {
			extend.setSystemInfo("Groups", inlcudeGroups.toString());
		}

	}

	public void onTestSucess(ITestResult result) {
		test = extend.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test Case Passed is:" + result.getName());

	}

	public void onTestFailure(ITestResult result) {
		test = extend.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, "Test Case Failed is:" + result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgpath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test = extend.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Case Skipped is:" + result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());

	}

	public void onFinish(ITestContext context) {

		extend.flush();

		String path = System.getProperty("user.dir") + "\\reports\\" + repName;
		File ententreport = new File(path);

		try {
			Desktop.getDesktop().browse(ententreport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try

		{
			URL url = new URL("file://" + System.getProperty("user.dir") + "\\reports\\" + repName);

			// create emial message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setSSLOnConnect(true);
			email.setAuthenticator(new DefaultAuthenticator("srinivasan.govindhan1856@gmai.com", "password"));
			email.setFrom("srinivasan.govindhan1856@gmai.com");// sender
			email.setSubject("Test Results");
			email.setMsg("Please Find The Attched Report...");
			email.addTo("srinivasan.govindhan1856@gmai.com"); // receiver
			email.attach(url, "extend report", "Please Check Report...");
			email.send(); // send
		} catch (Exception e) {

		}

	}
}
