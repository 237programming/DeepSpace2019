/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team237.robot.subsystems;

import org.usfirst.frc.team237.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class ClimberSubsystem extends Subsystem 
{
  private static Solenoid climbSolenoid = new Solenoid(51, RobotMap.climbSol);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public ClimberSubsystem()
  {
    climbSolenoid.set(false);
    DiskManipulatorSubsystem.compressor.enabled();
  }

  public void climberOn()
  {
    climbSolenoid.set(true);
  }

  public void climberOff()
  {
    climbSolenoid.set(false);
  }

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
