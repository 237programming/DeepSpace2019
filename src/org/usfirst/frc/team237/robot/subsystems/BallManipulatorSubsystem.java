package org.usfirst.frc.team237.robot.subsystems;

import org.usfirst.frc.team237.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallManipulatorSubsystem extends Subsystem 
{
	private WPI_TalonSRX ballMotor= new WPI_TalonSRX(RobotMap.ballMotor);
	
 // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public BallManipulatorSubsystem()
	{
		ballMotor.set(ControlMode.PercentOutput, 0);
	}
	
	public void ballSpit()
	{
		ballMotor.set(1.0);
	}
	
	public void ballSuck()
	{
		ballMotor.set(-1.0);
	}
	
	public void noBalls()
	{
		ballMotor.set(0.0);
	}

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
}

