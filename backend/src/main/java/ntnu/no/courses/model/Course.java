package ntnu.no.courses.model;


import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/**
 * Represents a resource: a course. Course objects are stored in the application state.
 */
@Entity
public final class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String title;
    private int levelId;
    private int categoryId;
    private Date startDate;
    private Date endDate;
    private double credit;
    private double hoursPerWeek;
    private String description;
    private String relatedCertification;

    public Course() {
    }

    /**
     * id
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String name) {
        this.title = name;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getCredit() {
        return credit;
    }

    public void setHoursPerWeek(double hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public double getHoursPerWeek() {
        return hoursPerWeek;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRelatedCertification() {
        return relatedCertification;
    }
    public void setRelatedCertification(String relatedCertification) {
        this.relatedCertification = relatedCertification;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Course) obj;
        return this.id == that.id &&
                Objects.equals(this.title, that.title) &&
                this.description == that.description &&
                this.relatedCertification == that.relatedCertification;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, relatedCertification);
    }
    @Override
    public String toString() {
        return "Course[" +
                "id=" + id + ", " +
                "name=" + title + ", " +
                "description=" + description + ", " +
                "duration=" + relatedCertification + ']';
    }
}
