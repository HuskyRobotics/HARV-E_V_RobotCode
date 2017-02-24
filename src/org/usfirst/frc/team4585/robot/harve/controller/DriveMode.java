package org.usfirst.frc.team4585.robot.harve.controller;

public enum DriveMode {
	DefaultDrive(2),
	ArcadeDrive(3),
	TankDrive(4);
	int axis;
	DriveMode(int axis){
		this.axis = axis;
	}
	
	public int getAxis(){
		return this.axis;
	}
}
