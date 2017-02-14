package org.usfirst.frc.team4585.robot.harve.model.drive;

import edu.wpi.first.wpilibj.Spark;
import org.usfirst.frc.team4585.robot.harve.model.sensors.*;

public class DefaultDrive extends HarvDrive{
	private Spark leftSide,rightSide;
	boolean correctionsEnabled;
	Sensors gyro=new Sensors();
	
	double magY, magRot;
	double targetAngle=0;
	
	
	public DefaultDrive(short leftSide, short rightSide){
		this.leftSide = new Spark(leftSide);
		this.rightSide = new Spark(rightSide);
		
		correctionsEnabled=true;
		
		gyro.calibrateGyro();
	}
	public DefaultDrive(short leftSide, short rightSide, boolean correctionState){
		this.leftSide = new Spark(leftSide);
		this.rightSide = new Spark(rightSide);
		
		correctionsEnabled=correctionState;
		
		gyro.calibrateGyro();
		
	}
	
	public void invertMotors(){
		if(this.rightSide.getInverted())
			this.rightSide.setInverted(false);
		else this.rightSide.setInverted(true);
		if(this.leftSide.getInverted())
			this.leftSide.setInverted(false);
		else this.leftSide.setInverted(true);
	}
	
	public DefaultDrive(int leftSide, int rightSide){
		super();
		this.leftSide = new Spark(leftSide);
		this.rightSide = new Spark(rightSide);
	}
	
	@Override
	public void update(double magY, double magRot){
		
		this.magY=magY;
		this.magRot=magRot;
		
		if(magRot==0)
			targetAngle=gyro.getAngle();
		if(correctionsEnabled)
			correctSteering();
		leftSide.set(this.magRot - this.magY);
		rightSide.set(this.magRot + this.magY);
	}

	@Override
	public void update(double magX, double magY, double magRot) {
		update(magY, magRot);
	}

	@Override
	public double getWheelRotation() {
		return 0;
	}

	@Override
	public double getWheelSpeed() {
		return 0;
	}
	
	public Spark getLeftSide(){
		return this.leftSide;
	}
	
	public Spark getRightSide(){
		return this.rightSide;
	}
	public void setCorrectionsEnabled(boolean correctionState){
		correctionsEnabled=correctionState;
	}
	
	private void correctSteering(){
		double deadzone=2;
		double correctionCoeff=5;
		double angleOffset=gyro.getAngle()-targetAngle;
		if(!(Math.abs(angleOffset)<deadzone))
			magRot+=angleOffset*correctionCoeff;
		
	
	}
	
}
