package cucumber.examples.squeaker.controllers;

import cucumber.examples.squeaker.models.Message;
import cucumber.examples.squeaker.models.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
public class SearchController {

    private final MessageRepository messageRepository;

    @Autowired
    public SearchController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        System.out.println("##################### CONSTROLLER MESSAGE_REPO = " + messageRepository);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView userPage(@RequestParam(required = false) String query) {
        System.out.println("********************************* query = " + query);
        if (query != null) {
            List<Message> messages = messageRepository.findByContent(query);
            System.out.println("messages = " + messages);
            return new ModelAndView("search", "messages", messages);
        } else {
            return new ModelAndView("search", "messages", Collections.EMPTY_LIST);
        }
    }
}
