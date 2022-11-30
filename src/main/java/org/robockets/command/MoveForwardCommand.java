package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.RobotMap;

public class MoveForwardCommand extends CommandBase {
    private double speed;

    public MoveForwardCommand(double speed) {
        this.speed = speed;
    }

    @Override
    public void execute() {
        RobotMap.m_drive.arcadeDrive(speed,0);
    }
}
