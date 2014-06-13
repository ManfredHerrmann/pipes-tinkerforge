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
 * Device for sensing Ambient Light
 */
public class BrickletAmbientLight extends Device {
	public final static int DEVICE_IDENTIFIER = 21;

	public final static byte FUNCTION_GET_ILLUMINANCE = (byte)1;
	public final static byte FUNCTION_GET_ANALOG_VALUE = (byte)2;
	public final static byte FUNCTION_SET_ILLUMINANCE_CALLBACK_PERIOD = (byte)3;
	public final static byte FUNCTION_GET_ILLUMINANCE_CALLBACK_PERIOD = (byte)4;
	public final static byte FUNCTION_SET_ANALOG_VALUE_CALLBACK_PERIOD = (byte)5;
	public final static byte FUNCTION_GET_ANALOG_VALUE_CALLBACK_PERIOD = (byte)6;
	public final static byte FUNCTION_SET_ILLUMINANCE_CALLBACK_THRESHOLD = (byte)7;
	public final static byte FUNCTION_GET_ILLUMINANCE_CALLBACK_THRESHOLD = (byte)8;
	public final static byte FUNCTION_SET_ANALOG_VALUE_CALLBACK_THRESHOLD = (byte)9;
	public final static byte FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD = (byte)10;
	public final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)11;
	public final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)12;
	public final static byte CALLBACK_ILLUMINANCE = (byte)13;
	public final static byte CALLBACK_ANALOG_VALUE = (byte)14;
	public final static byte CALLBACK_ILLUMINANCE_REACHED = (byte)15;
	public final static byte CALLBACK_ANALOG_VALUE_REACHED = (byte)16;
	public final static byte FUNCTION_GET_IDENTITY = (byte)255;

	public final static char THRESHOLD_OPTION_OFF = (char)'x';
	public final static char THRESHOLD_OPTION_OUTSIDE = (char)'o';
	public final static char THRESHOLD_OPTION_INSIDE = (char)'i';
	public final static char THRESHOLD_OPTION_SMALLER = (char)'<';
	public final static char THRESHOLD_OPTION_GREATER = (char)'>';

	private List<IlluminanceListener> listenerIlluminance = new ArrayList<IlluminanceListener>();
	private List<AnalogValueListener> listenerAnalogValue = new ArrayList<AnalogValueListener>();
	private List<IlluminanceReachedListener> listenerIlluminanceReached = new ArrayList<IlluminanceReachedListener>();
	private List<AnalogValueReachedListener> listenerAnalogValueReached = new ArrayList<AnalogValueReachedListener>();

	public class IlluminanceCallbackThreshold {
		public char option;
		public short min;
		public short max;

		public String toString() {
			return "[" + "option = " + option + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	public class AnalogValueCallbackThreshold {
		public char option;
		public int min;
		public int max;

		public String toString() {
			return "[" + "option = " + option + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link com.tinkerforge.BrickletAmbientLight.setIlluminanceCallbackPeriod}. The parameter is the illuminance of the
	 * ambient light sensor.
	 * 
	 * {@link com.tinkerforge.BrickletAmbientLight.IlluminanceListener} is only triggered if the illuminance has changed since the
	 * last triggering.
	 */
	public interface IlluminanceListener {
		public void illuminance(int illuminance);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link com.tinkerforge.BrickletAmbientLight.setAnalogValueCallbackPeriod}. The parameter is the analog value of the
	 * ambient light sensor.
	 * 
	 * {@link com.tinkerforge.BrickletAmbientLight.AnalogValueListener} is only triggered if the analog value has changed since the
	 * last triggering.
	 */
	public interface AnalogValueListener {
		public void analogValue(int value);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link com.tinkerforge.BrickletAmbientLight.setIlluminanceCallbackThreshold} is reached.
	 * The parameter is the illuminance of the ambient light sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link com.tinkerforge.BrickletAmbientLight.setDebouncePeriod}.
	 */
	public interface IlluminanceReachedListener {
		public void illuminanceReached(int illuminance);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link com.tinkerforge.BrickletAmbientLight.setAnalogValueCallbackThreshold} is reached.
	 * The parameter is the analog value of the ambient light sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link com.tinkerforge.BrickletAmbientLight.setDebouncePeriod}.
	 */
	public interface AnalogValueReachedListener {
		public void analogValueReached(int value);
	}

	/**
	 * Creates an object with the unique device ID \c uid. and adds it to
	 * the IP Connection \c ipcon.
	 */
	public BrickletAmbientLight(String uid, IPConnection ipcon) {
		super(uid, ipcon);

		apiVersion[0] = 2;
		apiVersion[1] = 0;
		apiVersion[2] = 0;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_ILLUMINANCE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_ANALOG_VALUE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_ILLUMINANCE_CALLBACK_PERIOD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_ILLUMINANCE_CALLBACK_PERIOD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_ANALOG_VALUE_CALLBACK_PERIOD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_ANALOG_VALUE_CALLBACK_PERIOD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_ILLUMINANCE_CALLBACK_THRESHOLD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_ILLUMINANCE_CALLBACK_THRESHOLD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_ANALOG_VALUE_CALLBACK_THRESHOLD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_DEBOUNCE_PERIOD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_DEBOUNCE_PERIOD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_IDENTITY)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_ILLUMINANCE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_ANALOG_VALUE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_ILLUMINANCE_REACHED)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_ANALOG_VALUE_REACHED)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;

		callbacks[CALLBACK_ILLUMINANCE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int illuminance = IPConnection.unsignedShort(bb.getShort());

				for(IlluminanceListener listener: listenerIlluminance) {
					listener.illuminance(illuminance);
				}
			}
		};

		callbacks[CALLBACK_ANALOG_VALUE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int value = IPConnection.unsignedShort(bb.getShort());

				for(AnalogValueListener listener: listenerAnalogValue) {
					listener.analogValue(value);
				}
			}
		};

		callbacks[CALLBACK_ILLUMINANCE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int illuminance = IPConnection.unsignedShort(bb.getShort());

				for(IlluminanceReachedListener listener: listenerIlluminanceReached) {
					listener.illuminanceReached(illuminance);
				}
			}
		};

		callbacks[CALLBACK_ANALOG_VALUE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int value = IPConnection.unsignedShort(bb.getShort());

				for(AnalogValueReachedListener listener: listenerAnalogValueReached) {
					listener.analogValueReached(value);
				}
			}
		};
	}

	/**
	 * Returns the illuminance of the ambient light sensor. The value
	 * has a range of 0 to 9000 and is given in Lux/10, i.e. a value
	 * of 4500 means that an illuminance of 450 Lux is measured.
	 * 
	 * If you want to get the illuminance periodically, it is recommended to use the
	 * listener {@link com.tinkerforge.BrickletAmbientLight.IlluminanceListener} and set the period with 
	 * {@link com.tinkerforge.BrickletAmbientLight.setIlluminanceCallbackPeriod}.
	 */
	public int getIlluminance() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_ILLUMINANCE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_ILLUMINANCE, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ILLUMINANCE);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int illuminance = IPConnection.unsignedShort(bb.getShort());

		return illuminance;
	}

	/**
	 * Returns the value as read by a 12-bit analog-to-digital converter.
	 * The value is between 0 and 4095.
	 * 
	 * \note
	 *  The value returned by {@link com.tinkerforge.BrickletAmbientLight.getIlluminance} is averaged over several samples
	 *  to yield less noise, while {@link com.tinkerforge.BrickletAmbientLight.getAnalogValue} gives back raw
	 *  unfiltered analog values. The only reason to use {@link com.tinkerforge.BrickletAmbientLight.getAnalogValue} is,
	 *  if you need the full resolution of the analog-to-digital converter.
	 * 
	 *  Also, the analog-to-digital converter covers three different ranges that are
	 *  set dynamically depending on the light intensity. It is impossible to
	 *  distinguish between these ranges with the analog value.
	 * 
	 * If you want the analog value periodically, it is recommended to use the 
	 * listener {@link com.tinkerforge.BrickletAmbientLight.AnalogValueListener} and set the period with 
	 * {@link com.tinkerforge.BrickletAmbientLight.setAnalogValueCallbackPeriod}.
	 */
	public int getAnalogValue() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_ANALOG_VALUE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_ANALOG_VALUE, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ANALOG_VALUE);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int value = IPConnection.unsignedShort(bb.getShort());

		return value;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletAmbientLight.IlluminanceListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletAmbientLight.IlluminanceListener} is only triggered if the illuminance has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setIlluminanceCallbackPeriod(long period) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_ILLUMINANCE_CALLBACK_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)12, FUNCTION_SET_ILLUMINANCE_CALLBACK_PERIOD, options, (byte)(0));
		bb.putInt((int)period);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_ILLUMINANCE_CALLBACK_PERIOD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the period as set by {@link com.tinkerforge.BrickletAmbientLight.setIlluminanceCallbackPeriod}.
	 */
	public long getIlluminanceCallbackPeriod() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_ILLUMINANCE_CALLBACK_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_ILLUMINANCE_CALLBACK_PERIOD, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ILLUMINANCE_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletAmbientLight.AnalogValueListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletAmbientLight.AnalogValueListener} is only triggered if the analog value has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setAnalogValueCallbackPeriod(long period) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_ANALOG_VALUE_CALLBACK_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)12, FUNCTION_SET_ANALOG_VALUE_CALLBACK_PERIOD, options, (byte)(0));
		bb.putInt((int)period);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_ANALOG_VALUE_CALLBACK_PERIOD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the period as set by {@link com.tinkerforge.BrickletAmbientLight.setAnalogValueCallbackPeriod}.
	 */
	public long getAnalogValueCallbackPeriod() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_ANALOG_VALUE_CALLBACK_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_ANALOG_VALUE_CALLBACK_PERIOD, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ANALOG_VALUE_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletAmbientLight.IlluminanceReachedListener} listener. 
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the illuminance is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the illuminance is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the illuminance is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the illuminance is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setIlluminanceCallbackThreshold(char option, short min, short max) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_ILLUMINANCE_CALLBACK_THRESHOLD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)13, FUNCTION_SET_ILLUMINANCE_CALLBACK_THRESHOLD, options, (byte)(0));
		bb.put((byte)option);
		bb.putShort((short)min);
		bb.putShort((short)max);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_ILLUMINANCE_CALLBACK_THRESHOLD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the threshold as set by {@link com.tinkerforge.BrickletAmbientLight.setIlluminanceCallbackThreshold}.
	 */
	public IlluminanceCallbackThreshold getIlluminanceCallbackThreshold() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_ILLUMINANCE_CALLBACK_THRESHOLD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_ILLUMINANCE_CALLBACK_THRESHOLD, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ILLUMINANCE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		IlluminanceCallbackThreshold obj = new IlluminanceCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = (bb.getShort());
		obj.max = (bb.getShort());

		return obj;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletAmbientLight.AnalogValueReachedListener} listener. 
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the analog value is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the analog value is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the analog value is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the analog value is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setAnalogValueCallbackThreshold(char option, int min, int max) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_ANALOG_VALUE_CALLBACK_THRESHOLD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)13, FUNCTION_SET_ANALOG_VALUE_CALLBACK_THRESHOLD, options, (byte)(0));
		bb.put((byte)option);
		bb.putShort((short)min);
		bb.putShort((short)max);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_ANALOG_VALUE_CALLBACK_THRESHOLD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the threshold as set by {@link com.tinkerforge.BrickletAmbientLight.setAnalogValueCallbackThreshold}.
	 */
	public AnalogValueCallbackThreshold getAnalogValueCallbackThreshold() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ANALOG_VALUE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		AnalogValueCallbackThreshold obj = new AnalogValueCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = IPConnection.unsignedShort(bb.getShort());
		obj.max = IPConnection.unsignedShort(bb.getShort());

		return obj;
	}

	/**
	 * Sets the period in ms with which the threshold listeners
	 * 
	 *  {@link com.tinkerforge.BrickletAmbientLight.IlluminanceReachedListener}, {@link com.tinkerforge.BrickletAmbientLight.AnalogValueReachedListener}
	 * 
	 * are triggered, if the thresholds
	 * 
	 *  {@link com.tinkerforge.BrickletAmbientLight.setIlluminanceCallbackThreshold}, {@link com.tinkerforge.BrickletAmbientLight.setAnalogValueCallbackThreshold}
	 * 
	 * keep being reached.
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
	 * Returns the debounce period as set by {@link com.tinkerforge.BrickletAmbientLight.setDebouncePeriod}.
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
	 * Adds a Illuminance listener.
	 */
	public void addIlluminanceListener(IlluminanceListener listener) {
		listenerIlluminance.add(listener);
	}

	/**
	 * Removes a Illuminance listener.
	 */
	public void removeIlluminanceListener(IlluminanceListener listener) {
		listenerIlluminance.remove(listener);
	}

	/**
	 * Adds a AnalogValue listener.
	 */
	public void addAnalogValueListener(AnalogValueListener listener) {
		listenerAnalogValue.add(listener);
	}

	/**
	 * Removes a AnalogValue listener.
	 */
	public void removeAnalogValueListener(AnalogValueListener listener) {
		listenerAnalogValue.remove(listener);
	}

	/**
	 * Adds a IlluminanceReached listener.
	 */
	public void addIlluminanceReachedListener(IlluminanceReachedListener listener) {
		listenerIlluminanceReached.add(listener);
	}

	/**
	 * Removes a IlluminanceReached listener.
	 */
	public void removeIlluminanceReachedListener(IlluminanceReachedListener listener) {
		listenerIlluminanceReached.remove(listener);
	}

	/**
	 * Adds a AnalogValueReached listener.
	 */
	public void addAnalogValueReachedListener(AnalogValueReachedListener listener) {
		listenerAnalogValueReached.add(listener);
	}

	/**
	 * Removes a AnalogValueReached listener.
	 */
	public void removeAnalogValueReachedListener(AnalogValueReachedListener listener) {
		listenerAnalogValueReached.remove(listener);
	}
}