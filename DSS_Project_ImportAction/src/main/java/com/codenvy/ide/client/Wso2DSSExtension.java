package com.codenvy.ide.client;


import com.codenvy.ide.api.extension.Extension;
import com.codenvy.ide.client.wizard.project.newP.WSO2PagePresenter;
import com.codenvy.ide.api.ui.action.ActionManager;
import com.codenvy.ide.api.ui.action.DefaultActionGroup;
import com.codenvy.ide.api.resources.ResourceProvider;
import com.codenvy.ide.api.ui.wizard.ProjectTypeWizardRegistry;
import com.codenvy.ide.api.ui.wizard.ProjectWizard;
import com.codenvy.ide.api.ui.wizard.WizardContext;
import com.codenvy.ide.client.action.ImportAction;
import com.codenvy.ide.client.action.WSO2ProjectActionGroup;
import com.codenvy.ide.client.wizard.project.option.WSO2OptionPagePresenter;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import static com.codenvy.ide.api.ui.action.Constraints.FIRST;
import static com.codenvy.ide.api.ui.action.Constraints.LAST;
import static com.codenvy.ide.api.ui.action.IdeActions.GROUP_MAIN_CONTEXT_MENU;
import static com.codenvy.ide.api.ui.action.IdeActions.GROUP_MAIN_MENU;


/**
 * @author Sohani
 */
@Singleton
@Extension(title = "Developer Studio - DSS", version = "1.0.0")

public class Wso2DSSExtension {
    
     public static final WizardContext.Key<Boolean> PAGE2_NEXT = new WizardContext.Key<Boolean>("Page 2 next");

     
    
    @Inject
    public Wso2DSSExtension(Provider<WSO2OptionPagePresenter> wso2OptionPagePresenter,
                            Provider<WSO2PagePresenter> wso2PagePresenter,
                         ProjectTypeWizardRegistry projectTypeWizardRegistry,
                         ProjectWizard projectWizard) {

        projectWizard.addPage(wso2OptionPagePresenter);
        projectWizard.addPage(wso2PagePresenter);
        projectTypeWizardRegistry.addWizard("DSSConfigurationProject", projectWizard);
    }
    
     @Inject
    public void initActions(ResourceProvider resourceProvider,
                            ActionManager actionManager,
                            ImportAction importSynapseAction) {

        DefaultActionGroup wso2MainMenu = (DefaultActionGroup)actionManager.getAction(GROUP_MAIN_MENU);
        DefaultActionGroup wso2ContextMenu = (DefaultActionGroup)actionManager.getAction(GROUP_MAIN_CONTEXT_MENU);

        DefaultActionGroup wso2MainGroup = new DefaultActionGroup("WSO2", true, actionManager);
        actionManager.registerAction("WSO2MainActionGroup", wso2MainGroup);

 
        DefaultActionGroup wso2ActionGroup = new WSO2ProjectActionGroup(actionManager, resourceProvider);
        actionManager.registerAction("WSO2ProjectActionGroup", wso2ActionGroup);

        DefaultActionGroup wso2NewGroup = new DefaultActionGroup("newAction", true, actionManager);
        actionManager.registerAction("WSO2NewResourceGroup", wso2NewGroup);

        DefaultActionGroup wso2ImportGroup = new DefaultActionGroup("Import", true, actionManager);
        actionManager.registerAction("WSO2ImportResourceGroup", wso2ImportGroup);

        actionManager.registerAction("ImportAction", importSynapseAction);
        
        wso2ActionGroup.add(wso2NewGroup);
        wso2ActionGroup.add(wso2ImportGroup);

        wso2MainGroup.add(wso2ActionGroup);
      
        wso2ImportGroup.add(importSynapseAction);

        wso2MainMenu.add(wso2MainGroup, LAST);

        wso2ContextMenu.add(wso2MainGroup, FIRST);
    }
    
}


  
