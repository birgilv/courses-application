package ntnu.no.courses.contoller;


import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ntnu.no.courses.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller for user collection.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private Map<Integer, User> users;
    private int latestId;
    /**
     * Constructor for UserController, initializes the user collection with dummy data.
     */
    public UserController() {
        users = new HashMap<>();
        String jdbcUrl = "jdbc:mysql://localhost:3306/courses";
        String databaseUsername = "root";
        String databasePassword = "7V9eR3123!";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, databaseUsername, databasePassword)) {
            // Create SQL query
            String query = "SELECT * FROM user";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                // Iterate over the result set and create Book objects
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String email = resultSet.getString("Email");
                    String password = resultSet.getString("Password");
                    User user = new User(id, firstName, lastName, email, password);
                    users.put(id,user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in a real-world scenario
        }
        //initializeData();
    }

    /**
     * Initialize dummy user data for the collection.
     */
    private void initializeData() {
        latestId = 1;
        users = new HashMap<>();
        addUserToCollection(new User(-1, "James", "Kurose", "james@email.com", "password"));
        addUserToCollection(new User(-1, "Keith", "Ross", "keith@email.com", "password"));
        addUserToCollection(new User(-1, "Jordan", "Peterson", "jordan@email.com","password" ));
    }
    /**
     * Generate a new unique ID for a user.
     *
     * @return The newly generated ID
     */
    private int createNewId() {
        return latestId++;
    }

    /**
     * Get All users.
     * HTTP GET to /users
     *
     * @return List of all users currently stored in the collection
     */
    @GetMapping
    public Collection<User> getAll() {
        return users.values();
    }

    /**
     * Get a specific user.
     *
     * @param id ID of the user to be returned
     * @return User with the given ID or status 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable Integer id) {
        ResponseEntity<User> response;
        User user = findUserById(id);
        if (user != null) {
            response = new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Add a new user to the collection.
     *
     * @param user The user object to add
     * @return Status 201 CREATED on success, or 400 BAD_REQUEST if the user is invalid
     */
    @PostMapping
    public ResponseEntity<String> add(@RequestBody User user) {
        ResponseEntity<String> response;

        try {
            addUserToCollection(user);
            response = new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
    /**
     * Add a user to the collection.
     *
     * @param user The user object to add
     * @throws IllegalArgumentException If the user is invalid
     */
    private void addUserToCollection(User user) throws IllegalArgumentException {
        if (!user.isValid()) {
            throw new IllegalArgumentException("Invalid user");
        }

        int id = createNewId();
        user.setId(id);
        users.put(id, user);
    }

    /**
     * Delete a user from the collection.
     *
     * @param id ID of the user to delete
     * @return Status 200 OK on success, or 404 NOT_FOUND if the user was not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        ResponseEntity<String> response;
        if (removeUserFromCollection(id)) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Remove a user from the collection.
     *
     * @param id ID of the user to remove
     * @return True if the user was found and removed, false otherwise
     */
    private boolean removeUserFromCollection(int id) {
        User removedUser = users.remove(id);
        return removedUser != null;
    }

    /**
     * Update a user in the collection.
     *
     * @param id   ID of the user to update
     * @param user The updated user object
     * @return Status 200 OK on success, or 400 BAD_REQUEST if the update failed
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody User user) {
        ResponseEntity<String> response;
        try {
            updateUser(id, user);
            response = new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
    /**
     * Update a user in the collection.
     *
     * @param id   ID of the user to update
     * @param user The updated user object
     * @throws IllegalArgumentException If the user is not found or the update is invalid
     */
    private void updateUser(int id, User user) throws IllegalArgumentException {
        User existingUser = findUserById(id);
        if (existingUser == null) {
            throw new IllegalArgumentException("No user with id " + id + " found");
        }
        if (user == null || !user.isValid()) {
            throw new IllegalArgumentException("Wrong data in request body");
        }
        if (user.getId() != id) {
            throw new IllegalArgumentException(
                    "User ID in the URL does not match the ID in the response body");
        }

        users.put(id, user);
    }

    /**
     * Search for a user in the collection by ID.
     *
     * @param id The ID of the user to search for
     * @return The user object if found, otherwise null
     */
    private User findUserById(Integer id) {
        return users.get(id);
    }
}


