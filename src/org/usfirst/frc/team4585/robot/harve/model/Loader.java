package org.usfirst.frc.team4585.robot.harve.model;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;

public class Loader {
	private Spark spinner;
	private Encoder encoder;
	
	private boolean isStuck;
	private boolean isLoading;
	private boolean isSetBySpeed;
	private boolean isSetByMagnitude;
	private boolean isSetByBallsPerSecond;
	private double speed;//is set and monitored by the encoder
	private double magnitude;//is value given to moter that is 1 or less
	private double ballsPerSecond;// how many balls fall through the hole
	private int direction; //-1 left, 0 is stopped, 1 right.
	
	private Loader(){
		this.isStuck = false;
		this.speed = 0;
		this.magnitude = 0;
		this.ballsPerSecond = 0;
		this.direction = 1;
	}
	
	public Loader(int port){
		this();
		spinner = new Spark(port);
	}
	
	public Loader(int port, int dataPortA, int dataPortB){
		this(port);
		encoder = new Encoder(dataPortA, dataPortB, false, Encoder.EncodingType.k4X);
		setupEncoder();
	}
	
	private void setupEncoder(){
		encoder.setMaxPeriod(0.1);
		encoder.setMinRate(10);
		encoder.setDistancePerPulse(1);
		encoder.setSamplesToAverage(7);
	}
	
	public void update(){
		if(isSetBySpeed && (speed > 0 || speed < 0)){
			if(encoder.get()/encoder.getSamplesToAverage()> speed){
				this.magnitude -= ((encoder.getPeriod()/encoder.getSamplesToAverage()) - speed)/20;
			}else{
				this.magnitude += (speed - (encoder.getPeriod()/encoder.getSamplesToAverage()))/20;
			}
		}
		spinner.set(magnitude);
	}
	
	public void changeDirection(){
		if(direction > 0){
			direction = -1;
			encoder.setReverseDirection(true);
		}else if(direction < 0){
			direction = 1;
			encoder.setReverseDirection(false);
		}
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
		if(isSetBySpeed == false){
			this.isSetBySpeed = true;
			this.isSetByBallsPerSecond = false;
			this.isSetByMagnitude = false;
		}
	}
	
	public void setMagnitude(double magnitude){
		this.magnitude = magnitude;
		if(isSetByMagnitude == false){
			this.isSetByMagnitude = true;
			this.isSetBySpeed = false;
			this.isSetByBallsPerSecond = false;
		}
	}
	
	public void setBallsPerSecond(double ballsPerSecond){
		this.ballsPerSecond = ballsPerSecond;
		if(isSetByBallsPerSecond == false){
			this.isSetByBallsPerSecond = true;
			this.isSetByMagnitude = false;
			this.isSetBySpeed = false;
		}
	}
	
	private double findSpeed(){
		final double rpsCoeff = 1;
		return encoder.getRate() * rpsCoeff;
	}
	
	private void findMagnitude(){
		if(this.findSpeed() > this.speed){
			
		}
	}
	
	public void setIsLoading(boolean isLoading){
		this.isLoading = isLoading;
	}
	
}
