package org.robockets.command;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class RotateDegreesCommand extends SequentialCommandGroup {
    private static final double ratio = 0.9;

    public RotateDegreesCommand(double speed, double degrees) {
        super(
                new MoveCommand(0, speed).withTimeout(computeTime(speed, degrees))
        );
    }

    public static double computeTime(double speed, double degrees) {
        return degrees/90.0 * speed * ratio;
    }
}
