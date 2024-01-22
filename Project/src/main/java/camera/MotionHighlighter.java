package camera;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

//responsible for highlighting motion in a frame
public class MotionHighlighter {
    private static final int MIN_DETECTED_AREA = 1000;
    private static final Scalar DRAW_COLOR = new Scalar(255.0);

    //highlight motion in a frame and return whether movement is detected
    public boolean highlightMotion(Mat painted, Mat diff) {
        try {
            //a matrix to store stat about connected comp
            Mat stats = new Mat();

            //count the number of connected components in the difference image
            int numberOfLabels = Imgproc.connectedComponentsWithStats(diff, new Mat(), stats, new Mat(), 8, CvType.CV_32S);
            //8-connectivity: Two pixels are considered connected if they share an edge or a vertex.

            //to indicate if movement
            boolean movementDetected = false;

            //iterate through connected components and highlight motion
            for (int i = 1; i < numberOfLabels; i++) {
                if (stats.get(i, 4)[0] < MIN_DETECTED_AREA) continue;

                //flag to true
                movementDetected = true;

                //a rectangle around the detected motion and draw it on the frame
                Rect block = new Rect(
                        new Point(stats.get(i, 0)[0], stats.get(i, 1)[0]),
                        new Size(stats.get(i, 2)[0], stats.get(i, 3)[0])
                );

                Imgproc.rectangle(painted, block.tl(), block.br(), DRAW_COLOR, 1);
            }
            return movementDetected;
        } catch (Exception e) {
            System.err.println("Error in MotionHighlighter: " + e.getMessage());
            return false;
        }
    }
}
