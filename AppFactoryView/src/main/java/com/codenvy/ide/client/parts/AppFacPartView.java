/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 * [2012] - [2013] Codenvy, S.A.
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.ide.client.parts;

import com.codenvy.ide.api.mvp.View;
import com.codenvy.ide.api.parts.base.BaseActionDelegate;
import com.google.inject.ImplementedBy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Element;



/**
 * The view of {@link AppFacPartPresenter}.
 *
 * @author Sohani
 */
@ImplementedBy(AppFacPartViewImpl.class)
public interface AppFacPartView extends View<AppFacPartView.ActionDelegate> {
    /** Required for delegating functions in view. */
    public interface ActionDelegate extends BaseActionDelegate {
        /** Performs some actions in response to a user's clicking on Button */
        void onButtonClicked();
        
        
    }

    /**
     * Set title of my part.
     *
     * @param title
     *         title that need to be set
     */
    void setTitle(String title);
   
  
}
