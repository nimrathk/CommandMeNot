package frc.robot.commands;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.PivotConstants;
import frc.robot.subsystems.PivotSubsystem;

public class PivotPIDCmd extends CommandBase {
    private final PivotSubsystem pivotSubsystem;
    private final PIDController pidController;

    public PivotPIDCmd(PivotSubsystem pivotSubsystem, double setpoint) {
        this.pivotSubsystem = pivotSubsystem;
        this.pidController = new PIDController(PivotConstants.kP, PivotConstants.kI, PivotConstants.kD);
        pidController.setSetpoint(setpoint);
        addRequirements(pivotSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("PivotPIDCmd started!");
        pidController.reset();
    }

    @Override
    public void execute() {
        double speed = pidController.calculate(pivotSubsystem.getEncoderMeters());
        pivotSubsystem.setMotor(speed);
    }

    @Override
    public void end(boolean interrupted) {
        pivotSubsystem.setMotor(0);
        System.out.println("PivotPIDCmd ended!");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
