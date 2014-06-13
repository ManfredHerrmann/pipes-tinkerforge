/* ***********************************************************
 * This file was automatically generated on 2013-01-28.      *
 *                                                           *
 * Bindings Version 2.0.2                                    *
 *                                                           *
 * If you have a bugfix for this file and want to commit it, *
 * please fix the bug in the generator. You can find a link  *
 * to the generator git on tinkerforge.com                   *
 *************************************************************/

package com.tinkerforge;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Device for sensing Temperature
 */
public class BrickletTemperature extends Device {
	public final static int DEVICE_IDENTIFIER = 216;

	public final static byte FUNCTION_GET_TEMPERATURE = (byte)1;
	public final static byte FUNCTION_SET_TEMPERATURE_CALLBACK_PERIOD = (byte)2;
	public final static byte FUNCTION_GET_TEMPERATURE_CALLBACK_PERIOD = (byte)3;
	public final static byte FUNCTION_SET_TEMPERATURE_CALLBACK_THRESHOLD = (byte)4;
	public final static byte FUNCTION_GET_TEMPERATURE_CALLBACK_THRESHOLD = (byte)5;
	public final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)6;
	public final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)7;
	public final static byte CALLBACK_TEMPERATURE = (byte)8;
	public final static byte CALLBACK_TEMPERATURE_REACHED = (byte)9;
	public final static byte FUNCTION_GET_IDENTITY = (byte)255;

	public final static char THRESHOLD_OPTION_OFF = (char)'x';
	public final static char THRESHOLD_OPTION_OUTSIDE = (char)'o';
	public final static char THRESHOLD_OPTION_INSIDE = (char)'i';
	public final static char THRESHOLD_OPTION_SMALLER = (char)'<';
	public final static char THRESHOLD_OPTION_GREATER = (char)'>';

	private List<TemperatureListener> listenerTemperature = new ArrayList<TemperatureListener>();
	private List<TemperatureReachedListener> listenerTemperatureReached = new ArrayList<TemperatureReachedListener>();

	public class TemperatureCallbackThreshold {
		public char option;
		public short min;
		public short max;

		public String toString() {
			return "[" + "option = " + option + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link com.tinkerforge.BrickletTemperature.setTemperatureCallbackPeriod}. The parameter is the temperature
	 * of the sensor.
	 * 
	 * {@link com.tinkerforge.BrickletTemperature.TemperatureListener} is only triggered if the temperature has changed since the
	 * last triggering.
	 */
	public interface TemperatureListener {
		public void temperature(short temperature);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link com.tinkerforge.BrickletTemperature.setTemperatureCallbackThreshold} is reached.
	 * The parameter is the temperature of the sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link com.tinkerforge.BrickletTemperature.setDebouncePeriod}.
	 */
	public interface TemperatureReachedListener {
		public void temperatureReached(short temperature);
	}

	/**
	 * Creates an object with the unique device ID \c uid. and adds it to
	 * the IP Connection \c ipcon.
	 */
	public BrickletTemperature(String uid, IPConnection ipcon) {
		super(uid, ipcon);

		apiVersion[0] = 2;
		apiVersion[1] = 0;
		apiVersion[2] = 0;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_TEMPERATURE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_TEMPERATURE_CALLBACK_PERIOD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_TEMPERATURE_CALLBACK_PERIOD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_TEMPERATURE_CALLBACK_THRESHOLD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_TEMPERATURE_CALLBACK_THRESHOLD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_DEBOUNCE_PERIOD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_DEBOUNCE_PERIOD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_IDENTITY)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_TEMPERATURE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_TEMPERATURE_REACHED)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;

		callbacks[CALLBACK_TEMPERATURE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short temperature = (bb.getShort());

				for(TemperatureListener listener: listenerTemperature) {
					listener.temperature(temperature);
				}
			}
		};

		callbacks[CALLBACK_TEMPERATURE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short temperature = (bb.getShort());

				for(TemperatureReachedListener listener: listenerTemperatureReached) {
					listener.temperatureReached(temperature);
				}
			}
		};
	}

	/**
	 * Returns the temperature of the sensor. The value
	 * has a range of -2500 to 8500 and is given in °C/100,
	 * e.g. a value of 4223 means that a temperature of 42.23 °C is measured.
	 * 
	 * If you want to get the temperature periodically, it is recommended 
	 * to use the listener {@link com.tinkerforge.BrickletTemperature.TemperatureListener} and set the period with 
	 * {@link com.tinkerforge.BrickletTemperature.setTemperatureCallbackPeriod}.
	 */
	public short getTemperature() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_TEMPERATURE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_TEMPERATURE, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_TEMPERATURE);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short temperature = (bb.getShort());

		return temperature;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletTemperature.TemperatureListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletTemperature.TemperatureListener} is only triggered if the temperature has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setTemperatureCallbackPeriod(long period) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_TEMPERATURE_CALLBACK_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)12, FUNCTION_SET_TEMPERATURE_CALLBACK_PERIOD, options, (byte)(0));
		bb.putInt((int)period);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_TEMPERATURE_CALLBACK_PERIOD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the period as set by {@link com.tinkerforge.BrickletTemperature.setTemperatureCallbackPeriod}.
	 */
	public long getTemperatureCallbackPeriod() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_TEMPERATURE_CALLBACK_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_TEMPERATURE_CALLBACK_PERIOD, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_TEMPERATURE_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletTemperature.TemperatureReachedListener} listener. 
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the temperature is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the temperature is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the temperature is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the temperature is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setTemperatureCallbackThreshold(char option, short min, short max) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_TEMPERATURE_CALLBACK_THRESHOLD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)13, FUNCTION_SET_TEMPERATURE_CALLBACK_THRESHOLD, options, (byte)(0));
		bb.put((byte)option);
		bb.putShort((short)min);
		bb.putShort((short)max);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_TEMPERATURE_CALLBACK_THRESHOLD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the threshold as set by {@link com.tinkerforge.BrickletTemperature.setTemperatureCallbackThreshold}.
	 */
	public TemperatureCallbackThreshold getTemperatureCallbackThreshold() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_TEMPERATURE_CALLBACK_THRESHOLD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_TEMPERATURE_CALLBACK_THRESHOLD, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_TEMPERATURE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		TemperatureCallbackThreshold obj = new TemperatureCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = (bb.getShort());
		obj.max = (bb.getShort());

		return obj;
	}

	/**
	 * Sets the period in ms with which the threshold listener
	 * 
	 *  {@link com.tinkerforge.BrickletTemperature.TemperatureReachedListener}
	 * 
	 * is triggered, if the threshold
	 * 
	 *  {@link com.tinkerforge.BrickletTemperature.setTemperatureCallbackThreshold}
	 * 
	 * keeps being reached.
	 * 
	 * The default value is 100.
	 */
	public void setDebouncePeriod(long debounce) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_DEBOUNCE_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)12, FUNCTION_SET_DEBOUNCE_PERIOD, options, (byte)(0));
		bb.putInt((int)debounce);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_DEBOUNCE_PERIOD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the debounce period as set by {@link com.tinkerforge.BrickletTemperature.setDebouncePeriod}.
	 */
	public long getDebouncePeriod() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_DEBOUNCE_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_DEBOUNCE_PERIOD, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_DEBOUNCE_PERIOD);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long debounce = IPConnection.unsignedInt(bb.getInt());

		return debounce;
	}

	/**
	 * Returns the UID, the UID where the Bricklet is connected to, 
	 * the position, the hardware and firmware version as well as the
	 * device identifier.
	 * 
	 * The position can be 'a', 'b', 'c' or 'd'.
	 * 
	 * The device identifiers can be found :ref:`here <device_identifier>`.
	 * 
	 * .. versionadded:: 2.0.0~(Plugin)
	 */
	public Identity getIdentity() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_IDENTITY);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_IDENTITY, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_IDENTITY);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Identity obj = new Identity();
		obj.uid = IPConnection.string(bb, 8);
		obj.connectedUid = IPConnection.string(bb, 8);
		obj.position = (char)(bb.get());
		for(int i = 0; i < 3; i++) {
			obj.hardwareVersion[i] = IPConnection.unsignedByte(bb.get());
		}

		for(int i = 0; i < 3; i++) {
			obj.firmwareVersion[i] = IPConnection.unsignedByte(bb.get());
		}

		obj.deviceIdentifier = IPConnection.unsignedShort(bb.getShort());

		return obj;
	}

	/**
	 * Adds a Temperature listener.
	 */
	public void addTemperatureListener(TemperatureListener listener) {
		listenerTemperature.add(listener);
	}

	/**
	 * Removes a Temperature listener.
	 */
	public void removeTemperatureListener(TemperatureListener listener) {
		listenerTemperature.remove(listener);
	}

	/**
	 * Adds a TemperatureReached listener.
	 */
	public void addTemperatureReachedListener(TemperatureReachedListener listener) {
		listenerTemperatureReached.add(listener);
	}

	/**
	 * Removes a TemperatureReached listener.
	 */
	public void removeTemperatureReachedListener(TemperatureReachedListener listener) {
		listenerTemperatureReached.remove(listener);
	}
}