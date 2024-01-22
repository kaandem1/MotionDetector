package camera;

import image.*;
import org.opencv.core.*;
import java.util.AbstractMap;
import java.util.Map;

//responsible for detecting motion
public class MotionDetector {

    //init obj for
    private static final ImagePreprocessor imagePreprocessor = new ImagePreprocessor();
    private static final DifferenceComputer differenceComputer = new DifferenceComputer(
            new ImageDifferenceCalculator(imagePreprocessor),//image preprocessing,
            new ThresholdProcessor(),// difference computation,
            new ImageDilationProcessor()//  and motion highlighting
    );
    private static final MotionHighlighter motionHighlighter = new MotionHighlighter();
    public Map.Entry<Mat, Boolean> detectMotion(Mat first, Mat second) {
        try {
            //preproc second frame
            Mat painted = imagePreprocessor.preprocessImage(second);
            //compute difference between first / second frames
            Mat diff = differenceComputer.computeDifference(first, second);
            //highlight motion, determine if movement
            boolean movementDetected = motionHighlighter.highlightMotion(painted, diff);
            //return result as a Map.Entry
            return new AbstractMap.SimpleEntry<>(painted, movementDetected);
        } catch (Exception e) {
            System.err.println("Error in MotionDetector: " + e.getMessage());
            return new AbstractMap.SimpleEntry<>(new Mat(), false);
        }
    }
}
