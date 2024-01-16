package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GripperSubsystem;

public class GripperJoyCmd extends CommandBase {
    private final GripperSubsystem gripperSubsystem;
    private final double speed;

    public GripperJoyCmd(GripperSubsystem gripperSubsystem, double speed) {
        this.speed = speed;
        this.gripperSubsystem = gripperSubsystem;
        addRequirements(gripperSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("GripperJoyCmd started!");
    }

    @Override
    public void execute() {
        gripperSubsystem.setMotor(speed);
    }

    @Override
    public void end(boolean interrupted) {
        gripperSubsystem.setMotor(0);
        System.out.println("GripperJoyCmd ended!");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
