package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.controller.XboxControl;

public abstract class XboxDriveBase extends CommandBase {
    protected XboxControl xbox = new XboxControl(0);

    @Override
    public abstract void execute();
}
