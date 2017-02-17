package org.usfirst.frc.team4585.robot.harve.controller;

import org.usfirst.frc.team4585.robot.harve.model.*;
import org.usfirst.frc.team4585.robot.harve.model.drive.*;
import org.usfirst.frc.team4585.robot.harve.view.*;
import org.usfirst.frc.team4585.robot.harve.model.Shooter;
import org.usfirst.frc.team4585.robot.harve.model.drive.HarvDrive;
import org.usfirst.frc.team4585.robot.harve.model.sensors.HRLV_MaxSonar_EZ_Analog;
import org.usfirst.frc.team4585.robot.harve.model.sensors.Sensors;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HarvOperationController {
	HarvDrive drive;
	HarvInput input;
	SmartDashboard dashboard;
	Sensors sensors;
	HRLV_MaxSonar_EZ_Analog sonar;
	Shooter shooter;
	
	private double magX, magY, magRot;
	private double rotLimit;
	private double time;
	private double millisPerIteration;
	
	public void HarvOperationController(){
		drive = new DefaultDrive(0,1);
		input = new FlightStick(0);
		dashboard = new SmartDashboard();
		sonar = new HRLV_MaxSonar_EZ_Analog(0);
		shooter = new Shooter(2);
		
		magX = 0;
		magY = 0;
		magRot = 0;
		rotLimit = 0;
		time = 0;
		millisPerIteration = 0;
	}
	
	public void start(){
		input.update();
		driveControll();
		weaponsControll();
	}
	
	private void driveControll(){
		this.augmentedDriveControll();
	}
	
	private void weaponsControll(){
		if(input.buttonIsPressed(1)){
			
		}
	}
	
	private void augmentedDriveControll(){
		magX = input.getInput(Axis.X);
		magY = input.getAxis(Axis.Y);
		magRot = input.getAxis(Axis.Z);
		if(input.getAxis(Axis.Z) > 0 || input.getAxis(Axis.Z) < 0){
			sensors.reset();
		}
		if(!(input.getAxis(Axis.Z) > 0 || input.getAxis(Axis.Z) < 0)){
			if(sensors.getAngle() > 1){
				magRot = -(sensors.getAngle() + 0.11) * 0.011;
			}else if(sensors.getAngle() < 1){
				magRot = -(sensors.getAngle()+0.11 )* 0.011;
			}
		}
	}

}
