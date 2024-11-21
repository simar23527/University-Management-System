import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.time.LocalDateTime;


public class Course {
    private String title;
    private String prof;
    private String course_code;
    private String timetable;
    private String pre_req;
    private String syllabus;
    private int credits;
    private String room_num;
    private String Stu_grade;
    private int max_limit_course=5;
    //not used rightnow
    private int current_stu_cnt=1;

    //ASSIGNMENT 2-ADDITIONS
    private Feedback<Integer> stuRatingCourse;
    private List<Student> studentsRegistered;
    private LocalDateTime dropDL;

    public Course(String title, String professor, String course_code, String timetable, String pre_req, int credits, String room_num, LocalDateTime dropdeadline){
        this.title=title;
        this.prof=professor;
        this.course_code=course_code;
        this.timetable=timetable;
        this.pre_req=pre_req;
       // this.syllabus=syllabus;
        this.credits=credits;
        this.room_num=room_num;

        //ASSIGNMENT 2-ADDITIONS
        this.Stu_grade=null;
        this.stuRatingCourse=new Feedback<>(this);
        //this.stuFeedbackDes=new ArrayList<>();
        this.dropDL=dropdeadline;

    }
    //Copy course constructor
    public Course(Course original) {
        this.title = original.title;
        this.prof = original.prof;
        this.course_code = original.course_code;
        this.timetable = original.timetable;
        this.pre_req = original.pre_req;
        this.credits = original.credits;
        this.room_num = original.room_num;
        this.Stu_grade = original.Stu_grade;
        this.max_limit_course = original.max_limit_course;
        this.current_stu_cnt = original.current_stu_cnt;

        // ASSIGNMENT 2-ADDITIONS
        this.stuRatingCourse = original.stuRatingCourse; // Assuming Feedback is immutable, otherwise use a deep copy
        this.dropDL = original.dropDL;
        this.Stu_grade=null;
    }


    //getter fns
    public String getCourseTitle(){
        return title;
    }
    public String getCourseProf(){
        return prof;
    }
    public String getCourseCode(){
        return course_code;
    }
    public String getCourseTT(){
        return timetable;
    }
    public String getCoursePre_req(){
        return pre_req;
    }
    public String getCourseSyllabus(){
        return syllabus;
    }
    public int  getCourseCredits(){
        return credits;
    }
    public String getCourseRoomnum(){
        return room_num;
    }

    public void setSyllabus(String sy){
      this.syllabus=sy;
    }
    public void setTT(String CourseTT){
       this.timetable=CourseTT;
    }

    //limit access by adding arguments
    public void setCredits(int creds){
        this.credits=creds;
    }
    public void setPrereq(String prereq){
        this.pre_req=prereq;
    }
    public void setUpperLim(int newStudentCount){
        this.max_limit_course=newStudentCount;
    }
    public void setCourseRoomNum(String newRoomNumber){
        this.room_num=newRoomNumber;
    }

    public void setCourseProf(Course c, Administrator ad, Professor p){
        if(c.getCourseProf()==null){
            c.prof=p.getName();
            System.out.println("Professor "+p.getName()+" assigned to course "+c.getCourseTitle());
        }
        else if(c.getCourseProf()!=null){
            System.out.println("Course already has a professor assigned.");
        }
        else{
            System.out.println("Permission denied. Change your role.");
        }
    }

//    public void setStu_grade(Administrator ad, String new_grade){
//        this.Stu_grade=new_grade;
//    }


    public String getCourseGrade(Student s){
        return s.getGrade(this.getCourseTitle());
//
    }

//    public void setCourseGrade_admin(Course c, Administrator ad, String newGrade, Student s){
//        if(ad!=null){
//            c.setStu_grade(ad, newGrade);
//            if(s.isPreCreated()==false){
//                System.out.println("Student grade updated successfully.");
//            }
//        }
//        else{
//                System.out.println("Permission denied. Change you role.");
//        }
//    }

    public Course get_crs_By_Name(Student s, String nm){
        for(Course crs:s.getCoursesReg_Student()){
            if(crs.getCourseTitle().equals(nm)){
                return crs;
            }
        }
        return null;
    }

    public int getEnrLim(){
        return max_limit_course;
    }

    public Course get_crs_By_Name_prof(Professor p, String nm){
        for(Course crs: p.getMyListCourses()){
            if(crs.getCourseTitle().equals(nm)){
                return crs;
            }
        }
        return null;
    }

    //ASSIGNMENT 2-ADDITION
    public Feedback<Integer> displayFeedbacks(){
        return stuRatingCourse;
    }

    public void setStudentFeedback(Integer rating, String feedbackDes){
        if(this.stuRatingCourse==null){
            this.stuRatingCourse=new Feedback<>(this);
        }
        this.stuRatingCourse.addCourseFeedback(rating, feedbackDes);
    }

    public Professor getProf(String name){
        Professor ans=null;
        for(Professor p:Main.registered_profs_portal){
            if(p.getName().equals(name)){
                ans=p;
                break;
            }
        }
        return ans;
    }

    //implementing course limit reached function to throw error

    public boolean courseLimitReached(Course c, String prof_name){
        Professor p=getProf(prof_name);
        if(p!=null){
            if(p.studentsEnrolledTotal(this)>=this.max_limit_course){
                return true;
            }
        }

            return false;

    }

    //ASSIGNMENT 2-ADDONS
    public int getCurrent_stu_cnt(){
        return this.current_stu_cnt;
    }
    public void setCurrent_stu_cnt(int new_cnt){
        this.current_stu_cnt=new_cnt;
    }
    public LocalDateTime getDropDeadline() {
        return this.dropDL;
    }
    public void setDropDeadline(LocalDateTime dropDeadline) {
        this.dropDL = dropDeadline;

    }

    //







    @Override
    public String toString(){
        return "Course title: "+title +"\nProffesor: "+prof+"\nCourse Code: "+course_code+"\nTimeTable: "+timetable+"\nPreRequisities: "+pre_req+"\nCredits: "+credits+"\nRoom No.: "+room_num;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return Objects.equals(title, course.title);  // Compare by course title
    }
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }



}


