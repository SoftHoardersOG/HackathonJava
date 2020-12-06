package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Movement {
    public static void driving(Hardware robot, Gamepad gamepad1) {
        double r = -Math.hypot(gamepad1.right_stick_x, gamepad1.right_stick_y);
        double robotAngle = Math.atan2(gamepad1.right_stick_y, gamepad1.right_stick_x) - Math.PI / 4;
        double leftX = -gamepad1.left_stick_x;
        double v1 = r * Math.cos(robotAngle) - leftX;
        double v2 = r * Math.sin(robotAngle) - leftX;
        double v3 = r * Math.cos(robotAngle) + leftX;
        double v4 = r * Math.sin(robotAngle) + leftX;
        robot.front_left.setPower(v2);
        robot.back_left.setPower(v1);
        robot.back_right.setPower(v3);
        robot.front_right.setPower(v4);
        double maxi= Math.max(v1,v2);
        maxi=Math.max(maxi,v3);maxi=Math.max(maxi,v4);
        double constanta=1/maxi;
        if(constanta<0){
            constanta=-constanta;
        }
        v1*=constanta;v2*=constanta;v3*=constanta;v4*=constanta;
    }

    public static void speedDebug(Hardware robot, Telemetry telemetry) {
        telemetry.addData("front_left: ", robot.front_left.getPower());
        telemetry.addData("front_right: ", robot.front_right.getPower());
        telemetry.addData("back_left: ", robot.back_left.getPower());
        telemetry.addData("back right: ", robot.back_right.getPower());
    }

    public static void speedDebug(Hardware robot, Telemetry telemetry, boolean update) {
        telemetry.addData("front_left: ", robot.front_left.getPower());
        telemetry.addData("front_right: ", robot.front_right.getPower());
        telemetry.addData("back_left: ", robot.back_left.getPower());
        telemetry.addData("back right: ", robot.back_right.getPower());
        if(update){
            telemetry.update();
        }
    }

}