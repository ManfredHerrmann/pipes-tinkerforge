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
 * Device for non-contact temperature sensing
 */
public class BrickletTemperatureIR extends Device {
	public final static int DEVICE_IDENTIFIER = 217;

	public final static byte FUNCTION_GET_AMBIENT_TEMPERATURE = (byte)1;
	public final static byte FUNCTION_GET_OBJECT_TEMPERATURE = (byte)2;
	public final static byte FUNCTION_SET_EMISSIVITY = (byte)3;
	public final static byte FUNCTION_GET_EMISSIVITY = (byte)4;
	public final static byte FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD = (byte)5;
	public final static byte FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD = (byte)6;
	public final static byte FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_PERIOD = (byte)7;
	public final static byte FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_PERIOD = (byte)8;
	public final static byte FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD = (byte)9;
	public final static byte FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD = (byte)10;
	public final static byte FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD = (byte)11;
	public final static byte FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD = (byte)12;
	public final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)13;
	public final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)14;
	public final static byte CALLBACK_AMBIENT_TEMPERATURE = (byte)15;
	public final static byte CALLBACK_OBJECT_TEMPERATURE = (byte)16;
	public final static byte CALLBACK_AMBIENT_TEMPERATURE_REACHED = (byte)17;
	public final static byte CALLBACK_OBJECT_TEMPERATURE_REACHED = (byte)18;
	public final static byte FUNCTION_GET_IDENTITY = (byte)255;

	public final static char THRESHOLD_OPTION_OFF = (char)'x';
	public final static char THRESHOLD_OPTION_OUTSIDE = (char)'o';
	public final static char THRESHOLD_OPTION_INSIDE = (char)'i';
	public final static char THRESHOLD_OPTION_SMALLER = (char)'<';
	public final static char THRESHOLD_OPTION_GREATER = (char)'>';

	private List<AmbientTemperatureListener> listenerAmbientTemperature = new ArrayList<AmbientTemperatureListener>();
	private List<ObjectTemperatureListener> listenerObjectTemperature = new ArrayList<ObjectTemperatureListener>();
	private List<AmbientTemperatureReachedListener> listenerAmbientTemperatureReached = new ArrayList<AmbientTemperatureReachedListener>();
	private List<ObjectTemperatureReachedListener> listenerObjectTemperatureReached = new ArrayList<ObjectTemperatureReachedListener>();

	public class AmbientTemperatureCallbackThreshold {
		public char option;
		public short min;
		public short max;

		public String toString() {
			return "[" + "option = " + option + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	public class ObjectTemperatureCallbackThreshold {
		public char option;
		public short min;
		public short max;

		public String toString() {
			return "[" + "option = " + option + ", " + "min = " + min + ", " + "max = " + max + "]";
		}
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link com.tinkerforge.BrickletTemperatureIR.setAmbientTemperatureCallbackPeriod}. The parameter is the ambient
	 * temperature of the sensor.
	 * 
	 * {@link com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureListener} is only triggered if the ambient temperature
	 * has changed since the last triggering.
	 */
	public interface AmbientTemperatureListener {
		public void ambientTemperature(short temperature);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link com.tinkerforge.BrickletTemperatureIR.setObjectTemperatureCallbackPeriod}. The parameter is the object
	 * temperature of the sensor.
	 * 
	 * {@link com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureListener} is only triggered if the object temperature
	 * has changed since the last triggering.
	 */
	public interface ObjectTemperatureListener {
		public void objectTemperature(short temperature);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link com.tinkerforge.BrickletTemperatureIR.setAmbientTemperatureCallbackThreshold} is reached.
	 * The parameter is the ambient temperature of the sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link com.tinkerforge.BrickletTemperatureIR.setDebouncePeriod}.
	 */
	public interface AmbientTemperatureReachedListener {
		public void ambientTemperatureReached(short temperature);
	}

	/**
	 * This listener is triggered when the threshold as set by
	 * {@link com.tinkerforge.BrickletTemperatureIR.setObjectTemperatureCallbackThreshold} is reached.
	 * The parameter is the object temperature of the sensor.
	 * 
	 * If the threshold keeps being reached, the listener is triggered periodically
	 * with the period as set by {@link com.tinkerforge.BrickletTemperatureIR.setDebouncePeriod}.
	 */
	public interface ObjectTemperatureReachedListener {
		public void objectTemperatureReached(short temperature);
	}

	/**
	 * Creates an object with the unique device ID \c uid. and adds it to
	 * the IP Connection \c ipcon.
	 */
	public BrickletTemperatureIR(String uid, IPConnection ipcon) {
		super(uid, ipcon);

		apiVersion[0] = 2;
		apiVersion[1] = 0;
		apiVersion[2] = 0;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_AMBIENT_TEMPERATURE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_OBJECT_TEMPERATURE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_EMISSIVITY)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_EMISSIVITY)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_PERIOD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_PERIOD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_DEBOUNCE_PERIOD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_DEBOUNCE_PERIOD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_IDENTITY)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_AMBIENT_TEMPERATURE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_OBJECT_TEMPERATURE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_AMBIENT_TEMPERATURE_REACHED)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_OBJECT_TEMPERATURE_REACHED)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;

		callbacks[CALLBACK_AMBIENT_TEMPERATURE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short temperature = (bb.getShort());

				for(AmbientTemperatureListener listener: listenerAmbientTemperature) {
					listener.ambientTemperature(temperature);
				}
			}
		};

		callbacks[CALLBACK_OBJECT_TEMPERATURE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short temperature = (bb.getShort());

				for(ObjectTemperatureListener listener: listenerObjectTemperature) {
					listener.objectTemperature(temperature);
				}
			}
		};

		callbacks[CALLBACK_AMBIENT_TEMPERATURE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short temperature = (bb.getShort());

				for(AmbientTemperatureReachedListener listener: listenerAmbientTemperatureReached) {
					listener.ambientTemperatureReached(temperature);
				}
			}
		};

		callbacks[CALLBACK_OBJECT_TEMPERATURE_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short temperature = (bb.getShort());

				for(ObjectTemperatureReachedListener listener: listenerObjectTemperatureReached) {
					listener.objectTemperatureReached(temperature);
				}
			}
		};
	}

	/**
	 * Returns the ambient temperature of the sensor. The value
	 * has a range of -400 to 1250 and is given in °C/10,
	 * e.g. a value of 423 means that an ambient temperature of 42.3 °C is 
	 * measured.
	 * 
	 * If you want to get the ambient temperature periodically, it is recommended 
	 * to use the listener {@link com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureListener} and set the period with 
	 * {@link com.tinkerforge.BrickletTemperatureIR.setAmbientTemperatureCallbackPeriod}.
	 */
	public short getAmbientTemperature() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_AMBIENT_TEMPERATURE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_AMBIENT_TEMPERATURE, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_AMBIENT_TEMPERATURE);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short temperature = (bb.getShort());

		return temperature;
	}

	/**
	 * Returns the object temperature of the sensor, i.e. the temperature
	 * of the surface of the object the sensor is aimed at. The value
	 * has a range of -700 to 3800 and is given in °C/10,
	 * e.g. a value of 3001 means that a temperature of 300.1 °C is measured
	 * on the surface of the object.
	 * 
	 * The temperature of different materials is dependent on their `emissivity 
	 * <http://en.wikipedia.org/wiki/Emissivity>`__. The emissivity of the material
	 * can be set with {@link com.tinkerforge.BrickletTemperatureIR.setEmissivity}.
	 * 
	 * If you want to get the object temperature periodically, it is recommended 
	 * to use the listener {@link com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureListener} and set the period with 
	 * {@link com.tinkerforge.BrickletTemperatureIR.setObjectTemperatureCallbackPeriod}.
	 */
	public short getObjectTemperature() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_OBJECT_TEMPERATURE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_OBJECT_TEMPERATURE, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_OBJECT_TEMPERATURE);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short temperature = (bb.getShort());

		return temperature;
	}

	/**
	 * Sets the `emissivity <http://en.wikipedia.org/wiki/Emissivity>`__ that is
	 * used to calculate the surface temperature as returned by 
	 * {@link com.tinkerforge.BrickletTemperatureIR.getObjectTemperature}. 
	 * 
	 * The emissivity is usually given as a value between 0.0 and 1.0. A list of
	 * emissivities of different materials can be found 
	 * `here <http://www.infrared-thermography.com/material.htm>`__.
	 * 
	 * The parameter of {@link com.tinkerforge.BrickletTemperatureIR.setEmissivity} has to be given with a factor of
	 * 65535 (16-bit). For example: An emissivity of 0.1 can be set with the
	 * value 6553, an emissivity of 0.5 with the value 32767 and so on.
	 * 
	 * \note
	 *  If you need a precise measurement for the object temperature, it is
	 *  absolutely crucial that you also provide a precise emissivity.
	 * 
	 * The default emissivity is 1.0 (value of 65535) and the minimum emissivity the
	 * sensor can handle is 0.1 (value of 6553).
	 */
	public void setEmissivity(int emissivity) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_EMISSIVITY);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)10, FUNCTION_SET_EMISSIVITY, options, (byte)(0));
		bb.putShort((short)emissivity);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_EMISSIVITY);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the emissivity as set by {@link com.tinkerforge.BrickletTemperatureIR.setEmissivity}.
	 */
	public int getEmissivity() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_EMISSIVITY);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_EMISSIVITY, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_EMISSIVITY);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int emissivity = IPConnection.unsignedShort(bb.getShort());

		return emissivity;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureListener} is only triggered if the temperature has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setAmbientTemperatureCallbackPeriod(long period) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)12, FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD, options, (byte)(0));
		bb.putInt((int)period);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the period as set by {@link com.tinkerforge.BrickletTemperatureIR.setAmbientTemperatureCallbackPeriod}.
	 */
	public long getAmbientTemperatureCallbackPeriod() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * {@link com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureListener} is only triggered if the temperature has changed since the
	 * last triggering.
	 * 
	 * The default value is 0.
	 */
	public void setObjectTemperatureCallbackPeriod(long period) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)12, FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_PERIOD, options, (byte)(0));
		bb.putInt((int)period);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_PERIOD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the period as set by {@link com.tinkerforge.BrickletTemperatureIR.setObjectTemperatureCallbackPeriod}.
	 */
	public long getObjectTemperatureCallbackPeriod() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_PERIOD, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_PERIOD);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureReachedListener} listener. 
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the ambient temperature is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the ambient temperature is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the ambient temperature is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the ambient temperature is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setAmbientTemperatureCallbackThreshold(char option, short min, short max) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)13, FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD, options, (byte)(0));
		bb.put((byte)option);
		bb.putShort((short)min);
		bb.putShort((short)max);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the threshold as set by {@link com.tinkerforge.BrickletTemperatureIR.setAmbientTemperatureCallbackThreshold}.
	 */
	public AmbientTemperatureCallbackThreshold getAmbientTemperatureCallbackThreshold() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_AMBIENT_TEMPERATURE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		AmbientTemperatureCallbackThreshold obj = new AmbientTemperatureCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = (bb.getShort());
		obj.max = (bb.getShort());

		return obj;
	}

	/**
	 * Sets the thresholds for the {@link com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureReachedListener} listener. 
	 * 
	 * The following options are possible:
	 * 
	 * \verbatim
	 *  "Option", "Description"
	 * 
	 *  "'x'",    "Listener is turned off"
	 *  "'o'",    "Listener is triggered when the object temperature is *outside* the min and max values"
	 *  "'i'",    "Listener is triggered when the object temperature is *inside* the min and max values"
	 *  "'<'",    "Listener is triggered when the object temperature is smaller than the min value (max is ignored)"
	 *  "'>'",    "Listener is triggered when the object temperature is greater than the min value (max is ignored)"
	 * \endverbatim
	 * 
	 * The default value is ('x', 0, 0).
	 */
	public void setObjectTemperatureCallbackThreshold(char option, short min, short max) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)13, FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD, options, (byte)(0));
		bb.put((byte)option);
		bb.putShort((short)min);
		bb.putShort((short)max);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the threshold as set by {@link com.tinkerforge.BrickletTemperatureIR.setObjectTemperatureCallbackThreshold}.
	 */
	public ObjectTemperatureCallbackThreshold getObjectTemperatureCallbackThreshold() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_OBJECT_TEMPERATURE_CALLBACK_THRESHOLD);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		ObjectTemperatureCallbackThreshold obj = new ObjectTemperatureCallbackThreshold();
		obj.option = (char)(bb.get());
		obj.min = (bb.getShort());
		obj.max = (bb.getShort());

		return obj;
	}

	/**
	 * Sets the period in ms with which the threshold listeners
	 * 
	 *  {@link com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureReachedListener}, {@link com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureReachedListener}
	 * 
	 * are triggered, if the thresholds
	 * 
	 *  {@link com.tinkerforge.BrickletTemperatureIR.setAmbientTemperatureCallbackThreshold}, {@link com.tinkerforge.BrickletTemperatureIR.setObjectTemperatureCallbackThreshold}
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
	 * Returns the debounce period as set by {@link com.tinkerforge.BrickletTemperatureIR.setDebouncePeriod}.
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
	 * Adds a AmbientTemperature listener.
	 */
	public void addAmbientTemperatureListener(AmbientTemperatureListener listener) {
		listenerAmbientTemperature.add(listener);
	}

	/**
	 * Removes a AmbientTemperature listener.
	 */
	public void removeAmbientTemperatureListener(AmbientTemperatureListener listener) {
		listenerAmbientTemperature.remove(listener);
	}

	/**
	 * Adds a ObjectTemperature listener.
	 */
	public void addObjectTemperatureListener(ObjectTemperatureListener listener) {
		listenerObjectTemperature.add(listener);
	}

	/**
	 * Removes a ObjectTemperature listener.
	 */
	public void removeObjectTemperatureListener(ObjectTemperatureListener listener) {
		listenerObjectTemperature.remove(listener);
	}

	/**
	 * Adds a AmbientTemperatureReached listener.
	 */
	public void addAmbientTemperatureReachedListener(AmbientTemperatureReachedListener listener) {
		listenerAmbientTemperatureReached.add(listener);
	}

	/**
	 * Removes a AmbientTemperatureReached listener.
	 */
	public void removeAmbientTemperatureReachedListener(AmbientTemperatureReachedListener listener) {
		listenerAmbientTemperatureReached.remove(listener);
	}

	/**
	 * Adds a ObjectTemperatureReached listener.
	 */
	public void addObjectTemperatureReachedListener(ObjectTemperatureReachedListener listener) {
		listenerObjectTemperatureReached.add(listener);
	}

	/**
	 * Removes a ObjectTemperatureReached listener.
	 */
	public void removeObjectTemperatureReachedListener(ObjectTemperatureReachedListener listener) {
		listenerObjectTemperatureReached.remove(listener);
	}
}