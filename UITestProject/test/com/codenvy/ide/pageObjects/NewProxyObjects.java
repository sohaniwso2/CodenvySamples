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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codenvy.ide.shared.GWTIDConstants;

public class NewProxyObjects {

	private static Logger log = LoggerFactory.getLogger(NewProxyObjects.class);

	final WebDriver driver;
	
	// Create web elements using GWT IDs
	@FindBy(how = How.ID, using = GWTIDConstants.NEW_PROXY_GWT_ID)
	WebElement newProxyOption;

	@FindBy(how = How.ID, using = GWTIDConstants.PROXY_NAME_GWT_ID)
	WebElement proxyNameElement;

	@FindBy(how = How.ID, using = GWTIDConstants.PROXY_TYPE_GWT_ID)
	WebElement proxyTypeElement;

	@FindBy(how = How.ID, using = GWTIDConstants.PROXY_LOCATION_GWT_ID)
	WebElement proxyLocationElement;

	@FindBy(how = How.ID, using = GWTIDConstants.BUTTON_GWT_ID)
	WebElement finishButtonElement;

	@FindBy(how = How.ID, using = GWTIDConstants.PROXY_ENDPOINT_GWT_ID)
	WebElement endpointURLElement;

	public NewProxyObjects(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Select the new proxy option
	 * @throws Exception
	 */
	public void selectNewProxyOption() throws Exception {

		try {
			newProxyOption.click();
		} catch (TimeoutException toe) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(newProxyOption));
		} /*catch (Exception e) {
			log.error("",e);
			//throw (e);
		}*/

	}

	/**
	 * Insert details when importing the proxy
	 * @param proxyName Name of the proxy
	 * @param proxyType Type of the proxy
	 * @param proxyLocation save location
	 * @throws Exception
	 */
	public void insertNewProxyDetails(String proxyName, String proxyType, String proxyLocation)
			throws Exception {
		try {
			proxyNameElement.sendKeys(proxyName);
			Select droplist = new Select(proxyTypeElement);
			droplist.selectByVisibleText(proxyType);
			proxyLocationElement.sendKeys(proxyLocation);
		} catch (TimeoutException toe) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.textToBePresentInElement(proxyNameElement, proxyName));
			wait.until(ExpectedConditions.textToBePresentInElement(proxyTypeElement, proxyName));
			wait.until(ExpectedConditions.textToBePresentInElement(proxyLocationElement,
					proxyLocation));
		} catch (NoSuchElementException e) {
			log.error("Proxy Creation field element not found", e);
		} /*catch (Exception e) {
			throw (e);
		}*/
	}

	/**
	 * Performs the button action
	 * @throws Exception
	 */
	public void finishButtonAction() throws Exception {
		try {
			finishButtonElement.click();
		} catch (TimeoutException toe) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(finishButtonElement));
		} catch (NoSuchElementException e) {
			log.error("Button element is not available", e);
		} /*catch (Exception e) {
			throw (e);
		}*/
	}

/**
 * Performs the list box action
 * @param proxyType Type of the proxy
 * @throws Exception
 */
	public void checkListBoxAction(String proxyType) throws Exception {
		try {
			Select droplist = new Select(proxyTypeElement);
			droplist.selectByVisibleText(proxyType);
			proxyTypeElement.click();
		} catch (TimeoutException toe) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(proxyTypeElement));
		} catch (NoSuchElementException e) {
			log.error("List Box element is not available", e);
		} /*catch (Exception e) {
			throw (e);
		}*/
	}

	/**
	 * Insert the Endpoint URL
	 * @param endpointURL URL of the endpoint
	 * @throws Exception
	 */
	public void insertEndpointURL(String endpointURL) throws Exception {
		try {
			endpointURLElement.sendKeys(endpointURL);
		} catch (TimeoutException toe) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.textToBePresentInElement(endpointURLElement, endpointURL));
		} catch (NoSuchElementException e) {
			log.error("Endpoint URL field element not found", e);
		}/* catch (Exception e) {
			throw (e);
		}*/
	}

}
