package com.bookit.step_definitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.bookit.utilities.DBUtils;
import com.bookit.utilities.Driver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	
	@Before("@db")
	public void createConnection() {
		DBUtils.createConnection();
		
	}
	
	@After("@db")
	public void afterDbHook() {
		DBUtils.destroy();
	}

	@Before
	public void setUp() {

		// we put logic that should apply to every scenario
		//Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// System.out.println("Before");
	}

	@After
	public void tearDown(Scenario scenario) {
		// only takes a screenshot if the scenario fails
		//if (scenario.isFailed()) {
			// taking a screenshot
			//final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
			// adding the screenshot to the report
			//scenario.embed(screenshot, "image/png");
		}
		//Driver.closeDriver();
	}
//}
