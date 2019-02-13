/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team237.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team237.robot.commands.PickUpDiskRoutine;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
//Xbox Controller Mapping: Joystick L- 1-y-axis, 0-x-axis; Joystick R- 5-y-axis, 4-x-axis; A button-1, B button-2, X button-3, Y button-4
//Bumper L-5, Bumper R-6, Trigger L-2, Trigger R-3, Back-7, Start-8, Joystick L Button-9, Joystick R Button-10
	
	public static Joystick driveJoystick = new Joystick(1);
	public static Joystick controller = new Joystick(0);
	public static Joystick panel = new Joystick(2);
	
	public static Joystick ballIntake = new Joystick(0);
	//intake left trigger
	//outtake right trigger
	public static Joystick ballOuttake = new Joystick(0);
	public static Joystick elevator = new Joystick(0);

	public static Button switchDrives = new JoystickButton(driveJoystick, 10);
	public static Button extend = new JoystickButton(controller, RobotMap.leftBumper);
	public static Button diskManipulatorDown = new JoystickButton(controller, RobotMap.rightBumper);
	public static Button eject = new JoystickButton(driveJoystick, 1);
	//public static Button elevatorUp = new JoystickButton(driveJoystick, 6);
	//public static Button elevatorDown = new JoystickButton(driveJoystick, 7);
	public static Button pickUpDisk = new JoystickButton(controller, RobotMap.A);
	public static Button ballManipulatorDown = new JoystickButton(controller, RobotMap.Y);

	
	//retract not being used and manipultor up not being used
	//public static Button retract = new JoystickButton(controller, RobotMap.rightBumper);
	//public static Button diskManipulatorUp = new JoystickButton(controller, RobotMap.Y);
	
	public OI()
	{
		//pickUpDisk.whenPressed(new PickUpDiskRoutine());
	}
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
