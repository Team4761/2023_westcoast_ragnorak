package org.robockets.impl;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public abstract class RobotImpl {
    public abstract DifferentialDrive getDrive();
    public abstract double calcTimeToMoveFeet(double feet, double speed);
    public abstract double calcTimeToRotate(double degrees, double speed);
}
