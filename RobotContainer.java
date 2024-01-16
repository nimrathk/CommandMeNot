// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.GripperConstants;
import frc.robot.Constants.Joystick;
import frc.robot.Constants.PivotConstants;
import frc.robot.commands.DriveForwardCmd;
import frc.robot.commands.DriveSlewFalseCmd;
import frc.robot.commands.DriveSlewTrueCmd;
import frc.robot.commands.ArcadeDriveCmd;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ElevatorPIDCmd;

import frc.robot.commands.PivotPIDCmd;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.commands.GripperJoyCmd;
import frc.robot.subsystems.PivotSubsystem;

// import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
// import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final XboxController m_stickDrive = new XboxController(0);
  private final XboxController m_stickArm = new XboxController(1);

  // The robot's subsystems are defined here...
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
  private final PivotSubsystem pivotSubsystem = new PivotSubsystem();
  private final GripperSubsystem gripperSubsystem = new GripperSubsystem();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    driveSubsystem.setDefaultCommand(new ArcadeDriveCmd(driveSubsystem, () -> m_stickDrive.getLeftY(), () -> m_stickDrive.getLeftX()));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Drive (in)
    new JoystickButton(m_stickArm, Joystick.kButtonY)
                .onTrue(new ParallelCommandGroup(new ElevatorPIDCmd(elevatorSubsystem, ElevatorConstants.drivePosition), (new PivotPIDCmd(pivotSubsystem, PivotConstants.drivePosition))));

    // Score (out)
    new JoystickButton(m_stickArm, Joystick.kButtonB)
                .onTrue(new ParallelCommandGroup(new SequentialCommandGroup(new WaitCommand(0.25), new ElevatorPIDCmd(elevatorSubsystem, ElevatorConstants.ScorePosition)), (new PivotPIDCmd(pivotSubsystem, PivotConstants.ScorePosition))));

    // Ground (down)
    new JoystickButton(m_stickArm, Joystick.kButtonA)
                .onTrue(new ParallelCommandGroup(new ElevatorPIDCmd(elevatorSubsystem, ElevatorConstants.groundPosition), new SequentialCommandGroup(new WaitCommand(0.5), new PivotPIDCmd(pivotSubsystem, PivotConstants.GroundPosition))));

    // Gripper
    new JoystickButton(m_stickArm, Joystick.leftBtn)
                .whileTrue(new GripperJoyCmd(gripperSubsystem, GripperConstants.speed));
    new JoystickButton(m_stickArm, Joystick.rightBtn)
                .whileTrue(new GripperJoyCmd(gripperSubsystem, -GripperConstants.speed));  

    new JoystickButton(m_stickDrive, Joystick.kButtonB)
                .whileTrue(new DriveSlewTrueCmd(driveSubsystem));
    new JoystickButton(m_stickDrive, Joystick.kButtonA)
                .whileTrue(new DriveSlewFalseCmd(driveSubsystem));
  }

  /**
   *Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
      new ParallelRaceGroup(
        new ParallelCommandGroup(
          new SequentialCommandGroup(
              new WaitCommand(0.25), 
              new ElevatorPIDCmd(elevatorSubsystem, ElevatorConstants.ScorePosition)), 
          new PivotPIDCmd(pivotSubsystem, PivotConstants.ScorePosition)), 
        new WaitCommand(2)),
      new ParallelRaceGroup(
        new GripperJoyCmd(gripperSubsystem, -GripperConstants.speed),
        new WaitCommand(0.5)),
      new WaitCommand(0.75),
      new ParallelRaceGroup(
        new ParallelCommandGroup(
          new ElevatorPIDCmd(elevatorSubsystem, ElevatorConstants.drivePosition), 
          (new PivotPIDCmd(pivotSubsystem, PivotConstants.drivePosition))),
        new WaitCommand(1.5)
        ),    
    new DriveForwardCmd(driveSubsystem, DriveConstants.kAutoDriveForwardDistance));
  }
}
