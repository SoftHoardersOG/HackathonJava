package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "TeleOp", group = "TeleOp's")
public class mainTeleOp extends LinearOpMode {

    Hardware robot = new Hardware();
    QRdetector qRdetector = new QRdetector();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);
        waitForStart();
        while (!isStopRequested() && opModeIsActive()) {
            if(gamepad1.a){
                robot.right_arm.setPosition(0);
                robot.left_arm.setPosition(0);
            }
            Movement.driving(robot, gamepad1);
            Movement.sliders(robot, gamepad1);
            //Movement.speedDebug(robot,telemetry,true);
            Movement.motorpos(robot,telemetry);
        }
    }
}
