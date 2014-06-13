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
