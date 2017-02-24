package org.usfirst.frc.team4585.robot.harve.view.smart.dashboard;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

public class HarvIO {
	private ITable toggleTable;
	private SendableChooser<Boolean> toggles;
	private SmartDashboard smartDashboard;
	
	public HarvIO(){
		smartDashboard = new SmartDashboard();
		toggles = new SendableChooser<Boolean>();
	}
	
	public void init(){
		toggles.initTable(toggleTable);
		toggles.addDefault("toggles", Boolean.FALSE);
	}
	
	public void addTogle(String key){//adds a toggle with the key you give it
		toggles.getTable().putBoolean(key, false);
	}
	
	public boolean getTogleValue(String key){
		return toggles.getTable().getBoolean(key, false);
	}
	
	public void setTogleValue(String key){
		toggles.getTable().putBoolean(key, false);
	}
}
