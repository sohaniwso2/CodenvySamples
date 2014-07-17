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

import com.codenvy.api.project.gwt.client.ProjectServiceClient;
import com.codenvy.api.project.shared.dto.ProjectDescriptor;
import com.codenvy.ide.api.resources.ResourceProvider;
import com.codenvy.ide.api.resources.model.Project;
import com.codenvy.ide.api.resources.model.File;
import com.codenvy.ide.api.ui.wizard.AbstractWizardPage;
import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import static com.codenvy.ide.api.ui.wizard.ProjectWizard.PROJECT;
import static com.codenvy.ide.api.ui.wizard.ProjectWizard.PROJECT_NAME;
import static com.codenvy.ide.api.ui.wizard.ProjectWizard.PROJECT_TYPE;
import static com.codenvy.ide.api.ui.wizard.ProjectWizard.PROJECT_VISIBILITY;

/**
 * The wizard page provides creating an empty ESB configuration project.
 *
 * @author Sohani
 */
public class WSO2PagePresenter extends AbstractWizardPage implements WSO2PageView.ActionDelegate {
    private final WSO2PageView         view;
    private final ProjectServiceClient projectServiceClient;
    private final ResourceProvider     resourceProvider;
    private final DtoFactory           factory;
    private String fileName;
    private String serviceNamespace;
    private String serviceGroup;
    private String description;
    private String id;
    private String type;
    private String engine;
    private String driverClass;
    private String url;
    private String uname;
    private String pass;
    

    @Inject
    public WSO2PagePresenter(WSO2PageView view,
                             ProjectServiceClient projectServiceClient,
                             ResourceProvider resourceProvider,
                             DtoFactory factory) {
        super("Create a new Data Service", null);

        this.view = view;
        this.projectServiceClient = projectServiceClient;
        this.resourceProvider = resourceProvider;
        this.factory = factory;
        this.view.setDelegate(this);
    }
    
    
    /** {@inheritDoc} */ 
   @Override
        public void onValueChanged() {
        fileName = view.getFileName();
        serviceNamespace = view.getServiceNamespace();
        serviceGroup = view.getServiceGroup();
        description = view.getDescription();
        id = view.getID();
        type = view.getType();
        engine = view.getEngine();
        driverClass = view.getDriverClass();
        url = view.getURL();
        uname = view.getUname();
        pass = view.getPass();
        
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
    }

    @Override
    public void removeOptions() {
    }

    @Override
    public void go(AcceptsOneWidget container) {
        container.setWidget(view);
    }
    

    
    
   @Override
    public void commit(@NotNull final CommitCallback callback) {
        final ProjectDescriptor projectDescriptor = factory.createDto(ProjectDescriptor.class);
        projectDescriptor.withProjectTypeId(wizardContext.getData(PROJECT_TYPE).getProjectTypeId());

        boolean visibility = wizardContext.getData(PROJECT_VISIBILITY);
        projectDescriptor.setVisibility(visibility ? "public" : "private");
        final String name = wizardContext.getData(PROJECT_NAME);
        final Project project = wizardContext.getData(PROJECT);
        

        if (project != null) {
           if (project.getName().equals(name)) {
             
                updateProject(project, projectDescriptor, callback);
            } else {
                projectServiceClient.rename(project.getPath(), name, null, new AsyncRequestCallback<Void>() {
                    @Override
                    protected void onSuccess(Void result) {
                        project.setName(name);

                        updateProject(project, projectDescriptor, callback);
                    }

                    @Override
                    protected void onFailure(Throwable exception) {
                        callback.onFailure(exception);
                    }
                });
            }

        } else {
           createProject(callback, projectDescriptor, name,fileName);
        }
    }

   private void updateProject(@Nonnull final Project project,
                               @Nonnull ProjectDescriptor projectDescriptor,
                               @Nonnull final CommitCallback callback) {
        projectServiceClient.updateProject(project.getPath(), projectDescriptor, new AsyncRequestCallback<ProjectDescriptor>() {
            
            @Override
            protected void onSuccess(ProjectDescriptor result) {
                resourceProvider.getProject(project.getName(), new AsyncCallback<Project>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        callback.onFailure(caught);
                    }

                    @Override
                    public void onSuccess(Project result) {
                        //callback.onSuccess();
                    }
                });
            }

            @Override
            protected void onFailure(Throwable exception) {
                callback.onFailure(exception);
            }
        });
    }

    private void createProject(@Nonnull final CommitCallback callback,
                               @Nonnull ProjectDescriptor projectDescriptor,
                               @Nonnull final String name, 
                               @Nonnull final String fileName) {
        projectServiceClient
                .createProject(name, projectDescriptor,
                               new AsyncRequestCallback<ProjectDescriptor>() {
                                   @Override
                                   protected void onSuccess(ProjectDescriptor result) {
                                       resourceProvider.getProject(name, new AsyncCallback<Project>() {
                                           @Override
                                           public void onSuccess(Project project) {
                                               callback.onSuccess();
                                           }

                                           @Override
                                           public void onFailure(Throwable caught) {
                                               callback.onFailure(caught);
                                           }
                                       });
                                   }

                                   @Override
                                   protected void onFailure(Throwable exception) {
                                       callback.onFailure(exception);
                                   }
                   });
        
        
            String content = "<data name=\""+ fileName+ "\""+ " " + "Service Namespace=\""+ serviceNamespace+"\""+ " "+ "Service Group=\""+ serviceGroup+ "\"/> \n"+ 
                              "<description>" + description + "</description> \n"+
                              "<config id=\""+ id+ "\"> \n"+
                              "<property name=\"org.wso2.ws.dataservice.user\">" + uname+"</property> \n"+
                              "<property name=\"org.wso2.ws.dataservice.password\">" + pass + "</property> \n"+
                              "<property name=\"org.wso2.ws.dataservice.protocol\">" + url + "</property> \n" +
                              "<property name=\"org.wso2.ws.dataservice.driver\">" + driverClass + "</property \n"+
                              "</config> \n"+
                              "</data> \n" ;

            String file = fileName+".dbs";
            projectServiceClient.createFile(name, file, content, "fff", new AsyncRequestCallback<Void>() {
            
 
            @Override
            public void onFailure(Throwable caught) {
                callback.onFailure(caught);
            }

            @Override
            protected void onSuccess(Void result) {
                callback.onSuccess();
                
            }

        });
        

    }
    
    
        
       
    

}
