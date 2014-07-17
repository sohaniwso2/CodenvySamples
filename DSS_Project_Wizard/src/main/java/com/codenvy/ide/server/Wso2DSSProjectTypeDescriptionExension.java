package com.codenvy.ide.server;

import com.codenvy.api.project.server.ProjectTypeDescriptionExtension;
import com.codenvy.api.project.server.ProjectTypeDescriptionRegistry;
import com.codenvy.api.project.server.ProjectTypeExtension;
import com.codenvy.api.project.shared.Attribute;
import com.codenvy.api.project.shared.AttributeDescription;
import com.codenvy.api.project.shared.ProjectType;
import com.google.inject.Singleton;
import com.codenvy.api.project.shared.ProjectTemplateDescription;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


/**
 * Register WSO2 extension {@link com.codenvy.api.project.server.ProjectTypeDescriptionExtension} to register project types.
 *
 * @author Sohani
 */
@Singleton
public class Wso2DSSProjectTypeDescriptionExension implements ProjectTypeDescriptionExtension, ProjectTypeExtension{
    @Inject
    public Wso2DSSProjectTypeDescriptionExension(ProjectTypeDescriptionRegistry registry) {
        registry.registerDescription(this);
    }

    @Override
    public List<ProjectType> getProjectTypes() {
        return Arrays.asList(new ProjectType("DSSConfigurationProject", "Data Service Project ", "WSO2Project"));
    }

    @Override
    public List<AttributeDescription> getAttributeDescriptions() {
        return Arrays.asList(new AttributeDescription("language"),
                             new AttributeDescription("framework"));
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

    @Override
    public List<Attribute> getPredefinedAttributes() {
               return null;

    }

    @Override
    public ProjectType getProjectType() {
        return null;
    }
}
