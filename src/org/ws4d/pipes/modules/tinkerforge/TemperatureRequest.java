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

import java.util.logging.Level;

import org.ws4d.pipes.module.annotation.InPort;
import org.ws4d.pipes.module.annotation.Module;
import org.ws4d.pipes.module.annotation.OutPort;
import org.ws4d.pipes.modules.tinkerforge.daemon.BaseBrickletRequestModule;

import com.tinkerforge.BrickletTemperature;
import com.tinkerforge.IPConnection.TimeoutException;

@Module(//
label = "Temperature Request", //
icon = "tinkerforgelogo.png",//
inPorts = {//
		@InPort(name = "host", label = "BrickD Host"), //
		@InPort(name = "port", label = "BrickD Port"), //
		@InPort(name = "uid", label = "Bricklet UID") }, //
outPorts = {//
		@OutPort(name = "out", label = "°C/100 (Temperature)") }//
)
public class TemperatureRequest extends
		BaseBrickletRequestModule<BrickletTemperature> {

	@Override
	protected BrickletTemperature createDevice(String uid) {
		return new BrickletTemperature(uid);
	}

	@Override
	protected void doWork() {

		super.doWork();

		if (device != null) {
			try {
				final Short temp = device.getTemperature();

				setOutData("out", temp.intValue());
			} catch (TimeoutException e) {
				getLogger().log(
						Level.SEVERE,
						"Can't get temperature from bricklet " + uid + " ("
								+ host + ":" + port + ")", e);
			}
		}
	}
}
