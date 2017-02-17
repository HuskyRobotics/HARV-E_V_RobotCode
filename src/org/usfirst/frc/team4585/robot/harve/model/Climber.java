package org.usfirst.frc.team4585.robot.harve.model;

import edu.wpi.first.wpilibj.Spark;

public class Climber {

	double speed = 0;
	int speedCoeff = 1; // this is here to reverse the motor if needed
	Spark motor;
	boolean climb=false;

	Climber(int port) {
		motor = new Spark(port);
	}

	public void setClimb(boolean climb) {
		this.climb=climb;
		if (climb)
			motor.set(speed * speedCoeff);
		else
			motor.set(0);
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void update() {
		if (climb)
			motor.set(speed * speedCoeff);
		else
			motor.set(0);
	}

}
