/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.ide.ext.plugins.client.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.eclipse.che.ide.api.icon.Icon;
import org.eclipse.che.ide.api.icon.IconRegistry;
import org.eclipse.che.ide.ext.plugins.client.PluginsResources;
import org.eclipse.che.ide.extension.machine.client.command.api.CommandConfigurationPage;
import org.eclipse.che.ide.extension.machine.client.command.api.CommandImpl;
import org.eclipse.che.ide.extension.machine.client.command.api.CommandProducer;
import org.eclipse.che.ide.extension.machine.client.command.api.CommandType;
import org.vectomatic.dom.svg.ui.SVGResource;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 'GWT SDM for Che' command type.
 *
 * @author Artem Zatsarynnyi
 */
@Singleton
public class GwtCheCommandType implements CommandType {

    public static final String CODE_SERVER_FQN  = "com.google.gwt.dev.codeserver.CodeServer";
    public static final String COMMAND_TEMPLATE =
            "java -classpath $CHE_CLASSPATH " + CODE_SERVER_FQN + " $GWT_MODULE -noincremental -noprecompile";

    private static final String ID             = "gwt_sdm_che";
    private static final String IDE_GWT_MODULE = "org.eclipse.che.ide.IDE";

    private final PluginsResources                  resources;
    private final GwtCheCommandConfigurationFactory configurationFactory;

    private final Collection<CommandConfigurationPage<? extends CommandImpl>> pages;

    @Inject
    public GwtCheCommandType(PluginsResources resources,
                             GwtCheCommandConfigurationFactory gwtCheCommandConfigurationFactory,
                             CommandPagePresenter page,
                             IconRegistry iconRegistry) {
        this.resources = resources;
        configurationFactory = gwtCheCommandConfigurationFactory;
        pages = new LinkedList<>();
        pages.add(page);

        iconRegistry.registerIcon(new Icon(ID + ".commands.category.icon", resources.gwtCheCommandType()));
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getDisplayName() {
        return "GWT SDM for Che";
    }

    @Override
    public String getDescription() {
        return "Command for launching GWT Super Dev Mode for the Che project sources";
    }

    @Override
    public SVGResource getIcon() {
        return resources.gwtCheCommandType();
    }

    @Override
    public Collection<CommandConfigurationPage<? extends CommandImpl>> getConfigurationPages() {
        return pages;
    }

    @Override
    public CommandConfigurationFactory<GwtCheCommandConfiguration> getConfigurationFactory() {
        return configurationFactory;
    }

    @Override
    public String getCommandTemplate() {
        return COMMAND_TEMPLATE.replace("$GWT_MODULE", IDE_GWT_MODULE)
                               .replace("$CHE_CLASSPATH", '"' + resources.cheClassPath().getText() + '"') + " -bindAddress 0.0.0.0";
    }

    @Override
    public List<CommandProducer> getProducers() {
        return Collections.emptyList();
    }

    @Override
    public String getPreviewUrlTemplate() {
        return "";
    }
}
