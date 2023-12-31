import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
    private Map<String, User> users = new HashMap<>();

    public boolean signUp(String username, String password, Role role) {
        if (!users.containsKey(username)) {
            User user = new User(username, password, role);
            users.put(username, user);
            System.out.println("Sign up successful!");           
            return true;
        } else {
            System.out.println("Username already exists. Sign up failed.");
            return false;
        }
    }

    public User authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}