package frc.robot.Commands;

import frc.robot.Subsystems.DrivetrainCAN;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;


//#include <frc/XboxController.h>

// "command", arcade drive using controller

public class GoCAN extends CommandBase{
    private final DrivetrainCAN m_drivetrain;

    public int driveMode = 0;

    XboxController m_controller = new XboxController(0);

    public GoCAN (DrivetrainCAN drivetrain, String controller) {
        addRequirements(drivetrain);
        m_drivetrain = drivetrain;

        // might do later, autodetect maybe?
        if (controller == "xbox") {
            
        } else if (controller == "joystick") {
            
        } else if (controller == "keyboard") {
            
        }
    }

    double ApplyDeadzone (double value, double deadzone) {
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


    double leftS = 0;
    double rightS = 0;

    @Override
    public void execute() {

        // goes based on left stick y and x
        //do an init or something with parameter of input type to make more easily compatible with joystick/controller/maybe keyboard

        if (m_controller.getLeftBumperPressed()) { if (driveMode==0) { driveMode = 1; } else driveMode--; }
        
        if (m_controller.getRightBumperPressed()) { driveMode++; driveMode %=2;} 


        switch (driveMode) {
        case 0:
            m_drivetrain.driveArcade(ApplyDeadzone(-m_controller.getLeftY(), 0.05), ApplyDeadzone(m_controller.getRightX(), 0.05)); 
            m_controller.setRumble(RumbleType.kLeftRumble, 0);
            m_controller.setRumble(RumbleType.kRightRumble, 0);
            break;
        case 1:
            leftS = ApplyDeadzone(-m_controller.getLeftY(), 0.05);
            rightS = ApplyDeadzone(-m_controller.getRightY(), 0.05);    
            m_drivetrain.driveTank(leftS, rightS);

            m_controller.setRumble(RumbleType.kLeftRumble, Math.abs(leftS));
            m_controller.setRumble(RumbleType.kRightRumble, Math.abs(rightS));
            break;
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.driveTank(0, 0);
    }                                                                   
}