/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team237.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	//drive stuff
		public static int driveTalon1 = 1;
		public static int driveTalon2 = 2;
		public static int driveTalon3 = 3;
		public static int driveTalon4 = 4;
		public static double deadband = 0.3;
		
	//ball manipulator stuff
		public static int ballMotor = 0;
		public static int elevatorMotor1 = 1;
		public static int elevatorMotor2 = 2;
		
	//disk manipulator stuff
		public static int diskSolenoid = 0;
		
	//Xbox controller stuff
		public static int A = 1;
		public static int B = 2;
		public static int X = 3;
		public static int Y = 4;
		public static int leftBumper = 5;
		public static int rightBumper = 6;
		public static int leftTrigger = 2;
		public static int rightTriggger = 3;
		public static int xaxisJoystick = 0;
		public static int yaxisJoystick = 1;
	//Xbox Controller Mapping: Joystick L- 1-y-axis, 0-x-axis; Joystick R- 5-y-axis, 4-x-axis; A button-1, B button-2, X button-3, Y button-4
	//Bumper L-5, Bumper R-6, Trigger L-2, Trigger R-3, Back-7, Start-8, Joystick L Button-9, Joystick R Button-10
}
