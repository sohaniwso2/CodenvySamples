/*
 * Copyright (c) 2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codenvy.ide.automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.codenvy.ide.pageObjects.ImportProxyObjects;
import com.codenvy.ide.shared.GWTIDConstants;

public class ImportProxyTest {

	
	private static final Logger log = LoggerFactory.getLogger(NewProxyCreationTest.class);
	//Defines data to be included while automation test runs
	private static final String FileName = "TestProxy_file";
	private static final String ProxyLocation = "TestESBProject";

	static WebDriver driver;
	ImportProxyObjects importProxyObject;

	@BeforeClass
	public void openBrowser() throws InterruptedException {
		System.setProperty("webdriver.firefox.profile", "default");
		// Initialize web driver
		driver = new FirefoxDriver();
		/* Allows WebDriver to poll the DOM for a certain duration until the
		 elements load*/
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// Navigates to the web page
		driver.get(GWTIDConstants.WEB_APP_URL);
        //Initialize elements to be used
		importProxyObject = PageFactory.initElements(driver, ImportProxyObjects.class);
	}

	@Test(description = "import a proxy")
	public void newProxyCreation() {
		try {
			importProxyObject.selectImportProxyOption();
			//Thread.sleep(500);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			importProxyObject.insertImportProxyDetails(FileName, ProxyLocation);
			Thread.sleep(500);
			importProxyObject.fishButtonAction();
			Thread.sleep(500);

		} catch (Exception e) {
			log.error("An unexpected error occurerd ", e);
		}

	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}
