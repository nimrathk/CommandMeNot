package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.GripperConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PivotSubsystem extends SubsystemBase {
    private CANSparkMax mGripperMotor = new CANSparkMax(GripperConstants.kMotorPort, MotorType.kBrushless);
    private RelativeEncoder gripperEncoder;

    public PivotSubsystem() {
    }

    public void resetEncoders()
    {
        gripperEncoder.setPosition(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Gripper encoder value", getEncoderMeters());
    }

    public void setMotor(double speed) {
        mGripperMotor.set(speed);
    }

    public double getEncoderMeters() {
        return gripperEncoder.getPosition() * GripperConstants.kEncoderTick2Meter;
    }
}
