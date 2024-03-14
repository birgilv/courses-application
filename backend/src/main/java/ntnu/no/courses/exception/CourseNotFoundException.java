package ntnu.no.courses.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(Long id){
        super("Could not found the user with id "+ id);
    }

}
