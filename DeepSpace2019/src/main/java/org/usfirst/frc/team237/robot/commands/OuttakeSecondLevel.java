/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team237.robot.commands;

import org.usfirst.frc.team237.robot.subsystems.BallManipulatorSubsystem;
import org.usfirst.frc.team237.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class OuttakeSecondLevel extends Command 
{
  public OuttakeSecondLevel() 
  {
    requires(ElevatorSubsystem);
    requires(BallManipulatorSubsystem);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) > -400000)
    {
      Robot.elevator.elevatorUp();
    }
    else if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) < -400000 && Robot.elevator.leftElevator.getSelectedSensorPosition(0) > -500000)
    {
      Robot.elevator.elevatorUp();
      Robot.ballHandler.ballOuttake();
    }
    else 
    {
      Robot.ballHandler.offIntake();
      Robot.elevator.elevatorOff();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return true;
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
