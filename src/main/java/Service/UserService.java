package Service;

import Entities.User;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

public class UserService {

    private MongoClient client = new MongoClient("localhost", 27017);
    private Datastore datastore = new Morphia().createDatastore(client, "blog");

    public String addUser(User user) {
        datastore.save(user);
        return "Usuário salvo com sucesso";
    }

    public List<User> getAllUsers() {
        List<User> userList = datastore.find(User.class).asList();
        if (userList != null) {
            return userList;
        } else {
            return null;
        }
    }
}
