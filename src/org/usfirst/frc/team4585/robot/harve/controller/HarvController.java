package org.usfirst.frc.team4585.robot.harve.controller;

import edu.wpi.first.wpilibj.smartdashboard.*;
import org.usfirst.frc.team4585.robot.harve.model.*;
import org.usfirst.frc.team4585.robot.harve.model.drive.DefaultDrive;
import org.usfirst.frc.team4585.robot.harve.model.drive.HarvDrive;
import org.usfirst.frc.team4585.robot.harve.model.drive.MecanumDrive;
import org.usfirst.frc.team4585.robot.harve.model.sensors.Sensors;
import org.usfirst.frc.team4585.robot.harve.model.autonomous.*;
import org.usfirst.frc.team4585.robot.harve.view.*;

public class HarvController {
	private HarvDrive drive;
	private HarvInput input;
	private HarvAutoController autonomous;
	private SmartDashboard dashboard;
	private Sensors sensors;
	
	private final double millisPerIteration;
	private double magX, magY, magRot;
	private double time;
	//rotation variables

	public HarvController() { //default constructor
		input = new FlightStick(0);
		autonomous = new HarvAutoController();
		drive = new DefaultDrive(0,1);
		dashboard = new SmartDashboard();
		sensors = new Sensors();
		time = 0;
		millisPerIteration = 20;
		magX = 0;
		magY = 0;
		magRot = 0;
	}

//	private void augmentedDriveControlV2(){
//		final double A = 0.12;
//		final double D = 0.004;
//		double rotationValue = 0;
//		magX = input.getJoystickInput(Axis.X);
//		magY = input.getJoystickInput(Axis.Y);
//		rotLimit = 1-(Math.abs(magY));
//		angleDifference = sensors.getAngle() - intendedAngle;
//
//		rotationValue = Math.abs((angleDifference * D)) * maxRotationPerIteration;
//		if(input.getJoystickInput(Axis.Z) > 0 || input.getJoystickInput(Axis.Z) < 0){
//			intendedAngle = sensors.getAngle();
//			magRot = input.getJoystickInput(Axis.Z) * rotLimit;
//		}else{
//			if(angleDifference > 0){//positive
//				magRot = -(rotationValue + A) * rotLimit;
//			}
//			else if(angleDifference < 0){//negative
//				magRot = (rotationValue + A) * rotLimit;
//			}
//		}
//	}

	private void showInformation() {
		dashboard.putNumber("magRot value", magRot);
		dashboard.putNumber("magY value", magY);
		dashboard.putNumber("magX value", magX);
	}

	public void robotInit() {
		time = System.currentTimeMillis();
		input.makeRound(true);
		sensors.calibrateGyro();
	}

	public void autonomous() {
		if(System.currentTimeMillis() >= time + millisPerIteration){
			 
			time = System.currentTimeMillis();
		}
	}

	public void operatorControl() {
		
		if (System.currentTimeMillis() >= time + millisPerIteration) {
			input.update();
			magX = input.getInput(Axis.X);
			magY = input.getInput(Axis.Y);
			magRot = input.getInput(Axis.Z);
			drive.update(magX, magY, magRot);
			
			
			this.showInformation();
			time = System.currentTimeMillis();
		}
	}
	
	
	public void test() {
	}

}
