package org.usfirst.frc.team4585.robot.harve.model.sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;

public class Encoder {//this is a class that is used along with cimcoders
	private int portA , portB;
	private double correctionCoefficiant;
	private double rotationsPerSecond;
	private boolean isDigital;
	private DigitalInput digitalInputA;
	private DigitalInput digitalInputB;
	private AnalogInput analogInputA;
	private AnalogInput analogInputB;
	
	
	public Encoder(){// default constructor
		correctionCoefficiant = 1;
		rotationsPerSecond = 0;
		isDigital = false;
		digitalInputA = null;
		digitalInputB = null;
		analogInputA  = null;
		analogInputB = null;
	}
	
	public Encoder(int port){
		this();
		portA = port;
		portB = port;
		digitalInputA = new DigitalInput(port);
		analogInputA = new AnalogInput(port);
	}
	
	public Encoder(int portA, int portB){
		this();
		this.portA = portA;
		this.portB = portB;
		digitalInputA = new DigitalInput(portA);
		digitalInputB = new DigitalInput(portB);
		analogInputA = new AnalogInput(portA);
		analogInputB = new AnalogInput(portB);
	}
	
	private void updateGPIOPorts(){
		if(digitalInputA.getChannel() != portA)
			digitalInputA = new DigitalInput(portA);
		if(digitalInputB.getChannel() != portB)
			digitalInputB = new DigitalInput(portB);
		if(analogInputA.getChannel() != portA)
			analogInputA = new AnalogInput(portA);
		if(analogInputB.getChannel() != portB)
			analogInputB = new AnalogInput(portB);
		
	}
	
	private void updateAnalogInput(){
		
	}
	
	private void updateDigitalInput(){
		
	}
	
	public void update(){//updates input receaved from encoders
		updateGPIOPorts();
		if(isDigital)
			updateDigitalInput();
		else
			updateAnalogInput();
	}
	
	public void setPortA(int portA){
		this.portA = portA;
	}
	
	public void setPortB(int portB){
		this.portB = portB;
	}
	
	public int getPortA(){
		return this.portA;
	}
	
	public int getPortB(){
		return this.portB;
	}
	
	public double getRotationsPerSecond(){
		return this.rotationsPerSecond;
	}
	
	public double getCorrectionCoefficiant(){
		return this.correctionCoefficiant;
	}
	
	public void setCorrectionCoefficiant(double correctionCoefficiant){
		this.correctionCoefficiant = correctionCoefficiant;
	}
	
	public boolean getIsDigital(){
		return this.isDigital;
	}
	
	public void setIsDigital(boolean isDigital){
		this.isDigital = isDigital;
	}
}
