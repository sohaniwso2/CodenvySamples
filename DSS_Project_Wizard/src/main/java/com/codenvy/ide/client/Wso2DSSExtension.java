package com.codenvy.ide.client;


import com.codenvy.ide.api.extension.Extension;
import com.codenvy.ide.client.wizard.project.WSO2PagePresenter;
import com.codenvy.ide.client.wizard.project.page2.WSO2DataSourcePagePresenter;
import com.codenvy.ide.api.ui.wizard.ProjectTypeWizardRegistry;
import com.codenvy.ide.api.ui.wizard.ProjectWizard;
import com.codenvy.ide.api.ui.wizard.WizardContext;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;


/**
 * @author Sohani
 */
@Singleton
@Extension(title = "Developer Studio - DSS", version = "1.0.0")

public class Wso2DSSExtension {
    
    @Inject
    public Wso2DSSExtension(Provider<WSO2PagePresenter> wso2PagePresenter,
                         ProjectTypeWizardRegistry projectTypeWizardRegistry,
                         ProjectWizard projectWizard) {

        projectWizard.addPage(wso2PagePresenter);
        projectTypeWizardRegistry.addWizard("DSSConfigurationProject", projectWizard);
    }

    
}


  
