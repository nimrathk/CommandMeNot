package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PivotConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PivotSubsystem extends SubsystemBase {
    private CANSparkMax mPivotMotor = new CANSparkMax(PivotConstants.kMotorPort, MotorType.kBrushless);
    private RelativeEncoder pivotEncoder;

    public PivotSubsystem() {
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
        mPivotMotor.set(speed);
    }

    public double getEncoderMeters() {
        return pivotEncoder.getPosition() * PivotConstants.kEncoderTick2Meter;
    }
}
