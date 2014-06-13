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

import java.util.logging.Level;

import org.ws4d.pipes.module.PortValue;
import org.ws4d.pipes.module.annotation.InPort;
import org.ws4d.pipes.module.annotation.Module;
import org.ws4d.pipes.modules.tinkerforge.daemon.BaseBrickletRequestModule;

import com.tinkerforge.BrickletAnalogOut;

@Module(//
label = "Analog Out", //
icon = "tinkerforgelogo.png",//
inPorts = {//
		@InPort(name = "host", label = "BrickD Host"), //
		@InPort(name = "port", label = "BrickD Port"), //
		@InPort(name = "uid", label = "Bricklet UID"), //
		@InPort(name = "mode", label = "Mode", isOptional = true), //
		@InPort(name = "value", label = "Voltage") }, //
outPorts = {}//
)
public class AnalogOut extends BaseBrickletRequestModule<BrickletAnalogOut> {

	private volatile boolean canShutdown = false;
	private Short mode = null;
	private Integer voltage = null;

	@Override
	protected BrickletAnalogOut createDevice(String uid) {
		try {
			return new BrickletAnalogOut(uid, Activator.getInstance().getConnectionManager()
					.getConnection(host, Integer.parseInt(port)));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	protected void basePortsClosed() {
		canShutdown = true;
	}

	@Override
	protected void doWork() {

		boolean modeChanged = false;

		super.doWork();

		PortValue modeV = getWiredOrConfiguredValue("mode");
		PortValue valueV = getWiredOrConfiguredValue("value");

		if (valueV.isNull() && modeV.isNull() && canShutdown) {
			closeAllPorts();
			return;
		}

		if (modeV.isInteger()) {
			Short newMode = modeV.getInteger().shortValue();
			if (newMode != mode) {
				mode = newMode;
				modeChanged = true;
			}
		}

		if (modeV.isString()) {
			Short newMode = Short.parseShort(modeV.getString());
			if (newMode != mode) {
				mode = newMode;
				modeChanged = true;
			}
		}

		if (valueV.isInteger()) {
			voltage = valueV.getInteger();
		}

		if (valueV.isString()) {
			voltage = Integer.parseInt(valueV.getString());
		}

		if (device != null) {
			if (modeChanged) {
				try {
					device.setMode(mode);
				} catch (Exception e) {
					getLogger().log(
							Level.SEVERE,
							"Can't set mode of bricklet " + uid + " (" + host
									+ ":" + port + ")", e);
				}
			}

			if (voltage != null) {
				try {
					// TODO: Check if voltage is in Range
					device.setVoltage(voltage);
				} catch (Exception e) {
					getLogger().log(
							Level.SEVERE,
							"Can't set voltage of bricklet " + uid + " ("
									+ host + ":" + port + ")", e);
				}
			}
		}
	}
}
