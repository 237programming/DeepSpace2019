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
import edu.wpi.first.wpilibj.DigitalInput;

import javax.lang.model.util.ElementScanner6;

import org.usfirst.frc.team237.robot.commands.AutoLeftSide;
import org.usfirst.frc.team237.robot.commands.AutoRightSide;
import org.usfirst.frc.team237.robot.commands.AutoCenterSide;
import org.usfirst.frc.team237.robot.commands.DiskFirstLevel;
import org.usfirst.frc.team237.robot.commands.DiskSecondLevel;
import org.usfirst.frc.team237.robot.commands.DiskThirdLevel;
import org.usfirst.frc.team237.robot.commands.OuttakeFirstLevel;
import org.usfirst.frc.team237.robot.commands.OuttakeSecondLevel;
import org.usfirst.frc.team237.robot.commands.OuttakeThirdLevel;
import org.usfirst.frc.team237.robot.commands.PickUpDiskRoutine;
import org.usfirst.frc.team237.robot.commands.SwitchDrive;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot 
{
	public static OI m_oi;
	public static DriveSubsystem driveTrain = new DriveSubsystem();
	public static DiskManipulatorSubsystem diskHandler = new DiskManipulatorSubsystem();
	public static ElevatorSubsystem elevator = new ElevatorSubsystem();
	public static BallManipulatorSubsystem ballHandler = new BallManipulatorSubsystem();
	public static PickUpDiskRoutine m_pickUpDisk = new PickUpDiskRoutine();
	public static SwitchDrive m_reverseDrive = new SwitchDrive();
	public static OuttakeFirstLevel m_outtakeFirstLevelCommand = new OuttakeFirstLevel(); 
	public static DiskFirstLevel m_diskFirstLevelCommand = new DiskFirstLevel();
	public static OuttakeSecondLevel m_outtakeSecLevelCommand = new OuttakeSecondLevel(); 
	public static DiskSecondLevel m_diskSecondLevelCommand = new DiskSecondLevel();
	public static OuttakeThirdLevel m_outtakeThirdLevel = new OuttakeThirdLevel();
	public static DiskThirdLevel m_diskThirdLevel = new DiskThirdLevel();
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
    private boolean m_RevButtonState;
	private boolean m_PrevRevButtonState;
	private DigitalInput autoSwitch1 = new DigitalInput(0);
	private DigitalInput autoSwitch2 = new DigitalInput(1);

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
		
		driveTrain.zeroYaw();
		driveTrain.zeroEnc();
		elevator.zeroEnc();
        m_PrevRevButtonState = false;
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() 
	{

	}

	@Override
	public void disabledPeriodic() 
	{
		Scheduler.getInstance().run();
		SmartDashboard.putData("sw 1", autoSwitch1);
		SmartDashboard.putData("sw 2", autoSwitch2);
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
	public void autonomousInit() 
	{
		if(!autoSwitch1.get())
		{
			m_autonomousCommand = new AutoLeftSide();
		}
		else if(!autoSwitch2.get())
		{
			m_autonomousCommand = new AutoRightSide();
		}
		else     
		{
			m_autonomousCommand = new AutoCenterSide();
		}

		driveTrain.reverseDriveSetter(false);
		//m_autonomousCommand = m_chooser.getSelected();
		//m_autonomousCommand = new AutoLeftSide();
		//m_autonomousCommand = new AutoRightSide();
		//m_autonomousCommand = new AutoCenterSide();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) 
		{
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() 
	{
		Scheduler.getInstance().run();
		if(OI.driveJoystick.getX() > .5 || OI.driveJoystick.getX() < -.5 )
		{
			m_autonomousCommand.cancel();
		}
		if(!m_autonomousCommand.isRunning())
		{
			driveTrain.setDrives(-OI.driveJoystick.getY(),-OI.driveJoystick.getX());
			if (!m_diskThirdLevel.isRunning() && !m_diskSecondLevelCommand.isRunning() && !m_diskFirstLevelCommand.isRunning())
			{
				//INTAKE
            	if (OI.Intake.get())
                	diskHandler.diskExtend();
           		else
					diskHandler.diskRetract();
				// EJECT
				if (OI.Eject.get())
					diskHandler.diskEject();
				else
					diskHandler.diskUnject();
				// SLAP
				if (OI.Slap.get())
					diskHandler.diskDown();
				else
					diskHandler.diskUp();

				ballHandler.offIntake();
			}
			//Automated stuff
			if(OI.AutoHigh.get() && !m_diskThirdLevel.isRunning())
                m_diskThirdLevel.start();
            else if(OI.AutoMedium.get() && !m_diskSecondLevelCommand.isRunning())
				m_diskSecondLevelCommand.start();
			else if(OI.AutoLow.get() && !m_diskFirstLevelCommand.isRunning())
				m_diskFirstLevelCommand.start();	
		}
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
		if (m_autonomousCommand != null) 
		{
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() 
    {        
        //First, check if a command is running. If so, don't check any inputs
        if (m_diskThirdLevel.isRunning() || m_diskSecondLevelCommand.isRunning() || m_diskFirstLevelCommand.isRunning() || m_outtakeThirdLevel.isRunning() || m_outtakeSecLevelCommand.isRunning() || m_outtakeFirstLevelCommand.isRunning())
        {
            if (elevator.leftElevator.getSelectedSensorPosition(0) < RobotMap.elevatorMaxHeight) 
				elevator.elevatorOff();					
			if(OI.elevatorDown.get() || OI.elevatorUp.get() || elevator.leftElevator.getSelectedSensorPosition(0) < RobotMap.elevatorMaxHeight)
			{
				m_diskThirdLevel.cancel();
				m_diskSecondLevelCommand.cancel();
				m_diskFirstLevelCommand.cancel();
				m_outtakeThirdLevel.cancel();
				m_outtakeSecLevelCommand.cancel();
				m_outtakeFirstLevelCommand.cancel();
			}
						
			driveTrain.setDrives(-OI.driveJoystick.getY(),-OI.driveJoystick.getX());
            driveTrain.post();
            elevator.post();
            Scheduler.getInstance().run();
            return;
        }    
    
        //Check for drive reversal        
        m_RevButtonState = OI.switchDrives.get();        
        if(OI.switchDrives.get() && !m_PrevRevButtonState)
		{
			driveTrain.reverseDriveSetter(!driveTrain.reverseDrive());            
		}       
        m_PrevRevButtonState = m_RevButtonState;

        if (driveTrain.reverseDrive())
        {
            //If reverse drive, reset all hatch mechanisms
            diskHandler.diskRetract();
            diskHandler.diskUnject();
            diskHandler.diskUp();
            
            //Now let's look at the DS inputs
            
            //INTAKE/EJECT/OFF
            if (OI.Intake.get())
                ballHandler.ballIntake();
            else if (OI.Eject.get())
                ballHandler.ballOuttake();
            else
                ballHandler.offIntake();
            //SLAP    
            if (OI.Slap.get())
                diskHandler.ballDown();
            else
                diskHandler.ballUp();
            
            //Now the automated stuff...
            if(OI.AutoHigh.get() && !m_outtakeThirdLevel.isRunning())
                m_outtakeThirdLevel.start();
            else if(OI.AutoMedium.get() && !m_outtakeSecLevelCommand.isRunning())
				m_outtakeSecLevelCommand.start(); 
			else if(OI.AutoLow.get() && !m_outtakeFirstLevelCommand.isRunning())
                m_outtakeFirstLevelCommand.start(); 	           
        }
        else
        {            
            //If reverse drive, reset all ball mexhanisms
            ballHandler.offIntake();
            diskHandler.ballUp();       
            
            //Now let's look at the DS inputs
            
            //INTAKE
            if (OI.Intake.get())
                diskHandler.diskExtend();
            else
                diskHandler.diskRetract();
            //EJECT
            if (OI.Eject.get())
                diskHandler.diskEject();
            else
                diskHandler.diskUnject();
            //SLAP
            if (OI.Slap.get())
                diskHandler.diskDown();
            else
                diskHandler.diskUp();    

            //Now the automated stuff... 
            if(OI.AutoHigh.get() && !m_diskThirdLevel.isRunning())
                m_diskThirdLevel.start();
            else if(OI.AutoMedium.get() && !m_diskSecondLevelCommand.isRunning())
				m_diskSecondLevelCommand.start();
			else if(OI.AutoLow.get() && !m_diskFirstLevelCommand.isRunning())
                m_diskFirstLevelCommand.start();	
        }      	
		
        //Elevator controls	
		if(OI.elevatorDown.get())
			elevator.elevatorDown();
		else if(OI.elevatorUp.get())
			elevator.elevatorUp();			
		else
			elevator.elevatorOff();
		
		if (elevator.leftElevator.getSelectedSensorPosition(0) < RobotMap.elevatorMaxHeight) 
		{
			elevator.elevatorOff();
		}	
	
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
