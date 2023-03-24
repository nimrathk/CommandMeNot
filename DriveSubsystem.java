// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.DriveConstants;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class DriveSubsystem extends SubsystemBase {

  // Driving motors
  private final WPI_VictorSPX m_leftFrontMotor = new WPI_VictorSPX(DriveConstants.kLeftFrontMotorPort);
  private final WPI_VictorSPX m_rightFrontMotor = new WPI_VictorSPX(DriveConstants.kRightFrontMotorPort);
  private final WPI_VictorSPX m_leftRearMotor = new WPI_VictorSPX(DriveConstants.kLeftBackMotorPort);
  private final WPI_VictorSPX m_rightRearMotor = new WPI_VictorSPX(DriveConstants.kRightBackMotorPort);

  private final MotorControllerGroup leftMotors = new MotorControllerGroup(m_leftFrontMotor, m_leftRearMotor);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(m_rightFrontMotor, m_rightRearMotor);
  private DifferentialDrive diffDrive = new DifferentialDrive(leftMotors, rightMotors);

  private Encoder leftEncoder = new Encoder(DriveConstants.kLeftEncoderChannelA, DriveConstants.kLeftEncoderChannelB);
  private Encoder rightEncoder = new Encoder(DriveConstants.kRightEncoderChannelA, DriveConstants.kRightEncoderChannelB);

  public double getEncoderFeetAverage()
  {
    return (getLeftEncoderFeet() + getRightEncoderFeet() / 2);
  }
            
  public double getLeftEncoderFeet() {
    double leftEncoderFeet = leftEncoder.get() * DriveConstants.kEncoderTick2Feet;
    return leftEncoderFeet;
  }

  public double getRightEncoderFeet() {
    double rightEncoderFeet = -rightEncoder.get() * DriveConstants.kEncoderTick2Feet;
    return rightEncoderFeet;
  }

  public void resetEncoders()
  {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  /** Creates a new DriveSubsystem object (this is the constructor). */
  public DriveSubsystem() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Left Encoder Feet", getLeftEncoderFeet());
    SmartDashboard.putNumber("Right Encoder Feet", getRightEncoderFeet());
    SmartDashboard.putNumber("AVERAGE Encoder Feet", getEncoderFeetAverage());
  }
  
  // This lets us alter the motor values for driving
  public void setMotors(double leftSpeed, double rightSpeed)
  {
    diffDrive.arcadeDrive(leftSpeed, rightSpeed);
  }
}
