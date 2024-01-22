package camera;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;//lib for image encoding decoding
import org.json.JSONObject;//lib for creating/manipulating JSON objects
import java.util.Base64;//lib for encoding/decoding Base64 data
import java.util.Map;

//responsible for encoding video frames and creating JSON object
public class FrameEncoder {
    public JSONObject encode(Map.Entry<Mat, Boolean> framePair) {
        try {
            //encode image frame into a byte array, JPEG format
            MatOfByte matOfByte = new MatOfByte();
            Imgcodecs.imencode(".jpeg", framePair.getKey(), matOfByte);

            //convert byte array to a Base64-encoded string
            String frameData = "data:image/jpeg;charset=utf-8;base64," +
                    Base64.getEncoder().encodeToString(matOfByte.toArray());

            //create JSON object, put image and sound info
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("image", frameData);
            jsonObject.put("sound", framePair.getValue());

            return jsonObject;
        } catch (Exception e) {
            System.err.println("Error in FrameEncoder: " + e.getMessage());
        }

        return null;
    }
}
