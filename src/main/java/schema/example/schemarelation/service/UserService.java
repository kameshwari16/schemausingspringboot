// package schema.example.schemarelation.service;
// import schema.example.schemarelation.entity.*;
// import schema.example.schemarelation.repository.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.List;
// import java.util.Optional;
// @Service
// public class UserService {
//     @Autowired
//     private UserRepository userRepository;

//     public User createUser(User user) {
//         return userRepository.save(user);
//     }

//     public List<User> getAllUsers() {
//         return userRepository.findAll();
//     }

//     public User getUserById(Long id) {
//         Optional<User> user = userRepository.findById(id);
//         return user.orElse(null);
//     }


//     public User updateUser(Long id, User userDetails) {
//         return userRepository.findById(id)
//             .map(user -> {
//                 user.setUserName(userDetails.getUserName());
//                 return userRepository.save(user);
//             })
//             .orElse(null);
//     }

//     public void deleteUser(Long id) {
//         userRepository.deleteById(id);
//     }

// }

package schema.example.schemarelation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import schema.example.schemarelation.entity.User;
import schema.example.schemarelation.repository.UserRepository;
import schema.example.schemarelation.response.UserResponse;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public UserResponse getAllUsers() {
        logger.info("Fetching all users");
        UserResponse userResponse = new UserResponse();
        userResponse.setUsers(userRepository.findAll());
        return userResponse;
    }

    public UserResponse getUserById(Long id) {
        logger.info("Fetching user with id: {}", id);
        UserResponse userResponse = new UserResponse();
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userResponse.addUser(userOptional.get());
            logger.info("User found: {}", userOptional.get());
        } else {
            userResponse.setMessage("User not found with id: " + id);
            logger.warn("User not found with id: {}", id);
        }
        return userResponse;
    }

    public UserResponse createUser(User user) {
        logger.info("Creating user: {}", user);
        UserResponse userResponse = new UserResponse();
        User savedUser = userRepository.save(user);
        userResponse.addUser(savedUser);
        logger.info("User created successfully: {}", savedUser);
        return userResponse;
    }

    public UserResponse updateUser(Long id, User userDetails) {
        logger.info("Updating user with id: {}", id);
        UserResponse userResponse = new UserResponse();
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setUserName(userDetails.getUserName());
            User updatedUser = userRepository.save(existingUser);
            userResponse.addUser(updatedUser);
            logger.info("User updated successfully: {}", updatedUser);
        } else {
            userResponse.setMessage("User not found with id: " + id);
            logger.warn("User not found with id: {}", id);
        }
        return userResponse;
    }

    public UserResponse deleteUser(Long id) {
        logger.info("Deleting user with id: {}", id);
        UserResponse userResponse = new UserResponse();
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            userResponse.setMessage("User deleted successfully.");
            logger.info("User deleted successfully with id: {}", id);
        } else {
            userResponse.setMessage("User not found with id: " + id);
            logger.warn("User not found with id: {}", id);
        }
        return userResponse;
    }
}
