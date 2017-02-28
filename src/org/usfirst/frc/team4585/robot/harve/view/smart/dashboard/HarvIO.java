package org.usfirst.frc.team4585.robot.harve.view.smart.dashboard;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.tables.ITable;
import java.util.ArrayList;

public class HarvIO {
	private ITable toggleTable;
	private Preferences prefs;
	private SendableChooser toggleChooser;
	private ArrayList<Toggle> toggles;
	private SmartDashboard smartDashboard;
	
	public HarvIO(){
		smartDashboard = new SmartDashboard();
		toggleChooser = new SendableChooser();
		toggles = new ArrayList<Toggle>();
	}
	
	public void init(){
		prefs = Preferences.getInstance();
	}
	
	public void putInformation(String key,Boolean value){
		SmartDashboard.putBoolean(key,value);
	}
	
	public void putInformation(String key,double value){
		SmartDashboard.putNumber(key, (double) value);
	}
	
	public void putInformation(String key, String value){
		SmartDashboard.putString(key, value);
	}
	
	public void addToggle(String name){
		Toggle toggle = new Toggle(name);
		toggles.add(toggle);
		prefs.putBoolean(name, false);
		toggle.setValue(prefs.getBoolean(name, false));
	}
	
	public boolean getToggle(String key){
		boolean returnValue = false;
		for(Toggle toggle: toggles){
			if(toggle.getName().equals(key)){
				returnValue = toggle.getValue();
				break;
			}
		}
		return returnValue;
	}
	
	public void update(){
		for(Toggle toggle: toggles){
			toggle.setValue(prefs.getBoolean(toggle.getName(), false));
		}
	}
}
