import Entities.User;
import Service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.util.List;

import static spark.Spark.*;



public class Api {

    public static UserService userService = new UserService();
    public static boolean authenticated = false;

    public static void main(String[] args){

        Gson gson = new Gson();

        notFound("<html><body><h1>Custom 404 handling</h1></body></html>");
        internalServerError("<html><body><h1>Contatar o administrador</h1></body></html>");

        before((request, response) -> {
            if (!authenticated) {
                halt(401, "{\"status\": \"not_allowed\"}");
            }
        });

        post("/addUser", (request, response) -> {
            response.type("application/json");
            User user = gson.fromJson(request.body(), User.class);
            return userService.addUser(user);
        }, gson ::toJson);

        get("/getAll", (req, res) -> {
            res.type("application/json");
            List<User> userList = userService.getAllUsers();
            return userList;
        }, gson ::toJson);
    }
}
