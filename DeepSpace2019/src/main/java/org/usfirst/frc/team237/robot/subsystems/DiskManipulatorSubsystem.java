package org.usfirst.frc.team237.robot.subsystems;

import org.usfirst.frc.team237.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;

public class DiskManipulatorSubsystem extends Subsystem 
{
	private Compressor compressor = new Compressor(51);
	private Solenoid diskExtentionSolenoid = new Solenoid(RobotMap.diskExtentionSol);
	private Solenoid diskEjectSolenoid = new Solenoid(RobotMap.diskEjectSol);
	private Solenoid ballSolenoid = new Solenoid(RobotMap.ballSol);
	private Solenoid slapSolenoid = new Solenoid(RobotMap.slapSol); 
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DiskManipulatorSubsystem()
	{
		diskExtentionSolenoid.set(false);
		diskEjectSolenoid.set(false);
		ballSolenoid.set(false);
		slapSolenoid.set(false);
		compressor.enabled();
	}
	
	public void diskDown()
	{
		slapSolenoid.set(true);
	}
	
	public void diskUp()
	{
		slapSolenoid.set(false);
	}
	
	public void diskExtend()
	{
		diskExtentionSolenoid.set(true);
	}
	public void diskRetract()
	{
		diskExtentionSolenoid.set(false);
	}
	public void diskEject()
	{
		diskExtentionSolenoid.set(true);
	}
	public void diskUnject()
	{
		diskExtentionSolenoid.set(false);
	}
	public void ballDown()
	{
		ballSolenoid.set(true);
	}

	public void ballUp()
	{
		ballSolenoid.set(false);
	}
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

