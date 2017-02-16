package org.usfirst.frc.team4585.robot.harve.model;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;
public class Shooter {
	private final double maxWheelSpeed = 1;
	private double wheelSpeed;//in rotations per second
	private double wheelMagnitude; //starts at 0 and goes to 1
	private double tolerance;//how many rotations per second the wheel can be off by
	private double distance;
	private int wheelPort, encoderPort;
	
	private Spark wheel;
	private Encoder wheelEncoder;
	
	public Shooter(){//default constructor
		tolerance = 0.1;
		wheelSpeed = 0;
		distance = 0;
		wheelMagnitude = 0;
		
	}
		
	public Shooter(int wheelPort){
		this();
		wheel = new Spark(wheelPort);
	}
	
	public Shooter (int wheelPort, int encoderPort){
		this();
		wheel = new Spark(wheelPort);
		wheelEncoder = new Encoder(encoderPort,0);
	}
	
	public Shooter(int wheelPort, int encoderPortA, int encoderPortB){
		this();
		wheel = new Spark(wheelPort);
		wheelEncoder = new Encoder(encoderPortA,encoderPortB);
	}
	
	
	private double calculateDistance(){
		distance = wheelEncoder.getRate() * 1/*distance thing */;
		return distance;
	}
	
	private void updateWheelSpeed(){//find the amount of power to apply to the motor to get the required wheel speed
		if(wheelSpeed < wheelEncoder.getRate()){
			if(wheelMagnitude < 1)
				wheelMagnitude += (wheelSpeed - wheelEncoder.getRate()) * (1/20);
		}else if(wheelSpeed > wheelEncoder.getRate()){
			wheelMagnitude -= (wheelSpeed - wheelEncoder.getRate()) * (1/20);
		}
		wheel.set(wheelMagnitude);
	}
	
	public void update(){
		this.updateWheelSpeed();
		this.calculateDistance();
	}
	
	public void setWheelMagnitude(double wheelMagnitude){
		this.wheelMagnitude = wheelMagnitude;
	}
	
	public void setWheelSpeed(double wheelSpeed){
		this.wheelSpeed = wheelSpeed;
	}
	
	public double getWheelSpeed(){
		return this.wheelSpeed;
	}
	
	public double getDistance(){
		return this.distance;
	}
	
}
