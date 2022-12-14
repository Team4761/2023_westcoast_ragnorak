package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

public class MoveFeetForward extends CommandBase {
    private static final double ratio = 1.2;
    private ParallelRaceGroup moveCommand;

    public MoveFeetForward(double speed, double feet) {
        moveCommand = new MoveCommand(speed, 0).withTimeout(computeTime(speed, feet));
    }

    public static double computeTime(double speed, double feet) {
        return feet * speed * ratio;
    }

    @Override
    public void execute() {
        moveCommand.execute();
    }
}
