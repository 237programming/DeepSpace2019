/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team237.robot;

import org.usfirst.frc.team237.robot.subsystems.BallManipulatorSubsystem;
import org.usfirst.frc.team237.robot.subsystems.DiskManipulatorSubsystem;
import org.usfirst.frc.team237.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team237.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;

import org.usfirst.frc.team237.robot.commands.AutoRightSide;
import org.usfirst.frc.team237.robot.commands.PickUpDiskRoutine;
import org.usfirst.frc.team237.robot.commands.SwitchDrive;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static OI m_oi;
	public static DriveSubsystem driveTrain = new DriveSubsystem();
	public static DiskManipulatorSubsystem diskHandler = new DiskManipulatorSubsystem();
	public static ElevatorSubsystem elevator = new ElevatorSubsystem();
	public static BallManipulatorSubsystem ballHandler = new BallManipulatorSubsystem();
	public static PickUpDiskRoutine m_pickUpDisk = new PickUpDiskRoutine();
	public static SwitchDrive m_reverseDrive = new SwitchDrive();
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() 
	{
		m_oi = new OI();
		CameraServer.getInstance().startAutomaticCapture();
		//m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		driveTrain.zeroYaw();
		driveTrain.zeroEnc();
		elevator.zeroEnc();
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		driveTrain.post();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();
		m_autonomousCommand = new AutoRightSide();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		driveTrain.post();
		elevator.post();
	}

	@Override
	public void teleopInit() 
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() 
	{
	//Pick Up Disk command button
		if (OI.pickUpDisk.get() && !m_pickUpDisk.isRunning())
		{
			m_pickUpDisk.start();
		}
		if (!m_pickUpDisk.isRunning() && OI.eject.get())
		{
			diskHandler.diskEject();
		}
		else 
		{
			diskHandler.diskUnject();
		}
	//Switch drives command
		if(OI.switchDrives.get() && !m_reverseDrive.isRunning())
		{
			m_reverseDrive.start();
		}
	//Extending and Retracting toggle
		if(OI.extend.get() )
		{
			diskHandler.diskExtend();
		}
		else
		{
			diskHandler.diskRetract();
		}
	//ball intake and outtake triggers
		if(OI.ballIntake.getY() > .8 )
		{
			ballHandler.ballIntake();
		}
		else if(OI.ballOuttake.getY() > .8  && OI.ballIntake.getY() == 0)
		{
			ballHandler.ballOuttake();
		}
		else
		{
			ballHandler.offIntake();
		}
	//ball slap thing
		if(OI.ballManipulatorDown.get())
		{
			diskHandler.ballDown();
		}
		else
		{
			diskHandler.ballUp();
		}
	//elevator on xbox stick
		if(OI.elevator.getY() > .8 )
		{
			elevator.elevatorUp();
		}
		else if(OI.elevator.getY() < -.8 )
		{
			elevator.elevatorDown();
		}
		else
		{
			elevator.elevatorOff();
		}
		
	//Up and Down Manipulator toggle 
		if(OI.diskManipulatorDown.get())
		{
			diskHandler.diskDown();
		}
		else
		{
			diskHandler.diskUp();
		}
	//Ejecting disk
		
		// Scheduler.getInstance().run();
	/*
		// elevator control Logic 
		if (OI.elevatorUp.get())
		{
			elevator.elevatorUp();
		}
		else if (OI.elevatorDown.get())
		{
			elevator.elevatorDown();
		}
		else 
		{
			elevator.elevatorOff();
		}
	*/
		driveTrain.setDrives(-OI.driveJoystick.getY(),-OI.driveJoystick.getX());
		driveTrain.post();
		elevator.post();
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() 
	{

	}
}
