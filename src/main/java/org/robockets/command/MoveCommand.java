package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import org.robockets.robomap.RobotMap;

public class MoveCommand extends CommandBase {
    private double speed;
    private double rotation;

    private static double TIMEOUT_MAX = 10;
    private static double SPEED_LIMIT = .8;

    public MoveCommand(double speed, double rotation) {
        if (speed > SPEED_LIMIT || speed < -SPEED_LIMIT) {
            throw new IllegalArgumentException("Speeding! " + speed);
        }
        this.speed = speed;
        this.rotation = rotation;
    }

    @Override
    public ParallelRaceGroup withTimeout(double timeout) {
        if (timeout >= TIMEOUT_MAX || timeout < 0.0) {
            throw new IllegalArgumentException("Maximum timeout is " + TIMEOUT_MAX);
        }
        return super.withTimeout(timeout);
    }

    @Override
    public void execute() {
        RobotMap.getDrive().arcadeDrive(speed,rotation);
    }
}
