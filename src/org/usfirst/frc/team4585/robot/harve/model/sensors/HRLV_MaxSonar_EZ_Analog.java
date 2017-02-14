package org.usfirst.frc.team4585.robot.harve.model.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class HRLV_MaxSonar_EZ_Analog{
	private AnalogInput input;
	int sampleBuffer;
	int millimetersPerVolt=976;//constant set by rangefinder
	double correctionCoeff = 1.05;
	int defaultSampleBuffer=20;

	HRLV_MaxSonar_EZ_Analog(int AIO_port, int sampleBuffer) {
		input = new AnalogInput(AIO_port);
		this.sampleBuffer = sampleBuffer;

		input.setAverageBits(sampleBuffer);
		input.setOversampleBits(sampleBuffer);

	}

	HRLV_MaxSonar_EZ_Analog(int AIO_port) {
		input = new AnalogInput(AIO_port);
		this.sampleBuffer = defaultSampleBuffer;

		input.setAverageBits(defaultSampleBuffer);
		input.setOversampleBits(defaultSampleBuffer);

	}

	// default=20, higher numbers are more accurate but have a slower polling
	// speed
	void setSampleBuffer(int buffer) {
		this.sampleBuffer = buffer;
		input.setAverageBits(buffer);
		input.setOversampleBits(buffer);
	}
	

	double getMillimeters() {
		return input.getAverageVoltage() * millimetersPerVolt * correctionCoeff;
	}

	double getInches() {
		return getMillimeters() * .03937;
	}

	double getCentimeters() {
		return getMillimeters() * 10;
	}
	double getVoltage(){
		return input.getAverageVoltage();
	}
	int getValue(){
		return input.getAverageValue();
	}

}
