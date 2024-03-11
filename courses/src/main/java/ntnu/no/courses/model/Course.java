package ntnu.no.courses.model;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

/**
 * Represents a resource: a course. Course objects are stored in the application state.
 */
@Entity
public final class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int duration;

    @ManyToMany(mappedBy = "courses")
    private Set<User> users = new HashSet<>();
    /**
     * Default constructor for Course.
     */
    public Course() {
    }



    /**
     * Check if this object is a valid course.
     *
     * @return True if the course is valid, false otherwise
     */
    @JsonIgnore
    public boolean isValid() {
        return name != null && !name.equals("");
    }
    /**
     * Getter for the course ID.
     *
     * @return The ID of the course
     */
    public int getId() {
        return id;
    }
    /**
     * Setter for the course ID.
     *
     * @param id The ID to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter for the course name.
     *
     * @return The name of the course
     */
    public String getName() {
        return name;
    }
    /**
     * Setter for the course name.
     *
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter for the course description.
     *
     * @return The description of the course
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the course description.
     *
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Getter for the course duration.
     *
     * @return The duration of the course
     */
    public int getDuration() {
        return duration;
    }
    /**
     * Setter for the course duration.
     *
     * @param duration The duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
    /**
     * Getter for the set of users enrolled in the course.
     *
     * @return The set of users enrolled in the course
     */
    public Set<User> getUsers() {
        return users;
    }
    /**
     * Setter for the set of users enrolled in the course.
     *
     * @param users The set of users to set
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    /**
     * Checks if two course objects are equal.
     *
     * @param obj The object to compare
     * @return True if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Course) obj;
        return this.id == that.id &&
                Objects.equals(this.name, that.name) &&
                this.description == that.description &&
                this.duration == that.duration;
    }
    /**
     * Generates a hash code for the course object.
     *
     * @return The hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, duration);
    }
    /**
     * Generates a string representation of the course object.
     *
     * @return The string representation of the object
     */
    @Override
    public String toString() {
        return "Course[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "description=" + description + ", " +
                "duration=" + duration + ']';
    }

}
