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
import org.usfirst.frc.team4585.robot.harve.view.smart.dashboard.HarvIO;

public class HarvController {
	private HarvOperationController operationController;
	private HarvAutoController autonomousController;
	private HarvIO inputOutput;
	private HarvDrive drive;
	private HarvInput driveInput;
	private HarvInput weaponsInput;
	private SmartDashboard dashboard;
	private Gyroscope gyro;
	private HRLV_MaxSonar_EZ_Analog sonar;
	private Shooter shooter;
	private Climber climber;
	private Loader loader;
	
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
		driveInput = new FlightStick(0);
		weaponsInput = new XBoxGamePad(1);
		dashboard = new SmartDashboard();
		gyro = new Gyroscope();
		sonar = new HRLV_MaxSonar_EZ_Analog(0, 20480);
		shooter = new Shooter(3);
		climber = new Climber(2);
		loader = new Loader(4);
		time = 0;
		inputOutput = new HarvIO();
		autonomousController = new HarvAutoController(drive,shooter,loader,climber);
		operationController= new HarvOperationController(drive,shooter,loader,climber,driveInput,weaponsInput,gyro);
		operationController.setWeaponsButtons(XBoxButtons.A.getValue(), XBoxButtons.X.getValue(), XBoxButtons.B.getValue(), XBoxButtons.Y.getValue());
		operationController.setDriveButtons(1);
	}
	
	private void showInformation() {
		
	}
//	
//	private void updateClimber(){
//		if(driveInput.buttonIsPressed(2)){
//			SmartDashboard.putString("Buttons pressed", "yes");
//			if(isFastClimber){
//				changeIsFastClimber = false;
//			}else{
//				changeIsFastClimber = true;
//			}
//		}else{
//			SmartDashboard.putString("Buttons pressed", "no");
//			isFastClimber = changeIsFastClimber;
//		}
//		
//		SmartDashboard.putBoolean("isChangingFast", changeIsFastClimber);
//		SmartDashboard.putBoolean("isFast", isFastClimber);
//		
//		if(driveInput.buttonIsPressed(5)){
//			if(isFastClimber){
//				climber.setSpeed(.95);
//			}else{
//				climber.setSpeed(.5);
//			}
//			climber.setClimb(true);
//		}else{
//			climber.setClimb(false);
//		}
//	}
//	
//	private void updateShooter(){
//		
//		loader.setIsLoading(driveInput.buttonIsPressed(1));
//		
//		SmartDashboard.putNumber("shooter speed", shooter.getRPS());
//		shooter.update();
//		loader.update();
//	}

	public void robotInit() {
		time = System.currentTimeMillis();
		operationController.init();
		autonomousController.init();
		inputOutput.init();
		driveInput.makeRound(true);
	}

	public void autonomous() {
		
	}

	public void operatorControl() {
		if (System.currentTimeMillis() >= time + millisPerIteration) {
			operationController.update();
//			input.update();
//			magY = input.getAxis(Axis.Y);
//			magRot = input.getAxis(Axis.Z);
//			
//			updateClimber();
//			updateShooter();
//			
//			showInformation();
//			drive.update(-magY, magRot);
//
			showInformation();
			time = System.currentTimeMillis();
		}
	}
	
	
	public void test() {
	}
	

}
