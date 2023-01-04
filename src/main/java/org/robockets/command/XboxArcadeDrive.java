package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.controller.XboxControl;
import org.robockets.robomap.RobotMap;

public class XboxArcadeDrive extends XboxDriveBase {
    @Override
    public void execute() {
        RobotMap.getDrive().arcadeDrive(xbox.getLeftX(),xbox.getRightX());
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.getDrive().arcadeDrive(0,0);
    }
}
