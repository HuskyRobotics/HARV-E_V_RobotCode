package org.usfirst.frc.team4585.robot.harve.model;

import edu.wpi.first.wpilibj.Spark;

public class Climber {
	
	double speed=0;
	int speedCoeff=1; //this is here to reverse the motor if needed
	Spark motor;
	
	Climber(int port){
		motor=new Spark(port);	
	}
	
	void setClimb(boolean climb){
		if(climb)
			motor.set(speed*speedCoeff);
	}
	void setSpeed(double speed){
		this.speed=speed;
	}

}
