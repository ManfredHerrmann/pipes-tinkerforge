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
 * Device for controlling up to 4 optically coupled digital inputs
 */
public class BrickletIndustrialDigitalIn4 extends Device {
	public final static int DEVICE_IDENTIFIER = 223;

	public final static byte FUNCTION_GET_VALUE = (byte)1;
	public final static byte FUNCTION_SET_GROUP = (byte)2;
	public final static byte FUNCTION_GET_GROUP = (byte)3;
	public final static byte FUNCTION_GET_AVAILABLE_FOR_GROUP = (byte)4;
	public final static byte FUNCTION_SET_DEBOUNCE_PERIOD = (byte)5;
	public final static byte FUNCTION_GET_DEBOUNCE_PERIOD = (byte)6;
	public final static byte FUNCTION_SET_INTERRUPT = (byte)7;
	public final static byte FUNCTION_GET_INTERRUPT = (byte)8;
	public final static byte CALLBACK_INTERRUPT = (byte)9;
	public final static byte FUNCTION_GET_IDENTITY = (byte)255;


	private List<InterruptListener> listenerInterrupt = new ArrayList<InterruptListener>();

	/**
	 * This listener is triggered whenever a change of the voltage level is detected
	 * on pins where the interrupt was activated with {@link com.tinkerforge.BrickletIndustrialDigitalIn4.setInterrupt}.
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
		public void interrupt(int interruptMask, int valueMask);
	}

	/**
	 * Creates an object with the unique device ID \c uid. and adds it to
	 * the IP Connection \c ipcon.
	 */
	public BrickletIndustrialDigitalIn4(String uid, IPConnection ipcon) {
		super(uid, ipcon);

		apiVersion[0] = 2;
		apiVersion[1] = 0;
		apiVersion[2] = 0;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_VALUE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_GROUP)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_GROUP)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_AVAILABLE_FOR_GROUP)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_DEBOUNCE_PERIOD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_DEBOUNCE_PERIOD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_INTERRUPT)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_INTERRUPT)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_IDENTITY)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_INTERRUPT)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;

		callbacks[CALLBACK_INTERRUPT] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int interruptMask = IPConnection.unsignedShort(bb.getShort());
				int valueMask = IPConnection.unsignedShort(bb.getShort());

				for(InterruptListener listener: listenerInterrupt) {
					listener.interrupt(interruptMask, valueMask);
				}
			}
		};
	}

	/**
	 * Returns the input value with a bitmask. The bitmask
	 * is 16 bit long, *true* refers to high and *false* refers to 
	 * low.
	 * 
	 * For example: The value 0b0000000000000011 means that pins 0-1 
	 * are high and the other pins are low.
	 * 
	 * If no groups are used (see {@link com.tinkerforge.BrickletIndustrialDigitalIn4.setGroup}), the pins correspond to the
	 * markings on the Digital In 4 Bricklet.
	 * 
	 * If groups are used, the pins correspond to the element in the group.
	 * Element 1 in the group will get pins 0-3, element 2 pins 4-7, element 3
	 * pins 8-11 and element 4 pins 12-15.
	 */
	public int getValue() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_VALUE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_VALUE, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_VALUE);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int valueMask = IPConnection.unsignedShort(bb.getShort());

		return valueMask;
	}

	/**
	 * Sets a group of Digital In 4 Bricklets that should work together. You can
	 * find Bricklets that can be grouped together with {@link com.tinkerforge.BrickletIndustrialDigitalIn4.getAvailableForGroup}.
	 * 
	 * The group consists of 4 elements. Element 1 in the group will get pins 0-3,
	 * element 2 pins 4-7, element 3 pins 8-11 and element 4 pins 12-15.
	 * 
	 * Each element can either be one of the ports ('a' to 'd') or 'n' if it should
	 * not be used.
	 * 
	 * For example: If you have two Digital In 4 Bricklets connected to port A and
	 * port B respectively, you could call with "['a', 'b', 'n', 'n']".
	 * 
	 * Now the pins on the Digital In 4 on port A are assigned to 0-3 and the
	 * pins on the Digital In 4 on port B are assigned to 4-7. It is now possible
	 * to call {@link com.tinkerforge.BrickletIndustrialDigitalIn4.getValue} and read out two Bricklets at the same time.
	 */
	public void setGroup(char[] group) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_GROUP);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)12, FUNCTION_SET_GROUP, options, (byte)(0));
		for(int i = 0; i < 4; i++) {
			bb.put((byte)group[i]);
		}


		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_GROUP);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the group as set by {@link com.tinkerforge.BrickletIndustrialDigitalIn4.setGroup}
	 */
	public char[] getGroup() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_GROUP);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_GROUP, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_GROUP);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		char[] group = new char[4];
		for(int i = 0; i < 4; i++) {
			group[i] = (char)(bb.get());
		}


		return group;
	}

	/**
	 * Returns a bitmask of ports that are available for grouping. For example the
	 * value 0b0101 means: Port *A* and Port *C* are connected to Bricklets that
	 * can be grouped together.
	 */
	public short getAvailableForGroup() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_AVAILABLE_FOR_GROUP);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_AVAILABLE_FOR_GROUP, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_AVAILABLE_FOR_GROUP);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short available = IPConnection.unsignedByte(bb.get());

		return available;
	}

	/**
	 * Sets the debounce period of the {@link com.tinkerforge.BrickletIndustrialDigitalIn4.InterruptListener} listener in ms.
	 * 
	 * For example: If you set this value to 100, you will get the interrupt
	 * maximal every 100ms. This is necessary if something that bounces is
	 * connected to the Digital In 4 Bricklet, such as a button.
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
	 * Returns the debounce period as set by {@link com.tinkerforge.BrickletIndustrialDigitalIn4.setDebouncePeriod}.
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
	 * For example: An interrupt bitmask of 9 (0b0000000000001001) will 
	 * enable the interrupt for pins 0 and 3.
	 * 
	 * The interrupts use the grouping as set by {@link com.tinkerforge.BrickletIndustrialDigitalIn4.setGroup}.
	 * 
	 * The interrupt is delivered with the listener {@link com.tinkerforge.BrickletIndustrialDigitalIn4.InterruptListener}.
	 */
	public void setInterrupt(int interruptMask) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_INTERRUPT);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)10, FUNCTION_SET_INTERRUPT, options, (byte)(0));
		bb.putShort((short)interruptMask);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_INTERRUPT);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the interrupt bitmask as set by {@link com.tinkerforge.BrickletIndustrialDigitalIn4.setInterrupt}.
	 */
	public int getInterrupt() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_INTERRUPT);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_INTERRUPT, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_INTERRUPT);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int interruptMask = IPConnection.unsignedShort(bb.getShort());

		return interruptMask;
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
}