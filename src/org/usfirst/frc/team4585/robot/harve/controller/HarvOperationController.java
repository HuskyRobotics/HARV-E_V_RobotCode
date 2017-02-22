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
	private HarvInput driveInput;
	private HarvInput weaponsInput;
	private SmartDashboard dashboard;
	private Gyroscope sensors;
	private HRLV_MaxSonar_EZ_Analog sonar;
	private Climber climber;
	private Shooter shooter;
	private Loader loader;
	
	private double magX, magY, magRot;
	private double rotLimit;
	private double time;
	private double millisPerIteration;
	private double distanceToGoal;
	private double xAimerIncorectness;
	
	private int weaponsLoaderToggle, weaponsShooterToggle, weaponsAutoAlign, weaponsClimberToggle, weaponsClimberSpeedToggle;//undefiend buttons incase of useing a different controller.
	private int driveXAxis, driveYAxis, driveZAxis;
	
	private boolean isFastClimber;
	private boolean changeIsFastClimber;
	
	private boolean isShooting;
	private boolean changeIsShooting;
	
	private boolean align;
	private boolean setAlign;
	private boolean isAligned;
	private boolean canSeeTarget;
	
	public HarvOperationController(){//default constructor
		magX = 0;
		magY = 0;
		magRot = 0;
		rotLimit = 0;
		time = 0;
		millisPerIteration = 0;
		distanceToGoal = 0;
		xAimerIncorectness = 0;
		isFastClimber = false;
		changeIsFastClimber = false;
		isShooting = false;
		changeIsShooting = false;
		align = false;
		setAlign = false;
		isAligned = false;
		canSeeTarget = false;
	}
	
	public HarvOperationController(HarvDrive drive, Shooter shooter, Loader loader,Climber climber, HarvInput driveInput, HarvInput weaponsInput){
		this();
		this.drive = drive;
		this.shooter = shooter;
		this.loader = loader;
		this.climber = climber;
		this.driveInput = driveInput;
		this.weaponsInput = weaponsInput;
	}
	
	public void start(){
		driveControll();
		weaponsControll();
	}
	
	private void driveControll(){
		driveInput.update();
		this.augmentedDriveControll();
	}
	
	private void weaponsControll(){
		weaponsInput.update();
		if(!(align)){
			updateClimber();
			updateShooter();
			updateLoader();
			checkAutoAlign();
		}else{
			updateAutoAlign();
		}
	}
	
	private void updateShooter(){
		if(weaponsInput.buttonIsPressed(this.weaponsShooterToggle)){//toggles the wheel
			if(isShooting)
				changeIsShooting = false;
			else
				changeIsShooting = true;
		}
		else
			isShooting = changeIsShooting;
		
		if(isShooting){
			shooter.setRPS(75);
			shooter.setIsShooting(true);
		}else{
			shooter.setIsShooting(false);
		}
		
		shooter.update();
	}
	
	private void updateLoader(){
		
		if(weaponsInput.buttonIsPressed(this.weaponsLoaderToggle)){
			loader.setIsLoading(true);
		}else{
			loader.setIsLoading(false);
		}
		loader.setRPS(0.5);
		
		loader.update();
	}
	
	private void updateClimber(){
		if(weaponsInput.buttonIsPressed(this.weaponsClimberSpeedToggle)){//toggles the speed of the climber
			if(this.isFastClimber){
				this.changeIsFastClimber = false;
			}else 
				this.changeIsFastClimber = true;
		}else{
			isFastClimber = changeIsFastClimber;
		}
		
		if(isFastClimber)
			this.climber.setSpeed(1);
		else
			this.climber.setSpeed(0.5);
		
		if(weaponsInput.buttonIsPressed(this.weaponsClimberToggle))
			this.climber.setClimb(true);
		else
			this.climber.setClimb(false);
		climber.update();
	}
	
	private void checkAutoAlign(){
		if(this.weaponsInput.buttonIsPressed(this.weaponsAutoAlign)){
			if(align){
				setAlign = false;
			}else
				setAlign = true;
		}else{
			align = setAlign;
		}
	}
	
	private void updateAutoAlign(){
		if(canSeeTarget){
			magRot = 0.1 + xAimerIncorectness;
		}
	}
	
	private void augmentedDriveControll(){
		magX = driveInput.getInput(Axis.X);
		magY = driveInput.getAxis(Axis.Y);
		magRot = driveInput.getAxis(Axis.Z);
		if(driveInput.getAxis(Axis.Z) > 0 || driveInput.getAxis(Axis.Z) < 0){
			sensors.reset();
		}
		if(!(driveInput.getAxis(Axis.Z) > 0 || driveInput.getAxis(Axis.Z) < 0)){
			if(sensors.getAngle() > 1){
				magRot = -(sensors.getAngle() + 0.11) * 0.011;
			}else if(sensors.getAngle() < 1){
				magRot = -(sensors.getAngle()+0.11 )* 0.011;
			}
		}
	}

	public HarvDrive getDrive() {
		return drive;
	}

	public void setDrive(HarvDrive drive) {
		this.drive = drive;
	}

	public HarvInput getDriveInput() {
		return driveInput;
	}

	public void setDriveInput(HarvInput driveInput) {
		this.driveInput = driveInput;
	}

	public HarvInput getWeaponsInput() {
		return weaponsInput;
	}

	public void setWeaponsInput(HarvInput weaponsInput) {
		this.weaponsInput = weaponsInput;
	}

	public SmartDashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(SmartDashboard dashboard) {
		this.dashboard = dashboard;
	}

	public HRLV_MaxSonar_EZ_Analog getSonar() {
		return sonar;
	}

	public void setSonar(HRLV_MaxSonar_EZ_Analog sonar) {
		this.sonar = sonar;
	}

	public Climber getClimber() {
		return climber;
	}

	public void setClimber(Climber climber) {
		this.climber = climber;
	}

	public Shooter getShooter() {
		return shooter;
	}

	public void setShooter(Shooter shooter) {
		this.shooter = shooter;
	}

	public Loader getLoader() {
		return loader;
	}

	public void setLoader(Loader loader) {
		this.loader = loader;
	}

	public double getMagX() {
		return magX;
	}

	public void setMagX(double magX) {
		this.magX = magX;
	}

	public double getMagY() {
		return magY;
	}

	public void setMagY(double magY) {
		this.magY = magY;
	}

	public double getMagRot() {
		return magRot;
	}

	public void setMagRot(double magRot) {
		this.magRot = magRot;
	}

	public double getRotLimit() {
		return rotLimit;
	}

	public void setRotLimit(double rotLimit) {
		this.rotLimit = rotLimit;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getMillisPerIteration() {
		return millisPerIteration;
	}

	public void setMillisPerIteration(double millisPerIteration) {
		this.millisPerIteration = millisPerIteration;
	}

	public double getDistanceToGoal() {
		return distanceToGoal;
	}

	public void setDistanceToGoal(double distanceToGoal) {
		this.distanceToGoal = distanceToGoal;
	}

	public double getxAimerIncorectness() {
		return xAimerIncorectness;
	}

	public void setxAimerIncorectness(double xAimerIncorectness) {
		this.xAimerIncorectness = xAimerIncorectness;
	}

	public int getWeaponsTriger() {
		return weaponsLoaderToggle;
	}

	public void setWeaponsTriger(int weaponsTriger) {
		this.weaponsLoaderToggle = weaponsTriger;
	}

	public int getWeaponsToggle() {
		return weaponsShooterToggle;
	}

	public void setWeaponsToggle(int weaponsToggle) {
		this.weaponsShooterToggle = weaponsToggle;
	}

	public int getWeaponsAutoAlign() {
		return weaponsAutoAlign;
	}

	public void setWeaponsAutoAlign(int weaponsAutoAlign) {
		this.weaponsAutoAlign = weaponsAutoAlign;
	}

	public int getWeaponsClimberToggle() {
		return weaponsClimberToggle;
	}

	public void setWeaponsClimberToggle(int weaponsClimberToggle) {
		this.weaponsClimberToggle = weaponsClimberToggle;
	}

	public int getWeaponsClimberSpeedToggle() {
		return weaponsClimberSpeedToggle;
	}

	public void setWeaponsClimberSpeedToggle(int weaponsClimberSpeedToggle) {
		this.weaponsClimberSpeedToggle = weaponsClimberSpeedToggle;
	}

	public int getDriveXAxis() {
		return driveXAxis;
	}

	public void setDriveXAxis(int driveXAxis) {
		this.driveXAxis = driveXAxis;
	}

	public int getDriveYAxis() {
		return driveYAxis;
	}

	public void setDriveYAxis(int driveYAxis) {
		this.driveYAxis = driveYAxis;
	}

	public int getDriveZAxis() {
		return driveZAxis;
	}

	public void setDriveZAxis(int driveZAxis) {
		this.driveZAxis = driveZAxis;
	}

	public boolean isFastClimber() {
		return isFastClimber;
	}

	public void setFastClimber(boolean isFastClimber) {
		this.isFastClimber = isFastClimber;
	}

	public boolean isChangeIsFastClimber() {
		return changeIsFastClimber;
	}

	public void setChangeIsFastClimber(boolean changeIsFastClimber) {
		this.changeIsFastClimber = changeIsFastClimber;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public boolean isChangeIsShooting() {
		return changeIsShooting;
	}

	public void setChangeIsShooting(boolean changeIsShooting) {
		this.changeIsShooting = changeIsShooting;
	}

	public boolean isAlign() {
		return align;
	}

	public void setAlign(boolean align) {
		this.align = align;
	}

	public boolean isSetAlign() {
		return setAlign;
	}

	public void setSetAlign(boolean setAlign) {
		this.setAlign = setAlign;
	}

	public boolean isAligned() {
		return isAligned;
	}

	public void setAligned(boolean isAligned) {
		this.isAligned = isAligned;
	}

	public boolean isCanSeeTarget() {
		return canSeeTarget;
	}

	public void setCanSeeTarget(boolean canSeeTarget) {
		this.canSeeTarget = canSeeTarget;
	}
}
