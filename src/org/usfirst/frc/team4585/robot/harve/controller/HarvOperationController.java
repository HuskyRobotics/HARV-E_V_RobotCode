package org.usfirst.frc.team4585.robot.harve.controller;

import org.usfirst.frc.team4585.robot.harve.model.*;
import org.usfirst.frc.team4585.robot.harve.model.drive.*;
import org.usfirst.frc.team4585.robot.harve.view.*;
import org.usfirst.frc.team4585.robot.harve.model.Shooter;
import org.usfirst.frc.team4585.robot.harve.model.drive.HarvDrive;
import org.usfirst.frc.team4585.robot.harve.model.sensors.HRLV_MaxSonar_EZ_Analog;
import org.usfirst.frc.team4585.robot.harve.model.sensors.Gyroscope;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HarvOperationController {
	private HarvDrive drive;
	private Shooter shooter;
	private Climber climber;
	private Loader loader;
	private HarvInput driveInput;
	private HarvInput weaponsInput;
	
	private double magX, magY, magRot;
	private double distanceToTarget;
	
	private int millisPerIteration;
	private int time;
	private int weaponsButtonShoot;
	private int weaponsButtonLoad;
	private int weaponsButtonClimb;
	private int weaponsButtonChangeClimbSpeed;
	
	private int driveButtonAllign;
	
	
	private boolean isShooting, changeIsShooting;
	private boolean isClimbing, changeIsClimbing;
	private boolean isFastClimbing, changeIsFastClimbing;
	private boolean isLoading, changeIsLoading;
	
	private HarvOperationController(){
		magX = 0; magY = 0; magRot = 0;
		distanceToTarget = 0;
		isShooting = false; changeIsShooting = false;
		isClimbing = false; changeIsClimbing = false;
		isFastClimbing = false; changeIsFastClimbing = false;
		isLoading = false; changeIsLoading = false;
	}
	
	public HarvOperationController(HarvDrive drive, Shooter shooter, Loader loader, Climber climber, HarvInput driveInput, HarvInput weaponsInput){
		this();
		this.drive = drive;
		this.shooter = shooter;
		this.loader = loader;
		this.climber = climber;
		this.driveInput = driveInput;
		this.weaponsInput = weaponsInput;
	}
	
	public void start(){
		
	}
	
	public void init(){
		
	}
	
	public void update(){
		this.updateWeapons();
		this.updateDrive();
	}
	
	private void updateWeapons(){
		this.updateShooter();
		this.updateLoader();
		this.updateClimber();
	}
	
	private void updateDrive(){
		
	}
	
	private void updateShooter(){
		if(this.weaponsInput.buttonIsPressed(this.weaponsButtonShoot)){
			if(isShooting){
				changeIsShooting = false;
			}else{
				changeIsShooting = true;
			}
		}else{
			isShooting = changeIsShooting;
		}
		if(isShooting){
			shooter.setIsShooting(true);
		}else{
			shooter.setIsShooting(false);
		}
		shooter.setDistance(distanceToTarget);
		shooter.update();
	}
	
	private void updateLoader(){

	}
	
	private void updateClimber(){
		if(this.weaponsInput.buttonIsPressed(this.weaponsButtonClimb)){
			if(isClimbing){
				changeIsClimbing = false;
			}else{
				changeIsClimbing = true;
			}
		}else{
			isClimbing = changeIsClimbing;
		}
		if(this.weaponsInput.buttonIsPressed(this.weaponsButtonChangeClimbSpeed)){
			if(isFastClimbing){
				changeIsFastClimbing = false;
			}else{
				changeIsFastClimbing = true;
			}
		}else{
			isFastClimbing = changeIsFastClimbing;
		}
		
		if(isClimbing){
			climber.setClimb(true);
			if(isFastClimbing){
				climber.setSpeed(1);
			}else{
				climber.setSpeed(0.5);
			}
		}else{
			climber.setClimb(false);
		}
		climber.update();
	}
	
	public void setWeaponsButtons(int shoot, int load, int climb){
		
	}
	
	public void setDriveButtons(int allign){
		
	}
}
