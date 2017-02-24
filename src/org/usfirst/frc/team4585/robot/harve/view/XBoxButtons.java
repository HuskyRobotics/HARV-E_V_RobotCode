package org.usfirst.frc.team4585.robot.harve.view;

public enum XBoxButtons {
	A(1),
	B(2),
	X(3),
	Y(4),
	LB(5),
	RB(6),
	SELECT(7),
	START(8),
	LSTICK(9),
	RSTICK(10)
	;
	private  int value;
	XBoxButtons(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
}
