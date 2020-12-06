package org.firstinspires.ftc.teamcode;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.QRCodeDetector;
import org.openftc.easyopencv.OpenCvPipeline;

public class QRdetector extends OpenCvPipeline {
    QRCodeDetector qrCodeDetector = new QRCodeDetector();

    @Override
    public  Mat processFrame(Mat input) {
        String s = qrCodeDetector.detectAndDecode(input);
        if(s.length()==0){
            s="Nothing yet";
        }
        Imgproc.putText(
                input,
                s,
                new Point(input.width()/2.0,input.height()/2.0),
                Imgproc.FONT_HERSHEY_COMPLEX,
                0.5,
                new Scalar(0,0,0)
        );
        return input;
    }
}
