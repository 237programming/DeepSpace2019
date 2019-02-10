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
		public static int driveTalonFL = 3;
		public static int driveTalonBL = 2;
		public static int driveTalonFR = 5;
		public static int driveTalonBR = 6;
		public static double deadband = 0.15;
		
	//ball manipulator stuff
		public static int ballMotor = 7;
		public static int elevatorMotor1 = 11;
		public static int elevatorMotor2 = 12;
		
	//disk manipulator stuff
		public static int diskExtentionSol = 0;
		public static int diskEjectSol = 1;
		public static int slapSol = 2; 
		public static int ballSol = 3; 

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

	//Rocket Angles right
		public static int frontRightRocket = 45;
		public static int middleRightRocket = 90;
		public static int backRightRocket = 135;

	//Rocket Angles Left
		public static int frontLeftRocket = -45;
		public static int middleLeftRocket = -90;
		public static int backLeftRocket = -135;

	//PID stuff
		public static double turnP = 0.0;
		public static double turnI = 0.0;
		public static double turnD = 0.0;
		public static double driveP = 0.0;
		public static double driveI = 0.0;
		public static double driveD = 0.0;

}
