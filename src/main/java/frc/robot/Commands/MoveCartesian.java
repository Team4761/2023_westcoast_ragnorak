package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.DrivetrainCAN;

import frc.robot.PID;

public class MoveCartesian extends CommandBase {

    private final DrivetrainCAN m_drivetrain;

    private double X, Y, ROT;

    private double moveNeeded, rotNeeded;
    
    public double setPointL, setPointR;

    private double speedL, speedR;

    private PID drivePID = new PID(0.4, 0.01, 0.005);

    public int phase = 0;

    private boolean finished = false;

    // turn in direction of coord, move there, rotate to rot relative to origional rotation
    // 0 rot = robot is parallel to how it started
    // x y units in revolutions for now, rot in degrees, goes clockwise
    public MoveCartesian (DrivetrainCAN drivetrain, double x, double y, double rot) {
        m_drivetrain = drivetrain;
        addRequirements(drivetrain);
        X=x;
        Y=y;
        ROT=rot;

        // in revs
        rotNeeded = Math.atan2(x, y)*0.159155*m_drivetrain.drivetrainWidth/m_drivetrain.wheelDiameter;
        setPointL = m_drivetrain.getLeftRevolutions() + rotNeeded;
        setPointR = m_drivetrain.getRightRevolutions() - rotNeeded;
    }

    @Override
    public void execute() {
        switch (phase) {
            case 0:
                // do the if with the speeds and error
                // check if pid error < certain amount and velocity is under a certain amount to prevent overshoot
                // calculate needed setpts in revs
                speedL = drivePID.PIDGo(setPointL, m_drivetrain.getLeftRevolutions());
                speedR = drivePID.PIDGo(setPointR, m_drivetrain.getRightRevolutions());
                m_drivetrain.driveTank(speedL, speedR);
                
                if (Math.abs(speedL) < 0.02 && Math.abs(speedR) < 0.02 && Math.abs(m_drivetrain.getLeftVelocity())<0.02 && Math.abs(m_drivetrain.getRightVelocity())<0.02) {
                    moveNeeded = Math.sqrt(X*X+Y*Y);
                    setPointL = m_drivetrain.getLeftRevolutions() + moveNeeded;
                    setPointR = m_drivetrain.getRightRevolutions() + moveNeeded;
                    phase++;
                }
                break;
            case 1:
                // calculate needed setpts in revs
                // no arcade drive cause arcade drive's broke
                speedL = drivePID.PIDGo(setPointL, m_drivetrain.getLeftRevolutions());
                speedR = drivePID.PIDGo(setPointR, m_drivetrain.getRightRevolutions());
                m_drivetrain.driveTank(speedL, speedR);

                if (Math.abs(speedL) < 0.02 && Math.abs(speedR) < 0.02 && Math.abs(m_drivetrain.getLeftVelocity())<0.02 && Math.abs(m_drivetrain.getRightVelocity())<0.02) {       
                    rotNeeded = -rotNeeded+ROT*0.00277777777*m_drivetrain.drivetrainWidth/m_drivetrain.wheelDiameter;
                    setPointL = m_drivetrain.getLeftRevolutions() + rotNeeded;
                    setPointR = m_drivetrain.getRightRevolutions() - rotNeeded;
                    phase++;
                }
            break;
            case 2:
                // calculate needed setpts in revs
                // no arcade drive cause arcade drive's broke
                speedL = drivePID.PIDGo(setPointL, m_drivetrain.getLeftRevolutions());
                speedR = drivePID.PIDGo(setPointR, m_drivetrain.getRightRevolutions());
                m_drivetrain.driveTank(speedL, speedR);  
                if (Math.abs(speedL) < 0.02 && Math.abs(speedR) < 0.02 && Math.abs(m_drivetrain.getLeftVelocity())<0.02 && Math.abs(m_drivetrain.getRightVelocity())<0.02) {
                    phase++;
                    finished = true;
                }
            break;
        }
    }

    @Override
    public void end(boolean interrupted) {
        phase = 3;
        m_drivetrain.driveTank(0, 0);
        finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}