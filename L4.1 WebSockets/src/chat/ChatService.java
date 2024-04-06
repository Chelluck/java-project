package chat;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class ChatService {
    private Set<ChatWebSocket> webSockets;

    public ChatService() {
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendMessage(String data,ChatWebSocket socket) {
        for (ChatWebSocket user : webSockets) {
            try {
                if(!socket.equals(user)){
                    user.sendString(data);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void sendMessageToMe(String data,ChatWebSocket socket){
        if(webSockets.contains(socket)){
            socket.sendString(data);
        }
    }


    public void add(ChatWebSocket webSocket) {
        webSockets.add(webSocket);
    }

    public void remove(ChatWebSocket webSocket) {
        webSockets.remove(webSocket);
    }

}
