/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team237.robot.commands;

import javax.lang.model.util.ElementScanner6;

import org.usfirst.frc.team237.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DiskFirstLevel extends Command
{
  private boolean m_done = false;
  private double time;
  private double dTime;

  public DiskFirstLevel() 
  {
    requires(Robot.diskHandler);
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
    if (time < 0)
    {
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
    Robot.diskHandler.diskUnject();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    Robot.diskHandler.diskUnject();
  }
}
