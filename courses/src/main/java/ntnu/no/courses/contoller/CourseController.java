package ntnu.no.courses.contoller;


import java.util.Optional;

import ntnu.no.courses.model.Course;
import ntnu.no.courses.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * REST API controller for book collection.
 */
@RestController
@RequestMapping("/courses")
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;


    /**
     * Get All books.
     * HTTP GET to /books
     *
     * @return List of all books currently stored in the collection
     */
    @GetMapping
    public Iterable<Course> getAll() {
        logger.warn("Getting all courses");
        return courseService.getAll();
    }

    /**
     * Get a specific book.
     *
     * @param id ID` of the book to be returned
     * @return Book with the given ID or status 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Course> getOne(@PathVariable Integer id) {
        ResponseEntity<Course> response;
        Optional<Course> course = courseService.findById(id);
        if (course.isPresent()) {
            response = new ResponseEntity<>(course.get(), HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }


    @PostMapping()
    ResponseEntity<String> add(@RequestBody Course course) {
        ResponseEntity<String> response;
        try {
            int id = courseService.add(course);
            response = new ResponseEntity<>("" + id, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    /**
     * Delete a book from the collection.
     *
     * @param id ID of the book to delete
     * @return 200 OK on success, 404 Not found on error
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        ResponseEntity<String> response;
        if (courseService.delete(id)) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Course course) {
        ResponseEntity<String> response;
        try {
            courseService.update(id, course);
            response = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
