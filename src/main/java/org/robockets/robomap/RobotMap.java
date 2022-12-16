package org.robockets.robomap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

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
