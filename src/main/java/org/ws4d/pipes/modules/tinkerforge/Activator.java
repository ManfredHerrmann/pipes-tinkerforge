/**
 * Copyright (C) 2013 PipesBox UG (haftungsbeschränkt) (elmar.zeeb@pipesbox.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ws4d.pipes.modules.tinkerforge;

import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import org.ws4d.pipes.bundle.PackageActivator;
import org.ws4d.pipes.bundle.annotation.Package;
import org.ws4d.pipes.modules.tinkerforge.daemon.ConnectionManager;

@Package(//
label = "Tinkerforge", //
name = "tinkerforge", //
scripts = {}, //
style = "tinkerforge.css", //
javaPackages = { "org.ws4d.pipes.modules.tinkerforge" })
public class Activator extends PackageActivator {

	private static Activator instance = null;

	public static Activator getInstance() {
		return instance;
	}
	
	private ConnectionManager cm = null;
	
	public ConnectionManager getConnectionManager() {
		return cm;
	}
	
	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		super.init(context, manager);

		instance = this;

		setupLogger();
		
		cm = new ConnectionManager();

	}

	@Override
	public void destroy(BundleContext context, DependencyManager manager)
			throws Exception {
		super.destroy(context, manager);
		
		if (cm != null) {
			cm.shutdown();
			cm = null;
		}
	}
}