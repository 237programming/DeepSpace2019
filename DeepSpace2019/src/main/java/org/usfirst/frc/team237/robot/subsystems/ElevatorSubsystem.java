package org.usfirst.frc.team237.robot.subsystems;

import org.usfirst.frc.team237.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem 
{

	public WPI_TalonSRX leftElevator = new WPI_TalonSRX(RobotMap.elevatorMotor1);
	private WPI_TalonSRX rightElevator = new WPI_TalonSRX(RobotMap.elevatorMotor2);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public ElevatorSubsystem()
	{
		leftElevator.configClearPositionOnLimitF(true, 0);
		leftElevator.set(ControlMode.PercentOutput, 0);
		rightElevator.set(ControlMode.Follower, RobotMap.elevatorMotor1);
	}
	
	public void elevatorUp()
	{
		leftElevator.set(-1);
	}
	
	public void elevatorDown()
	{
		leftElevator.set(.15);
	/*	if(leftElevator.getSelectedSensorPosition(0) > -60000)
		{
			leftElevator.set(.2);
		}
		else
		{
			leftElevator.set(.15);
		}
*/	}
	
	public void elevatorOff()
	{
		leftElevator.set(0);
	}

	public void setSpeed(double speed)
	{
		leftElevator.set(speed);
	}

	public void post()
	{
		SmartDashboard.putNumber("Left Elevator", leftElevator.getSelectedSensorPosition(0));
		//SmartDashboard.putNumber("Right Elevator", rightElevator.getSelectedSensorPosition(0));
	}

	public void zeroEnc()
	{
		leftElevator.setSelectedSensorPosition(0, 0, 0);
	}
	
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

