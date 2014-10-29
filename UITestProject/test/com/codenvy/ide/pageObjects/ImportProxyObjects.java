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
package com.codenvy.ide.pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codenvy.ide.shared.GWTIDConstants;

public class ImportProxyObjects {

	private static Logger log = LoggerFactory.getLogger(NewProxyObjects.class);

	final WebDriver driver;

	// Create web elements using GWT IDs
	@FindBy(how = How.ID, using = GWTIDConstants.IMPORT_PROXY_GWT_ID)
	WebElement importProxyOption;

	@FindBy(how = How.ID, using = GWTIDConstants.PROXY_FILE_GWT_ID)
	WebElement proxyFileElement;

	@FindBy(how = How.ID, using = GWTIDConstants.PROXY_LOCATION_GWT_ID)
	WebElement proxyLocationElement;

	@FindBy(how = How.ID, using = GWTIDConstants.BUTTON_GWT_ID)
	WebElement finishButtonElement;

	public ImportProxyObjects(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Selects the import proxy option
	 * @throws Exception
	 */
	public void selectImportProxyOption() throws Exception {

		try {
			importProxyOption.click();

		} catch (TimeoutException toe) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(importProxyOption));

		} catch (Exception e) {
			throw (e);
		}

	}

	/**
	 * Insert details when importing the proxy
	 * 
	 * @param fileName  proxy file
	 * @param proxyLocation  save location
	 * @throws Exception
	 */
	public void insertImportProxyDetails(String fileName, String proxyLocation) throws Exception {
		try {
			proxyFileElement.sendKeys(fileName);
			proxyLocationElement.sendKeys(proxyLocation);
		} catch (TimeoutException toe) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.textToBePresentInElement(proxyFileElement, fileName));
			wait.until(ExpectedConditions.textToBePresentInElement(proxyLocationElement,
					proxyLocation));
		} catch (NoSuchElementException e) {
			log.error("Proxy Import field element not found", e);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Performs the button action
	 * @throws Exception
	 */
	public void fishButtonAction() throws Exception {

		try {
			finishButtonElement.click();
		} catch (TimeoutException toe) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(finishButtonElement));

		} catch (NoSuchElementException e) {
			log.error("Button element is not available", e);
		} catch (Exception e) {
			throw (e);
		}
	}
}
