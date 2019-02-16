/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team237.robot.commands;

import javax.lang.model.util.ElementScanner6;

import org.usfirst.frc.team237.robot.Robot;
import org.usfirst.frc.team237.robot.subsystems.BallManipulatorSubsystem;
import org.usfirst.frc.team237.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class OuttakeSecondLevel extends Command 
{
  
  private enum State
  {
    start,
    Up,
    outtake,
    Down,
    finished,
  };
  private boolean m_done;
  private State currentState;

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
    m_done = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    switch (currentState)
    {
      case start:
        currentState = State.Up;
        break;

      case Up:
        if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) > -400000)
        {
          Robot.elevator.elevatorUp();
        }
        else
        {
          currentState = State.outtake;
        }
        break;
        

      case outtake: 
        if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) < -400000 && Robot.elevator.leftElevator.getSelectedSensorPosition(0) > -500000)
        {
          Robot.elevator.elevatorUp();
          Robot.ballHandler.ballOuttake();
        }
        else   
        {
          currentState = State.Down;
        }
        break;

      case Down: 
        if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) < -1000)
        {
          Robot.ballHandler.offIntake();
          Robot.elevator.elevatorDown();
        }
        else 
        {
          currentState = State.finished;
        }
        break;        

      case finished:
        Robot.elevator.elevatorOff();
        Robot.ballHandler.offIntake();
        m_done = true;
        break;

      default: 
        break;
    }
    /*
    if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) > -400000)
    {
      Robot.elevator.elevatorUp();
    }
    else if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) < -400000 && Robot.elevator.leftElevator.getSelectedSensorPosition(0) > -500000)
    {
      Robot.elevator.elevatorUp();
      Robot.ballHandler.ballOuttake();
    }
    else if(Robot.elevator.leftElevator.getSelectedSensorPosition(0) < -1000 && ) 
    {
      Robot.ballHandler.offIntake();
      Robot.elevator.elevatorDown();
      
    }
    else 
    {
      Robot.elevator.elevatorOff();
      Robot.ballHandler.offIntake();
      m_done = true;
    }
    */
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