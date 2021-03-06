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

import com.codenvy.ide.api.mvp.View;
import com.google.inject.ImplementedBy;
import javax.annotation.Nonnull;
/**
 * The view of {@link WSO2PagePresenter}.
 *
 * @author Sohani
 */
@ImplementedBy(WSO2OptionPageViewImpl.class)
public interface WSO2OptionPageView extends View<WSO2OptionPageView.ActionDelegate> {

    public interface ActionDelegate {
        
         /** Performs some actions in response to a user's choosing page 2. */
        void onPage2Chosen();

        /** Performs some actions in response to a user's choosing page 3. */
        void onPage3Chosen();
       
    }
    
     /**
     * Returns whether the page 2 is next.
     *
     * @return <code>true</code> if the page 2 is next, and <code>false</code> if it's not
     */
    boolean isPage2Next();

    /**
     * Change state of the visual component of view. This component provides logical about next page.
     *
     * @param page2Next
     *         need to choose page 2 item or item page 3
     */
    void setPage2Next(boolean page2Next);
    
   
  
   
}
    

