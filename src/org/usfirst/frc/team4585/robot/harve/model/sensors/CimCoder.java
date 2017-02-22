package org.usfirst.frc.team4585.robot.harve.model.sensors;

import edu.wpi.first.wpilibj.Encoder;
import java.lang.Math;

public class CimCoder {
	
	Encoder encoder;
	private int ticksPerRevolution=40;
	
	private double wheelCircumfrence=1;
	
	public CimCoder(int chA, int chB){
		encoder=new Encoder(chA, chB);
		encoder.setDistancePerPulse(1/ticksPerRevolution);
	}
	
	public double getRPS(){
		return encoder.getRate();
		
	}
	
	public double getFPS(){
		
		return getRPS()*(wheelCircumfrence/12);
	}
	
	public void setWheelRadiusInches(double inches){
		this.wheelCircumfrence=2*Math.PI*inches;
		
	}
	public void setWheelRadiusCentimeters(double Centimeters){
		setWheelRadiusInches(0.393701*Centimeters);
		
	}

}
