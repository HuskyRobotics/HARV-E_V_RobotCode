package org.usfirst.frc.team4585.robot.harve.view.smart.dashboard;

public class Toggle{
	private Boolean value;
	private String name;
	private Toggle(){
		value = false;
		name = "";
	}
	
	public Toggle(String name){
		this();
		this.name = name;
	}
	
	public Toggle(Boolean value){
		this();
		this.value = value;
	}
	
	public Toggle(String name, Boolean value){
		this.name = name;
		this.value = value;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setValue(Boolean value){
		this.value = value;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean getValue(){
		return this.value;
	}
}
