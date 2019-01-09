package org.usfirst.frc.team237.robot.subsystems;

import org.usfirst.frc.team237.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DiskManipulatorSubsystem extends Subsystem 
{
	private static DoubleSolenoid diskSolenoid = new DoubleSolenoid(RobotMap.diskSolenoid, 0);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DiskManipulatorSubsystem()
	{
		diskSolenoid.set(DoubleSolenoid.Value.kOff);
	}
	
	public void diskDown()
	{
		diskSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void diskUp()
	{
		diskSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void diskOff()
	{
		diskSolenoid.set(DoubleSolenoid.Value.kOff);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

