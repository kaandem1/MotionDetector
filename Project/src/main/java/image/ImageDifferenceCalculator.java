package image;

import org.opencv.core.Core;
import org.opencv.core.Mat;

//responsible for calculating the absolute difference between two preprocessed images
public class ImageDifferenceCalculator {
    private final ImagePreprocessor imagePreprocessor;
    public ImageDifferenceCalculator(ImagePreprocessor imagePreprocessor) {
        this.imagePreprocessor = imagePreprocessor;
    }
    public Mat calculateDifference(Mat first, Mat second) {
        Mat diff = new Mat();
        Core.absdiff(imagePreprocessor.preprocessImage(first), imagePreprocessor.preprocessImage(second), diff);

        return diff;
    }
}
