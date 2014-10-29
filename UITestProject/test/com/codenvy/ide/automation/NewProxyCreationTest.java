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

import com.codenvy.ide.pageObjects.NewProxyObjects;
import com.codenvy.ide.shared.GWTIDConstants;

public class NewProxyCreationTest {

	private static Logger log = LoggerFactory.getLogger(NewProxyCreationTest.class);
	private static final String ProxyName = "TestProxy";
	private static final String ProxyLocation = "TestESBProject";
	private static final String ProxyType = "Custom Proxy";

	static WebDriver driver;

	NewProxyObjects newProxyObject;

	@BeforeClass
	public void openBrowser() throws InterruptedException {
		//TODO use try- catch, log and throw
		System.setProperty("webdriver.firefox.profile", "default");
		// Initialize web driver
		driver = new FirefoxDriver();
		// Allows WebDriver to poll the DOM for a certain duration until the
		// elements load
		
		//TODO use some event mechanism to identify dynamic load time without using a constant value
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// Navigates to the web page
		driver.get(GWTIDConstants.WEB_APP_URL);
		// Initialize elements to be used
		newProxyObject = PageFactory.initElements(driver, NewProxyObjects.class);
	}

	@Test(description = "create a new proxy")
	public void newProxyCreation() {
		try {
			newProxyObject.selectNewProxyOption();
			newProxyObject.insertNewProxyDetails(ProxyName, ProxyType, ProxyLocation);
			newProxyObject.finishButtonAction();
		} catch (Exception e) {
			log.error("An unexpected error occurerd ", e);
		}
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}