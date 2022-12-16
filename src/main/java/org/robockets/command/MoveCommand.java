package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.robomap.RobotMap;

public class MoveCommand extends CommandBase {
    private double speed;
    private double rotation;

    public MoveCommand(double speed, double rotation) {
        this.speed = speed;
        this.rotation = rotation;
    }

    @Override
    public void execute() {
        RobotMap.getDrive().arcadeDrive(speed,rotation);
    }
}
