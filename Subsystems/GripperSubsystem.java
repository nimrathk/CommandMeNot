package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.GripperConstants;

// Left max is 85 for gripper encoder
// Right max is -54 for gripper encoder

public class GripperSubsystem extends SubsystemBase {

    private CANSparkMax gripperMotor = new CANSparkMax(GripperConstants.kMotorPort, MotorType.kBrushless);
    private RelativeEncoder gripperEncoder = gripperMotor.getEncoder();

    public GripperSubsystem() {
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Gripper encoder value", getEncoder());
    }

    public void setMotor(double speed) {
        double encoder = getEncoder();
        if((speed < 0 && encoder < -54) || (speed > 0 && encoder > 85))
        {
            gripperMotor.set(0);
        }
        else if((speed < 0 && encoder < -44) || (speed > 0 && encoder > 75))
        {
            gripperMotor.set(speed/2);
        }
        else{
            gripperMotor.set(speed);
        }
    }

    public double getEncoder() {
        return gripperEncoder.getPosition();
    }
}
