package frc.robot.commands;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveSlewFalseCmd extends CommandBase{
    private final DriveSubsystem driveSubsystem;

    public DriveSlewFalseCmd(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Slew stopped!");
    }

    @Override
    public void execute() {
        driveSubsystem.setSlewFalse();
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
            return false;
    }
}
