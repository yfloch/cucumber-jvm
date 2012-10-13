package cucumber.examples.squeaker.models;

import java.util.List;

public interface MessageRepository {
    void save(Message message);

    List<Message> findByContent(String partialText);
}
