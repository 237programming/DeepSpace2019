/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team237.robot.commands;

import org.usfirst.frc.team237.robot.Robot;
import org.usfirst.frc.team237.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoRightSide extends Command 
{

  private double time;
  private State currentState;

  private enum State
  {
      start,
      driveOffPlatform,
      reorient,
      turnToAngle,
      moveAtAngle,
      turnToRocketAngle,
      moveAtRocketAngle,
      outtakeDisk,
      backAwayFromRocket,
      turnToBase,
      moveToBase,
      turnToDiskAngle,
      moveToDiskAngle,
      turnToDisk,
      moveToDisk,
      intakeDisk,
      backAwayFromDisk,
      turnAround,
      finished  
  };

  public AutoRightSide() 
  {
      requires(Robot.driveTrain);
    //requires(Robot.diskManipulator);

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
      Robot.driveTrain.zeroEnc();
      Robot.driveTrain.disableRotateTo();
      Robot.driveTrain.setPIDValues(RobotMap.driveP, RobotMap.driveI, RobotMap.driveD);
      Robot.driveTrain.rotateTo(0);
      time = Timer.getFPGATimestamp();
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
            Robot.driveTrain.pidDrive(1);
            if(Robot.driveTrain.getEncPos()>1000)
            {
              Robot.driveTrain.disableRotateTo();
              Robot.driveTrain.zeroEnc();
              Robot.driveTrain.setDrives(0, 0);
              Robot.driveTrain.setPIDValues(RobotMap.driveP, RobotMap.driveI, RobotMap.driveD);
              Robot.driveTrain.rotateTo(0);
              currentState = State.reorient;
            }
            break;

          case reorient:
            Robot.driveTrain.setDrives(0, 0);
            if(Timer.getFPGATimestamp() > time + .25)
            {
              Robot.driveTrain.disableRotateTo();
              Robot.driveTrain.zeroEnc();
              Robot.driveTrain.setDrives(0, 0);
              Robot.driveTrain.setPIDValues(RobotMap.turnP, RobotMap.turnI, RobotMap.turnD);
              Robot.driveTrain.rotateTo(10);
              currentState = State.turnToAngle;
            }
            break;
          
          case turnToAngle:
            Robot.driveTrain.pidDrive(1);
            if(Timer.getFPGATimestamp() > time + 1)
            {
              Robot.driveTrain.disableRotateTo();
              Robot.driveTrain.zeroEnc();
              Robot.driveTrain.setDrive(0, 0);
              Robot.driveTrain.setPIDValues(RobotMap.driveP, RobotMap.driveI, RobotMap.driveD);
              Robot.driveTrain.rotateTo(10);
              currentState = State.moveAtAngle;
            }
            break;
          
          case moveAtAngle:
            Robot.driveTrain.pidDrive(1);
            if(Robot.driveTrain.getEncPos() > 1000)
            {
              Robot.driveTrain.disableRotateTo();
              Robot.driveTrain.zeroEnc();
              Robot.driveTrain.setDrives(0, 0);
              Robot.driveTrain.setPIDValues(RobotMap.turnP, RobotMap.turnI, RobotMap.turnD);
              Robot.driveTrain.rotateTo(135);
              currentState = State.turnToRocketAngle;
            }

          case turnToRocketAngle:
            break;
          default:
      }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}