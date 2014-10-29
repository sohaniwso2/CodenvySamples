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
package com.codenvy.ide.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.codenvy.ide.shared.Constants;
import com.codenvy.ide.shared.GWTIDConstants;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@SuppressWarnings("deprecation")
public class UITestProject implements EntryPoint {

	final RootPanel root = RootPanel.get();

	// Create UI widgets
	RadioButton newProxyOption = new RadioButton(Constants.RADIO_GROUP, Constants.WSO2_NEW);
	RadioButton importProxyOption = new RadioButton(Constants.RADIO_GROUP, Constants.WSO2_IMPORT);
	Label proxyNameLabel = new Label(Constants.PROXY_NAME_LABEL);
	TextBox proxyNameBox = new TextBox();
	Label proxyTypeLabel = new Label(Constants.PROXY_TYPE_LABEL);
	ListBox proxyTypeBox = new ListBox();
	Label proxySaveLocation = new Label(Constants.PROXY_SAVE_LOCATION);
	TextBox proxySaveBox = new TextBox();
	Label proxyFileLabel = new Label(Constants.PROXY_FILE_LABEL);
	TextBox proxyFileBox = new TextBox();
	Label proxyEndpointLabel = new Label(Constants.ENDPOINT_URL);
	TextBox proxyEndpointBox = new TextBox();
	Button button = new Button(Constants.FINISH_BUTTON);

	Grid grid = new Grid(6, 2);

	public void onModuleLoad() {

		// Defines debug IDs
		button.ensureDebugId(GWTIDConstants.BUTTON_DEBUG_ID);
		newProxyOption.ensureDebugId(GWTIDConstants.NEW_PROXY_DEBUG_ID);
		importProxyOption.ensureDebugId(GWTIDConstants.IMPORT_PROXY_DEBUG_ID);
		proxyNameBox.ensureDebugId(GWTIDConstants.PROXY_NAME_DEBUG_ID);
		proxyTypeBox.ensureDebugId(GWTIDConstants.PROXY_TYPE_DEBUG_ID);
		proxySaveBox.ensureDebugId(GWTIDConstants.PROXY_LOCATION_DEBUG_ID);
		proxyFileBox.ensureDebugId(GWTIDConstants.PROXY_FILE_DEBUG_ID);
		proxyEndpointBox.ensureDebugId(GWTIDConstants.PROXY_ENDPOINT_DEBUG_ID);

		// Adds items to the list
		proxyTypeBox.addItem(Constants.CUSTOM_PROXY);
		proxyTypeBox.addItem(Constants.PASS_THROUGH_PROXY);
		proxyTypeBox.addItem(Constants.TRANSFORMER_PROXY);
		proxyTypeBox.addItem(Constants.LOG_FORWARD_PROXY);
		proxyTypeBox.addItem(Constants.WSDL_BASED_PROXY);
		proxyTypeBox.addItem(Constants.SECURE_PROXY);

		grid.setWidget(0, 0, newProxyOption);
		grid.setWidget(0, 1, importProxyOption);
		root.add(grid);

		 /*Allows relevant fields to be visible when selecting the new proxy
		 option*/
		//TODO use for hadler for deprecated methods
		newProxyOption.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {

				grid.setWidget(1, 0, proxyNameLabel);
				grid.setWidget(1, 1, proxyNameBox);
				grid.setWidget(2, 0, proxyTypeLabel);
				grid.setWidget(2, 1, proxyTypeBox);
				grid.setWidget(3, 0, proxySaveLocation);
				grid.setWidget(3, 1, proxySaveBox);
				grid.setWidget(5, 1, button);
				root.add(grid);
			}
		});

		 /*Allows relevant fields to be visible when selecting the import proxy
		 option*/
		importProxyOption.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {

				grid.setWidget(1, 0, proxyFileLabel);
				grid.setWidget(1, 1, proxyFileBox);
				grid.setWidget(2, 0, proxySaveLocation);
				grid.setWidget(2, 1, proxySaveBox);
				grid.setWidget(5, 1, button);
				root.add(grid);
			}
		});

		// Allows relevant fields to be visible when selecting the list item
		proxyTypeBox.addChangeListener(new ChangeListener() {

			@Override
			public void onChange(Widget sender) {
				// Get the index of the selected item
				int itemSelected = proxyTypeBox.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = proxyTypeBox.getValue(itemSelected);
				if (itemStringSelected.equals(Constants.TRANSFORMER_PROXY)) {
					grid.setWidget(4, 0, proxyEndpointLabel);
					grid.setWidget(4, 1, proxyEndpointBox);
					root.add(grid);
				} else {
					grid.remove(proxyEndpointLabel);
					grid.remove(proxyEndpointBox);
					root.add(grid);
				}
			}
		});

		// Handles the button action when creating and importing proxies
		button.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {

				if (!proxyNameBox.getValue().isEmpty() && !proxySaveBox.getValue().isEmpty()) {
					grid.setVisible(false);
					root.add(new Label("Proxy Created Successfully...."));

				} else if (!proxyFileBox.getValue().isEmpty() && !proxySaveBox.getValue().isEmpty()) {
					grid.setVisible(false);
					root.add(new Label("Proxy Imported Successfully...."));
				} else {
					Window.alert("Please insert Data...");
				}
			}
		});
	}

}
