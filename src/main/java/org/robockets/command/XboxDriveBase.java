package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public abstract class XboxDriveBase extends CommandBase {
    protected CommandXboxController xbox = new CommandXboxController(0);

    @Override
    public abstract void execute();
}
