/*
 * Copyright 2014 Codenvy, S.A.
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
package com.codenvy.ide.client.wizard.project.imports;


import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import javax.validation.constraints.NotNull;

/**
 * The implementation of {@link ImportFileView}.
 *
 * @author Sohani
 */
public class ImportFileViewImpl extends DialogBox implements ImportFileView {
    interface ImportFileViewImplUiBinder extends UiBinder<Widget, ImportFileViewImpl> {
    }

    private ActionDelegate delegate;
    @UiField
    Button    btnCancel;
    @UiField
    Button    btnImport;
    @UiField
    FormPanel uploadForm;
    @UiField
    TextBox   url;
    @UiField
    RadioButton useUrl;
    @UiField
    RadioButton useLocalPath;
    @UiField
    HTML        message;
    FileUpload file;

    @Inject
    public ImportFileViewImpl(ImportFileViewImplUiBinder ourUiBinder) {
       
        Widget widget = ourUiBinder.createAndBindUi(this);

        this.setText("Import");
        this.setWidget(widget);

        bind();
    }

    /** Bind handlers. */
    private void bind() {
        uploadForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {

            @Override
            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
                delegate.onSubmitComplete(event.getResults());
            }
        });
    }

    /** {@inheritDoc} */
    @Override
    public String getUrl() {
        return url.getText();
    }

    /** {@inheritDoc} */
    @Override
    public void setUrl(@NotNull String url) {
        this.url.setText(url);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isUseUrl() {
        return useUrl.getValue();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isUseLocalPath() {
        return useLocalPath.getValue();
    }

    /** {@inheritDoc} */
    @Override
    public void setUseLocalPath(boolean isUseLocalPath) {
        useLocalPath.setValue(isUseLocalPath);
    }

    @Override
    public void setUseUrl(boolean isUseUrl) {
        useUrl.setValue(isUseUrl);
    }

    /** {@inheritDoc} */
    @Override
    public String getFileName() {
        String fileName = file.getFilename();
        if (fileName.indexOf("\\") > 0) {
            return fileName.substring(fileName.lastIndexOf('\\') + 1, fileName.length());
        } else {
            return fileName;
        }
    }

    @Override
    public void setMessage(@NotNull String message) {
        this.message.setHTML(message);
    }

    /** {@inheritDoc} */
    @Override
    public void setEnabledImportButton(boolean enabled) {
        btnImport.setEnabled(enabled);
    }

    /** {@inheritDoc} */
    @Override
    public void setEnterUrlFieldEnabled(boolean enabled) {
        url.setEnabled(enabled);
    }

    /** {@inheritDoc} */
    @Override
    public void setAction(@NotNull String url) {
        uploadForm.setAction(url);
    }

    /** {@inheritDoc} */
    @Override
    public void submit() {
        uploadForm.submit();
    }

    /** {@inheritDoc} */
    @Override
    public void close() {
        this.hide();

        uploadForm.remove(file);
        file = null;

        url.setText("");
    }

    /** {@inheritDoc} */
    @Override
    public void showDialog() {
        uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
        uploadForm.setMethod(FormPanel.METHOD_POST);

        VerticalPanel panel = new VerticalPanel();
        uploadForm.setWidget(panel);

        file = new FileUpload();
        file.setName("ImportFile");
        file.setHeight("26px");
        file.setWidth("100%");
        file.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent changeEvent) {
                if (!file.getFilename().endsWith(".dbs")) {
                    delegate.onFileNameChangedWithInvalidFormat();
                } else {
                    delegate.onFileNameChanged();
                }
            }
        });
        panel.add(file);
        this.center();
        this.show();
    }

    /** {@inheritDoc} */
    @Override
    public void setDelegate(ActionDelegate actionDelegate) {
        this.delegate = actionDelegate;
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("btnCancel")
    public void onCancelClicked(ClickEvent event) {
        delegate.onCancelClicked();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("btnImport")
    public void onImportClicked(ClickEvent event) {
        delegate.onImportClicked();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("useUrl")
    public void onUseUrlClicked(ClickEvent event) {
        file.setEnabled(false);
        delegate.onUseUrlChosen();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("useLocalPath")
    public void onUseLocalPathClicked(ClickEvent event) {
        file.setEnabled(true);
        delegate.onUseLocalPathChosen();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("url")
    public void onUrlChanged(KeyUpEvent event) {
        delegate.onUrlChanged();
    }
}