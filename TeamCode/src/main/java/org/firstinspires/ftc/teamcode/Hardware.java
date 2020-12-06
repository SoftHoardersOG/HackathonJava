package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvPipeline;

public class Hardware {

    public DcMotor front_right, front_left, back_right, back_left;
    public WebcamName webcam;
    public OpenCvCamera cvCamera;


    public void init(HardwareMap hm, Telemetry telemetry) {
        telemetry.addLine("Initializing...");
        telemetry.update();

        hardwareMaping(hm);

        directionChanging(back_left);
        powerBehaviorChanging(front_left, front_right, back_right, back_left);

        telemetry.addLine("Initialization completed.");
        telemetry.update();
    }

    public void init(HardwareMap hm, Telemetry telemetry, OpenCvPipeline process) {
        telemetry.addLine("Initializing...");
        telemetry.update();

        hardwareMaping(hm);
        OpenCVSetup(hm, process);
        directionChanging(back_left);
        powerBehaviorChanging(front_left, front_right, back_right, back_left);

        telemetry.addLine("Initialization completed.");
        telemetry.update();
    }

    private void powerBehaviorChanging(DcMotor... dcMotors){
        for(DcMotor dcMotor: dcMotors){
            dcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    private void directionChanging(DcMotor... dcMotors){
        for(DcMotor dcMotor: dcMotors){
            dcMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    private void hardwareMaping(HardwareMap hardwareMap){
        front_right = getDC("front_right", hardwareMap);
        front_left = getDC("front_left", hardwareMap);
        back_left = getDC("back_left", hardwareMap);
        back_right = getDC("back_right", hardwareMap);

        webcam = hardwareMap.get(WebcamName.class, "webcam");
    }

    private void OpenCVSetup(HardwareMap hm, OpenCvPipeline process){
        int cameraMonitorID = hm.appContext.getResources().getIdentifier("cameraMonitorId","id",hm.appContext.getPackageResourcePath());
        cvCamera = OpenCvCameraFactory.getInstance().createWebcam(webcam,cameraMonitorID);
        for(int i=1; i<=100; i++)
            cvCamera.openCameraDevice();
        cvCamera.setPipeline(process);
        cvCamera.startStreaming(1980,1080);

    }

    private DcMotor getDC(String name, HardwareMap hm) {
        return hm.get(DcMotor.class, name);
    }

    private Servo getServo(String name, HardwareMap hm) {
        return hm.get(Servo.class, name);
    }

    private CRServo getCRServo(String name, HardwareMap hm) {
        return hm.get(CRServo.class, name);
    }

    private DigitalChannel getDigitalChannel(String name, HardwareMap hm) {
        return hm.get(DigitalChannel.class, name);
    }

}
//This is just a comment