package org.usfirst.frc.team237.robot.subsystems;

import org.usfirst.frc.team237.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem 
{

	private WPI_TalonSRX leftElevator = new WPI_TalonSRX(RobotMap.elevatorMotor1);
	private WPI_TalonSRX rightElevator = new WPI_TalonSRX(RobotMap.elevatorMotor2);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public ElevatorSubsystem()
	{
		leftElevator.set(ControlMode.PercentOutput, 0);
		rightElevator.set(ControlMode.Follower, RobotMap.elevatorMotor1);
	}
	
	public void elevatorUp()
	{
		leftElevator.set(0.75);
	}
	
	public void elevatorDown()
	{
		leftElevator.set(-0.20);
	}
	
	public void elevatorOff()
	{
		leftElevator.set(0);
	}
	
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

