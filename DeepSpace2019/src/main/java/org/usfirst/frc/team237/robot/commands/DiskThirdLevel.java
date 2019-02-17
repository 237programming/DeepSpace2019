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

public class DiskThirdLevel extends Command 
{
  private boolean m_done = false;
  private double time;
  private double dTime;

  public DiskThirdLevel() 
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
    m_done = false;
    time = -1;
    dTime = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) > -810000 && time < 0)
    {
      Robot.elevator.elevatorUp();
    }
    else if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) < -810000 && time < 0) 
    {
      Robot.elevator.elevatorOff();
      Robot.diskHandler.diskEject();
      time = Timer.getFPGATimestamp();
      dTime = time;
    }
    else if (dTime < time + 1)
    {
      dTime = Timer.getFPGATimestamp();
    }
    else
    {
      Robot.diskHandler.diskUnject();
      if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) < -250000)
      {
        Robot.elevator.elevatorDown();
        m_done = true;
      }
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
    Robot.diskHandler.diskUnject();
    Robot.elevator.elevatorOff();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
  Robot.diskHandler.diskUnject();
  Robot.elevator.elevatorOff();
  }
}
