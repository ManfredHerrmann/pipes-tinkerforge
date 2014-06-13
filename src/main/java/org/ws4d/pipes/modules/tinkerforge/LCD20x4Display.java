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

import org.ws4d.pipes.module.InPortListener;
import org.ws4d.pipes.module.PortValue;
import org.ws4d.pipes.module.annotation.InPort;
import org.ws4d.pipes.module.annotation.Module;
import org.ws4d.pipes.modules.tinkerforge.daemon.BaseBrickletRequestModule;
import org.ws4d.pipes.modules.tinkerforge.daemon.ConnectionManager;

import com.tinkerforge.BrickletLCD20x4;

@Module(//
label = "LCD 20x4", //
icon = "tinkerforgelogo.png",//
inPorts = {//
		@InPort(name = "host", label = "BrickD Host"), //
		@InPort(name = "port", label = "BrickD Port"), //
		@InPort(name = "uid", label = "Bricklet UID"), //
		@InPort(name = "line1", label = "Line 1", isOptional = true), //
		@InPort(name = "line2", label = "Line 2", isOptional = true), //
		@InPort(name = "line3", label = "Line 3", isOptional = true), //
		@InPort(name = "line4", label = "Line 4", isOptional = true) }, //
outPorts = {}//
)
public class LCD20x4Display extends BaseBrickletRequestModule<BrickletLCD20x4> {

	private class Line1PortListener extends InPortListener {
		public Line1PortListener(LCD20x4Display module) {
			super(module, "line1");
		}

		@Override
		protected void doWork(PortValue portV) {
			if (device != null) {
				final String line = String.format("%1$-20s", portV.getObject()
						.toString());
				try {
					device.writeLine((short) 0, (short) 0, line);
				} catch (Exception e) {
					getLogger().log(
							Level.SEVERE,
							"Can't write to lcd bricklet " + uid + " (" + host
									+ ":" + port + ")", e);
				}
			}
		}
	}

	private class Line2PortListener extends InPortListener {
		public Line2PortListener(LCD20x4Display module) {
			super(module, "line2");
		}

		@Override
		protected void doWork(PortValue portV) {
			if (device != null) {
				final String line = String.format("%1$-20s", portV.getObject()
						.toString());
				try {
					device.writeLine((short) 1, (short) 0, line);
				} catch (Exception e) {
					getLogger().log(
							Level.SEVERE,
							"Can't write to lcd bricklet " + uid + " (" + host
									+ ":" + port + ")", e);
				}
			}
		}
	}

	private class Line3PortListener extends InPortListener {
		public Line3PortListener(LCD20x4Display module) {
			super(module, "line3");
		}

		@Override
		protected void doWork(PortValue portV) {
			if (device != null) {
				final String line = String.format("%1$-20s", portV.getObject()
						.toString());
				try {
					device.writeLine((short) 2, (short) 0, line);
				} catch (Exception e) {
					getLogger().log(
							Level.SEVERE,
							"Can't write to lcd bricklet " + uid + " (" + host
									+ ":" + port + ")", e);
				}
			}
		}
	}

	private class Line4PortListener extends InPortListener {
		public Line4PortListener(LCD20x4Display module) {
			super(module, "line4");
		}

		@Override
		protected void doWork(PortValue portV) {
			if (device != null) {
				final String line = String.format("%1$-20s", portV.getObject()
						.toString());
				try {
					device.writeLine((short) 3, (short) 0, line);
				} catch (Exception e) {
					getLogger().log(
							Level.SEVERE,
							"Can't write to lcd bricklet " + uid + " (" + host
									+ ":" + port + ")", e);
				}
			}
		}
	}

	private Thread line1PortListener = null;
	private Thread line2PortListener = null;
	private Thread line3PortListener = null;
	private Thread line4PortListener = null;

	@Override
	protected BrickletLCD20x4 createDevice(String uid) {
		try {
			return new BrickletLCD20x4(uid, Activator.getInstance().getConnectionManager()
					.getConnection(host, Integer.parseInt(port)));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	protected void basePortsClosed() {
		if (line1PortListener != null) {
			try {
				line1PortListener.join();
			} catch (InterruptedException e) {
				getLogger().log(Level.WARNING,
						"Exception while waiting for line1 port listener", e);
			}
		}

		if (line2PortListener != null) {
			try {
				line2PortListener.join();
			} catch (InterruptedException e) {
				getLogger().log(Level.WARNING,
						"Exception while waiting for line2 port listener", e);
			}
		}

		if (line3PortListener != null) {
			try {
				line3PortListener.join();
			} catch (InterruptedException e) {
				getLogger().log(Level.WARNING,
						"Exception while waiting for line3 port listener", e);
			}
		}

		if (line4PortListener != null) {
			try {
				line4PortListener.join();
			} catch (InterruptedException e) {
				getLogger().log(Level.WARNING,
						"Exception while waiting for line4 port listener", e);
			}
		}

		super.basePortsClosed();
	}

	@Override
	protected void doWork() {

		BrickletLCD20x4 oldDevice = this.device;

		super.doWork();

		if ((device != null) && (oldDevice != device)) {

			try {
				device.backlightOn();
				device.clearDisplay();
			} catch (Exception e) {
				getLogger().log(
						Level.SEVERE,
						"Can't reset display bricklet " + uid + " (" + host
								+ ":" + port + ")", e);
			}

			if (line1PortListener == null) {
				line1PortListener = new Thread(new Line1PortListener(this));
				line1PortListener.start();
			}

			if (line2PortListener == null) {
				line2PortListener = new Thread(new Line2PortListener(this));
				line2PortListener.start();
			}

			if (line3PortListener == null) {
				line3PortListener = new Thread(new Line3PortListener(this));
				line3PortListener.start();
			}

			if (line4PortListener == null) {
				line4PortListener = new Thread(new Line4PortListener(this));
				line4PortListener.start();
			}
		}
	}
}
