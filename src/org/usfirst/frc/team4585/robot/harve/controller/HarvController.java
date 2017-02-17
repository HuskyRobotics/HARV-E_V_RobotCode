package org.usfirst.frc.team4585.robot.harve.controller;

import edu.wpi.first.wpilibj.smartdashboard.*;
import org.usfirst.frc.team4585.robot.harve.model.*;
import org.usfirst.frc.team4585.robot.harve.model.drive.DefaultDrive;
import org.usfirst.frc.team4585.robot.harve.model.drive.HarvDrive;
import org.usfirst.frc.team4585.robot.harve.model.drive.MecanumDrive;
import org.usfirst.frc.team4585.robot.harve.model.sensors.HRLV_MaxSonar_EZ_Analog;
import org.usfirst.frc.team4585.robot.harve.model.sensors.Sensors;
import org.usfirst.frc.team4585.robot.harve.model.autonomous.*;
import org.usfirst.frc.team4585.robot.harve.view.*;

public class HarvController {
	HarvDrive drive;
	FlightStick input;
	HarvAutoController autonomous;
	SmartDashboard dashboard;
	Sensors sensors;
	HRLV_MaxSonar_EZ_Analog sonar;
	Shooter shooter;
	Climber climber;
	
	private double magX, magY, magRot;
	private double rotLimit;
	private double time;
	private double millisPerIteration;
	
	private boolean isFastClimber;
	//rotation variables
	
	
	public HarvController() {
		millisPerIteration = 20;
		drive = new DefaultDrive(0, 1);
		input = new FlightStick(0);
		autonomous = new HarvAutoController();
		dashboard = new SmartDashboard();
		sensors = new Sensors();
		sonar = new HRLV_MaxSonar_EZ_Analog(0, 20480);
		shooter = new Shooter(3);//port three is open
		climber = new Climber(2);
		time = 0;
	}
	
	private void showInformation() {
		dashboard.putNumber("Rangefinder", sonar.getInches());
	}
	
	private void updateClimber(){
		boolean changeIsFastClimber = false;
		
		if(input.buttonIsPressed(2)){
			if(isFastClimber){
				changeIsFastClimber = false;
			}else{
				changeIsFastClimber = true;
			}
		}else{
			isFastClimber = changeIsFastClimber;
		}
		
		if(input.getPOVHat(0)){
			if(isFastClimber){
				climber.setSpeed(1);
			}else{
				climber.setSpeed(0.5);
			}
			climber.setClimb(true);
		}
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
			
			showInformation();
			drive.update(magY, magRot);

			time = System.currentTimeMillis();
		}
	}
	
	
	public void test() {
	}
	

}
