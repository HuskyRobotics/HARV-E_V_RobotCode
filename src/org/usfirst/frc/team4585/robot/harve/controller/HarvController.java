package org.usfirst.frc.team4585.robot.harve.controller;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.*;
import org.usfirst.frc.team4585.robot.harve.model.*;
import org.usfirst.frc.team4585.robot.harve.model.drive.DefaultDrive;
import org.usfirst.frc.team4585.robot.harve.model.drive.HarvDrive;
import org.usfirst.frc.team4585.robot.harve.model.drive.MecanumDrive;
import org.usfirst.frc.team4585.robot.harve.model.sensors.HRLV_MaxSonar_EZ_Analog;
import org.usfirst.frc.team4585.robot.harve.model.sensors.Gyroscope;
import org.usfirst.frc.team4585.robot.harve.model.autonomous.*;
import org.usfirst.frc.team4585.robot.harve.view.*;

public class HarvController {
	HarvDrive drive;
	FlightStick input;
	HarvAutoController autonomous;
	SmartDashboard dashboard;
	Gyroscope sensors;
	HRLV_MaxSonar_EZ_Analog sonar;
	Shooter shooter;
	Climber climber;
	Talon spinner;
	
	private double magX, magY, magRot;
	private double rotLimit;
	private double time;
	private double millisPerIteration;
	
	private boolean isFastClimber;
	boolean changeIsFastClimber;
	
	private boolean isShooting;
	private boolean changeShooting;

	//rotation variables
	
	
	public HarvController() {
		isShooting = false;
		millisPerIteration = 20;
		drive = new DefaultDrive(0, 1);
		input = new FlightStick(0);
		autonomous = new HarvAutoController();
		dashboard = new SmartDashboard();
		sensors = new Gyroscope();
		sonar = new HRLV_MaxSonar_EZ_Analog(0, 20480);
		shooter = new Shooter(3);//port three is open
		climber = new Climber(2);
		spinner = new Talon(4);
		time = 0;
	}
	
	private void showInformation() {
		dashboard.putNumber("Rangefinder", sonar.getInches());
	}
	
	private void updateClimber(){
		
		if(input.buttonIsPressed(2)){
			SmartDashboard.putString("Buttons pressed", "yes");
			if(isFastClimber){
				changeIsFastClimber = false;
			}else{
				changeIsFastClimber = true;
			}
		}else{
			SmartDashboard.putString("Buttons pressed", "no");
			isFastClimber = changeIsFastClimber;
		}
		
		SmartDashboard.putBoolean("isChangingFast", changeIsFastClimber);
		SmartDashboard.putBoolean("isFast", isFastClimber);
		
		if(input.buttonIsPressed(5)){
			if(isFastClimber){
				climber.setSpeed(.95);
			}else{
				climber.setSpeed(0.5);
			}
			climber.setClimb(true);
		}else{
			climber.setClimb(false);
		}
	}
	
	private void updateShooter(){
		
		if(input.buttonIsPressed(6)){
			SmartDashboard.putString("shoot Buttons pressed", "yes");
			if(isShooting){
				changeShooting = false;
			}else{
				changeShooting = true;
			}
		}else{
			SmartDashboard.putString("shoot Buttons pressed", "no");
			isShooting = changeShooting;
		}
		
		SmartDashboard.putBoolean("isShooting", isShooting);
		
		if(isShooting){
			shooter.setWheelMagnitude(1);
		}else{
			shooter.setWheelMagnitude(0);
		}
		
		SmartDashboard.putNumber("shooter speed", shooter.getWheelSpeed());
		shooter.update();
	}

	public void robotInit() {
		time = System.currentTimeMillis();
		input.makeRound(true);
		sensors.calibrateGyro();
	}

	public void autonomous() {
		autonomous.start();
	}

	public void operatorControl() {
		
		if (System.currentTimeMillis() >= time + millisPerIteration) {
			input.update();
			magY = input.getAxis(Axis.Y);
			magRot = input.getAxis(Axis.Z);
			
			updateClimber();
			updateShooter();
			
			showInformation();
			drive.update(-magY, magRot);

			time = System.currentTimeMillis();
		}
	}
	
	
	public void test() {
	}
	

}
