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

package org.ws4d.pipes.modules.tinkerforge.daemon;

import java.io.IOException;
import java.util.logging.Level;

import org.ws4d.pipes.module.DefaultModuleWorker;
import org.ws4d.pipes.module.PortValue;
import org.ws4d.pipes.modules.tinkerforge.Activator;

import com.tinkerforge.Device;

public abstract class BaseBrickletRequestModule<T extends Device> extends
		DefaultModuleWorker {

	protected String host = null;
	protected String port = null;
	protected String uid = null;
	protected T device = null;

	protected abstract T createDevice(String uid);

	protected void basePortsClosed() {
		closeAllPorts();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doWork() {

		boolean portChanged = false;

		PortValue hostV = getWiredOrConfiguredValue("host");
		PortValue portV = getWiredOrConfiguredValue("port");
		PortValue uidV = getWiredOrConfiguredValue("uid");

		if (hostV.isNull() && portV.isNull() && uidV.isNull()) {
			basePortsClosed();
			return;
		}

		if (hostV.isString()) {
			if (host == null) {
				host = hostV.getString();
				portChanged = true;
			} else {
				if (!hostV.getString().equals(host)) {
					host = hostV.getString();
					portChanged = true;
				}
			}
		}

		if (portV.isString()) {
			if (port == null) {
				port = portV.getString();
				portChanged = true;
			} else {
				if (!portV.getString().equals(port)) {
					port = portV.getString();
					portChanged = true;
				}
			}
		}

		if (uidV.isString()) {
			if (uid == null) {
				uid = uidV.getString();
				portChanged = true;
			} else {
				if (!uidV.getString().equals(uid)) {
					uid = uidV.getString();
					portChanged = true;
				}
			}
		}

		if (portChanged && (host != null) && (port != null) && (uid != null)) {
			device = (T) Activator.getInstance().getConnectionManager()
					.getDevice(host, Integer.parseInt(port), uid);
			if (device == null) {
				try {
					device = (T) Activator
							.getInstance()
							.getConnectionManager()
							.addDevice(host, Integer.parseInt(port), uid,
									createDevice(uid));
				} catch (NumberFormatException e) {
					getLogger().log(Level.SEVERE, "invalid brickd port number",
							e);
				} catch (IOException e) {
					getLogger().log(Level.SEVERE, "error connecting to brickd",
							e);
				}
			}
		}
	}
}
