package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {

    private CANSparkMax elevatorMotor = new CANSparkMax(ElevatorConstants.kMotorPort, MotorType.kBrushless);
    private RelativeEncoder elevatorEncoder = elevatorMotor.getEncoder();

    public ElevatorSubsystem() {
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Elevator encoder value", getEncoder());
    }

    public void setMotor(double speed) {
        elevatorMotor.set(speed);
    }

    public double getEncoder() {
        return elevatorEncoder.getPosition();
    }
}
