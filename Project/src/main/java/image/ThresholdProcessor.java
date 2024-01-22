package image;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

//responsible for applying a threshold to an image
public class ThresholdProcessor {
    public Mat applyThreshold(Mat image, double thresholdValue) {
        Mat threshold = new Mat();

        //binary thresholding
        Imgproc.threshold(image, threshold, thresholdValue, 255, Imgproc.THRESH_BINARY);

        return threshold;
    }
}
