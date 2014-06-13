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
