import java.util.ArrayList;
import java.util.List;

public class TA extends Student {
    private Course myCourseAllotted;
    private boolean permissionGradeUpdate;


    public TA(String name, String mailID, String stream, int roll_num, String pwd) {
        super(name, mailID, stream, roll_num, pwd);
        // this.myCourseAllotted = nu;
        this.permissionGradeUpdate=false;

    }

    public Course getMyCourseAllotted() {
        return this.myCourseAllotted;
    }

    public void setMyCourse_TA(Course c) {
        if(this.getCGPA()>=8){
            this.myCourseAllotted = c;
        }
        else{
            System.out.println("You can't become TA for this course as your CGPA is <8.5");
        }

    }

    public void getMyAllottedCourse_Details(){
        System.out.println("Course Name: "+myCourseAllotted.getCourseTitle());
        System.out.println("Instructor: "+myCourseAllotted.getCourseProf());
        System.out.println("TimeTable: "+myCourseAllotted.getCourseTT());
        System.out.println("Room No.: "+myCourseAllotted.getCourseRoomnum());
        System.out.println("Syllabus: "+myCourseAllotted.getCourseSyllabus());
    }

    public void ViewStuGrades() {
        for (Student s : Main.registered_studs_portal) {
            if (!(s instanceof TA) && s.get_completed_Courses().contains(myCourseAllotted)) {
                System.out.println("Name: " + s.getName());
                System.out.println("Garde in  " + myCourseAllotted.getCourseTitle() + ": " + myCourseAllotted.getCourseGrade(s));
            }
        }
    }

    public List<Student> getStudentsInCourse(){
        List<Student> all_studs=new ArrayList<>();
        for (Student s : Main.registered_studs_portal) {
            //just to make sure TA's name is not reflected in this
            if (!(s instanceof TA) && s.get_completed_Courses().contains(myCourseAllotted)) {
                all_studs.add(s);
            }
        }
        return all_studs;
    }

    public void viewStudentsInCourse(){
        for(int i=0; i<this.getStudentsInCourse().size(); i++){
            System.out.println(i+1+": "+getStudentsInCourse().get(i).getName());
            System.out.println("Current Grade in "+myCourseAllotted.getCourseTitle()+" : "+getStudentsInCourse().get(i).getGrade(myCourseAllotted.getCourseTitle()));
            System.out.println("Current CGPA: "+getStudentsInCourse().get(i).getCGPA());
        }
    }

    public void Permission_gradeUpdate(Administrator ad){
        this.permissionGradeUpdate=true;
        System.out.println("Request to edit grades granted!");
    }

    public void PermissionDenial_gradeUpdate(Administrator ad){
        this.permissionGradeUpdate=false;
        System.out.println("Request to edit grades denied!");
    }

    public boolean myPermission(){
        return this.permissionGradeUpdate;
    }

    //here in this function first it checks whether TA has permission to edit grades, if yes then only TA can update grade via admin else no
    public void giveGrades(Student s, Administrator ad, String grade) {
        if(myPermission()==false){
            System.out.println("You don't have permission to edit grades.");
        }
        else{
            if (!(s instanceof TA) && s.get_completed_Courses().contains(myCourseAllotted)) {
                ad.UpdateGrade(myCourseAllotted, ad, s, grade);
                System.out.println("Grade assigned successfully!");
            }
            else{
                System.out.println("Course not completed.");
            }
        }
    }

    //student having this course c and prof under which this course c is allotted can view announcements made by TA
    public void givePublicAnnouncemnets(Course c, String a) {
        if (c.equals(myCourseAllotted)) {
            Main.public_announcemnets.put(c, a);
            System.out.println("Announcement sent.");
        } else {
            System.out.println("You can only give announcements for your allotted course.");
        }

    }


}
