import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Student{
   private String name_stu;
    private int curr_sem;
    private String branch;
    private int total_credits;
    //changed from String to Course
    private ArrayList<Course> Courses_completed;
    private ArrayList<Course> Courses_registered;
    private double cgpa;
    private String uni_mail;
   private String pwd;
    private int RollNum;
    private List<Complaints> myComplaints;
    private boolean preCreated;
    //ASSIGNMNET 2-ADDONS
    private Map<Course, Feedback<?>> myFeedbackList;
    private List<Course> myCompletedCourses;
    private Map<String, String> myGrades;

    public Student(String name, String mailID, String stream, int roll_num, String pwd){
        this.name_stu=name;
       // super(name, pwd);
        this.curr_sem=1;
        this.total_credits=0;
        this.Courses_completed=new ArrayList<>();
        this.Courses_registered=new ArrayList<>();
        this.myComplaints=new ArrayList<>();
        this.branch=stream;
        this.uni_mail=mailID;
        this.RollNum=roll_num;
        this.pwd=pwd;
        //ASSIGNMNET 2-ADDONS
        this.myFeedbackList=new HashMap<>();
        this.myCompletedCourses=new ArrayList<>();
        this.myGrades=new HashMap<>();

    }


   public void setPreCreation(boolean pc){
        this.preCreated=pc;
   }
    public boolean isPreCreated(){
        return this.preCreated;
    }

    public List<Complaints> getMyComplaints(){
        return this.myComplaints;
    }
    public void WriteComplaints(Complaints newComp){
        //Complaints c=new Complaints(newCompDes);
        myComplaints.add(newComp);
        if(!this.isPreCreated()){
            System.out.println("Your complaint id is: "+newComp.getID()+". Submission Successful.");
        }
    }

    public void viewComplaints(){
        if(this.getMyComplaints().isEmpty()){
            System.out.println("No Pending Complaints.");
        }
        else{
            for(Complaints cmps:this.getComplaints()){
                System.out.println(cmps);
                System.out.println(cmps.getID());
                System.out.println(cmps.getRes());
            }
        }
    }


    public boolean IsEnrolledInCourse(Course c){
        return Courses_registered.contains(c);
    }


    public List<Course> getCoursesReg_Student(){
        return this.Courses_registered;
    }

//    public List<String> getCompletedCourses_Student(){
//        return this.Courses_completed;
//    }

    public boolean VerificationPwd(String ip){
        return this.pwd.equals(ip);
    }
    public void setPsswd(String pd){
        this.pwd=pd;
    }
    public Student getStudentByName(String name){
        for(Student s:Main.registered_studs_portal){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }


    public List<Course> get_completed_Courses(){
        return this.Courses_completed;
    }


//    public String getCoursegrade(String  c){
//        for(String c_find:this.get_completed_Courses()){
//            if(c_find.equals(c)){
//               to_find=getco
//            }
//        }
//    }


    public void Courses_Completed_add(Course course_name){
        this.Courses_completed.add(course_name);
    }






    //registration for courses
    public void registerForCourses(Course course){
        try {
            String name=course.getCourseProf();
            //Professor p_c=course.getProf(name);
            if(course.courseLimitReached(course, name)){
                throw new CourseFullException("Course registration not possible. Limit Reached.");
            }
            if (this.preCreated == false) {
                //2 cases when u cant register for courses
                //1-if course registration credit limit for a sem has been reached
                if (total_credits + course.getCourseCredits() > 20) {
                    System.out.println("Credit limit reached. Can't register for " + course.getCourseTitle());
                    return;
                }
                //2-pre-req not completed
                if (course.getCoursePre_req() != null && !Courses_completed.contains(course.getCoursePre_req())) {
                    System.out.println("Prerequisite not completed. Can't register for " + course.getCourseTitle());
                    return;
                }
                //3-if course has already been registered
                if (Courses_registered.contains(course)) {
                    System.out.println("Course already added. Select some other course.");
                } else {
                    Courses_registered.add(course);
                    course.setCurrent_stu_cnt(course.getCurrent_stu_cnt()-1);
                    total_credits += course.getCourseCredits();
                    System.out.println("Course registration successful for: " + course.getCourseTitle());
                }
            } else {
                if (total_credits + course.getCourseCredits() <= 20 && !Courses_registered.contains(course)) {
                    Courses_registered.add(course);
                    total_credits += course.getCourseCredits();
                    //student registration increases current student count
                    course.setCurrent_stu_cnt(course.getCurrent_stu_cnt()-1);
                    //return;
                    //System.out.println("Credit limit reached.");
                } else {
                    System.out.println("Course can't be added. Credit limit exceeded or course already registered");
                }
            }
        }
        catch (CourseFullException e){
            System.out.println(e.getMessage());
        }
    }

    public void view_registered_courses(){
        System.out.println("Courses registered for semester "+curr_sem+": ");
        for(Course c:Courses_registered){
            System.out.println(c);
        }
    }

    public void view_completed_courses(){
        System.out.println("Courses completed: ");
        for(Course c:this.Courses_completed){
            System.out.println(c.getCourseTitle());
        }
    }



    public int Courses_registered_total(){
        int cnt=0;
        for(Course c:this.Courses_registered){
            cnt+=c.getCourseCredits();
        }
        return cnt;
    }

    public void view_SemSchedule(){
        System.out.println("Your Schedule for this semester- "+"Semester "+this.getCurrSem()+" : ");
        for(Course c: this.Courses_registered){
            System.out.println("Course Name: "+c.getCourseTitle());
            System.out.println("Timings: "+c.getCourseTT());
            System.out.println("Class Location: "+c.getCourseRoomnum());
            System.out.println("Instructor Name: "+c.getCourseProf()+"\n");
        }
    }

   public void setCurr_sem(){
        Sem1 s1_check=new Sem1();
        Sem2 s2_check=new Sem2();
        Sem3 s3_check=new Sem3();
        if(s1_check.getCourseNames().equals(this.get_completed_Courses())){
            this.curr_sem=2;
        }
        else if(s2_check.getCourseNames().equals(this.get_completed_Courses())){
            this.curr_sem=2;
        }
        else{
            this.curr_sem=3;
        }
   }

    public int getCurrSem(){
        return this.curr_sem;
    }

    public String getContact(){
        return this.uni_mail;
    }
    public int getstuRollnum(){
        return this.RollNum;
    }
    public String getName(){
        return this.name_stu;
    }
    public double getCGPA(){
        return this.cgpa;
    }
    public void setCGPA(double new_CG){
        this.cgpa=new_CG;
    }
    protected void setContacts(String newmail){
        this.uni_mail=newmail;
    }

    public List<Complaints> getComplaints(){
        return this.myComplaints;
    }

    public boolean find_course_registered(Course c){
        return this.Courses_registered.contains(c);
//
    }

    public boolean find_course_completed(Course c){
        return this.Courses_completed.contains(c);
    }




    public void drop_course_currSem(Course course) throws DropDeadlinePassedException{
        //view your registered courses
         //view_registered_courses();
        LocalDateTime curr=LocalDateTime.now();
        if(curr.isAfter(course.getDropDeadline())){
            throw new DropDeadlinePassedException("Deadline to drop this course has been passed.");
        }
        else{
            if(this.Courses_registered.contains(course)){
                Courses_registered.remove(course);
                total_credits-=course.getCourseCredits();
                System.out.println(course.getCourseTitle()+" dropped successfully.");
                //student registration decreases current student count
                course.setCurrent_stu_cnt(course.getCurrent_stu_cnt()+1);
            }
            else{
                System.out.println(course.getCourseTitle()+" doesn't exist in registered courses list thus can't be removed.");
            }
        }


    }

    //ASSIGNMENT 2-ADDITIONS
    public void assignRatingCourse(Course c, int r, String des){
        if(!this.get_completed_Courses().contains(c.getCourseTitle())){
            System.out.println("Course not completed. Can't submit feedback.");
            return;
        }
        else{
            Feedback<Integer> f=new Feedback<>(c);
            f.addCourseFeedback(r,des);
            c.setStudentFeedback(r, des);
            myFeedbackList.put(c, f);
            System.out.println("Feedback submitted successfully!");

        }
    }

    public Map<Course, Feedback<?>> getFeedbacksList(){
        return myFeedbackList;
    }

    public void setGrade(String courseTitle, String grade, Administrator ad) {
        this.myGrades.put(courseTitle, grade);
    }

    public String getGrade(String courseTitle) {
        return this.myGrades.get(courseTitle);
    }





}
