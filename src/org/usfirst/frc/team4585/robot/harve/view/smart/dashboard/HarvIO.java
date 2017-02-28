package org.usfirst.frc.team4585.robot.harve.view.smart.dashboard;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import java.util.ArrayList;

public class HarvIO {
	private ITable toggleTable;
	private SendableChooser toggleChooser;
	private ArrayList<Boolean> toggles;
	private SmartDashboard smartDashboard;
	
	public HarvIO(){
		smartDashboard = new SmartDashboard();
		toggleChooser = new SendableChooser();
	}
	
	public void init(){
		toggleChooser.addDefault("default auto", new Boolean(false));
	}
	
	public void addTogle(String key){//adds a toggle with the key you give it
		Boolean toggle = new Boolean(key);
		toggles.add(toggle);
		toggleChooser.addDefault(key, toggle);
	}
	
	public boolean getTogleValue(String key){
		boolean toggleValue = false;
		for(Boolean toggle:toggles){
			if((Boolean)toggleChooser.getSelected().equals(toggle)){
				if(toggle)
					toggleValue = toggle.booleanValue();
			}
		}
		return toggleValue;
	}
	
	public void setTogleValue(String key){
		toggleChooser.getTable().putBoolean(key, false);
	}
	
	public void update(){
		
	}
}
