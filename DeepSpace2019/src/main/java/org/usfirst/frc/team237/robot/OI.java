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
	
	public static Button ballIntake = new JoystickButton(controller, 2);
	public static Button ballOuttake = new JoystickButton(controller, 3);
	public static Button diskManipulatorDown = new JoystickButton(controller, 5);
	public static Button diskManipulatorUp = new JoystickButton(controller, 6);
	public static Button elevator = new JoystickButton(controller, 1);
	
	public OI()
	{
		
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
