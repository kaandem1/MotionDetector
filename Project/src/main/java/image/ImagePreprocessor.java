package image;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

//responsible for preprocessing
public class ImagePreprocessor {

    //gaussian blur, convert to grayscale
    public Mat preprocessImage(Mat image) {
        try {
            Mat preprocessed = new Mat();
            //copy of the original image
            image.copyTo(preprocessed);
            //gaussian blur to reduce noise and improve detection
            Imgproc.GaussianBlur(preprocessed, preprocessed, new Size(15, 15), 0);
            //image to grayscale for simplicity
            Imgproc.cvtColor(preprocessed, preprocessed, Imgproc.COLOR_RGB2GRAY);

            return preprocessed;
        } catch (Exception e) {
            System.err.println("Error in ImagePreprocessor: " + e.getMessage());
            return new Mat();
        }
    }
}
