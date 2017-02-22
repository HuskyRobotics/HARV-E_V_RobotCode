package org.usfirst.frc.team4585.robot.harve.model;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.*;



public class Climber {
	
	SmartDashboard dashboard;

	double speed = 0;
	int speedCoeff = 1; // this is here to reverse the motor if needed
	Talon motor;
	boolean climb=false;

	public Climber(int port) {
		motor = new Talon(port);
		dashboard = new SmartDashboard();
	}

	public void setClimb(boolean climb) {
		this.climb=climb;
		if (this.climb){
			dashboard.putBoolean("isClimbing", true);
			motor.set(speed * speedCoeff);
		}
		else{
			motor.set(0);
			dashboard.putBoolean("isClimbing", false);
		}
	}

	public void setSpeed(double speed) {
		dashboard.putNumber("climberSpeed", speed);
		this.speed = speed;
	}

	public void update() {
		if (climb)
			motor.set(speed * speedCoeff);
		else
			motor.set(0);
	}

}
