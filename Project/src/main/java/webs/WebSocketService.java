package webs;

import java.util.Timer;
import java.util.TimerTask;

//manages the periodic broadcasting of messages
public class WebSocketService {

    private final WebSocketHandler webSocketHandler;
    private final MessageBroadcaster broadcaster;
    public WebSocketService(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
        this.broadcaster = new MessageBroadcaster(webSocketHandler);
    }

    //to start the WebSocket service with periodic message broadcasting
    public void startService() {
        try {
            //a timer for scheduling periodic tasks
            Timer timer = new Timer("Timer", true);

            //a task to broadcast messages every 200 ms with an init delay of 5 ms
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    broadcaster.broadcastMessage();
                    System.out.println("someting");
                }
            }, 5, 50);
        } catch (Exception e) {
            System.err.println("Error in WebSocketService: " + e.getMessage());
        }
    }
}
