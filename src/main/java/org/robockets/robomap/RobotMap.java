package org.robockets.robomap;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {
    private static final boolean isWestCoast = true;
    public static DifferentialDrive getDrive() {
        if (!isWestCoast) {
            return TalonRoboMap.getDrive();
        } else {
            return CanSparkMaxRoboMap.getDrive();
        }
    }
}
