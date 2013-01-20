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
import java.util.HashMap;

import com.tinkerforge.Device;
import com.tinkerforge.IPConnection;
import com.tinkerforge.IPConnection.TimeoutException;

public class ConnectionManager {

	private static ConnectionManager instance = new ConnectionManager();

	public synchronized static ConnectionManager getInstance() {
		return instance;
	}

	private final HashMap<String, Device> devices = new HashMap<String, Device>();
	private final HashMap<String, IPConnection> connections = new HashMap<String, IPConnection>();

	private IPConnection getConnection(String ipAddress, int port)
			throws IOException {

		final String address = ipAddress + ":" + Integer.toString(port);
		IPConnection result = connections.get(address);
		if (result != null) {
			return result;
		}

		try {
			result = new IPConnection(ipAddress, port);
			connections.put(address, result);
		} catch (IOException e) {
			throw e;
		}

		return result;
	}

	public Device getDevice(String ipAddress, int port, String UID) {
		return devices
				.get(ipAddress + ":" + Integer.toString(port) + ":" + UID);
	}

	public Device addDevice(String ipAddress, int port, String UID,
			Device device) throws IOException, TimeoutException {

		Device result = getDevice(ipAddress, port, UID);
		if (result != null) {
			return result;
		}

		final IPConnection connection = getConnection(ipAddress, port);
		if (connection == null) {
			return null;
		}

		final String deviceId = ipAddress + ":" + Integer.toString(port) + ":"
				+ UID;
		result = device;
		connection.addDevice(result);
		devices.put(deviceId, result);

		return result;
	}

	public void shutdown() {
		for (IPConnection connection : connections.values()) {
			connection.destroy();
		}
		connections.clear();
		devices.clear();
	}
}
