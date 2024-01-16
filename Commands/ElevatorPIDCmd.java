package frc.robot.commands;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorPIDCmd extends CommandBase {
    private final ElevatorSubsystem elevatorSubsystem;
    private final PIDController pidController;
    // private final double setpoint;

    public ElevatorPIDCmd(ElevatorSubsystem elevatorSubsystem, double setpoint) {
        // this.setpoint = setpoint;
        this.elevatorSubsystem = elevatorSubsystem;
        this.pidController = new PIDController(ElevatorConstants.kP, ElevatorConstants.kI, ElevatorConstants.kD);
        pidController.setSetpoint(setpoint);
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("ElevatorPIDCmd started!");
        pidController.reset();
    }

    @Override
    public void execute() {
        double speed = pidController.calculate(elevatorSubsystem.getEncoder());
        elevatorSubsystem.setMotor(speed);
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.setMotor(0);
        System.out.println("ElevatorPIDCmd ended!");
    }

    @Override
    public boolean isFinished() {
        return false;
        
        // if((Math.abs(elevatorSubsystem.getEncoder()) - setpoint) < ElevatorConstants.kError)
        // {
        //     return true;
        // }
        // else{
        //     return false;
        // }
    }
}
