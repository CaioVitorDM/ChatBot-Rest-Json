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

/**
 * This is a Rest Controller for handling chat operations with a bot.
 * It uses the AliceBot framework for chat operations.
 */
@RestController
@RequestMapping("/chat")
public class ChatBotController {

    private Bot bot;
    private Chat chatSession;

    public ChatBotController() {
        initializeBot();
    }

    /**
     * This method is a POST endpoint for sending a message to the bot.
     * It takes a Message object as input and returns a Message object as the bot's reply.
     *
     * @param message The message sent to the bot.
     * @return The bot's reply as a Message object.
     */
    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        Message reply = new Message();
        reply.setContent(chatSession.multisentenceRespond(message.getContent()));
        return reply;
    }

    /**
     * This method initializes the bot and the chat session.
     * It sets the trace mode to false.
     */
    private void initializeBot() {
        String resourcesPath = getResourcesPath();
        bot = new org.alicebot.ab.Bot("super", resourcesPath);
        chatSession = new Chat(bot);
        MagicBooleans.trace_mode = false;
    }

    /**
     * This method returns the path to the resources directory.
     *
     * @return The path to the resources directory.
     */
    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 1);
        String resourcesPath = path + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }
}
