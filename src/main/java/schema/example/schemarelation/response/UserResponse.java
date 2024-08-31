package schema.example.schemarelation.response;

import schema.example.schemarelation.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {
    private List<User> users = new ArrayList<>();
    private String message;

    public UserResponse() {
        // Default constructor
    }

    public UserResponse(String message) {
        this.message = message;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
