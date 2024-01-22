import camera.Camera;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.opencv.opencv_java;
import spark.Spark;
import webs.WebSocketHandler;

//initializes and starts the application
public class Main {

    public static void main(String[] args) {
        try {
            //the port for the web server to 8080
            Spark.port(8080);
            //load OpenCV lib using Loader
            Loader.load(opencv_java.class);
            Camera.openCamera();
            //location of static files for the web server
            Spark.staticFiles.location("/web");
            Spark.staticFiles.expireTime(600);
            //a WebSocket endpoint at /socket, associate it with the WebSocketHandler class
            Spark.webSocket("/socket", WebSocketHandler.class);
            //init Spark web server
            Spark.init();
        } catch (Exception e) {
            System.err.println("Error in Main " + e.getMessage());
        }
    }
}
