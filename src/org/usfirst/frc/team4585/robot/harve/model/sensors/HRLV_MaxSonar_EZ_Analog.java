package org.usfirst.frc.team4585.robot.harve.model.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class HRLV_MaxSonar_EZ_Analog{
	private AnalogInput input;
	int sampleBuffer;
	int millimetersPerVolt=976;//constant set by rangefinder
	int defaultSampleBuffer=20;

	void HRLV_MaxSonar_EZ_Analog(int AIO_port, int sampleBuffer) {
		input = new AnalogInput(AIO_port);
		this.sampleBuffer = sampleBuffer;

		input.setAverageBits(sampleBuffer);
		input.setOversampleBits(sampleBuffer);

	}

	void HRLV_MaxSonar_EZ_Analog(int AIO_port) {
		input = new AnalogInput(AIO_port);
		this.sampleBuffer = defaultSampleBuffer;

		input.setAverageBits(defaultSampleBuffer);
		input.setOversampleBits(defaultSampleBuffer);

	}

	// default=1, higher numbers are more accurate but have a slower polling
	// speed
	void setSampleBuffer(int buffer) {
		this.sampleBuffer = buffer;
		input.setAverageBits(buffer);
		input.setOversampleBits(buffer);
	}
	

	int getMillimeters() {
		return (int) input.getAverageVoltage() * millimetersPerVolt;
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
