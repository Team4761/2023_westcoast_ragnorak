package frc.robot.Subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class DrivetrainCAN extends SubsystemBase{
  public double wheelDiameter;
  public double drivetrainWidth;

  private CANSparkMax m_left = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANSparkMax m_right = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);

  private RelativeEncoder m_leftEncoder = m_left.getEncoder();
  private RelativeEncoder m_rightEncoder = m_right.getEncoder();

  // not currently using dont know why
  //private double m_deadband = 0.02;


  
  // singleton or something
  private final static DrivetrainCAN INSTANCE = new DrivetrainCAN(10, 65, 6);
  
  @SuppressWarnings("WeakerAccess")
  public static DrivetrainCAN getInstance() {
      return INSTANCE;
  }


  private DrivetrainCAN (double wheelD, double driveW, double gearRatio) {
    wheelDiameter = wheelD;
    drivetrainWidth = driveW;

    m_leftEncoder.setPosition(0);
    m_rightEncoder.setPosition(0);

    m_leftEncoder.setPositionConversionFactor(1/gearRatio);
    m_rightEncoder.setPositionConversionFactor(1/gearRatio);
  }

  double ApplyDeadband (double value, double deadband) {
    if (Math.abs(value) > deadband) {
        if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
        } else {
        return (value + deadband) / (1.0 - deadband);
        }
    } else {
        return 0.0;
    }
  }
  
  void Desaturate(double[] wheelSpeeds) {
    double maxMagnitude = Math.abs(wheelSpeeds[0]);
    for (int i = 1; i < wheelSpeeds.length; i++) {
      double temp = Math.abs(wheelSpeeds[i]);
      if (maxMagnitude < temp) {
        maxMagnitude = temp;
      }
    }
    if (maxMagnitude > 1.0) {
      for (int i = 0; i < wheelSpeeds.length; i++) {
        wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
      }
    }
  }

  public double getLeftRevolutions () {
    //do a getcountsperrev or something
    return m_leftEncoder.getPosition();
  }
  public double getRightRevolutions () {
    return m_rightEncoder.getPosition();
  }

  public double getLeftVelocity () {
    //do a getcountsperrev or something
    return m_leftEncoder.getVelocity();
  }
  public double getRightVelocity () {
    return m_rightEncoder.getVelocity();
  }

  // sped and rot input from -1.0 to 1.0
  //square inputs currently squares both which means diagonal is slow
  public void driveArcade (double xSpeed, double zRotation) {
      // not using diffdrive and stuff cause it won't work
      xSpeed = Math.max(-1.0, Math.min(1.0, xSpeed));
      zRotation = Math.max(-1.0, Math.min(1.0, zRotation));

      //squares inputs so moving slow is easier, can adjust input curve here later
      xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
      zRotation = Math.copySign(zRotation * zRotation, zRotation);
      

      // magic copied from differential drive
      double leftSpeed;
      double rightSpeed;

      double maxInput =
          Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

      // >= 0 cause signbit too hard
      if (xSpeed>=0) {
          // First quadrant, else second quadrant
          if (zRotation>=0) {
            leftSpeed = maxInput;
            rightSpeed = xSpeed - zRotation;
          } else {
            leftSpeed = xSpeed + zRotation;
            rightSpeed = maxInput;
          }
      } else {
          // Third quadrant, else fourth quadrant
          if (zRotation>=0) {
            leftSpeed = xSpeed + zRotation;
            rightSpeed = maxInput;
          } else {
            leftSpeed = maxInput;
            rightSpeed = xSpeed - zRotation;
          }
      }

      // Normalize the wheel speeds
      double maxMagnitude = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
      if (maxMagnitude > 1.0) {
          leftSpeed /= maxMagnitude;
          rightSpeed /= maxMagnitude;
      }


      m_left.set(leftSpeed);
      m_right.set(rightSpeed);
  }

  public void driveTank (double leftSpeed, double rightSpeed) {
      leftSpeed = Math.max(-1.0, Math.min(1.0, leftSpeed));
      rightSpeed = Math.max(-1.0, Math.min(1.0, rightSpeed));

      leftSpeed = Math.copySign(leftSpeed * leftSpeed, leftSpeed);
      rightSpeed = Math.copySign(rightSpeed * rightSpeed, rightSpeed);

      m_left.set(leftSpeed);
      m_right.set(rightSpeed);
  }
}
//add a sim thing that reports the speeds of motors to do calculations