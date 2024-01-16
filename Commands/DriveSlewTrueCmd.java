package frc.robot.commands;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveSlewTrueCmd extends CommandBase{
    private final DriveSubsystem driveSubsystem;

    public DriveSlewTrueCmd(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Slew started!");
    }

    @Override
    public void execute() {
        driveSubsystem.setSlewTrue();
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
            return false;
    }
}
