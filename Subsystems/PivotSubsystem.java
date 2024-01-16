package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PivotConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PivotSubsystem extends SubsystemBase {
    private CANSparkMax pivotMotor = new CANSparkMax(PivotConstants.kMotorPort, MotorType.kBrushless);
    private RelativeEncoder pivotEncoder = pivotMotor.getEncoder();

    public PivotSubsystem() {
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Pivot encoder value", getEncoder());
    }

    public void setMotor(double speed) {
        pivotMotor.set(speed);
    }

    public double getEncoder() {
        return pivotEncoder.getPosition();
    }
}
