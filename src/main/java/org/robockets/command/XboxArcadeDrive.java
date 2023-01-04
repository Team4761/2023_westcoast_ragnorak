package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.Robot;
import org.robockets.controller.XboxControl;

public class XboxArcadeDrive extends XboxDriveBase {
    @Override
    public void execute() {
        Robot.impl.getDrive().arcadeDrive(xbox.getLeftX(),xbox.getRightX());
    }

    @Override
    public void end(boolean interrupted) {
        Robot.impl.getDrive().arcadeDrive(0,0);
    }
}
