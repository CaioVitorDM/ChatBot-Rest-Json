package com.ufrn.imd.chatbotrestjson.Controller;

import com.ufrn.imd.chatbotrestjson.Model.Message;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicBooleans;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/chat")
public class ChatBotController {

    private Bot bot;
    private Chat chatSession;

    public ChatBotController(){
        initializeBot();
    }

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        Message reply = new Message();
        reply.setContent(chatSession.multisentenceRespond(message.getContent()));
        return reply;
    }

    private void initializeBot() {
        String resourcesPath = getResourcesPath();
        bot = new org.alicebot.ab.Bot("super", resourcesPath);
        chatSession = new Chat(bot);
        MagicBooleans.trace_mode = false;
    }

    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 1);
        String resourcesPath = path + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }
}
