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
import java.util.HashMap;

import com.tinkerforge.AlreadyConnectedException;
import com.tinkerforge.Device;
import com.tinkerforge.IPConnection;
import com.tinkerforge.NotConnectedException;

public class ConnectionManager {

	private final HashMap<String, Device> devices = new HashMap<String, Device>();
	private final HashMap<String, IPConnection> connections = new HashMap<String, IPConnection>();

	public IPConnection getConnection(String ipAddress, int port)
			throws AlreadyConnectedException, IOException {

		final String address = ipAddress + ":" + Integer.toString(port);
		IPConnection result = connections.get(address);
		if (result != null) {
			return result;
		}

		try {
			result = new IPConnection();
			result.connect(ipAddress, port);
			connections.put(address, result);
		} catch (IOException e) {
			throw e;
		} catch (AlreadyConnectedException e) {
			throw e;
		}

		return result;
	}

	public Device getDevice(String ipAddress, int port, String UID) {
		return devices
				.get(ipAddress + ":" + Integer.toString(port) + ":" + UID);
	}

	public Device addDevice(String ipAddress, int port, String UID,
			Device device) throws IOException {

		Device result = getDevice(ipAddress, port, UID);
		if (result != null) {
			return result;
		}

		final String deviceId = ipAddress + ":" + Integer.toString(port) + ":"
				+ UID;
		result = device;
		devices.put(deviceId, result);

		return result;
	}

	public void shutdown() {
		for (IPConnection connection : connections.values()) {
			try {
				connection.disconnect();
			} catch (NotConnectedException ignore) {
				// ignore
			}
		}
		connections.clear();
		devices.clear();
	}
}
