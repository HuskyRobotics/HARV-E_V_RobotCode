package org.usfirst.frc.team4585.robot.harve.model.drive;

import edu.wpi.first.wpilibj.Spark;

public class MecanumDrive extends HarvDrive {
	private final int FRONTLEFT = 0, BACKLEFT = 1, FRONTRIGHT = 2, BACKRIGHT = 3;
	private final int FRONT = 0, LEFT = 1, RIGHT = 2;
	Spark[] wheelMotors;
	Spark frontLeft, backLeft, frontRight, backRight;
	Spark front, left, right;
	float wheelSize;
	double speed;

	private boolean hasThreeWheels;

	public MecanumDrive(int frontLeft, int backLeft, int frontRight, int backRight) {
		super();
		wheelMotors = new Spark[] { this.frontLeft = new Spark(frontLeft), this.backLeft = new Spark(backLeft),
				this.frontRight = new Spark(frontRight), this.backRight = new Spark(backRight) };
		wheelMotors[FRONTLEFT].setInverted(true);
		wheelMotors[BACKLEFT].setInverted(true);
		wheelSize = 0;
		speed = 0;
	}
	
	public MecanumDrive(int front,int left, int right){
		super();
		wheelMotors = new Spark[] { this.front = new Spark(front), this.left = new Spark(left), this.right = new Spark(right)};
		wheelSize = 0;
		speed = 0;
	}
	
	private void updateThreeWheels(double magX, double magY, double magRot){
		//covert magnitudes to angles
		double angle = 0;
		double mag = 0;
		mag = Math.sqrt(Math.pow(magX,2) + Math.pow(magY,2));
		angle = Math.atan2(magY, magX);
		this.wheelMotors[FRONT].set(mag*Math.sin(angle +180+45));
		this.wheelMotors[LEFT].set(mag*Math.sin(angle + 60+45));
		this.wheelMotors[RIGHT].set(mag*Math.sin(angle + 300 + 45));
	}

	@Override
	public void update(double magX, double magY, double magRot) {
			if (magX > 1)
				this.setMagX(1);
			else
				this.setMagX(magX);
			if (magY > 1)
				this.setMagY(1);
			else
				this.setMagY(magY);
			if (magRot > 1)
				this.setMagRot(1);
			else
				this.setMagRot(magRot);
			this.setMagRot(this.getMagRot() * this.getMaxMagRot());
			this.setMagX(this.getMagX() * this.getMaxMagX());
			this.setMagY(this.getMagY() * this.getMaxMagY());
		if (!hasThreeWheels) {
			this.wheelMotors[FRONTLEFT].set(this.getMagY() - this.getMagX() - this.getMagRot());
			this.wheelMotors[BACKLEFT].set(this.getMagY() + this.getMagX() - this.getMagRot());
			this.wheelMotors[FRONTRIGHT].set(this.getMagY() + this.getMagX() + this.getMagRot());
			this.wheelMotors[BACKRIGHT].set(this.getMagY() - this.getMagX() + this.getMagRot());
		}else{
			this.updateThreeWheels(magX,magY,magRot);
		}
	}

	public double getRotation(int wheel) {
		double wheelRotation = 0;

		return wheelRotation;
	}

	@Override
	public void update(double magY, double magRot) {
		this.update(0, magY, magRot);
	}

	@Override
	public double getWheelRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWheelSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}
}
