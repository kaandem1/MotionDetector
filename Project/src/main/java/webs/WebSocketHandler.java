package webs;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

//handles WebSocket connection events
@WebSocket
public class WebSocketHandler {

    private Session session;
    private final WebSocketService webSocketService;

    //initialize with a WebSocketService
    public WebSocketHandler() {
        this.webSocketService = new WebSocketService(this);
    }

    //called when connection is established
    @OnWebSocketConnect
    public void connected(Session session) {
        try {
            System.out.println("Socket open");
            this.session = session;

            //start the WebSocket service when connected
            webSocketService.startService();
        } catch (Exception e) {
            System.err.println("Error in WebSocketHandler connected: " + e.getMessage());
        }
    }

    //called when a connection is closed
    @OnWebSocketClose
    public void close(int statusCode, String reason) {
        try {
            System.out.println("Socket closed");
            this.session = null;
        } catch (Exception e) {
            System.err.println("Error in WebSocketHandler close: " + e.getMessage());
        }
    }
    //to retrieve the current WebSocket session
    public Session getSession() {
        return session;
    }
}
