package org.usfirst.frc.team4585.robot.harve.model.autonomous;

import org.usfirst.frc.team4585.robot.harve.model.Climber;
import org.usfirst.frc.team4585.robot.harve.model.Loader;
import org.usfirst.frc.team4585.robot.harve.model.Shooter;
import org.usfirst.frc.team4585.robot.harve.model.drive.*;
import org.usfirst.frc.team4585.robot.harve.model.sensors.*;

public class HarvAutoController {
	private Shooter shooter;
	private Loader loader;
	private Climber climber;
	
	private HarvDrive drive;
	
	private double magX, magY, magRot;
	private double speedX,speedY,speedRot;
	private double distanceX,distanceY;
	private double distanceToObject;
	private double accuracyX;
	
	private long intendedAngle;
	private long angle;
	private long time;
	private long waitTime;
	private long millisPerIteration;
	
	
	public HarvAutoController(){
		magX = 0;
		magY = 0;
		magRot = 0;
		speedX = 0;
		speedY = 0;
		speedRot = 0;
		distanceX = 0;
		distanceY = 0;
		distanceToObject = 0;
		accuracyX = 0;
		angle = 0;
		time = 0;
		millisPerIteration = 20;
	}
	
	public HarvAutoController(Shooter shooter,Loader loader, Climber climber,HarvDrive drive){
		this();
		this.shooter = shooter;
		this.loader = loader;
		this.climber = climber;
		this.drive = drive;
	}
	
	public void start(){
		
	}
	
	public void init(){
		
	}
	
	public void update(){
		shooter.update();
		loader.update();
		climber.update();
		drive.update(magY, magRot);
	}
	
	public void driveForward(int meaters){
		
	}
	
	public void driveBackward(int meaters){
		
	}
	
	public void driveLeftward(int meaters){
		
	}
	
	public void driveRightward(int meaters){
		
	}
	
	public void turnRight(int degrees){
		
	}
	
	public void turnLeft(int degrees){
		
	}
	
	public boolean lookForObject(){//returns false if no object to target found
		boolean hasFoundObject = false;
		return hasFoundObject;
	}
	
	public void targetObject(){
		
	}
}
