package org.usfirst.frc.team237.robot.subsystems;


import org.usfirst.frc.team237.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Notifier;
/*
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
*/
/**
 *
 */
public class DriveSubsystem extends Subsystem implements edu.wpi.first.wpilibj.PIDOutput
{	
	private static final int k_ticks_per_rev = 1024;
  	private static final double k_wheel_diameter = 4.0 / 12.0;
  	private static final double k_max_velocity = 10;

	private WPI_TalonSRX leftDrive = new WPI_TalonSRX(RobotMap.driveTalonFL);
	private WPI_TalonSRX leftDriveSlave = new WPI_TalonSRX(RobotMap.driveTalonBL);
	private WPI_TalonSRX rightDrive = new WPI_TalonSRX(RobotMap.driveTalonFR);
	private WPI_TalonSRX rightDriveSlave = new WPI_TalonSRX(RobotMap.driveTalonBR);
//	private AHRS gyro = new AHRS(SerialPort.Port.kUSB, AHRS.SerialDataType.kProcessedData, (byte) 200);
	private AHRS gyro = new AHRS(SPI.Port.kMXP);
	private PIDController angularPID = new PIDController(0.1, 0.0, 0.1, gyro, this);
	private double PIDOutput = 0;
	private boolean reverseDriveFlag = false;
	/*
	private EncoderFollower m_left_follower;
	private EncoderFollower m_right_follower;
	private Notifier m_Notifier; 
    private Trajectory leftTrajectory; //= PathfinderFRC.getTrajectory("Start.left.pf1");
	private Trajectory rightTrajectory; //= PathfinderFRC.getTrajectory("Start.right.pf1");
	*/
	public DriveSubsystem()
	{
		leftDrive.set(ControlMode.PercentOutput, 0);
		leftDriveSlave.set(ControlMode.Follower, RobotMap.driveTalonFL);
		rightDrive.set(ControlMode.PercentOutput, 0);
		rightDriveSlave.set(ControlMode.Follower, RobotMap.driveTalonFR);
		angularPID.disable();
		angularPID.setInputRange(-180, 180);
		angularPID.setOutputRange(-0.7, 0.7);
		angularPID.setPercentTolerance(30);
		angularPID.setContinuous();
		
		
		gyro.reset();
		
		leftDrive.setSensorPhase(true);
		/*
		m_left_follower = new EncoderFollower(leftTrajectory);
    	m_right_follower = new EncoderFollower(rightTrajectory);
		m_left_follower.configureEncoder(leftDrive.getSelectedSensorPosition(), k_ticks_per_rev, k_wheel_diameter);
    	// You must tune the PID values on the following line!
    	m_left_follower.configurePIDVA(1.0, 0.0, 0.0, 1 / k_max_velocity, 0);

    	m_right_follower.configureEncoder(rightDrive.getSelectedSensorPosition(), k_ticks_per_rev, k_wheel_diameter);
    	// You must tune the PID values on the following line!
    	m_right_follower.configurePIDVA(1.0, 0.0, 0.0, 1 / k_max_velocity, 0);
		*/
    	
	}
	public void enablePathFollower()
	{
		/*
		m_Notifier = new Notifier(this::followPath);
		m_Notifier.startPeriodic(leftTrajectory.get(0).dt);
		*/
	}
	public void followPath()
	{
		/*
		if (m_left_follower.isFinished() || m_right_follower.isFinished()) {
			m_Notifier.stop();
		} 
		else 
		{
			double left_speed = m_left_follower.calculate(leftDrive.getSelectedSensorPosition());
			double right_speed = m_right_follower.calculate(rightDrive.getSelectedSensorPosition());
			double heading = gyro.getAngle();
			double desired_heading = Pathfinder.r2d(m_left_follower.getHeading());
			double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
			double turn = 0.8 * (-1.0 / 80.0) * heading_difference;
			leftDrive.set(left_speed + turn);
			rightDrive.set(right_speed - turn);
		}
		*/
	}
	public void stopMP()
	{
		/*
		m_Notifier.stop();
		leftDrive.set(0);
		rightDrive.set(0); 
		*/
	}
	public void setDrives(double x, double y)
	{
			x = Math.abs(x) > RobotMap.deadband ? x : 0;
			y = Math.abs(y) > RobotMap.deadband ? y : 0;
			

			if(reverseDriveFlag)
			{
				x = -x;
			}
			
			if(x != 0)
			{
				x = sgn(x) * ((Math.abs(x) - RobotMap.deadband) / (1 - RobotMap.deadband));
			}
			
			if(y != 0)
			{
				y = sgn(y) * ((Math.abs(y) - RobotMap.deadband) / (1 - RobotMap.deadband));
			}
			
			double right = y + x;
			double left = (y - x) * -1;
			double absLeft = Math.abs(left);
			double absRight = Math.abs(right);
			double normalLeft;
			double normalRight;
			
			if(absLeft > absRight && absLeft > 1)
			{
				normalLeft = left / absLeft;
				normalRight = right / absLeft;
			}
			
			else if(absRight > absLeft && absRight > 1)
			{
				normalLeft = left / absRight;
				normalRight = right / absRight;
			}
			
			else
			{
				normalLeft = left;
				normalRight = right;
			}
			
			leftDrive.set(normalLeft);
			rightDrive.set(normalRight);
	}
	
	double sgn(double x)
	{
		if (x < 0)
		{
			return -1.0; 
		}
		else if (x > 0)
		{
			return 1.0; 
		} 
		else
		{
			return 0.0; 
		}
	}
	
	public double getEncPos()
	{
		int leftEnc = leftDrive.getSelectedSensorPosition(0);
		int rightEnc = rightDrive.getSelectedSensorPosition(0);
		double position = (leftEnc + rightEnc)/2;
		return position;
	}
	
	public double getYaw()
	{
		double realYaw = gyro.getYaw();
		return realYaw;
	}
	
	public void zeroYaw()
	{
		gyro.zeroYaw();
	}
	
	public void zeroEnc()
	{
		leftDrive.setSelectedSensorPosition(0, 0, 0);
		rightDrive.setSelectedSensorPosition(0, 0, 0);
	}
	
	@Override
	public void pidWrite(double output)
	{
		PIDOutput = output;
	}
	
	public void rotateTo(double targetAngle)
	{
		PIDOutput = 0; 
		angularPID.disable();
		angularPID.setSetpoint(targetAngle);
		angularPID.enable();
	}
	
	public void disableRotateTo()
	{
		angularPID.disable();
		setDrives(0.0,0.0);
	}
	public void setPIDValues(double P, double I, double D)
	{
		angularPID.setP(P);
		angularPID.setI(I);
		angularPID.setD(D);
	}
	public void pidDrive(double speed)
	{
		setDrives(speed, PIDOutput);
	}
	
	public void post()
	{
		SmartDashboard.putNumber("Right Drive", rightDrive.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Left Drive", leftDrive.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Gyro Yaw", getYaw());
		SmartDashboard.putBoolean("Reverse Drive", reverseDrive());
		SmartDashboard.putNumber("PID Output", PIDOutput);
	}

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
	}

	public void reverseDriveSetter(boolean flag)
	{
		reverseDriveFlag = flag;
	}

	public boolean reverseDrive()
	{
		return reverseDriveFlag;
	}

	
}

