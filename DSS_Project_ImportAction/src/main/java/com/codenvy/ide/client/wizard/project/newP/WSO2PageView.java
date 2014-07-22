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
package com.codenvy.ide.client.wizard.project.newP;

import com.codenvy.ide.api.mvp.View;
import com.google.inject.ImplementedBy;
import javax.annotation.Nonnull;
/**
 * The view of {@link WSO2PagePresenter}.
 *
 * @author Sohani
 */
@ImplementedBy(WSO2PageViewImpl.class)
public interface WSO2PageView extends View<WSO2PageView.ActionDelegate> {

    public interface ActionDelegate {
        
     
        /** Performs any actions appropriate in response to the user having changed something. */
        void onValueChanged();
    }
    
     
    /** @return file name */
    @Nonnull
    String getFileName();

    /**
     * Set file name into field on the view.
     *
     * @param fileName
     *         text what will be shown on view
     */
    void setFileName(@Nonnull String fileName);
    
    
    
    @Nonnull
    String getServiceNamespace();
    
    void setServiceNamespace(@Nonnull String serviceNamespace);
    
    @Nonnull
    String getServiceGroup();
    
    void setServiceGroup(@Nonnull String serviceGroup);
    
    @Nonnull
    String getDescription();
    
    void setDescription(@Nonnull String description);
    
    /** @return file name */
    @Nonnull
    String getID();

    /**
     * Set file name into field on the view.
     *
     * @param fileName
     *         text what will be shown on view
     */
    void setID(@Nonnull String id);
    
    
    
    @Nonnull
    String getType();
    
    void setType(@Nonnull String type);
    
    @Nonnull
    String getEngine();
    
    void setEngine(@Nonnull String engine);
    
    @Nonnull
    String getDriverClass();
    
    void setDriverClass(@Nonnull String driverClass);
    
    @Nonnull
    String getURL();
    
    void setURL(@Nonnull String url);
    
    @Nonnull
    String getUname();
    
    void setUname(@Nonnull String uname);
    
    @Nonnull
    String getPass();
    
    void setPass(@Nonnull String pass);
}
    

