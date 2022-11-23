package org.robockets;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class RobotMap {
    // Drivetrain motors. The Talons already have encoders inside them
    public static WPI_TalonFX front_left = new WPI_TalonFX(0);
    public static WPI_TalonFX front_right = new WPI_TalonFX(1);
    public static WPI_TalonFX back_left = new WPI_TalonFX(2);
    public static WPI_TalonFX back_right = new WPI_TalonFX(3);

    // Drivetrain
    public static final MotorControllerGroup m_leftMotors = new MotorControllerGroup(front_left,back_left);
    public static final MotorControllerGroup m_rightMotors = new MotorControllerGroup(front_right,back_right);

    public static final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors,m_rightMotors);

    //GyroScope
    public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
}
