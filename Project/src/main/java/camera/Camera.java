package camera;

import org.opencv.core.Mat;//lib for working with matrices (image frames)
import org.json.JSONObject;//lib for encoding/decoding JSON objects
import org.opencv.videoio.VideoCapture;//lib for capturing video from a camera
import java.util.Map;//lib for working with pairs
public class Camera {

    //init obj for
    private static final MotionDetector motionDetector = new MotionDetector();//motion detection,
    private static final VideoCapture videoCapture = new VideoCapture();// video capture,
    private static final FrameEncoder frameEncoder = new FrameEncoder();//and frame encoding
    public static void openCamera() {
        try {
            videoCapture.open(0);//open the camera index 0
        } catch (Exception e) {
            System.err.println("Error opening camera." + e.getMessage());
        }
    }
    public static JSONObject encodeWebcamFrame() {
        Mat frame1 = new Mat();//first frame matrix
        Mat frame2 = new Mat();//second frame matrix

        try {
            videoCapture.read(frame1);//the first frame from the video capture
            videoCapture.read(frame2);//the second frame from the video capture
        } catch (Exception e) {
            System.err.println("Error reading frames." + e.getMessage());
        }

        //detect motion between the frames and get the result as a pair
        Map.Entry<Mat, Boolean> framePair = motionDetector.detectMotion(frame1, frame2);

        //encode the pair using the encoder and return the result as JSON object
        return frameEncoder.encode(framePair);
    }
}
