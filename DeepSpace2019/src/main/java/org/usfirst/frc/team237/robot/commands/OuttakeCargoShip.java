/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team237.robot.commands;

import org.usfirst.frc.team237.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class OuttakeCargoShip extends Command 
{

  private boolean m_step1Done;
  private boolean m_step2Done;
  private boolean m_done = false;
  private double time;
  private double dTime;

  public OuttakeCargoShip() 
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
    time = -1;
    dTime = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) > -300000 && !m_step1Done)
    {
      Robot.elevator.elevatorUp();
      time = Timer.getFPGATimestamp();
    }
    else if(/*Robot.elevator.leftElevator.getSelectedSensorPosition(0) < -390000 && time < 0 && */!m_step2Done)
    {
      m_step1Done = true;
 //     Robot.elevator.elevatorUp();
      Robot.elevator.setSpeed(-.4);
      Robot.ballHandler.ballOuttake();
      dTime = Timer.getFPGATimestamp();
      //dTime = time;
      if (dTime > time + 1)
        m_step2Done = true;
    }
    else //else if(dTime < time + 4)
    //{
    //  m_step2Done = true;
    //  dTime = Timer.getFPGATimestamp();
    //  //Robot.elevator.elevatorDown();
    // }
    //else
    {
      Robot.elevator.elevatorOff();
      Robot.ballHandler.offIntake();
      m_done = true;
 //     Robot.ballHandler.offIntake();
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
    Robot.elevator.elevatorOff();
    Robot.ballHandler.offIntake();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    Robot.elevator.elevatorOff();
    Robot.ballHandler.offIntake();
  }
}
