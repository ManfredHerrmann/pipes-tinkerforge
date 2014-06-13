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
 * Device for controlling up to 4 general purpose input/output pins
 */
public class BrickletIO4 extends Device {
	public final static int DEVICE_IDENTIFIER = 29;

	public final static byte FUNCTION_SET_VALUE = (byte)1;
	public final static byte FUNCTION_GET_VALUE = (byte)2;
	public final static byte FUNCTION_SET_CONFIGURATION = (byte)3;
	public final static byte FUNCTION_GET_CONFIGURATION = (byte)4;
	public final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)5;
	public final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)6;
	public final static byte FUNCTION_SET_INTERRUPT = (byte)7;
	public final static byte FUNCTION_GET_INTERRUPT = (byte)8;
	public final static byte CALLBACK_INTERRUPT = (byte)9;
	public final static byte FUNCTION_SET_MONOFLOP = (byte)10;
	public final static byte FUNCTION_GET_MONOFLOP = (byte)11;
	public final static byte CALLBACK_MONOFLOP_DONE = (byte)12;
	public final static byte FUNCTION_SET_SELECTED_VALUES = (byte)13;
	public final static byte FUNCTION_GET_IDENTITY = (byte)255;

	public final static char DIRECTION_IN = (char)'i';
	public final static char DIRECTION_OUT = (char)'o';

	private List<InterruptListener> listenerInterrupt = new ArrayList<InterruptListener>();
	private List<MonoflopDoneListener> listenerMonoflopDone = new ArrayList<MonoflopDoneListener>();

	public class Configuration {
		public short directionMask;
		public short valueMask;

		public String toString() {
			return "[" + "directionMask = " + directionMask + ", " + "valueMask = " + valueMask + "]";
		}
	}

	public class Monoflop {
		public short pin;
		public short value;
		public long time;
		public long timeRemaining;

		public String toString() {
			return "[" + "pin = " + pin + ", " + "value = " + value + ", " + "time = " + time + ", " + "timeRemaining = " + timeRemaining + "]";
		}
	}

	/**
	 * This listener is triggered whenever a change of the voltage level is detected
	 * on pins where the interrupt was activated with {@link com.tinkerforge.BrickletIO4.setInterrupt}.
	 * 
	 * The values are a bitmask that specifies which interrupts occurred
	 * and the current value bitmask.
	 * 
	 * For example:
	 * 
	 * * (1, 1) means that an interrupt on pin 0 occurred and
	 *   currently pin 0 is high and pins 1-3 are low.
	 * * (9, 14) means that interrupts on pins 0 and 3
	 *   occurred and currently pin 0 is low and pins 1-3 are high.
	 */
	public interface InterruptListener {
		public void interrupt(short interruptMask, short valueMask);
	}

	/**
	 * This listener is triggered whenever a monoflop timer reaches 0. The
	 * parameters contain the involved pins and the current value of the pins
	 * (the value after the monoflop).
	 * 
	 * .. versionadded:: 1.1.1~(Plugin)
	 */
	public interface MonoflopDoneListener {
		public void monoflopDone(short selectionMask, short valueMask);
	}

	/**
	 * Creates an object with the unique device ID \c uid. and adds it to
	 * the IP Connection \c ipcon.
	 */
	public BrickletIO4(String uid, IPConnection ipcon) {
		super(uid, ipcon);

		apiVersion[0] = 2;
		apiVersion[1] = 0;
		apiVersion[2] = 0;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_VALUE)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_VALUE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_CONFIGURATION)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_CONFIGURATION)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_DEBOUNCE_PERIOD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_DEBOUNCE_PERIOD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_INTERRUPT)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_INTERRUPT)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_MONOFLOP)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_MONOFLOP)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_SELECTED_VALUES)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_IDENTITY)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_INTERRUPT)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_MONOFLOP_DONE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;

		callbacks[CALLBACK_INTERRUPT] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short interruptMask = IPConnection.unsignedByte(bb.get());
				short valueMask = IPConnection.unsignedByte(bb.get());

				for(InterruptListener listener: listenerInterrupt) {
					listener.interrupt(interruptMask, valueMask);
				}
			}
		};

		callbacks[CALLBACK_MONOFLOP_DONE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short selectionMask = IPConnection.unsignedByte(bb.get());
				short valueMask = IPConnection.unsignedByte(bb.get());

				for(MonoflopDoneListener listener: listenerMonoflopDone) {
					listener.monoflopDone(selectionMask, valueMask);
				}
			}
		};
	}

	/**
	 * Sets the output value (high or low) with a bitmask. The bitmask
	 * is 4 bit long, *true* refers to high and *false* refers to low.
	 * 
	 * For example: The value 0b0011 will turn the pins 0-1 high and the
	 * pins 2-3 low.
	 * 
	 * \note
	 *  This function does nothing for pins that are configured as input.
	 *  Pull-up resistors can be switched on with {@link com.tinkerforge.BrickletIO4.setConfiguration}.
	 */
	public void setValue(short valueMask) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_VALUE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)9, FUNCTION_SET_VALUE, options, (byte)(0));
		bb.put((byte)valueMask);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_VALUE);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns a bitmask of the values that are currently measured.
	 * This function works if the pin is configured to input
	 * as well as if it is configured to output.
	 */
	public short getValue() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_VALUE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_VALUE, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_VALUE);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short valueMask = IPConnection.unsignedByte(bb.get());

		return valueMask;
	}

	/**
	 * Configures the value and direction of the specified pins. Possible directions
	 * are "i" and "o" for input and output.
	 * 
	 * If the direction is configured as output, the value is either high or low
	 * (set as *true* or *false*).
	 * 
	 * If the direction is configured as input, the value is either pull-up or
	 * default (set as *true* or *false*).
	 * 
	 * For example:
	 * 
	 * * (15, 'i', true) will set all pins of as input pull-up.
	 * * (8, 'i', false) will set pin 3 of as input default (floating if nothing is connected).
	 * * (3, 'o', false) will set pins 0 and 1 as output low.
	 * * (4, 'o', true) will set pin 2 of as output high.
	 */
	public void setConfiguration(short selectionMask, char direction, boolean value) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_CONFIGURATION);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)11, FUNCTION_SET_CONFIGURATION, options, (byte)(0));
		bb.put((byte)selectionMask);
		bb.put((byte)direction);
		bb.put((byte)(value ? 1 : 0));

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_CONFIGURATION);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns a value bitmask and a direction bitmask.
	 * 
	 * For example: A return value of 0b0011 and 0b0101 for
	 * direction and value means that:
	 * 
	 * * pin 0 is configured as input pull-up,
	 * * pin 1 is configured as input default,
	 * * pin 2 is configured as output high
	 * * and pin 3 is are configured as output low.
	 */
	public Configuration getConfiguration() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_CONFIGURATION);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_CONFIGURATION, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CONFIGURATION);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Configuration obj = new Configuration();
		obj.directionMask = IPConnection.unsignedByte(bb.get());
		obj.valueMask = IPConnection.unsignedByte(bb.get());

		return obj;
	}

	/**
	 * Sets the debounce period of the {@link com.tinkerforge.BrickletIO4.InterruptListener} listener in ms.
	 * 
	 * For example: If you set this value to 100, you will get the interrupt
	 * maximal every 100ms. This is necessary if something that bounces is
	 * connected to the IO-4 Bricklet, such as a button.
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
	 * Returns the debounce period as set by {@link com.tinkerforge.BrickletIO4.setDebouncePeriod}.
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
	 * Sets the pins on which an interrupt is activated with a bitmask.
	 * Interrupts are triggered on changes of the voltage level of the pin,
	 * i.e. changes from high to low and low to high.
	 * 
	 * For example: An interrupt bitmask of 9 will enable the interrupt for
	 * pins 0 and 3.
	 * 
	 * The interrupt is delivered with the listener {@link com.tinkerforge.BrickletIO4.InterruptListener}.
	 */
	public void setInterrupt(short interruptMask) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_INTERRUPT);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)9, FUNCTION_SET_INTERRUPT, options, (byte)(0));
		bb.put((byte)interruptMask);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_INTERRUPT);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the interrupt bitmask as set by {@link com.tinkerforge.BrickletIO4.setInterrupt}.
	 */
	public short getInterrupt() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_INTERRUPT);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_INTERRUPT, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_INTERRUPT);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short interruptMask = IPConnection.unsignedByte(bb.get());

		return interruptMask;
	}

	/**
	 * Configures a monoflop of the pins specified by the first parameter as 4 bit
	 * long bitmask. The specified pins must be configured for output. Non-output
	 * pins will be ignored.
	 * 
	 * The second parameter is a bitmask with the desired value of the specified
	 * output pins (*true* means high and *false* means low).
	 * 
	 * The third parameter indicates the time (in ms) that the pins should hold
	 * the value.
	 * 
	 * If this function is called with the parameters ((1 << 0) | (1 << 3), (1 << 0), 1500):
	 * Pin 0 will get high and pin 3 will get low. In 1.5s pin 0 will get low and pin
	 * 3 will get high again.
	 * 
	 * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
	 * have a RS485 bus and an IO-4 Bricklet connected to one of the slave
	 * stacks. You can now call this function every second, with a time parameter
	 * of two seconds and pin 0 set to high. Pin 0 will be high all the time. If now
	 * the RS485 connection is lost, then pin 0 will get low in at most two seconds.
	 * 
	 * .. versionadded:: 1.1.1~(Plugin)
	 */
	public void setMonoflop(short selectionMask, short valueMask, long time) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_MONOFLOP);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)14, FUNCTION_SET_MONOFLOP, options, (byte)(0));
		bb.put((byte)selectionMask);
		bb.put((byte)valueMask);
		bb.putInt((int)time);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_MONOFLOP);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns (for the given pin) the current value and the time as set by
	 * {@link com.tinkerforge.BrickletIO4.setMonoflop} as well as the remaining time until the value flips.
	 * 
	 * If the timer is not running currently, the remaining time will be returned
	 * as 0.
	 * 
	 * .. versionadded:: 1.1.1~(Plugin)
	 */
	public Monoflop getMonoflop(short pin) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_MONOFLOP);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)9, FUNCTION_GET_MONOFLOP, options, (byte)(0));
		bb.put((byte)pin);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MONOFLOP);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Monoflop obj = new Monoflop();
		obj.value = IPConnection.unsignedByte(bb.get());
		obj.time = IPConnection.unsignedInt(bb.getInt());
		obj.timeRemaining = IPConnection.unsignedInt(bb.getInt());

		return obj;
	}

	/**
	 * Sets the output value (high or low) with a bitmask, according to
	 * the selction mask. The bitmask is 4 bit long, *true* refers to high 
	 * and *false* refers to low.
	 * 
	 * For example: The values 0b0110, 0b0011 will turn pin 2 high and
	 * pin 1 low, pin 0 and 3 will remain untouched.
	 * 
	 * \note
	 *  This function does nothing for pins that are configured as input.
	 *  Pull-up resistors can be switched on with {@link com.tinkerforge.BrickletIO4.setConfiguration}.
	 * 
	 * .. versionadded:: 2.0.0~(Plugin)
	 */
	public void setSelectedValues(short selectionMask, short valueMask) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_SELECTED_VALUES);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)10, FUNCTION_SET_SELECTED_VALUES, options, (byte)(0));
		bb.put((byte)selectionMask);
		bb.put((byte)valueMask);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_SELECTED_VALUES);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
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
	 * Adds a Interrupt listener.
	 */
	public void addInterruptListener(InterruptListener listener) {
		listenerInterrupt.add(listener);
	}

	/**
	 * Removes a Interrupt listener.
	 */
	public void removeInterruptListener(InterruptListener listener) {
		listenerInterrupt.remove(listener);
	}

	/**
	 * Adds a MonoflopDone listener.
	 */
	public void addMonoflopDoneListener(MonoflopDoneListener listener) {
		listenerMonoflopDone.add(listener);
	}

	/**
	 * Removes a MonoflopDone listener.
	 */
	public void removeMonoflopDoneListener(MonoflopDoneListener listener) {
		listenerMonoflopDone.remove(listener);
	}
}