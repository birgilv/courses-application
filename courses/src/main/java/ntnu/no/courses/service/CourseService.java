package ntnu.no.courses.service;

import java.util.Optional;

import ntnu.no.courses.model.Course;
import ntnu.no.courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic for books.
 */
@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public int add(Course course) throws IllegalArgumentException {
        if (!course.isValid()) {
            throw new IllegalArgumentException("Course is invalid");
        }
        courseRepository.save(course);
        return course.getId();
    }

    public Iterable<Course> getAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(int id) {
        return courseRepository.findById(id);
    }

    /**
     * Try to delete a book with the given ID.
     *
     * @param id ID of the book to delete
     * @return True if the book was found and got deleted. False otherwise.
     */
    public boolean delete(int id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            courseRepository.deleteById(id);
        }
        return course.isPresent();
    }

    public void update(int id, Course course) throws IllegalArgumentException {
        Optional<Course> existingCourse = courseRepository.findById(id);
        if (existingCourse.isEmpty()) {
            throw new IllegalArgumentException("No course with id " + id + " found");
        }
        if (course == null || !course.isValid()) {
            throw new IllegalArgumentException("Wrong data in request body");
        }
        if (course.getId() != id) {
            throw new IllegalArgumentException(
                    "Course ID in the URL does not match the ID in JSON data (response body)");
        }

        courseRepository.save(course);
    }

}
