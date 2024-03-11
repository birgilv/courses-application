package ntnu.no.courses.contoller;


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
 * REST API controller for author collection.
 */
@RestController
@RequestMapping("users")
public class UserController {
    private Map<Integer, User> users;
    private int latestId;

    public UserController() {
        initializeData();
    }

    /**
     * Initialize dummy author data for the collection.
     */
    private void initializeData() {
        latestId = 1;
        users = new HashMap<>();
        addUserToCollection(new User(-1, "James", "Kurose", "james@email.com", "password"));
        addUserToCollection(new User(-1, "Keith", "Ross", "keith@email.com", "password"));
        addUserToCollection(new User(-1, "Jordan", "Peterson", "jordan@email.com","password" ));
    }

    private int createNewId() {
        return latestId++;
    }

    /**
     * Get All authors.
     * HTTP GET to /authors
     *
     * @return List of all authors currently stored in the collection
     */
    @GetMapping
    public Collection<User> getAll() {
        return users.values();
    }

    /**
     * Get a specific author.
     *
     * @param id ID of the author to be returned
     * @return Author with the given ID or status 404
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

    private void addUserToCollection(User user) throws IllegalArgumentException {
        if (!user.isValid()) {
            throw new IllegalArgumentException("Invalid user");
        }

        int id = createNewId();
        user.setId(id);
        users.put(id, user);
    }

    /**
     * Delete an author from the collection.
     *
     * @param id ID of the author to delete
     * @return 200 OK on success, 404 Not found on error
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
     * Try to remove an author from the collection.
     *
     * @param id ID of the author to remove
     * @return True when author with given ID was found and removed, false otherwise
     */
    private boolean removeUserFromCollection(int id) {
        User removedUser = users.remove(id);
        return removedUser != null;
    }


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
     * Search through the author collection, find the author by given ID.
     *
     * @param id Author ID
     * @return Author or null if not found
     */
    private User findUserById(Integer id) {
        return users.get(id);
    }
}


