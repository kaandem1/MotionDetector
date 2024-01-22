package webs;

import camera.Camera;
import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;

//responsible for broadcasting messages to WebSocket
public class MessageBroadcaster {

    private final WebSocketHandler webSocketHandler;
    public MessageBroadcaster(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    //broadcast the encoded webcam frame message
    public void broadcastMessage() {
        try {
            //get the WebSocket session from WebSocketHandler
            Session session = webSocketHandler.getSession();
            //if the session is open
            if (session != null && session.isOpen()) {
                //encode the webcam frame as a JSON message and send it
                session.getRemote().sendString(String.valueOf(Camera.encodeWebcamFrame()));
            }
        } catch (IOException e) {
            System.err.println("Error in MessageBroadcaster " + e.getMessage());
        }
    }
}
