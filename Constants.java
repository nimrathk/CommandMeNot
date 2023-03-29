// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static final class DriveConstants {
    public static final int kLeftFrontMotorPort = 6;
    public static final int kRightFrontMotorPort = 5;
    public static final int kLeftBackMotorPort = 3;
    public static final int kRightBackMotorPort = 2;

    public static final int kLeftEncoderChannelA = 0;
    public static final int kLeftEncoderChannelB = 1;
    public static final int kRightEncoderChannelA = 2;
    public static final int kRightEncoderChannelB = 3;
    public static final double kEncoderTick2Feet = 1.0 / 2048.0 * Math.PI * 9 / 17.8;

    public static final double kAutoDriveForwardSpeed = -0.5;
    public static final double kAutoDriveForwardDistance = 3;
  }

  public static final class ElevatorConstants {
    public static final int kMotorPort = 9;
    public static final double kEncoderTick2Meter = 1.0 / 4096.0 * 0.1 * Math.PI;

    public static final double kElevatorPowerOut = 1.0;
    public static final double kElevatorPowerIn = -0.7;
    public static final double kElevatorCLRampRate = 0.5;

    public static final double kP = 0.05;
    public static final double kI = 1e-8;
    public static final double kD = 0;
    public static final double kIZone = 0;
    public static final double kFF = 0;
  }

  public static final class GripperConstants {
    public static final int kMotorPort = 8;
    public static final double kGripperPowerOut = 1.0;
    public static final double kGripperPowerIn = -1.0;
    private static final double kGripperCLRampRate = 0.5;
    public static final double kEncoderTick2Meter = 1.0 / 4096.0 * 0.1 * Math.PI;

    public static final double kP = 6e-5;
    public static final double kI = 0;
    public static final double kD = 0;
    public static final double kIZone = 0;
    public static final double kFF = 0.00015;
  }

  public static final class PivotConstants {
    public static final int kMotorPort = 4;
    public static final double kEncoderTick2Meter = 1.0 / 4096.0 * 0.1 * Math.PI;
    
    public static final double kPivotPowerOut = 1.0;
    public static final double kPivotPowerIn = -0.7;
    public static final double kPivotCLRampRate = 0.5;

    public static final double kP = 0.05;
    public static final double kI = 0;
    public static final double kD = 0;
    public static final double kIZone = 0;
    public static final double kFF = 0.00015;
  }
}
