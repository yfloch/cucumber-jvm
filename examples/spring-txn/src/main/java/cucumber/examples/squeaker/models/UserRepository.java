package cucumber.examples.squeaker.models;

import java.util.List;

public interface UserRepository {
    void save(User user);

    List<User> findAll();
}
