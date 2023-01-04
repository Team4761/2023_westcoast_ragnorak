package org.robockets.command;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class MoveForward extends CommandBase {
    private double speed;
    private double time;

    public MoveForward(double speed, double time){
        this.speed = speed;
        this.time = time;
    }

    @Override
    public void execute(){}

}
