/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team237.robot.commands;

import org.usfirst.frc.team237.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class AutoLeftSide extends Command 
{

  private State currentState;
  private double currentAngle;
   

  private enum State 
  {
    start,
    driveOffPlatform,
    turnToRocketAngle,
    finished
  };

  public AutoLeftSide() 
  {
    requires(Robot.driveTrain);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    Robot.driveTrain.zeroEnc();
    Robot.driveTrain.zeroYaw();
    currentState = State.start;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    switch(currentState)
    {
      case start:
        currentState = State.driveOffPlatform;
        break;

      case driveOffPlatform:
        Robot.driveTrain.setDrives(.7, .17);
        if(Robot.driveTrain.getEncPos() > 17000)
        {
          Robot.driveTrain.disableRotateTo();
          Robot.driveTrain.setDrives(0, .4);
          currentState = State.turnToRocketAngle;
        }
        break;

      case turnToRocketAngle:
        currentAngle = Robot.driveTrain.getYaw();
        if (currentAngle < -130 && currentAngle > -135)
        {
          Robot.driveTrain.zeroEnc();
          Robot.driveTrain.setDrives(.5, 0);
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
    
    Robot.driveTrain.setDrives(0, 0);
    Robot.driveTrain.zeroEnc();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    
    Robot.driveTrain.setDrives(0, 0);
    Robot.driveTrain.zeroEnc();
  }
}
