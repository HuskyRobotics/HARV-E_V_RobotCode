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
	
	private final double millisBetweenIterations=20;
	private double magX, magY, magRot;
	private double time;
	//rotation variables

	public HarvController() {
		input = new HarvInput(0);
		autonomous = new HarvAutoController();
		dashboard = new SmartDashboard();
		sensors = new Sensors();
		time = 0;
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
			//this.augmentedDriveControlV2();
			magY = this.input.getJoystickInput(Axis.Y);
			magRot = this.input.getJoystickInput(Axis.Z);
			sensors.updateBIAcceleration();
			this.showInformation();
			time = System.currentTimeMillis();
			drive.update(magY, magRot);
		}
	}
	
	
	public void test() {
	}

}
