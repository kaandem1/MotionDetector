package image;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

//responsible for dilating image to enhance features
public class ImageDilationProcessor {

    //dilate an image using a kernel size
    public Mat dilateImage(Mat image, Size kernelSize) {
        //larger kernel will result in a more pronounced dilation effect, impacting a larger neighborhood of pixels.
        Mat dilated = new Mat();
        //dilate image using rectangular element with kernel size
        Imgproc.dilate(image, dilated, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, kernelSize));

        return dilated;
    }
}
