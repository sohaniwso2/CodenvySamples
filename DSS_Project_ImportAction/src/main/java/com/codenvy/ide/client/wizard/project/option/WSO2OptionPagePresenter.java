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

import com.codenvy.api.project.gwt.client.ProjectServiceClient;
import com.codenvy.ide.api.resources.ResourceProvider;

import com.codenvy.ide.api.ui.wizard.AbstractWizardPage;
import com.codenvy.ide.dto.DtoFactory;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.codenvy.ide.client.Wso2DSSExtension.PAGE2_NEXT;



/**
 * The wizard page provides creating an empty ESB configuration project.
 *
 * @author Sohani
 */
public class WSO2OptionPagePresenter extends AbstractWizardPage implements WSO2OptionPageView.ActionDelegate {
    private final WSO2OptionPageView         view;

    

    @Inject
    public WSO2OptionPagePresenter(WSO2OptionPageView view,
                             ProjectServiceClient projectServiceClient,
                             ResourceProvider resourceProvider,
                             DtoFactory factory) {
        super("Select an Option", null);

        this.view = view;
        this.view.setDelegate(this);
    }
    
   

    @Nullable
    @Override
    public String getNotice() {
        return null;
    }

    @Nonnull
    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public void focusComponent() {
        view.setPage2Next(true);
 
        wizardContext.putData(PAGE2_NEXT, true);

    }

    @Override
    public void removeOptions() {
         wizardContext.removeData(PAGE2_NEXT);
         
         
    }

    @Override
    public void go(AcceptsOneWidget container) {
        container.setWidget(view);
    }
    

    /** {@inheritDoc} */
    @Override
    public void onPage2Chosen() {
        wizardContext.putData(PAGE2_NEXT, view.isPage2Next());
    }

    /** {@inheritDoc} */
    @Override
    public void onPage3Chosen() {
        wizardContext.putData(PAGE2_NEXT, view.isPage2Next());
    }
    
   
    

}
