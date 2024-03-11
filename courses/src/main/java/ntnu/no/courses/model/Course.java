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
 * Represents a resource: a book. We store Book objects in the application state.
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

    public Course() {
    }


    /**
     * Check if this object is a valid book.
     *
     * @return True if the book is valid, false otherwise
     */
    @JsonIgnore
    public boolean isValid() {
        return name != null && !name.equals("");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, duration);
    }

    @Override
    public String toString() {
        return "Course[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "description=" + description + ", " +
                "duration=" + duration + ']';
    }

}
