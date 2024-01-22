package image;
import org.opencv.core.Mat;
import org.opencv.core.Size;

//responsible for computing the difference between two images
public class DifferenceComputer {

    private final ImageDifferenceCalculator differenceCalculator;
    private final ThresholdProcessor thresholdProcessor;
    private final ImageDilationProcessor dilationProcessor;
    public DifferenceComputer(
            ImageDifferenceCalculator differenceCalculator,
            ThresholdProcessor thresholdProcessor,
            ImageDilationProcessor dilationProcessor) {
        this.differenceCalculator = differenceCalculator;
        this.thresholdProcessor = thresholdProcessor;
        this.dilationProcessor = dilationProcessor;
    }
    public Mat computeDifference(Mat first, Mat second) {
        //calculate absolute diff between preproc images
        Mat diff = differenceCalculator.calculateDifference(first, second);
        //apply a threshold to the difference image to emphasize changes
        diff = thresholdProcessor.applyThreshold(diff, 10);
        //dilate the image: enhance and connect regions of change
        diff = dilationProcessor.dilateImage(diff, new Size(35, 35));

        return diff;
    }
}
