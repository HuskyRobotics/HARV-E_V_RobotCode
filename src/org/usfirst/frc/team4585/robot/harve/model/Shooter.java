package org.usfirst.frc.team4585.robot.harve.model;

import org.usfirst.frc.team4585.robot.harve.model.sensors.CimCoder;
import edu.wpi.first.wpilibj.Spark;
public class Shooter {
	private boolean isEncoderShoot;
	private boolean shootByDistance;
	private boolean isShooting;
	private final double maxWheelSpeed = 1;
	private double wheelSpeed;//in rotations per second
	private final double wheelSpeedCoeff; //what to multiply against wheel speed to get the speed of the ball
	private double wheelMagnitude; //starts at 0 and goes to 1
	private double tolerance;//how many rotations per second the wheel can be off by
	private double launchDistance;
	private double launchCoeff;
	private double distance;
	private int wheelPort, encoderPort;
	
	private Spark wheel;
	private CimCoder encoder;
	
	public Shooter(){//default constructor
		wheelSpeedCoeff = 1;
		isEncoderShoot =false;
		tolerance = 0.1;
		wheelSpeed = 0;
		distance = 0;
		wheelMagnitude = 0;
		
	}
		
	public Shooter(int wheelPort){
		this();
		wheel = new Spark(wheelPort);
	}
	
	public Shooter(int wheelPort, int encoderPortA, int encoderPortB){
		this();
		wheel = new Spark(wheelPort);
		encoder = new CimCoder(encoderPortA,encoderPortB);
	}
	
	private void findLaunchDistance(){//finds distance from ball at project ft/s at 70degrees
		double ballSpeed = wheelSpeed * wheelSpeedCoeff;
		double magY = Math.sin(70)*ballSpeed;
		double magX = Math.cos(70)*ballSpeed;
		//find time it will take for ball to go up and down
		double timeInAir = -magY/-9.8;
		timeInAir += magY/-9.8;
		
		this.launchDistance = magX * timeInAir * launchCoeff;
	}
	
	private void encoderShoot(){
		//find the amount of power to apply to the motor to get the required wheel speed
		if(wheelSpeed < encoder.getFPS()){
			if(wheelMagnitude < 1)
				wheelMagnitude += (wheelSpeed - encoder.getFPS()) * (1/20);
			}else if(wheelSpeed > encoder.getFPS()){
				wheelMagnitude -= (wheelSpeed - encoder.getFPS()) * (1/20);
			}
		
		this.shoot(wheelMagnitude);
	}
	
	private void shoot(double magnitude){
		wheel.set(magnitude);
	}
	
	private double calculateDistance(){
		distance = encoder.getFPS() * 1/*distance thing */;
		return distance;
	}
	
	private void updateWheelSpeed(){
		if(isShooting){
			if(isEncoderShoot){
				encoderShoot();
			}else{
				shoot(wheelMagnitude);
			}
		}else{
			shoot(0);
		}
	}
	
	public void update(){
		this.updateWheelSpeed();//blalbalba
	}
	
	public void setIsShooting(boolean isShooting){
		this.isShooting = isShooting;
	}
	
	public void setWheelMagnitude(double wheelMagnitude){
		this.wheelMagnitude = wheelMagnitude;
	}
	
	public void setWheelSpeed(double wheelSpeed){
		shootByDistance = false;
		this.wheelSpeed = wheelSpeed;
	}
	
	public void setDistance(double distance){
		shootByDistance = true;
		this.distance = distance;
	}
	
	public double getWheelSpeed(){
		return this.wheelSpeed;
	}
	
	public double getDistance(){
		return this.distance;
	}
	
	public boolean getIsEncoderShoot(){
		return this.isEncoderShoot;
	}
	
	public void setIsEncoderShoot(boolean isEncoderShoot){
		this.isEncoderShoot = isEncoderShoot;
	}
	
}
