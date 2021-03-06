package org.usfirst.frc.team4585.robot.harve.model;

import org.usfirst.frc.team4585.robot.harve.model.sensors.CimCoder;
import edu.wpi.first.wpilibj.Spark;

public class Shooter {
	private boolean isEncoderShoot;
	private boolean shootByDistance;
	private boolean isShooting;
	private final double maxWheelSpeed = 1;
	private double wheelSpeed;// in rotations per second
	private final double wheelSpeedCoeff; // what to multiply against wheel speed to get the speed of the ball
	private double wheelMagnitude; // starts at 0 and goes to 1
	private double tolerance;// how many rotations per second the wheel can be off by
	private double launchDistance;
	private double launchCoeff;
	private double distance;
	private double targetRPS;
	private double RPS;

	private Spark wheel;
	private CimCoder encoder;
	
	private boolean isReady;

	public Shooter() {// default constructor
		wheelSpeedCoeff = 1;
		isEncoderShoot = false;
		isReady = false;
		tolerance = .1;
		wheelSpeed = 0;
		distance = 0;
		wheelMagnitude = 0;
	}

	public Shooter(int wheelPort) {
		this();
		wheel = new Spark(wheelPort);
		encoder  = new CimCoder();
	}

	public Shooter(int wheelPort, int encoderPortA, int encoderPortB) {
		this();
		wheel = new Spark(wheelPort);
		encoder = new CimCoder(encoderPortA, encoderPortB);
	}

	private void findLaunchDistance() {// finds distance from ball at project
										// ft/s at 70degrees
		double ballSpeed = wheelSpeed * wheelSpeedCoeff;
		double magY = Math.sin(70) * ballSpeed;
		double magX = Math.cos(70) * ballSpeed;
		// find time it will take for ball to go up and down
		double timeInAir = -magY / -9.8;
		timeInAir += magY / -9.8;

		this.launchDistance = magX * timeInAir * launchCoeff;
	}

	private void shoot(double magnitude) {
		wheel.set(magnitude);
	}

	private void updateWheelSpeed() {
		if (isShooting) {
			RPS=encoder.getRPS();
			if(Math.abs(RPS-targetRPS)>tolerance){
				wheelMagnitude+=(targetRPS-RPS);
				wheel.set(wheelMagnitude);
			}

		} else {
			shoot(0);
		}
	}

	public void update() {
		this.updateWheelSpeed();
	}

	public void setIsShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public void setRPS(double RPS) {
		shootByDistance = false;
		this.targetRPS = RPS;
	}

	public void setDistance(double distance) {
		shootByDistance = true;
		this.distance = distance;
	}

	public void setMagnitude(double mag){
		this.wheelMagnitude = mag;
	}
	
	public double getRPS() {
		return this.RPS;
	}

	public boolean getIsEncoderShoot() {
		return this.isEncoderShoot;
	}
	
	public boolean getIsReady(){
		return this.isReady;
	}
	public void stop(){
		setRPS(0);
		update();		
	}

	public boolean isShootByDistance() {
		return shootByDistance;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public double getMaxWheelSpeed() {
		return maxWheelSpeed;
	}

	public double getWheelSpeed() {
		return wheelSpeed;
	}

	public double getWheelSpeedCoeff() {
		return wheelSpeedCoeff;
	}

	public double getWheelMagnitude() {
		return wheelMagnitude;
	}

	public double getTolerance() {
		return tolerance;
	}

	public double getLaunchDistance() {
		return launchDistance;
	}

	public double getLaunchCoeff() {
		return launchCoeff;
	}

	public double getDistance() {
		return distance;
	}

	public double getTargetRPS() {
		return targetRPS;
	}
}
