package schema.example.schemarelation.response;

import schema.example.schemarelation.entity.UserTaskMapper;

import java.util.ArrayList;
import java.util.List;

public class UserTaskMapperResponse {
    private List<UserTaskMapper> userTaskMappers = new ArrayList<>();

    public UserTaskMapperResponse() {
        // Default constructor
    }

    public UserTaskMapperResponse(String message) {
        this.message = message;
    }

    public List<UserTaskMapper> getUserTaskMappers() {
        return userTaskMappers;
    }

    public void setUserTaskMappers(List<UserTaskMapper> userTaskMappers) {
        this.userTaskMappers = userTaskMappers;
    }

    public void addUserTaskMapper(UserTaskMapper userTaskMapper) {
        this.userTaskMappers.add(userTaskMapper);
    }

    // Message field, if needed
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
