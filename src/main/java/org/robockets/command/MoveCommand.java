package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.Robot;

public class MoveCommand extends CommandBase {
    private double speed;
    private double rotation;

    private static double TIMEOUT_MAX = 10;
    private static double SPEED_LIMIT = .8;

    public MoveCommand(double speed, double rotation) {
        this.speed = speed;
        this.rotation = rotation;
    }

    @Override
    public void execute() {
        Robot.impl.getDrive().arcadeDrive(speed,rotation);
    }
}
