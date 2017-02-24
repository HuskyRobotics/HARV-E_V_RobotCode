package org.usfirst.frc.team4585.robot.harve.model.sensors;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.ADXL345_SPI;
import edu.wpi.first.wpilibj.ADXL345_SPI.Axes;
import edu.wpi.first.wpilibj.ADXL362;

public class Accelerometer {
	private ADXL345_SPI SPIAccelerometer;
	private AnalogAccelerometer analogAcceleromter;
	private BuiltInAccelerometer builtInAcceleromter;
	
	private double magX, magY, magZ;
	private double gMagX, gMagY, gMagZ;
	private double mdirectionX, mDirectionY, mDirectionZ;
	private double roboAccelerationMagY;
	private double degreesTurned;
	
	public Accelerometer(){
		magX = 0;
		magY = 0;
		magZ = 0;
	}
	
	public void init(){
	}
	
	public void update(){
		magX = SPIAccelerometer.getX();
		magY = SPIAccelerometer.getY();
		magZ = SPIAccelerometer.getZ();
	}
	
	private void findDown(){
		
	}
	
	private void trackDown(){
		
	}
	
	public double getMagX(){
		return this.magX;
	}
	
	public double getMagY(){
		return this.magY;
	}
	
	public double getMagZ(){
		return this.magZ;
	}
	
	public void setDegreesTurned(double degrees){
		
	}
}
