package org.usfirst.frc.team4585.robot.harve.model.sensors;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.GyroBase;
import java.util.ArrayList;

public class Gyroscope {
	private ADXRS450_Gyro gyroscope;
	private AnalogGyro analogGyro;
	private GyroBase gyroBase;
	
	private ArrayList<Double> angleList;
	
	private double angle;
	
	public Gyroscope(){
		gyroscope = new ADXRS450_Gyro();
	}
	
	public void init(){
		gyroscope.calibrate();
	}
	
	public double getAngle(){
		return gyroscope.getAngle();
	}
	
	public double getRate(){
		return gyroscope.getRate();
	}
	
	public void reset(){
		gyroscope.reset();
	}
}
