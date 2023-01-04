package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.controller.XboxControl;
import org.robockets.robomap.RobotMap;

public class XboxDrive extends CommandBase {
    private XboxControl xbox = new XboxControl(0);

    @Override
    public void execute() {
        RobotMap.getDrive().arcadeDrive(xbox.getSpeed(),xbox.getRotate());
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.getDrive().arcadeDrive(0,0);
    }
}
