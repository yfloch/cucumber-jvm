package cucumber.examples.spring.txn;

import cucumber.api.java.en.Given;
import cucumber.examples.squeaker.models.Message;
import cucumber.examples.squeaker.models.MessageRepository;
import cucumber.examples.squeaker.models.User;
import cucumber.examples.squeaker.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserStepdefs {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    private User user;

    public void thereIsAuser() {
        user = new User();
        user.setUsername("testuser");
        userRepository.save(user);
    }

    @Given("^a User has posted the following messages:$")
    public void a_User_has_posted_the_following_messages(List<Message> messages) throws Throwable {
        System.out.println("##################### STEPDEF MESSAGE_REPO = " + messageRepository);
        thereIsAuser();
        for (Message m : messages) {
            m.setAuthor(user);
            messageRepository.save(m);
        }
    }
}
