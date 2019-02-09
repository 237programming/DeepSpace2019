/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team237.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class PickUpDiskRoutine extends Command 
{
  private State currentState;
  private double time;
  private enum State
  {
    start,
    slapdown,
    extend,
    retract,
    slapUp,
    finished
  };
  public PickUpDiskRoutine() 
  { 
    requires(Robot.diskHandler);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    time = Timer.getFGPATimeStamp;
    currentState = State.start;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute()
  {
    switch(currentState)
    {
      case start:
        currentState = State.slapdown;
        break;

      case slapDown:
      Robot.diskHandler.diskDown();
      if(Timer.getFGPATimeStamp + time > 1)
      {
        time = Timer.getFGPATimeStamp;
        currentState = State.extend;
      }
      break;

      case extend:
      Robot.diskHandler.diskEntend();
      if(Timer.getFGPATimeStamp + time > 1)
      {
        time = Timer.getFGPATimeStamp;
        currentState = State.retract;
      }
      break;

      case retract:
      Robot.diskHandler.diskRetract();
      if(Timer.getFGPATimeStamp + time > 1)
      {
        time = Timer.getFGPATimeStamp;
        currentState = State.slapUp;
      }
      break;

      case slapUp: 
      Robot.diskHandler.diskUp();
      if(Timer.getFGPATimeStamp + time > 1)
      {
        time = Timer.getFGPATimeStamp;
        currentState = State.finished;
      }
      break;

      default:
      break;
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    if(currentState == State.finished)
    {
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {

  }
}
