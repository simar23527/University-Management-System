import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Professor {
    private String myName;
    private String myCourse;
    private String myTimeTable;
    private int myCourse_credits;
    private String myCourse_prereq;
    private String Dept;
    private ArrayList<Student> Students_enrolled;
    private String myOfficeHrs;
    private String myOffice;
    private String myMail;
    private String myPwd;
    private ArrayList<Course> Courses_assigned;
    private HashMap<String, List<String>> myCourses;


    public Professor(String name, String office_hrs, String offc, String myMail, String myPwd) {
        // super(name, myPwd);
        this.myName = name;
        this.myCourse = "";
        this.myTimeTable = "";
        this.myCourse_credits = 0;
        this.myCourse_prereq = "";
        this.Students_enrolled = new ArrayList<>();
        this.myOfficeHrs = office_hrs;
        this.myOffice = offc;
        this.myMail = myMail;
        this.myPwd = myPwd;
        this.Courses_assigned = new ArrayList<>();
        this.myCourses = new HashMap<>();
    }


    public String getOffcHrs() {
        return this.myOfficeHrs;
    }

    public void UpdateOffcHours(String newHours) {
        this.myOfficeHrs = newHours;
    }

    public String getName() {
        return this.myName;
    }

    public ArrayList<Course> getMyListCourses() {
        return this.Courses_assigned;
    }

    public void SetCoursesassignedList(ArrayList<Course> crs) {
        this.Courses_assigned = crs;
    }

    public void SetStudentsEnrolled(ArrayList<Student> students_enrol) {
        this.Students_enrolled = students_enrol;
    }

    public void ViewMyCourses() {
        System.out.println("My Courses: ");
        for (Course c : this.getMyListCourses()) {
            System.out.println("Name of course: " + c.getCourseTitle());
            System.out.println("Timings of course: " + c.getCourseTT());
            System.out.println("Syllabus of course: " + c.getCourseSyllabus());
            System.out.println("Prerequisite of course: " + c.getCoursePre_req());
            System.out.println("Credits of course: " + c.getCourseCredits());
            System.out.println("Enrollment Limit of course: " + c.getEnrLim());
            System.out.println("\n");
        }
    }


    public void ViewMyCoursesnames() {
        System.out.println("My Courses");
        for (Course c : this.getMyListCourses()) {
            System.out.println(c.getCourseTitle());
        }
        System.out.println("\n");
    }


    //enrolled students
    public void UpdateMap() {
        myCourses.clear();
        for (Course c : getMyListCourses()) {
            for (Student s : Main.registered_studs_portal) {
                List<String> stu_details = new ArrayList<>();
                if (s.IsEnrolledInCourse(c)==true || s.get_completed_Courses().contains(c) == true)
                    stu_details.add(s.getContact());
                    stu_details.add(s.getGrade(c.getCourseTitle()));
                    myCourses.put(s.getName(), stu_details);
            }

        }

    }

    public void ViewEnrolledStudents() {
        UpdateMap();
        for (Map.Entry<String, List<String>> entry : myCourses.entrySet()) {
            String key_name = entry.getKey();
            List<String> ls = entry.getValue();
            if (ls.size() >= 2) {
                String val_mail = entry.getValue().get(0);
                String val_grade = entry.getValue().get(1);
                System.out.println("\nName: " + key_name);
                System.out.println("Mail ID: " + val_mail);
                System.out.println("Current Grade: " + val_grade);
            } else {
                System.out.println("Details insufficient.");
            }

        }
    }

    public boolean VerificationPwd(String ip) {
        return myPwd.equals(ip);
    }


    //Office Hours
    public void setOffcHrs(String OfHr) {
        this.myOfficeHrs = OfHr;
    }


    //Syllabus
    public void UpdateSyll(String myCourse, String newSyll) {
        for (Course c : Courses_assigned) {
            if (c.getCourseTitle().equals(myCourse)) {
                c.setSyllabus(newSyll);
                System.out.println("Syllabus updated for " + myCourse + ".");
                return;
            }
        }
        System.out.println("Course not found in your assigned courses list. Syllabus update unsuccessful.");
    }

    //ClassTimings
    public void UpdateTT(String myCourse, String newTT) {
        for (Course c : Courses_assigned) {
            if (c.getCourseTitle().equals(myCourse)) {
                c.setTT(newTT);
                System.out.println("Timings updated for " + myCourse + ".");
                return;
            }
        }
        System.out.println("Course not found in your assigned courses list. Timing update unsuccessful.");

    }

    //Credits
    public void UpdateCredits(String myCourse, int newCreds) {
        for (Course c : Courses_assigned) {
            if (c.getCourseTitle().equals(myCourse)) {
                c.setCredits(newCreds);
                System.out.println("Credits updated for " + myCourse + ".");
                return;
            }
        }
        System.out.println("Course not found in your assigned courses list. Credit update unsuccessful.");
    }

    //Prerequisite
    public void UpdatePrereq(String myCourse, String newPrereq) {
        for (Course c : Courses_assigned) {
            if (c.getCourseTitle().equals(myCourse)) {
                c.setPrereq(newPrereq);
                System.out.println("Prerequisite updated for " + myCourse + ".");
                return;
            }
        }
        System.out.println("Course not found in your assigned courses list. Prerequisite update unsuccessful.");

    }

    //Enrollment Limit
    public void UpdateUpperCount(String myCourse, int newNumber) {
        for (Course c : Courses_assigned) {
            if (c.getCourseTitle().equals(myCourse)) {
                c.setUpperLim(newNumber);
                System.out.println("Enrollment updated for " + myCourse + ".");
                return;
            }
        }
        System.out.println("Course not found in your assigned courses list. Enrollment limit update unsuccessful.");

    }


    public Professor getProfByName(String nm) {
        for (Professor p : Main.registered_profs_portal) {
            if (p.getName().equals(nm)) {
                return p;
            }
        }
        return null;
    }

    //ASSIGNMENT 2-ADDONS
    public void viewFeedback_myCourses() {
        for (Course c : Courses_assigned) {
            Feedback<Integer> f_prof = c.displayFeedbacks();
            if (f_prof != null) {
                System.out.println(f_prof);
            } else {
                System.out.println("No feedbacks for this course.");
            }

        }
    }

    public int studentsEnrolledTotal(Course c) {
        int cnt = 0;
        for (Student s : Main.registered_studs_portal) {
            if (s.getCoursesReg_Student().contains(c)) {
                cnt++;
            }


        }
        return cnt;
    }


}

