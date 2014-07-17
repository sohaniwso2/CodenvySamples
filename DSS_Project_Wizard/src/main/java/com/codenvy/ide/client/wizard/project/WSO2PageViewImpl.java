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
package com.codenvy.ide.client.wizard.project;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.inject.Inject;
import javax.annotation.Nonnull;
/**
 * The implementation of {@link WSO2PageView}.
 *
 * @author Sohani
 */
public class WSO2PageViewImpl extends Composite implements WSO2PageView {

    @Inject
    public WSO2PageViewImpl(WSO2PageViewImplUiBinder ourUiBinder) {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
    
    @UiField
    TextBox fileName; 
    @UiField
    TextBox serviceNamespace;
    @UiField
    TextBox serviceGroup;
    @UiField
    TextBox description;
    @UiField
    TextBox id; 
    @UiField
    TextBox type;
    @UiField
    TextBox engine;
    @UiField
    TextBox driverClass;
    @UiField
    TextBox url; 
    @UiField
    TextBox uname;
    @UiField
    TextBox pass;
      
    
    private ActionDelegate delegate;
    
   /** {@inheritDoc} */
    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }
    
    @UiHandler("fileName")
    public void onFileNameChanged(KeyUpEvent event) {
        delegate.onValueChanged();
    }
    
    @UiHandler("serviceNamespace")
    public void onServiceNamespaceChanged(KeyUpEvent event) {
        delegate.onValueChanged();
    }
    
    @UiHandler("serviceGroup")
    public void onSeviceGroupChanged(KeyUpEvent event) {
        delegate.onValueChanged();
    }
    
    @UiHandler("description")
    public void onDescriptionChanged(KeyUpEvent event) {
        delegate.onValueChanged();
    }
    
    @UiHandler("id")
    public void onIDChanged(KeyUpEvent event) {
        delegate.onValueChanged();
    }
    
    @UiHandler("type")
    public void onTypeChanged(KeyUpEvent event) {
        delegate.onValueChanged();
    }
    
    @UiHandler("engine")
    public void onEngineChanged(KeyUpEvent event) {
        delegate.onValueChanged();
    }
    
    @UiHandler("driverClass")
    public void onDriverClassChanged(KeyUpEvent event) {
        delegate.onValueChanged();
    }
    @UiHandler("url")
    public void onURLChanged(KeyUpEvent event) {
        delegate.onValueChanged();
    }
    
    @UiHandler("uname")
    public void onUnameroupChanged(KeyUpEvent event) {
        delegate.onValueChanged();
    }
    
    @UiHandler("pass")
    public void onPassChanged(KeyUpEvent event) {
        delegate.onValueChanged();
    }

     /** {@inheritDoc} */
    @Nonnull
    @Override
    public String getFileName() {
        return fileName.getText();
    }

    /** {@inheritDoc} */
    @Override
    public void setFileName(@Nonnull String fileName) {
        this.fileName.setText(fileName);
    }
    
    
     /** {@inheritDoc} */
    @Nonnull
    @Override
    public String getServiceNamespace() {
        return serviceNamespace.getText();
    }

    /** {@inheritDoc} */
    @Override
    public void setServiceNamespace(@Nonnull String serviceNamespace) {
        this.serviceNamespace.setText(serviceNamespace);
    }
    
    
     /** {@inheritDoc} */
    @Nonnull
    @Override
    public String getServiceGroup() {
        return serviceGroup.getText();
    }

    /** {@inheritDoc} */
    @Override
    public void setServiceGroup(@Nonnull String serviceGroup) {
        this.serviceGroup.setText(serviceGroup);
    }
    
     /** {@inheritDoc} */
    @Nonnull
    @Override
    public String getDescription() {
        return description.getText();
    }

    /** {@inheritDoc} */
    @Override
    public void setDescription(@Nonnull String description) {
        this.description.setText(description);
    }
    
     /** {@inheritDoc} */
    @Nonnull
    @Override
    public String getID() {
        return id.getText();
    }

    /** {@inheritDoc} */
    @Override
    public void setID(@Nonnull String id) {
        this.id.setText(id);
    }
    
    
     /** {@inheritDoc} */
    @Nonnull
    @Override
    public String getType() {
        return type.getText();
    }

    /** {@inheritDoc} */
    @Override
    public void setType(@Nonnull String type) {
        this.type.setText(type);
    }
    
    
     /** {@inheritDoc} */
    @Nonnull
    @Override
    public String getEngine() {
        return engine.getText();
    }

    /** {@inheritDoc} */
    @Override
    public void setEngine(@Nonnull String engine) {
        this.engine.setText(engine);
    }
    
     /** {@inheritDoc} */
    @Nonnull
    @Override
    public String getDriverClass() {
        return driverClass.getText();
    }

    /** {@inheritDoc} */
    @Override
    public void setDriverClass(@Nonnull String driverClass) {
        this.driverClass.setText(driverClass);
    }
   
     /** {@inheritDoc} */
    @Nonnull
    @Override
    public String getURL() {
        return url.getText();
    }

    /** {@inheritDoc} */
    @Override
    public void setURL(@Nonnull String url) {
        this.url.setText(url);
    }
   
     /** {@inheritDoc} */
    @Nonnull
    @Override
    public String getUname() {
        return uname.getText();
    }

    /** {@inheritDoc} */
    @Override
    public void setUname(@Nonnull String uname) {
        this.uname.setText(uname);
    }
    
     /** {@inheritDoc} */
    @Nonnull
    @Override
    public String getPass() {
        return pass.getText();
    }

    /** {@inheritDoc} */
    @Override
    public void setPass(@Nonnull String pass) {
        this.pass.setText(pass);
    }
    
   
    interface WSO2PageViewImplUiBinder extends UiBinder<DockLayoutPanel, WSO2PageViewImpl> {
    }
}
