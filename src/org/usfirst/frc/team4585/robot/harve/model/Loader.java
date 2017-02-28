package org.usfirst.frc.team4585.robot.harve.model;

import edu.wpi.first.wpilibj.Spark;
import org.usfirst.frc.team4585.robot.harve.model.sensors.CimCoder;

public class Loader {
	private Spark spinner;
	private CimCoder encoder;
	
	private boolean isLoading;
	private double targetRPS;//is set and monitored by the encoder
	private int direction;
	private double wheelMagnitude;
	private double RPS;
	private double agression=2;
	
	private Loader(){
		this.targetRPS = .5;
		this.direction = 1;
	}
	
	public Loader(int port){
		this();
		spinner = new Spark(port);
	}
	
	public Loader(int port, int dataPortA, int dataPortB){
		this(port);
		encoder = new CimCoder(dataPortA, dataPortB);
	}
	
	public void update(){
		if (isLoading) {
			RPS=encoder.getRPS();
				wheelMagnitude+=(targetRPS-RPS)*agression;
				spinner.set(wheelMagnitude);
			
		}
		else{
			spinner.set(0);
		}
	}
	
	public void setRPS(double RPS){
		this.targetRPS=RPS;
	}
	
	public void setIsLoading(boolean isLoading){
		this.isLoading = isLoading;
	}

	public Spark getSpinner() {
		return spinner;
	}

	public CimCoder getEncoder() {
		return encoder;
	}

	public boolean isLoading() {
		return isLoading;
	}

	public double getTargetRPS() {
		return targetRPS;
	}

	public int getDirection() {
		return direction;
	}

	public double getWheelMagnitude() {
		return wheelMagnitude;
	}

	public double getRPS() {
		return RPS;
	}

	public double getAgression() {
		return agression;
	}
}
