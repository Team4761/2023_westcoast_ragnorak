package org.robockets.controller;

import edu.wpi.first.wpilibj.XboxController;

public class XboxControl {
    private XboxController xbox;
    private boolean inverted = true;

    public XboxControl(int port)
    {
        xbox=new XboxController(port);
    }

    double applyDeadzone (double value, double deadzone) {
        if (Math.abs(value) > deadzone) {
            if (value > 0.0) {
                return (value - deadzone) / (1.0 - deadzone);
            } else {
                return (value + deadzone) / (1.0 - deadzone);
            }
        } else {
            return 0.0;
        }

    }
    public double getSpeed()
    {
        double left = xbox.getLeftY() * (inverted ? -1 : 1);
        return applyDeadzone(left,0.05);
    }
    
    public double getRotate()
    {
        double rot = xbox.getRightX() ;
        return applyDeadzone(rot,0.05);
    }
}
