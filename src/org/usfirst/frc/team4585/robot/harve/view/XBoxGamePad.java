package org.usfirst.frc.team4585.robot.harve.view;

import edu.wpi.first.wpilibj.Joystick;

public class XBoxGamePad extends HarvInput{
	Joystick xBoxController;
	
	double lMagX, lMagY, rMagX, rMagY;
	double lTrigger, rTrigger;
	
	public XBoxGamePad(){
		lMagY = 0;
		lMagX = 0;
		rMagY = 0;
		rMagX = 0;
		lTrigger = 0;
		rTrigger = 0;
	}
	
	public XBoxGamePad(int port){
		this();
		xBoxController = new Joystick(port);
	}
	
	@Override
	public double getInput(Axis axis) {
		double mag = 0;
		switch(axis){
		case LY:
			mag = lMagY;
			break;
		case LX:
			mag = lMagX;
			break;
		case RY:
			mag = rMagY;
			break;
		case RX:
			mag = rMagX;
			break;
		case LT:
			mag = lTrigger;
			break;
		case RT:
			mag = rTrigger;
			break;
		default: break;
		}
		return mag;
	}

	@Override
	public double getAxis(Axis axis) {
		getInput(axis);
		return 0;
	}

	@Override
	public boolean buttonIsPressed(int button) {
		return xBoxController.getRawButton(button);
	}
	
	public boolean buttonIsPressed(XBoxButtons button){
		boolean buttonPressed = false;
		switch(button){
		case A:
			buttonPressed = xBoxController.getRawButton(XBoxButtons.A.getValue());
			break;
		case B:
			buttonPressed = xBoxController.getRawButton(XBoxButtons.B.getValue());
			break;
		case X:
			buttonPressed = xBoxController.getRawButton(XBoxButtons.X.getValue());
			break;
		case Y:
			buttonPressed = xBoxController.getRawButton(XBoxButtons.Y.getValue());
			break;
		case LB:
			buttonPressed = xBoxController.getRawButton(XBoxButtons.LB.getValue());
			break;
		case RB:
			buttonPressed = xBoxController.getRawButton(XBoxButtons.RB.getValue());
			break;
		case SELECT:
			buttonPressed = xBoxController.getRawButton(XBoxButtons.SELECT.getValue());
			break;
		case START:
			buttonPressed = xBoxController.getRawButton(XBoxButtons.START.getValue());
			break;
		case LSTICK:
			buttonPressed = xBoxController.getRawButton(XBoxButtons.LSTICK.getValue());
			break;
		case RSTICK:
			buttonPressed = xBoxController.getRawButton(XBoxButtons.RSTICK.getValue());
			break;
		default: break;
		}
		return buttonPressed;
	}

	@Override
	public boolean isRound() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void update() {
		lMagY = xBoxController.getRawAxis(1);
		lMagX = xBoxController.getRawAxis(2);
		rMagY = xBoxController.getRawAxis(3);
		rMagX = xBoxController.getRawAxis(4);
		lTrigger = xBoxController.getRawAxis(5);
		rTrigger = xBoxController.getRawAxis(6);
	}
	
}
