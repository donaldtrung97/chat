package hello;


import java.security.Principal;

import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.server.PathParam;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {


    @MessageMapping("/message")
    @SendTo("/topic/{client-id}")
    

    public Message say (Message message, Principal principal, @PathParam("client-id") String name) throws Exception {
    	 
    	 String nickname;
    	 nickname = name ;	
    	 JsonProvider provider = JsonProvider.provider();
    	 JsonObject connected = provider.createObjectBuilder()
    			 .add("name", nickname)
                 .build();
    	 
    	System.out.println("From Client: " + message.getContent());
    	Thread.sleep(1000); // simulated delay
		return new Message(  principal.getName()+":" + message.getContent() + "!");
        
    }

}
