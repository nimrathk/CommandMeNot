package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.GripperConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GripperSubsystem extends SubsystemBase {
    private CANSparkMax pivotMotor = new CANSparkMax(GripperConstants.kMotorPort, MotorType.kBrushless);
    private RelativeEncoder pivotEncoder;

    public GripperSubsystem() {
    }

    public void resetEncoders()
    {
        pivotEncoder.setPosition(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Pivot encoder value", getEncoderMeters());
    }

    public void setMotor(double speed) {
        pivotMotor.set(speed);
    }

    public double getEncoderMeters() {
        return pivotEncoder.getPosition() * GripperConstants.kEncoderTick2Meter;
    }
}
