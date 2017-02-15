package org.usfirst.frc.team4585.robot.harve.model;

import edu.wpi.first.wpilibj.Spark;
import org.usfirst.frc.team4585.robot.harve.model.sensors.Encoder;

public class Shooter {
	private double wheelSpeed;//in rotations per minute
	private double distance;
	private int port;
	
	private Spark wheel;
	private Encoder wheelEncoder;
	
	public Shooter(){//default constructor
		
	}
	
	public Shooter(int wheelPort){
		wheel = new Spark(wheelPort);
	}
	
	public Shooter (int wheelPort, int encoderPort){
		wheel = new Spark(wheelPort);
		wheelEncoder = new Encoder(encoderPort);
	}
	
	
}
