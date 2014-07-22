package com.codenvy.ide.server;

import com.codenvy.api.project.server.ProjectTypeDescriptionRegistry;
import com.codenvy.api.project.server.ProjectTypeExtension;
import com.codenvy.api.project.shared.Attribute;
import com.codenvy.api.project.shared.ProjectTemplateDescription;
import com.codenvy.api.project.shared.ProjectType;
import com.google.inject.Singleton;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Sohani
 */
@Singleton
public class Wso2DSSProjectTypeExtension implements ProjectTypeExtension
{

    @Inject
    public Wso2DSSProjectTypeExtension(ProjectTypeDescriptionRegistry registry) {
        registry.registerProjectType(this);
    }

    @Override
    public ProjectType getProjectType() {
        return new ProjectType("DSSConfigurationProject", "WSO2Project", "WSO2Project");

    }

    @Override
    public List<Attribute> getPredefinedAttributes() {
        return Arrays.asList(new Attribute("language", "WSO2Project"),
                             new Attribute("framework", "WSO2Project"));
    }
    
     @Override
    public List<ProjectTemplateDescription> getTemplates() {
        final List<ProjectTemplateDescription> list = new ArrayList<>(4);
        list.add(new ProjectTemplateDescription("zip",
                "Data Service Project",
                "This is a simple DSS configuration project.",
                "template/dssproject.zip"));

        return list;
    }


}