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
package org.eclipse.che.ide.extension.machine.client.command.api;

/**
 * Can produce commands of concrete type from the current context.
 *
 * @author Artem Zatsarynnyi
 */
public interface CommandProducer {

    /** Returns the text that will be used as related action's title. */
    String getName();

    /** Whether the producer can produce command from the current context? */
    boolean isApplicable();

    /** Creates command from the current context of application. */
    CommandImpl createCommand();
}
