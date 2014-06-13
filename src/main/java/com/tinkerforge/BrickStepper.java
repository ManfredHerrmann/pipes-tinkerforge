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
 * Device for controlling stepper motors
 */
public class BrickStepper extends Device {
	public final static int DEVICE_IDENTIFIER = 15;

	public final static byte FUNCTION_SET_MAX_VELOCITY = (byte)1;
	public final static byte FUNCTION_GET_MAX_VELOCITY = (byte)2;
	public final static byte FUNCTION_GET_CURRENT_VELOCITY = (byte)3;
	public final static byte FUNCTION_SET_SPEED_RAMPING = (byte)4;
	public final static byte FUNCTION_GET_SPEED_RAMPING = (byte)5;
	public final static byte FUNCTION_FULL_BRAKE = (byte)6;
	public final static byte FUNCTION_SET_CURRENT_POSITION = (byte)7;
	public final static byte FUNCTION_GET_CURRENT_POSITION = (byte)8;
	public final static byte FUNCTION_SET_TARGET_POSITION = (byte)9;
	public final static byte FUNCTION_GET_TARGET_POSITION = (byte)10;
	public final static byte FUNCTION_SET_STEPS = (byte)11;
	public final static byte FUNCTION_GET_STEPS = (byte)12;
	public final static byte FUNCTION_GET_REMAINING_STEPS = (byte)13;
	public final static byte FUNCTION_SET_STEP_MODE = (byte)14;
	public final static byte FUNCTION_GET_STEP_MODE = (byte)15;
	public final static byte FUNCTION_DRIVE_FORWARD = (byte)16;
	public final static byte FUNCTION_DRIVE_BACKWARD = (byte)17;
	public final static byte FUNCTION_STOP = (byte)18;
	public final static byte FUNCTION_GET_STACK_INPUT_VOLTAGE = (byte)19;
	public final static byte FUNCTION_GET_EXTERNAL_INPUT_VOLTAGE = (byte)20;
	public final static byte FUNCTION_GET_CURRENT_CONSUMPTION = (byte)21;
	public final static byte FUNCTION_SET_MOTOR_CURRENT = (byte)22;
	public final static byte FUNCTION_GET_MOTOR_CURRENT = (byte)23;
	public final static byte FUNCTION_ENABLE = (byte)24;
	public final static byte FUNCTION_DISABLE = (byte)25;
	public final static byte FUNCTION_IS_ENABLED = (byte)26;
	public final static byte FUNCTION_SET_DECAY = (byte)27;
	public final static byte FUNCTION_GET_DECAY = (byte)28;
	public final static byte FUNCTION_SET_MINIMUM_VOLTAGE = (byte)29;
	public final static byte FUNCTION_GET_MINIMUM_VOLTAGE = (byte)30;
	public final static byte CALLBACK_UNDER_VOLTAGE = (byte)31;
	public final static byte CALLBACK_POSITION_REACHED = (byte)32;
	public final static byte FUNCTION_SET_SYNC_RECT = (byte)33;
	public final static byte FUNCTION_IS_SYNC_RECT = (byte)34;
	public final static byte FUNCTION_SET_TIME_BASE = (byte)35;
	public final static byte FUNCTION_GET_TIME_BASE = (byte)36;
	public final static byte FUNCTION_GET_ALL_DATA = (byte)37;
	public final static byte FUNCTION_SET_ALL_DATA_PERIOD = (byte)38;
	public final static byte FUNCTION_GET_ALL_DATA_PERIOD = (byte)39;
	public final static byte CALLBACK_ALL_DATA = (byte)40;
	public final static byte CALLBACK_NEW_STATE = (byte)41;
	public final static byte FUNCTION_GET_PROTOCOL1_BRICKLET_NAME = (byte)241;
	public final static byte FUNCTION_GET_CHIP_TEMPERATURE = (byte)242;
	public final static byte FUNCTION_RESET = (byte)243;
	public final static byte FUNCTION_GET_IDENTITY = (byte)255;

	public final static short STEP_MODE_FULL_STEP = (short)1;
	public final static short STEP_MODE_HALF_STEP = (short)2;
	public final static short STEP_MODE_QUARTER_STEP = (short)4;
	public final static short STEP_MODE_EIGHTH_STEP = (short)8;
	public final static short STATE_STOP = (short)1;
	public final static short STATE_ACCELERATION = (short)2;
	public final static short STATE_RUN = (short)3;
	public final static short STATE_DEACCELERATION = (short)4;
	public final static short STATE_DIRECTION_CHANGE_TO_FORWARD = (short)5;
	public final static short STATE_DIRECTION_CHANGE_TO_BACKWARD = (short)6;

	private List<UnderVoltageListener> listenerUnderVoltage = new ArrayList<UnderVoltageListener>();
	private List<PositionReachedListener> listenerPositionReached = new ArrayList<PositionReachedListener>();
	private List<AllDataListener> listenerAllData = new ArrayList<AllDataListener>();
	private List<NewStateListener> listenerNewState = new ArrayList<NewStateListener>();

	public class SpeedRamping {
		public int acceleration;
		public int deacceleration;

		public String toString() {
			return "[" + "acceleration = " + acceleration + ", " + "deacceleration = " + deacceleration + "]";
		}
	}

	public class AllData {
		public int currentVelocity;
		public int currentPosition;
		public int remainingSteps;
		public int stackVoltage;
		public int externalVoltage;
		public int currentConsumption;

		public String toString() {
			return "[" + "currentVelocity = " + currentVelocity + ", " + "currentPosition = " + currentPosition + ", " + "remainingSteps = " + remainingSteps + ", " + "stackVoltage = " + stackVoltage + ", " + "externalVoltage = " + externalVoltage + ", " + "currentConsumption = " + currentConsumption + "]";
		}
	}

	public class Protocol1BrickletName {
		public char port;
		public short protocolVersion;
		public short[] firmwareVersion = new short[3];
		public String name;

		public String toString() {
			return "[" + "port = " + port + ", " + "protocolVersion = " + protocolVersion + ", " + "firmwareVersion = " + Arrays.toString(firmwareVersion) + ", " + "name = " + name + "]";
		}
	}

	/**
	 * This listener is triggered when the input voltage drops below the value set by
	 * {@link com.tinkerforge.BrickStepper.setMinimumVoltage}. The parameter is the current voltage given
	 * in mV.
	 */
	public interface UnderVoltageListener {
		public void underVoltage(int voltage);
	}

	/**
	 * This listener is triggered when a position set by {@link com.tinkerforge.BrickStepper.setSteps} or
	 * {@link com.tinkerforge.BrickStepper.setTargetPosition} is reached.
	 * 
	 * \note
	 *  Since we can't get any feedback from the stepper motor, this only works if the
	 *  acceleration (see {@link com.tinkerforge.BrickStepper.setSpeedRamping}) is set smaller or equal to the
	 *  maximum acceleration of the motor. Otherwise the motor will lag behind the
	 *  control value and the listener will be triggered too early.
	 */
	public interface PositionReachedListener {
		public void positionReached(int position);
	}

	/**
	 * This listener is triggered periodically with the period that is set by
	 * {@link com.tinkerforge.BrickStepper.setAllDataPeriod}. The parameters are: the current velocity,
	 * the current position, the remaining steps, the stack voltage, the external
	 * voltage and the current consumption of the stepper motor.
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public interface AllDataListener {
		public void allData(int currentVelocity, int currentPosition, int remainingSteps, int stackVoltage, int externalVoltage, int currentConsumption);
	}

	/**
	 * This listener is triggered whenever the Stepper Brick enters a new state. 
	 * It returns the new state as well as the previous state.
	 * 
	 * Possible states are:
	 * 
	 * * Stop = 1
	 * * Acceleration = 2
	 * * Run = 3
	 * * Deacceleration = 4
	 * * Direction change to forward = 5
	 * * Direction change to backward = 6
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public interface NewStateListener {
		public void newState(short stateNew, short statePrevious);
	}

	/**
	 * Creates an object with the unique device ID \c uid. and adds it to
	 * the IP Connection \c ipcon.
	 */
	public BrickStepper(String uid, IPConnection ipcon) {
		super(uid, ipcon);

		apiVersion[0] = 2;
		apiVersion[1] = 0;
		apiVersion[2] = 0;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_MAX_VELOCITY)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_MAX_VELOCITY)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_CURRENT_VELOCITY)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_SPEED_RAMPING)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_SPEED_RAMPING)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_FULL_BRAKE)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_CURRENT_POSITION)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_CURRENT_POSITION)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_TARGET_POSITION)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_TARGET_POSITION)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_STEPS)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_STEPS)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_REMAINING_STEPS)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_STEP_MODE)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_STEP_MODE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_DRIVE_FORWARD)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_DRIVE_BACKWARD)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_STOP)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_STACK_INPUT_VOLTAGE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_EXTERNAL_INPUT_VOLTAGE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_CURRENT_CONSUMPTION)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_MOTOR_CURRENT)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_MOTOR_CURRENT)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_ENABLE)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_DISABLE)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_IS_ENABLED)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_DECAY)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_DECAY)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_MINIMUM_VOLTAGE)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_MINIMUM_VOLTAGE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_SYNC_RECT)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_IS_SYNC_RECT)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_TIME_BASE)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_TIME_BASE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_ALL_DATA)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_SET_ALL_DATA_PERIOD)] = RESPONSE_EXPECTED_FLAG_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_ALL_DATA_PERIOD)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_PROTOCOL1_BRICKLET_NAME)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_CHIP_TEMPERATURE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_RESET)] = RESPONSE_EXPECTED_FLAG_FALSE;
		responseExpected[IPConnection.unsignedByte(FUNCTION_GET_IDENTITY)] = RESPONSE_EXPECTED_FLAG_ALWAYS_TRUE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_UNDER_VOLTAGE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_POSITION_REACHED)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_ALL_DATA)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;
		responseExpected[IPConnection.unsignedByte(CALLBACK_NEW_STATE)] = RESPONSE_EXPECTED_FLAG_ALWAYS_FALSE;

		callbacks[CALLBACK_UNDER_VOLTAGE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int voltage = IPConnection.unsignedShort(bb.getShort());

				for(UnderVoltageListener listener: listenerUnderVoltage) {
					listener.underVoltage(voltage);
				}
			}
		};

		callbacks[CALLBACK_POSITION_REACHED] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int position = (bb.getInt());

				for(PositionReachedListener listener: listenerPositionReached) {
					listener.positionReached(position);
				}
			}
		};

		callbacks[CALLBACK_ALL_DATA] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				int currentVelocity = IPConnection.unsignedShort(bb.getShort());
				int currentPosition = (bb.getInt());
				int remainingSteps = (bb.getInt());
				int stackVoltage = IPConnection.unsignedShort(bb.getShort());
				int externalVoltage = IPConnection.unsignedShort(bb.getShort());
				int currentConsumption = IPConnection.unsignedShort(bb.getShort());

				for(AllDataListener listener: listenerAllData) {
					listener.allData(currentVelocity, currentPosition, remainingSteps, stackVoltage, externalVoltage, currentConsumption);
				}
			}
		};

		callbacks[CALLBACK_NEW_STATE] = new CallbackListener() {
			public void callback(byte[] data) {
				ByteBuffer bb = ByteBuffer.wrap(data, 8, data.length - 8);
				bb.order(ByteOrder.LITTLE_ENDIAN);

				short stateNew = IPConnection.unsignedByte(bb.get());
				short statePrevious = IPConnection.unsignedByte(bb.get());

				for(NewStateListener listener: listenerNewState) {
					listener.newState(stateNew, statePrevious);
				}
			}
		};
	}

	/**
	 * Sets the maximum velocity of the stepper motor in steps per second.
	 * This function does *not* start the motor, it merely sets the maximum
	 * velocity the stepper motor is accelerated to. To get the motor running use
	 * either {@link com.tinkerforge.BrickStepper.setTargetPosition}, {@link com.tinkerforge.BrickStepper.setSteps}, {@link com.tinkerforge.BrickStepper.driveForward} or
	 * {@link com.tinkerforge.BrickStepper.driveBackward}.
	 */
	public void setMaxVelocity(int velocity) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_MAX_VELOCITY);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)10, FUNCTION_SET_MAX_VELOCITY, options, (byte)(0));
		bb.putShort((short)velocity);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_MAX_VELOCITY);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the velocity as set by {@link com.tinkerforge.BrickStepper.setMaxVelocity}.
	 */
	public int getMaxVelocity() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_MAX_VELOCITY);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_MAX_VELOCITY, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MAX_VELOCITY);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int velocity = IPConnection.unsignedShort(bb.getShort());

		return velocity;
	}

	/**
	 * Returns the *current* velocity of the stepper motor in steps per second.
	 */
	public int getCurrentVelocity() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_CURRENT_VELOCITY);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_CURRENT_VELOCITY, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT_VELOCITY);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int velocity = IPConnection.unsignedShort(bb.getShort());

		return velocity;
	}

	/**
	 * Sets the acceleration and deacceleration of the stepper motor. The values
	 * are given in *steps/s²*. An acceleration of 1000 means, that
	 * every second the velocity is increased by 1000 *steps/s*.
	 * 
	 * For example: If the current velocity is 0 and you want to accelerate to a
	 * velocity of 8000 *steps/s* in 10 seconds, you should set an acceleration
	 * of 800 *steps/s²*.
	 * 
	 * An acceleration/deacceleration of 0 means instantaneous
	 * acceleration/deacceleration (not recommended)
	 * 
	 * The default value is 1000 for both
	 */
	public void setSpeedRamping(int acceleration, int deacceleration) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_SPEED_RAMPING);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)12, FUNCTION_SET_SPEED_RAMPING, options, (byte)(0));
		bb.putShort((short)acceleration);
		bb.putShort((short)deacceleration);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_SPEED_RAMPING);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the acceleration and deacceleration as set by 
	 * {@link com.tinkerforge.BrickStepper.setSpeedRamping}.
	 */
	public SpeedRamping getSpeedRamping() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_SPEED_RAMPING);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_SPEED_RAMPING, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_SPEED_RAMPING);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		SpeedRamping obj = new SpeedRamping();
		obj.acceleration = IPConnection.unsignedShort(bb.getShort());
		obj.deacceleration = IPConnection.unsignedShort(bb.getShort());

		return obj;
	}

	/**
	 * Executes an active full brake. 
	 *  
	 * \warning
	 *  This function is for emergency purposes,
	 *  where an immediate brake is necessary. Depending on the current velocity and
	 *  the strength of the motor, a full brake can be quite violent.
	 * 
	 * Call {@link com.tinkerforge.BrickStepper.stop} if you just want to stop the motor.
	 */
	public void fullBrake() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_FULL_BRAKE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_FULL_BRAKE, options, (byte)(0));

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_FULL_BRAKE);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Sets the current steps of the internal step counter. This can be used to
	 * set the current position to 0 when some kind of starting position
	 * is reached (e.g. when a CNC machine reaches a corner).
	 */
	public void setCurrentPosition(int position) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_CURRENT_POSITION);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)12, FUNCTION_SET_CURRENT_POSITION, options, (byte)(0));
		bb.putInt((int)position);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_CURRENT_POSITION);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the current position of the stepper motor in steps. On startup
	 * the position is 0. The steps are counted with all possible driving
	 * functions ({@link com.tinkerforge.BrickStepper.setTargetPosition}, {@link com.tinkerforge.BrickStepper.setSteps}, {@link com.tinkerforge.BrickStepper.driveForward} or
	 * {@link com.tinkerforge.BrickStepper.driveBackward}). It also is possible to reset the steps to 0 or
	 * set them to any other desired value with {@link com.tinkerforge.BrickStepper.setCurrentPosition}.
	 */
	public int getCurrentPosition() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_CURRENT_POSITION);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_CURRENT_POSITION, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT_POSITION);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int position = (bb.getInt());

		return position;
	}

	/**
	 * Sets the target position of the stepper motor in steps. For example,
	 * if the current position of the motor is 500 and {@link com.tinkerforge.BrickStepper.setTargetPosition} is
	 * called with 1000, the stepper motor will drive 500 steps forward. It will
	 * use the velocity, acceleration and deacceleration as set by
	 * {@link com.tinkerforge.BrickStepper.setMaxVelocity} and {@link com.tinkerforge.BrickStepper.setSpeedRamping}.
	 * 
	 * A call of {@link com.tinkerforge.BrickStepper.setTargetPosition} with the parameter *x* is equivalent to
	 * a call of {@link com.tinkerforge.BrickStepper.setSteps} with the parameter 
	 * (*x* - {@link com.tinkerforge.BrickStepper.getCurrentPosition}).
	 */
	public void setTargetPosition(int position) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_TARGET_POSITION);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)12, FUNCTION_SET_TARGET_POSITION, options, (byte)(0));
		bb.putInt((int)position);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_TARGET_POSITION);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the last target position as set by {@link com.tinkerforge.BrickStepper.setTargetPosition}.
	 */
	public int getTargetPosition() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_TARGET_POSITION);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_TARGET_POSITION, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_TARGET_POSITION);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int position = (bb.getInt());

		return position;
	}

	/**
	 * Sets the number of steps the stepper motor should run. Positive values
	 * will drive the motor forward and negative values backward. 
	 * The velocity, acceleration and deacceleration as set by
	 * {@link com.tinkerforge.BrickStepper.setMaxVelocity} and {@link com.tinkerforge.BrickStepper.setSpeedRamping} will be used.
	 */
	public void setSteps(int steps) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_STEPS);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)12, FUNCTION_SET_STEPS, options, (byte)(0));
		bb.putInt((int)steps);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_STEPS);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the last steps as set by {@link com.tinkerforge.BrickStepper.setSteps}.
	 */
	public int getSteps() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_STEPS);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_STEPS, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_STEPS);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int steps = (bb.getInt());

		return steps;
	}

	/**
	 * Returns the remaining steps of the last call of {@link com.tinkerforge.BrickStepper.setSteps}.
	 * For example, if {@link com.tinkerforge.BrickStepper.setSteps} is called with 2000 and 
	 * {@link com.tinkerforge.BrickStepper.getRemainingSteps} is called after the motor has run for 500 steps,
	 * it will return 1500.
	 */
	public int getRemainingSteps() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_REMAINING_STEPS);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_REMAINING_STEPS, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_REMAINING_STEPS);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int steps = (bb.getInt());

		return steps;
	}

	/**
	 * Sets the step mode of the stepper motor. Possible values are:
	 * 
	 * * Full Step = 1
	 * * Half Step = 2
	 * * Quarter Step = 4
	 * * Eighth Step = 8
	 * 
	 * A higher value will increase the resolution and
	 * decrease the torque of the stepper motor.
	 * 
	 * The default value is 8 (Eighth Step).
	 */
	public void setStepMode(short mode) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_STEP_MODE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)9, FUNCTION_SET_STEP_MODE, options, (byte)(0));
		bb.put((byte)mode);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_STEP_MODE);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the step mode as set by {@link com.tinkerforge.BrickStepper.setStepMode}.
	 */
	public short getStepMode() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_STEP_MODE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_STEP_MODE, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_STEP_MODE);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short mode = IPConnection.unsignedByte(bb.get());

		return mode;
	}

	/**
	 * Drives the stepper motor forward until {@link com.tinkerforge.BrickStepper.driveBackward} or
	 * {@link com.tinkerforge.BrickStepper.stop} is called. The velocity, acceleration and deacceleration as 
	 * set by {@link com.tinkerforge.BrickStepper.setMaxVelocity} and {@link com.tinkerforge.BrickStepper.setSpeedRamping} will be used.
	 */
	public void driveForward() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_DRIVE_FORWARD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_DRIVE_FORWARD, options, (byte)(0));

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_DRIVE_FORWARD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Drives the stepper motor backward until {@link com.tinkerforge.BrickStepper.driveForward} or
	 * {@link com.tinkerforge.BrickStepper.stop} is triggered. The velocity, acceleration and deacceleration as
	 * set by {@link com.tinkerforge.BrickStepper.setMaxVelocity} and {@link com.tinkerforge.BrickStepper.setSpeedRamping} will be used.
	 */
	public void driveBackward() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_DRIVE_BACKWARD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_DRIVE_BACKWARD, options, (byte)(0));

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_DRIVE_BACKWARD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Stops the stepper motor with the deacceleration as set by 
	 * {@link com.tinkerforge.BrickStepper.setSpeedRamping}.
	 */
	public void stop() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_STOP);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_STOP, options, (byte)(0));

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_STOP);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the stack input voltage in mV. The stack input voltage is the
	 * voltage that is supplied via the stack, i.e. it is given by a 
	 * Step-Down or Step-Up Power Supply.
	 */
	public int getStackInputVoltage() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_STACK_INPUT_VOLTAGE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_STACK_INPUT_VOLTAGE, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_STACK_INPUT_VOLTAGE);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int voltage = IPConnection.unsignedShort(bb.getShort());

		return voltage;
	}

	/**
	 * Returns the external input voltage in mV. The external input voltage is
	 * given via the black power input connector on the Stepper Brick. 
	 *  
	 * If there is an external input voltage and a stack input voltage, the motor
	 * will be driven by the external input voltage. If there is only a stack 
	 * voltage present, the motor will be driven by this voltage.
	 * 
	 * \warning
	 *  This means, if you have a high stack voltage and a low external voltage,
	 *  the motor will be driven with the low external voltage. If you then remove
	 *  the external connection, it will immediately be driven by the high
	 *  stack voltage
	 */
	public int getExternalInputVoltage() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_EXTERNAL_INPUT_VOLTAGE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_EXTERNAL_INPUT_VOLTAGE, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_EXTERNAL_INPUT_VOLTAGE);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int voltage = IPConnection.unsignedShort(bb.getShort());

		return voltage;
	}

	/**
	 * Returns the current consumption of the motor in mA.
	 */
	public int getCurrentConsumption() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_CURRENT_CONSUMPTION);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_CURRENT_CONSUMPTION, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CURRENT_CONSUMPTION);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int current = IPConnection.unsignedShort(bb.getShort());

		return current;
	}

	/**
	 * Sets the current in mA with which the motor will be driven.
	 * The minimum value is 100mA, the maximum value 2291mA and the 
	 * default value is 800mA.
	 * 
	 * \warning
	 *  Do not set this value above the specifications of your stepper motor.
	 *  Otherwise it may damage your motor.
	 */
	public void setMotorCurrent(int current) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_MOTOR_CURRENT);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)10, FUNCTION_SET_MOTOR_CURRENT, options, (byte)(0));
		bb.putShort((short)current);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_MOTOR_CURRENT);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the current as set by {@link com.tinkerforge.BrickStepper.setMotorCurrent}.
	 */
	public int getMotorCurrent() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_MOTOR_CURRENT);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_MOTOR_CURRENT, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MOTOR_CURRENT);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int current = IPConnection.unsignedShort(bb.getShort());

		return current;
	}

	/**
	 * Enables the driver chip. The driver parameters can be configured (maximum velocity,
	 * acceleration, etc) before it is enabled.
	 */
	public void enable() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_ENABLE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_ENABLE, options, (byte)(0));

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_ENABLE);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Disables the driver chip. The configurations are kept (maximum velocity,
	 * acceleration, etc) but the motor is not driven until it is enabled again.
	 */
	public void disable() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_DISABLE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_DISABLE, options, (byte)(0));

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_DISABLE);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns *true* if the driver chip is enabled, *false* otherwise.
	 */
	public boolean isEnabled() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_IS_ENABLED);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_IS_ENABLED, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_IS_ENABLED);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		boolean enabled = (bb.get()) != 0;

		return enabled;
	}

	/**
	 * Sets the decay mode of the stepper motor. The possible value range is
	 * between 0 and 65535. A value of 0 sets the fast decay mode, a value of
	 * 65535 sets the slow decay mode and a value in between sets the mixed
	 * decay mode.
	 * 
	 * Changing the decay mode is only possible if synchronous rectification
	 * is enabled (see {@link com.tinkerforge.BrickStepper.setSyncRect}).
	 * 
	 * For a good explanation of the different decay modes see 
	 * `this <http://ebldc.com/?p=86/>`__ blog post by Avayan.
	 * 
	 * A good decay mode is unfortunately different for every motor. The best
	 * way to work out a good decay mode for your stepper motor, if you can't
	 * measure the current with an oscilloscope, is to listen to the sound of
	 * the motor. If the value is too low, you often hear a high pitched 
	 * sound and if it is too high you can often hear a humming sound.
	 * 
	 * Generally, fast decay mode (small value) will be noisier but also
	 * allow higher motor speeds.
	 * 
	 * The default value is 10000.
	 * 
	 * \note
	 *  There is unfortunately no formula to calculate a perfect decay
	 *  mode for a given stepper motor. If you have problems with loud noises
	 *  or the maximum motor speed is too slow, you should try to tinker with
	 *  the decay value
	 */
	public void setDecay(int decay) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_DECAY);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)10, FUNCTION_SET_DECAY, options, (byte)(0));
		bb.putShort((short)decay);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_DECAY);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the decay mode as set by {@link com.tinkerforge.BrickStepper.setDecay}.
	 */
	public int getDecay() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_DECAY);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_DECAY, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_DECAY);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int decay = IPConnection.unsignedShort(bb.getShort());

		return decay;
	}

	/**
	 * Sets the minimum voltage in mV, below which the {@link com.tinkerforge.BrickStepper.UnderVoltageListener} listener
	 * is triggered. The minimum possible value that works with the Stepper Brick is 8V.
	 * You can use this function to detect the discharge of a battery that is used
	 * to drive the stepper motor. If you have a fixed power supply, you likely do 
	 * not need this functionality.
	 * 
	 * The default value is 8V.
	 */
	public void setMinimumVoltage(int voltage) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_MINIMUM_VOLTAGE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)10, FUNCTION_SET_MINIMUM_VOLTAGE, options, (byte)(0));
		bb.putShort((short)voltage);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_MINIMUM_VOLTAGE);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the minimum voltage as set by {@link com.tinkerforge.BrickStepper.setMinimumVoltage}.
	 */
	public int getMinimumVoltage() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_MINIMUM_VOLTAGE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_MINIMUM_VOLTAGE, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_MINIMUM_VOLTAGE);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		int voltage = IPConnection.unsignedShort(bb.getShort());

		return voltage;
	}

	/**
	 * Turns synchronous rectification on or off (*true* or *false*).
	 * 
	 * With synchronous rectification on, the decay can be changed
	 * (see {@link com.tinkerforge.BrickStepper.setDecay}). Without synchronous rectification fast
	 * decay is used.
	 * 
	 * For an explanation of synchronous rectification see 
	 * `here <http://en.wikipedia.org/wiki/Active_rectification>`__.
	 * 
	 * \warning
	 *  If you want to use high speeds (> 10000 steps/s) for a large 
	 *  stepper motor with a large inductivity we strongly
	 *  suggest that you disable synchronous rectification. Otherwise the
	 *  Brick may not be able to cope with the load and overheat.
	 * 
	 * The default value is *false*.
	 * 
	 * .. versionadded:: 1.1.4~(Firmware)
	 */
	public void setSyncRect(boolean syncRect) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_SYNC_RECT);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)9, FUNCTION_SET_SYNC_RECT, options, (byte)(0));
		bb.put((byte)(syncRect ? 1 : 0));

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_SYNC_RECT);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns *true* if synchronous rectification is enabled, *false* otherwise.
	 * 
	 * .. versionadded:: 1.1.4~(Firmware)
	 */
	public boolean isSyncRect() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_IS_SYNC_RECT);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_IS_SYNC_RECT, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_IS_SYNC_RECT);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		boolean syncRect = (bb.get()) != 0;

		return syncRect;
	}

	/**
	 * Sets the time base of the velocity and the acceleration of the stepper brick
	 * (in seconds).
	 * 
	 * For example, if you want to make one step every 1.5 seconds, you can set 
	 * the time base to 15 and the velocity to 10. Now the velocity is 
	 * 10steps/15s = 1steps/1.5s.
	 * 
	 * The default value is 1.
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public void setTimeBase(long timeBase) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_TIME_BASE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)12, FUNCTION_SET_TIME_BASE, options, (byte)(0));
		bb.putInt((int)timeBase);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_TIME_BASE);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the time base as set by {@link com.tinkerforge.BrickStepper.setTimeBase}.
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public long getTimeBase() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_TIME_BASE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_TIME_BASE, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_TIME_BASE);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long timeBase = IPConnection.unsignedInt(bb.getInt());

		return timeBase;
	}

	/**
	 * Returns the following parameters: The current velocity,
	 * the current position, the remaining steps, the stack voltage, the external
	 * voltage and the current consumption of the stepper motor.
	 * 
	 * There is also a listener for this function, see {@link com.tinkerforge.BrickStepper.AllDataListener}.
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public AllData getAllData() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_ALL_DATA);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_ALL_DATA, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ALL_DATA);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		AllData obj = new AllData();
		obj.currentVelocity = IPConnection.unsignedShort(bb.getShort());
		obj.currentPosition = (bb.getInt());
		obj.remainingSteps = (bb.getInt());
		obj.stackVoltage = IPConnection.unsignedShort(bb.getShort());
		obj.externalVoltage = IPConnection.unsignedShort(bb.getShort());
		obj.currentConsumption = IPConnection.unsignedShort(bb.getShort());

		return obj;
	}

	/**
	 * Sets the period in ms with which the {@link com.tinkerforge.BrickStepper.AllDataListener} listener is triggered
	 * periodically. A value of 0 turns the listener off.
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public void setAllDataPeriod(long period) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_SET_ALL_DATA_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)12, FUNCTION_SET_ALL_DATA_PERIOD, options, (byte)(0));
		bb.putInt((int)period);

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_SET_ALL_DATA_PERIOD);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the period as set by {@link com.tinkerforge.BrickStepper.setAllDataPeriod}.
	 * 
	 * .. versionadded:: 1.1.6~(Firmware)
	 */
	public long getAllDataPeriod() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_ALL_DATA_PERIOD);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_ALL_DATA_PERIOD, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_ALL_DATA_PERIOD);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		long period = IPConnection.unsignedInt(bb.getInt());

		return period;
	}

	/**
	 * Returns the firmware and protocol version and the name of the Bricklet for a given port.
	 * 
	 * This functions sole purpose is to allow automatic flashing of v1.x.y Bricklet plugins.
	 * 
	 * .. versionadded:: 2.0.0~(Firmware)
	 */
	public Protocol1BrickletName getProtocol1BrickletName(char port) throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_PROTOCOL1_BRICKLET_NAME);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)9, FUNCTION_GET_PROTOCOL1_BRICKLET_NAME, options, (byte)(0));
		bb.put((byte)port);

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_PROTOCOL1_BRICKLET_NAME);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		Protocol1BrickletName obj = new Protocol1BrickletName();
		obj.protocolVersion = IPConnection.unsignedByte(bb.get());
		for(int i = 0; i < 3; i++) {
			obj.firmwareVersion[i] = IPConnection.unsignedByte(bb.get());
		}

		obj.name = IPConnection.string(bb, 40);

		return obj;
	}

	/**
	 * Returns the temperature in °C/10 as measured inside the microcontroller. The
	 * value returned is not the ambient temperature!
	 * 
	 * The temperature is only proportional to the real temperature and it has an
	 * accuracy of +-15%. Practically it is only useful as an indicator for
	 * temperature changes.
	 * 
	 * .. versionadded:: 1.1.4~(Firmware)
	 */
	public short getChipTemperature() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_GET_CHIP_TEMPERATURE);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_GET_CHIP_TEMPERATURE, options, (byte)(0));

		byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_GET_CHIP_TEMPERATURE);

		bb = ByteBuffer.wrap(response, 8, response.length - 8);
		bb.order(ByteOrder.LITTLE_ENDIAN);

		short temperature = (bb.getShort());

		return temperature;
	}

	/**
	 * Calling this function will reset the Brick. Calling this function
	 * on a Brick inside of a stack will reset the whole stack.
	 * 
	 * After a reset you have to create new device objects,
	 * calling functions on the existing ones will result in
	 * undefined behavior!
	 * 
	 * .. versionadded:: 1.1.4~(Firmware)
	 */
	public void reset() throws TimeoutException, NotConnectedException {
		byte options = 0;
		boolean isResponseExpected = getResponseExpected(FUNCTION_RESET);
		if(isResponseExpected) {
			options = 8;
		}
		ByteBuffer bb = ipcon.createRequestBuffer(uid, (byte)8, FUNCTION_RESET, options, (byte)(0));

		if(isResponseExpected) {
			byte[] response = sendRequestExpectResponse(bb.array(), FUNCTION_RESET);

			bb = ByteBuffer.wrap(response, 8, response.length - 8);
			bb.order(ByteOrder.LITTLE_ENDIAN);
		} else {
			sendRequestNoResponse(bb.array());
		}
	}

	/**
	 * Returns the UID, the UID where the Brick is connected to, 
	 * the position, the hardware and firmware version as well as the
	 * device identifier.
	 * 
	 * The position can be '0'-'8' (stack position).
	 * 
	 * The device identifiers can be found :ref:`here <device_identifier>`.
	 * 
	 * .. versionadded:: 2.0.0~(Firmware)
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
	 * Adds a UnderVoltage listener.
	 */
	public void addUnderVoltageListener(UnderVoltageListener listener) {
		listenerUnderVoltage.add(listener);
	}

	/**
	 * Removes a UnderVoltage listener.
	 */
	public void removeUnderVoltageListener(UnderVoltageListener listener) {
		listenerUnderVoltage.remove(listener);
	}

	/**
	 * Adds a PositionReached listener.
	 */
	public void addPositionReachedListener(PositionReachedListener listener) {
		listenerPositionReached.add(listener);
	}

	/**
	 * Removes a PositionReached listener.
	 */
	public void removePositionReachedListener(PositionReachedListener listener) {
		listenerPositionReached.remove(listener);
	}

	/**
	 * Adds a AllData listener.
	 */
	public void addAllDataListener(AllDataListener listener) {
		listenerAllData.add(listener);
	}

	/**
	 * Removes a AllData listener.
	 */
	public void removeAllDataListener(AllDataListener listener) {
		listenerAllData.remove(listener);
	}

	/**
	 * Adds a NewState listener.
	 */
	public void addNewStateListener(NewStateListener listener) {
		listenerNewState.add(listener);
	}

	/**
	 * Removes a NewState listener.
	 */
	public void removeNewStateListener(NewStateListener listener) {
		listenerNewState.remove(listener);
	}
}