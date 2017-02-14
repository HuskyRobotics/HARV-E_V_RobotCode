package org.usfirst.frc.team4585.robot.harve.view;

public abstract class HarvInput {//abstract class to confuse people who look at this code later
	protected int port;
	protected boolean isRound;
	protected boolean isXboxControler;
	
	public HarvInput(){//default constructor
		port = 0;//default HID input port
		isRound = true;//things being round is good
		isXboxControler = false;
	}
	
	public HarvInput(int port){
		this();
		this.port = port;
	}
	
	
	public void setPort(int port){
		this.port = port;
	}
	
	public int getPort(){
		return this.port;
	}
	
	public void makeRound(boolean isRound){
		this.isRound = isRound;
	}
	
	public boolean getIsRound(){
		return this.isRound;
	}
	
	public abstract double getInput(Axis axis);//same as getAxis
	public abstract double getAxis(Axis axis);
	public abstract boolean buttonIsPressed(int button);
	public abstract boolean isRound();//xbox has round input and some contollers need input to be converted round
	public abstract void update();
}
