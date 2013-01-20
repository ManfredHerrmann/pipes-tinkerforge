/**
 * PipesBox Tinkerforge Package - contains modules for Tinkerforge sensors 
 *                                and actors
 * Copyright (C) 2013 PipesBox UG (haftungsbeschränkt)
 * 
 * PipesBox Tinkerforge Package is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your 
 * option) any later version.
 *
 * PipesBox Tinkerforge Package is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser 
 * Public License for more details.
 *
 * A full text copy of the GNU Lesser Public License can be found in 
 * COPYING.LESSER. If not, see <http://www.gnu.org/licenses/>.
 */

package org.ws4d.pipes.modules.tinkerforge;

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

	public void start(BundleContext context) throws Exception {
		super.start(context);

		instance = this;

		setupTrackerManager();
	}

	public void stop(BundleContext context) throws Exception {
		super.stop(context);

		ConnectionManager.getInstance().shutdown();
	}
}