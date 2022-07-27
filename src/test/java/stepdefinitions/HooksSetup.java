package stepdefinitions;

import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.ibsplc.common.BaseSetup;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class HooksSetup extends BaseSetup {
	
	private BaseSetup base;

	public HooksSetup(BaseSetup base) {
		this.base = base;
	}
	
//	@Before
//	public void before() {
//		base.a = "b";
//		System.out.println("before: "+base.a);
//	}
//	
//	@After
//	public void after(Scenario scenario) {
//		System.out.println("after: "+base.a );
//	}

	@Before
	public void before() throws MalformedURLException {
		System.out.println("before");
		base.driver = initializeDriver();
	}

	@After
	public void after(Scenario scenario) {
		System.out.println("after");
		LogStatus runStatus = base.test.getRunStatus();
		if (/*runStatus.toString().equalsIgnoreCase("fail")*/scenario.isFailed()) {
			base.test.log(LogStatus.FAIL, "The test failed");
			captureAndAddScreenshot();
		}
		base.driver.quit();
		extent.endTest(base.test);
	}

	public void captureAndAddScreenshot() {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String path = dtf.format(now).toString().replaceAll(" ", "").replaceAll("/", ".").replaceAll(":", "")
					+ ".png";
			File scrFile = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("./Reports/ScreenShots/" + path));
			String dir = System.getProperty("user.dir") + "\\Reports\\Screenshots\\";
			base.test.log(LogStatus.INFO, test.addScreenCapture(dir + path));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

}
