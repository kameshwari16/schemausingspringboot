// package schema.example.schemarelation.controller;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import schema.example.schemarelation.entity.User;
// import schema.example.schemarelation.service.*;
// import java.util.List;
// @RestController
// // @RequestMapping("/schemacrud")
// public class UserController {
//     @Autowired
//     private UserService userService;
//     @PostMapping("/schemacrud")
    
//     public User createUser(@RequestBody User user) {
//         return userService.createUser(user);
//     }

//     @GetMapping("/schemacrud")
//     public List<User> getAllUsers() {
//         return userService.getAllUsers();
//     }

//     @GetMapping("/schemacrud/{id}")
//     public ResponseEntity<User> getUserById(@PathVariable Long id) {
//         User user = userService.getUserById(id);
//         return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
//     }

//     @PutMapping("schemacrud/{id}")
//     public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
//         User updatedUser = userService.updateUser(id, userDetails);
//         return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
//     }

//     @DeleteMapping("schemacrud/{id}")
//     public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//         userService.deleteUser(id);
//         return ResponseEntity.noContent().build();
//     }

// }



// package schema.example.schemarelation.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import schema.example.schemarelation.entity.User;
// import schema.example.schemarelation.service.UserService;

// import java.util.List;

// @RestController
// public class UserController {

//     @Autowired
//     private UserService userService;

//     @PostMapping("/schemacrud")
//     public ResponseEntity<?> createUser(@RequestBody User user) {
//         if (user == null || user.getUserName() == null || user.getUserName().isEmpty()) {
//             return ResponseEntity.badRequest().body("Username is required.");
//         }

//         User createdUser = userService.createUser(user);
//         return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//     }

//     @GetMapping("/schemacrud")
//     public ResponseEntity<List<User>> getAllUsers() {
//         List<User> users = userService.getAllUsers();
//         return ResponseEntity.ok(users);
//     }

//     @GetMapping("/schemacrud/{id}")
//     public ResponseEntity<?> getUserById(@PathVariable Long id) {
//         User user = userService.getUserById(id);
//         return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
//     }

//     @PutMapping("/schemacrud/{id}")
//     public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
//         if (userDetails == null || userDetails.getUserName() == null || userDetails.getUserName().isEmpty()) {
//             return ResponseEntity.badRequest().body("Username is required.");
//         }

//         User updatedUser = userService.updateUser(id, userDetails);
//         return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
//     }

//     @DeleteMapping("/schemacrud/{id}")
//     public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//         User user = userService.getUserById(id);
//         if (user == null) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
//         }

//         userService.deleteUser(id);
//         return ResponseEntity.status(HttpStatus.NO_CONTENT)
//                 .header("X-Message", "User with ID: " + id + " was successfully deleted.")
//                 .build();
//     }
// }

package schema.example.schemarelation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import schema.example.schemarelation.entity.User;
import schema.example.schemarelation.service.UserService;
import schema.example.schemarelation.response.UserResponse;

@RestController
@RequestMapping("/schemacrud/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getAllUsers() {
        logger.info("Received request to get all users");
        UserResponse response = userService.getAllUsers();
        if (response.getUsers().isEmpty()) {
            logger.info("No users found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        logger.info("Received request to get user with id: {}", id);
        UserResponse response = userService.getUserById(id);
        if (response.getUsers().isEmpty()) {
            logger.warn("User not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new UserResponse("User not found with id: " + id));
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody User user) {
        logger.info("Received request to create user: {}", user);
        UserResponse response = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        logger.info("Received request to update user with id: {}", id);
        UserResponse response = userService.updateUser(id, userDetails);
        if (response.getUsers().isEmpty()) {
            logger.warn("User not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new UserResponse("User not found with id: " + id));
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
        logger.info("Received request to delete user with id: {}", id);
        UserResponse response = userService.deleteUser(id);
        if (response.getUsers().isEmpty()) {
            logger.warn("User not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new UserResponse("User not found with id: " + id));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
