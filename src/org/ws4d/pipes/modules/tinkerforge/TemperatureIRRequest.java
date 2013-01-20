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

import com.tinkerforge.BrickletTemperatureIR;
import com.tinkerforge.IPConnection.TimeoutException;

@Module(//
label = "Temperature IR Request", //
icon = "tinkerforgelogo.png",//
inPorts = {//
		@InPort(name = "host", label = "BrickD Host"), //
		@InPort(name = "port", label = "BrickD Port"), //
		@InPort(name = "uid", label = "Bricklet UID") }, //
outPorts = {//
		@OutPort(name = "ambient", label = "°C/10 (Ambient Temperature)"), //
		@OutPort(name = "object", label = "°C/10 (Object Temperature)") }//
)
public class TemperatureIRRequest extends
		BaseBrickletRequestModule<BrickletTemperatureIR> {

	@Override
	protected BrickletTemperatureIR createDevice(String uid) {
		return new BrickletTemperatureIR(uid);
	}

	@Override
	protected void doWork() {

		super.doWork();

		if (device != null) {
			try {
				final Short temp = device.getAmbientTemperature();

				setOutData("ambient", temp.intValue());
			} catch (TimeoutException e) {
				getLogger().log(
						Level.SEVERE,
						"Can't get ambient temperature from bricklet " + uid
								+ " (" + host + ":" + port + ")", e);
			}
			
			try {
				final Short temp = device.getObjectTemperature();
				
				setOutData("object", temp.intValue());
			} catch (TimeoutException e) {
				getLogger().log(
						Level.SEVERE,
						"Can't get object temperature from bricklet " + uid
								+ " (" + host + ":" + port + ")", e);
			}
		}
	}
}
