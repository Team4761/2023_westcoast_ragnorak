package org.robockets.command;

import org.robockets.Robot;

public class XboxTankDrive extends XboxDriveBase {

    @Override
    public void execute() {
        Robot.impl.getDrive().tankDrive(xbox.getLeftY(), xbox.getRightY());
    }

    @Override
    public void end(boolean interrupted) {
        Robot.impl.getDrive().tankDrive(0, 0);
    }

}
