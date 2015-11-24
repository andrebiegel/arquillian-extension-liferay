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

package org.arquillian.liferay.portal.activator;

import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.LayoutLocalService;
import com.liferay.portal.service.PortletPreferencesLocalService;
import com.liferay.portal.service.UserLocalService;

import java.util.Dictionary;

import javax.servlet.Servlet;

import org.arquillian.liferay.portal.servlet.PortalURLServlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Cristina González Castellano
 */
public class PortalURLBundleActivator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		_companyLocalServiceServiceReference = context.getServiceReference(
			CompanyLocalService.class);

		CompanyLocalService companyLocalService;

		if (_companyLocalServiceServiceReference == null) {
			throw new IllegalStateException(
				"Can not access CompanyLocalService");
		}

		companyLocalService = context.getService(
			_companyLocalServiceServiceReference);

		_groupLocalServiceServiceReference = context.getServiceReference(
			GroupLocalService.class);

		GroupLocalService groupLocalService;

		if (_groupLocalServiceServiceReference == null) {
			throw new IllegalStateException("Can not access GroupLocalService");
		}

		groupLocalService = context.getService(
			_groupLocalServiceServiceReference);

		_layoutLocalServiceServiceReference = context.getServiceReference(
			LayoutLocalService.class);

		LayoutLocalService layoutLocalService;

		if (_layoutLocalServiceServiceReference == null) {
			throw new IllegalStateException(
				"Can not access LayoutLocalService");
		}

		layoutLocalService = context.getService(
			_layoutLocalServiceServiceReference);

		_portletPreferencesLocalServiceServiceReference =
			context.getServiceReference(PortletPreferencesLocalService.class);

		PortletPreferencesLocalService portletPreferencesLocalService = null;

		if (_portletPreferencesLocalServiceServiceReference == null) {
			throw new IllegalStateException(
				"Can not access PortletPreferencesLocalService");
		}

		portletPreferencesLocalService = context.getService(
			_portletPreferencesLocalServiceServiceReference);

		_userLocalServiceServiceReference = context.getServiceReference(
			UserLocalService.class);

		UserLocalService userLocalService;

		if (_userLocalServiceServiceReference == null) {
			throw new IllegalStateException("Can not access UserLocalService");
		}

		userLocalService = context.getService(
			_userLocalServiceServiceReference);

		PortalURLServlet portalURLServlet = new PortalURLServlet(
			companyLocalService, groupLocalService, layoutLocalService,
			portletPreferencesLocalService, userLocalService);

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put(
			"osgi.http.whiteboard.servlet.name", "Install Portlet Servlet");

		properties.put(
			"osgi.http.whiteboard.servlet.pattern",
			"/install-portlet-servlet/*");

		_servletServiceRegistration = context.registerService(
			Servlet.class, portalURLServlet, properties);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		context.ungetService(_companyLocalServiceServiceReference);
		context.ungetService(_groupLocalServiceServiceReference);
		context.ungetService(_layoutLocalServiceServiceReference);
		context.ungetService(_portletPreferencesLocalServiceServiceReference);
		context.ungetService(_userLocalServiceServiceReference);

		_servletServiceRegistration.unregister();
	}

	private ServiceReference<CompanyLocalService>
		_companyLocalServiceServiceReference;
	private ServiceReference<GroupLocalService>
		_groupLocalServiceServiceReference;
	private ServiceReference<LayoutLocalService>
		_layoutLocalServiceServiceReference;
	private ServiceReference<PortletPreferencesLocalService>
		_portletPreferencesLocalServiceServiceReference;
	private ServiceRegistration<Servlet> _servletServiceRegistration;
	private ServiceReference<UserLocalService>
		_userLocalServiceServiceReference;

}