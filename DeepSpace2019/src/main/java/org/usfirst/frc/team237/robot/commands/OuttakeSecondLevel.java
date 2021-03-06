/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team237.robot.commands;

import org.usfirst.frc.team237.robot.Robot;
import org.usfirst.frc.team237.robot.subsystems.BallManipulatorSubsystem;
import org.usfirst.frc.team237.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class OuttakeSecondLevel extends Command 
{
  private boolean m_step1Done;
  private boolean m_step2Done;
  private boolean m_done = false; 
//  private double time;
//  private double dTime;
   
  public OuttakeSecondLevel() 
  {
    requires(Robot.elevator);
    requires(Robot.ballHandler);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    m_step1Done = false;
    m_step2Done = false;
    m_done = false; 
//    time = -1;
//    dTime = 0; 
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) > -425000 && !m_step1Done)
    {
      Robot.elevator.elevatorUp();
    }
    else if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) >  -650000 && !m_step2Done)
    {
      m_step1Done = true;
      Robot.elevator.elevatorUp();
      Robot.ballHandler.ballOuttake();
     // time = Timer.getFPGATimestamp();
     //dTime = time; 
    }
    else if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) < -250000)
    {
      m_step2Done = true;
      //dTime = Timer.getFPGATimestamp(); 
     // Robot.ballHandler.offIntake();
      Robot.elevator.elevatorDown();
    }
    //max -600000
    else 
    {
      Robot.ballHandler.offIntake();
      //Robot.elevator.elevatorOff();
      //m_done = true; 
        Robot.elevator.elevatorOff();
        m_done = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return m_done;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    Robot.ballHandler.offIntake();
    Robot.elevator.elevatorOff();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    Robot.ballHandler.offIntake();
    Robot.elevator.elevatorOff();
  }
}
