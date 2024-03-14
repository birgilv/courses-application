package ntnu.no.courses.contoller;


import java.util.List;
import java.util.Optional;

import ntnu.no.courses.exception.CourseNotFoundException;
import ntnu.no.courses.model.Course;
import ntnu.no.courses.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST API controller for managing courses.
 */
@RestController
@CrossOrigin("http://localhost:5173")
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/course")
    Course newCourse(@RequestBody Course newCourse) {
        return courseRepository.save(newCourse);
    }

    @GetMapping("/courses")
    List<Course> getAllCourses() {
        logger.warn("Getting all courses");
        return courseRepository.findAll();
    }

    @GetMapping("/course/{id}")
    Course getCourseById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    @PutMapping("/course/{id}")
    Course updateCourse(@RequestBody Course newCourse, @PathVariable Long id) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setName(newCourse.getName());
                    course.setDescription(newCourse.getDescription());
                    course.setDuration(newCourse.getDuration());
                    return courseRepository.save(course);
                }).orElseThrow(() -> new CourseNotFoundException(id));
    }

    @DeleteMapping("/course/{id}")
    String deleteCourse(@PathVariable Long id){
        if(!courseRepository.existsById(id)){
            throw new CourseNotFoundException(id);
        }
        courseRepository.deleteById(id);
        return  "Course with id "+id+" has been deleted success.";
    }
}
