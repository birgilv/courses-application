package ntnu.no.courses.repository;

import ntnu.no.courses.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for SQL access to our database table.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
}