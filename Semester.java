import java.util.List;
import java.util.ArrayList;

public interface Semester {
    void AddCourses_admin(Course c1, Administrator ad);
    void DelCourses_admin(Course c1, Administrator ad);
    void ViewAvailableCourses();
    void ViewCourseNames();
    List<Course> getCourseList();
    double getCGPA(Student s);
}
