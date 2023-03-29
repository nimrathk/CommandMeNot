package frc.robot.commands;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.GripperConstants;
import frc.robot.subsystems.GripperSubsystem;

public class GripperPIDCmd extends CommandBase {
    private final GripperSubsystem gripperSubsystem;
    private final PIDController pidController;

    public GripperPIDCmd(GripperSubsystem gripperSubsystem, double setpoint) {
        this.gripperSubsystem = gripperSubsystem;
        this.pidController = new PIDController(GripperConstants.kP, GripperConstants.kI, GripperConstants.kD);
        pidController.setSetpoint(setpoint);
        addRequirements(gripperSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("GripperPIDCmd started!");
        pidController.reset();
    }

    @Override
    public void execute() {
        double speed = pidController.calculate(gripperSubsystem.getEncoderMeters());
        gripperSubsystem.setMotor(speed);
    }

    @Override
    public void end(boolean interrupted) {
        gripperSubsystem.setMotor(0);
        System.out.println("GripperPIDCmd ended!");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
