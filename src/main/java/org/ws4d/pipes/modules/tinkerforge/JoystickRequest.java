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

import org.ws4d.pipes.module.annotation.InPort;
import org.ws4d.pipes.module.annotation.Module;
import org.ws4d.pipes.module.annotation.OutPort;
import org.ws4d.pipes.modules.tinkerforge.daemon.BaseBrickletRequestModule;

import com.tinkerforge.BrickletJoystick;
import com.tinkerforge.BrickletJoystick.Position;

@Module(//
label = "Joystick Request", //
icon = "tinkerforgelogo.png",//
inPorts = {//
		@InPort(name = "host", label = "BrickD Host"), //
		@InPort(name = "port", label = "BrickD Port"), //
		@InPort(name = "uid", label = "Bricklet UID") }, //
outPorts = {//
		@OutPort(name = "x", label = "Position X"),
		@OutPort(name = "y", label = "Position Y") }//
)
public class JoystickRequest extends
		BaseBrickletRequestModule<BrickletJoystick> {

	@Override
	protected BrickletJoystick createDevice(String uid) {
		try {
			return new BrickletJoystick(uid, Activator.getInstance().getConnectionManager()
					.getConnection(host, Integer.parseInt(port)));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	protected void doWork() {

		super.doWork();

		if (device != null) {
			Position position;
			try {
				position = device.getPosition();

				final Short x = position.x;
				final Short y = position.y;

				setOutData("x", x.intValue());
				setOutData("y", y.intValue());
			} catch (Exception e) {
				getLogger().log(
						Level.SEVERE,
						"Can't get position from bricklet " + uid + " (" + host
								+ ":" + port + ")", e);
			}
		}
	}
}
