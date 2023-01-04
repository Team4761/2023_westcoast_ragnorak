package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.controller.XboxControl;
import org.robockets.robomap.RobotMap;

public class XboxTankDrive extends XboxDriveBase {

    @Override
    public void execute() {
        RobotMap.getDrive().tankDrive(xbox.getLeftY(),xbox.getRightY());
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.getDrive().tankDrive(0, 0);
    }

}
