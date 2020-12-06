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
    public DcMotor right_slider,left_slider;
    public Servo left_arm,right_arm;
    public Servo pill_dispenser1,getPill_dispenser2;
    public WebcamName webcam;
    public OpenCvCamera cvCamera;


    public void init(HardwareMap hm, Telemetry telemetry) {
        telemetry.addLine("Initializing...");
        telemetry.update();

        hardwareMaping(hm);
        telemetry.addLine("Hardware Mapping Done!");
        telemetry.update();
        directionChanging(back_left,right_slider);
        telemetry.addLine("Direction changing for DCMotors Done!");
        telemetry.update();
        directionChanging(right_arm);
        telemetry.addLine("Direction changing for Servos Done!");
        telemetry.update();
        powerBehaviorChanging(front_left, front_right, back_right, back_left,left_slider,right_slider);
        telemetry.addLine("Power behavior Done!");
        telemetry.update();
        ResetEncoders(left_slider,right_slider);
        telemetry.addLine("Encoders reset done!");
        telemetry.update();
        telemetry.addLine("Initialization completed.");
        telemetry.update();
    }

    public void init(HardwareMap hm, Telemetry telemetry, OpenCvPipeline process) {
        telemetry.addLine("Initializing...");
        telemetry.update();

        hardwareMaping(hm);
        telemetry.addLine("Hardware Mapping Done!");
        telemetry.update();
        OpenCVSetup(hm, process,telemetry);
        telemetry.addLine("Open CV setup Done!");
        telemetry.update();
        directionChanging(back_left);
        telemetry.addLine("Direction changing for DCMotors Done!");
        telemetry.update();
        directionChanging(right_arm);
        telemetry.addLine("Direction changing for Servos Done!");
        telemetry.update();
        powerBehaviorChanging(front_left, front_right, back_right, back_left,left_slider,right_slider);
        telemetry.addLine("Power behavior Done!");
        telemetry.update();
        ResetEncoders(left_slider,right_slider);
        telemetry.addLine("Encoder reseting done.");
        telemetry.update();
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

    private void directionChanging(Servo... servos){
        for(Servo servo: servos){
            servo.setDirection(Servo.Direction.REVERSE);
        }
    }

    private void hardwareMaping(HardwareMap hardwareMap){
        front_right = getDC("front_right", hardwareMap);
        front_left = getDC("front_left", hardwareMap);
        back_left = getDC("back_left", hardwareMap);
        back_right = getDC("back_right", hardwareMap);

        right_slider = getDC("right_slider", hardwareMap);
        left_slider = getDC("left_slider", hardwareMap);

        right_arm = getServo("right_arm", hardwareMap);
        left_arm= getServo("left_arm", hardwareMap);

        pill_dispenser1=getServo("pill1", hardwareMap);
        getPill_dispenser2=getServo("pill2",hardwareMap);

        webcam = hardwareMap.get(WebcamName.class, "webcam");
    }
    public void ResetEncoders(DcMotor...dcMotors){
        for (DcMotor dcMotor:dcMotors){
            dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            dcMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
    public void OpenCVSetup(HardwareMap hm, OpenCvPipeline process, Telemetry telemetry){
        int cameraMonitorID = hm.appContext.getResources().getIdentifier("cameraMonitorViewId","id",hm.appContext.getPackageName());
        telemetry.addLine("Monitor ID DONE!");
        telemetry.update();
        cvCamera = OpenCvCameraFactory.getInstance().createWebcam(webcam,cameraMonitorID);
        telemetry.addLine("CVCam initialisation DONE!");
        telemetry.update();
        for(int i=1; i<=100; i++)
            cvCamera.openCameraDevice();
        telemetry.addLine("Camera opened DONE!");
        telemetry.update();
       cvCamera.setPipeline(process);
       telemetry.addLine("Pipeline seted DONE!");
       telemetry.update();
       cvCamera.startStreaming(640,480);
       telemetry.addLine("Stream started DONE!");
       telemetry.update();

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