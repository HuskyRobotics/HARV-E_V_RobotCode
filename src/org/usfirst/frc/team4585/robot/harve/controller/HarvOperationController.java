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
	private Gyroscope gyro;

	private Axis driveRotation, driveForward, driveSidways;
	private Axis driveForwardLeft, driveForwardRight;
	private Axis driveSidwaysLeft, driveSidwaysRight;
	private DriveMode driveMode;

	private double magX, magY, magRot;
	private double distanceToTarget;
	private double slowClimbSpeed, fastClimbSpeed;

	private long millisPerIteration;
	private long time;
	private int weaponsButtonShoot;
	private int weaponsButtonLoad;
	private int weaponsButtonClimb;
	private int weaponsButtonChangeClimbSpeed;
	private int driveButtonAllign;

	private boolean isShooting, changeIsShooting;
	private boolean isClimbing, changeIsClimbing;
	private boolean isFastClimbing, changeIsFastClimbing;
	private boolean isLoading, changeIsLoading;
	private boolean isAligning, changeIsAligning;

	private HarvOperationController() {
		driveRotation = Axis.Z;
		driveForward = Axis.Y;
		driveSidways = Axis.Z;
		driveForwardLeft = Axis.LY;
		driveForwardRight = Axis.RY;
		driveSidwaysLeft = Axis.LX;
		driveSidwaysLeft = Axis.RX;
		driveMode = DriveMode.DefaultDrive;
		magX = 0;
		magY = 0;
		magRot = 0;
		distanceToTarget = 0;
		millisPerIteration = 20;
		time = 0;
		isShooting = false;
		changeIsShooting = false;
		isClimbing = false;
		changeIsClimbing = false;
		isFastClimbing = false;
		changeIsFastClimbing = false;
		isLoading = false;
		changeIsLoading = false;
	}

	public HarvOperationController(HarvDrive drive, Shooter shooter, Loader loader, Climber climber,HarvInput driveInput, HarvInput weaponsInput, Gyroscope gyro) {
		this();
		this.drive = drive;
		this.shooter = shooter;
		this.loader = loader;
		this.climber = climber;
		this.driveInput = driveInput;
		this.weaponsInput = weaponsInput;
		this.gyro = gyro;
	}

	public void start() {
		if(time + millisPerIteration < System.currentTimeMillis()){
			update();
			time = System.currentTimeMillis();
		}
	}

	public void init() {
		loader.setRPS(2);
	}

	public void update() {
		this.updateWeapons();
		this.updateDrive();
	}

	private void updateWeapons() {
		this.updateShooter();
		this.updateLoader();
		this.updateClimber();
	}

	private void updateDrive() {
		if(this.driveInput.buttonIsPressed(this.driveButtonAllign)){
			if(isAligning){
				changeIsAligning = false;
			}else{
				changeIsAligning = true;
			}
		}else{
			isAligning = changeIsAligning;
		}
		if(isAligning){
			updateAlignment();
		}else{
			augmentedDrive();
		}
		
		drive.update(magX, magY, magRot);
	}

	private void augmentedDrive(){
		if(this.driveMode == DriveMode.ArcadeDrive){
			this.arcadeDrive();
		}else if(this.driveMode == DriveMode.TankDrive){
			this.tankDrive();
		}else{
			this.defaultDrive();
		}
		if(driveInput.getInput(driveRotation) > 0 || driveInput.getInput(driveRotation) < 0){
			gyro.reset();
		}else{
			magRot = gyro.getAngle()*0.0360 + Math.copySign(0.11, gyro.getAngle());
		}
	}
	
	private void defaultDrive(){
		magY = driveInput.getInput(this.driveForward);
		magRot = driveInput.getInput(this.driveRotation);
	}
	
	private void arcadeDrive(){
		magY = driveInput.getInput(driveForward);
		magX = driveInput.getInput(driveSidways);
		magRot = driveInput.getInput(driveRotation);
	}
	
	private void tankDrive(){
		magY = (driveInput.getInput(driveForwardLeft)+ driveInput.getInput(driveForwardRight))/2;
		magRot = (driveInput.getInput(driveForwardLeft)/2) - (driveInput.getInput(driveForwardRight)/2);
		magX = (driveInput.getInput(driveSidwaysLeft)+driveInput.getInput(driveSidwaysRight))/2;
	}
	
	private void updateAlignment(){
		
		gyro.reset();
	}

	private void updateShooter() {
		if (this.weaponsInput.buttonIsPressed(this.weaponsButtonShoot)) {
			if (isShooting) {
				changeIsShooting = false;
			} else {
				changeIsShooting = true;
			}
		} else {
			isShooting = changeIsShooting;
		}
		if (isShooting) {
			shooter.setIsShooting(true);
		} else {
			shooter.setIsShooting(false);
		}
		shooter.setDistance(distanceToTarget);
		shooter.update();
	}

	private void updateLoader() {
		if (this.weaponsInput.buttonIsPressed(weaponsButtonLoad)) {
			if (isLoading) {
				changeIsLoading = false;
			} else {
				changeIsLoading = true;
			}
		} else {
			isLoading = changeIsLoading;
		}

		if (isLoading && shooter.getIsReady()) {
			loader.setIsLoading(true);
		}
	}

	private void updateClimber() {
		if (this.weaponsInput.buttonIsPressed(this.weaponsButtonClimb)) {
			if (isClimbing) {
				changeIsClimbing = false;
			} else {
				changeIsClimbing = true;
			}
		} else {
			isClimbing = changeIsClimbing;
		}
		if (this.weaponsInput.buttonIsPressed(this.weaponsButtonChangeClimbSpeed)) {
			if (isFastClimbing) {
				changeIsFastClimbing = false;
			} else {
				changeIsFastClimbing = true;
			}
		} else {
			isFastClimbing = changeIsFastClimbing;
		}

		if (isClimbing) {
			climber.setClimb(true);
			if (isFastClimbing) {
				climber.setSpeed(this.fastClimbSpeed);
			} else {
				climber.setSpeed(this.slowClimbSpeed);
			}
		} else {
			climber.setClimb(false);
		}
		climber.update();
	}

	public void setWeaponsButtons(int shoot, int load, int climb, int changeClimbSpeed) {
		this.weaponsButtonShoot = shoot;
		this.weaponsButtonLoad = climb;
		this.weaponsButtonClimb = climb;
		this.weaponsButtonChangeClimbSpeed = changeClimbSpeed;
	}

	public void setWeaponsAxis() {

	}

	public void setDriveButtons(int allign) {
		this.driveButtonAllign = allign;
	}

	public void setDriveAxis(Axis rotation, Axis forward, Axis sidways) {
		this.driveRotation = rotation;
		this.driveForward = forward;
		this.driveSidways = sidways;
	}

	public void setDriveAxis(Axis forwardLeft, Axis forwardRight, Axis sidwaysLeft, Axis sidwaysRight) {
		this.driveForwardLeft = forwardLeft;
		this.driveForwardRight = forwardRight;
		this.driveSidwaysLeft = sidwaysLeft;
		this.driveSidwaysRight = sidwaysRight;
	}
	
	public void setMillisPerIteration(int millis){
		this.millisPerIteration = millis;
	}
}
