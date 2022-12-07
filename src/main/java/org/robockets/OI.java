package org.robockets;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {
    public static Joystick m_joystick = new Joystick(0);

    //declare buttons like
    JoystickButton a = new JoystickButton(m_joystick, 1);

    public OI(){
       //to run commands with buttons
        // a.whenPressed(new Command)
    }


}
