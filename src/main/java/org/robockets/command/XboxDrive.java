package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.Robot;
import org.robockets.controller.XboxControl;

public class XboxDrive extends CommandBase {
    private XboxControl xbox = new XboxControl(0);

    @Override
    public void execute() {
        Robot.impl.getDrive().arcadeDrive(xbox.getSpeed(),xbox.getRotate());
    }

    @Override
    public void end(boolean interrupted) {
        Robot.impl.getDrive().arcadeDrive(0,0);
    }
}
