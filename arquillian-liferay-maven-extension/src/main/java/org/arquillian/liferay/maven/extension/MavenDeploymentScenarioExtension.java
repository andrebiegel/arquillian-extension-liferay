/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.arquillian.liferay.maven.extension;

import org.arquillian.liferay.maven.generator.MavenDeploymentScenarioGenerator;

import org.jboss.arquillian.container.test.spi.RemoteLoadableExtension;
import org.jboss.arquillian.container.test.spi.client.deployment.DeploymentScenarioGenerator;

/**
 * @author <a href="mailto:kamesh.sampath@liferay.com">Kamesh Sampath</a>
 */
public class MavenDeploymentScenarioExtension
    implements RemoteLoadableExtension {

    /*
     * (non-Javadoc)
     * @see
     * org.jboss.arquillian.core.spi.LoadableExtension#register(org.jboss.arquillian
     * .core.spi.LoadableExtension.ExtensionBuilder)
     */
    @Override
    public void register(ExtensionBuilder builder) {
        builder.service(DeploymentScenarioGenerator.class,
            MavenDeploymentScenarioGenerator.class);
    }
}