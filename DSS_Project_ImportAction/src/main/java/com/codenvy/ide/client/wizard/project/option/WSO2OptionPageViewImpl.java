/*
 * Copyright [2014] Codenvy, S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codenvy.ide.client.wizard.project.option;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.uibinder.client.UiHandler;
import com.google.inject.Inject;

/**
 * The implementation of {@link WSO2PageView}.
 *
 * @author Sohani
 */
public class WSO2OptionPageViewImpl extends Composite implements WSO2OptionPageView {

    @Inject
    public WSO2OptionPageViewImpl(WSO2OptionPageViewImplUiBinder ourUiBinder) {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
    
    @UiField
    RadioButton page2;
    @UiField
    RadioButton page3;
  
   
      
    
    private ActionDelegate delegate;
    
     /** {@inheritDoc} */
    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }
  
      
    /** {@inheritDoc} */
    @Override
    public boolean isPage2Next() {
        return page2.getValue();
    }
    
    /** {@inheritDoc} */
    @Override
    public void setPage2Next(boolean page2Next) {
        if (page2Next) {
            page2.setValue(true);
        } else {
            page3.setValue(true);
        }
    }
    
   
    @UiHandler("page2")
    public void onPage2Clicked(ClickEvent event) {
        delegate.onPage2Chosen();
    }

    @UiHandler("page3")
    public void onPage3Clicked(ClickEvent event) {
        delegate.onPage3Chosen();
    }
    
 
    
    interface WSO2OptionPageViewImplUiBinder extends UiBinder<DockLayoutPanel, WSO2OptionPageViewImpl> {
    }
}
