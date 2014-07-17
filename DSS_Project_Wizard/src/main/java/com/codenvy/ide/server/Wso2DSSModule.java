package com.codenvy.ide.server;

import com.codenvy.inject.DynaModule;
import com.google.inject.AbstractModule;


/**
 * @author Sohani
 */
@DynaModule
public class Wso2DSSModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(Wso2DSSProjectTypeDescriptionExension.class);
        bind(Wso2DSSProjectTypeExtension.class);
    }

}