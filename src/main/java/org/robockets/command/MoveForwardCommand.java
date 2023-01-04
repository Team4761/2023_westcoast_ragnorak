package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.Robot;

public class MoveForwardCommand extends CommandBase {
    private double speed;

    public MoveForwardCommand(double speed) {
        this.speed = speed;
    }

    @Override
    public void execute() {
        Robot.impl.getDrive().arcadeDrive(speed,0);
    }
}
