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
		return new BrickletAnalogOut(uid);
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
				device.setMode(mode);
			}

			if (voltage != null) {
				// TODO: Check if voltage is in Range
				device.setVoltage(voltage);
			}
		}
	}
}
