package com.ufrn.imd.chatbotrestjson.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This is a Controller for handling chat view operations.
 * It uses the Spring's @Controller annotation for defining controllers.
 */
@Controller
public class ChatViewController {

    /**
     * This method is a GET endpoint for accessing the chat page.
     *
     * @param model The Model object used to add attributes to the view.
     * @return The name of the view to be rendered.
     */
    @GetMapping("/chat")
    public String chatPage(Model model) {
        return "chat";
    }
}
