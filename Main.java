import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static ArrayList<Student> registered_studs_portal = new ArrayList<>();
    public static ArrayList<Professor> registered_profs_portal = new ArrayList<>();
    public static Map<String, List<Complaints>> allPortalComplaints=new HashMap<>();

    //ASSIGNMENT 2-ADDONS
    public static Map<Course, String> public_announcemnets=new HashMap<>();

    private static void updateStudentGrades(Administrator ad, Student student, List<Course> completedCourses, Map<String, String> grades) {
        for (Course c : completedCourses) {
            Course duplicate = new Course(c);
            String grade=grades.get(c.getCourseTitle());
            ad.UpdateGrade(duplicate, ad, student, grade);
            student.Courses_Completed_add(duplicate);
        }
        ad.UpdateStuCG(student);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Administrator Admin_portal = new Administrator();

        Student Simar = new Student("Simar", "simar@iiitd.ac.in", "CSB", 2023527, "pwd345");
        Student Rushil = new Student("Rushil", "rushil@iiitd.ac.in", "CSAI", 2023451, "pwd890");
        Student Zoyaa=new Student("Zoyaa", "zoyaa@iiitd.ac.in", "CSD", 2023671,"pwd456");
        Simar.setPreCreation(true);
        Rushil.setPreCreation(true);
        Zoyaa.setPreCreation(true);

        allPortalComplaints.put(Simar.getName(), new ArrayList<>());
        Complaints cmp_by_sim=new TechComplaint("Water clog issue in girls hostel.", "Hostel Dept");
        Simar.WriteComplaints(cmp_by_sim);
        AddComplaintsToSys(Simar, cmp_by_sim);
        allPortalComplaints.put(Rushil.getName(), new ArrayList<>());
        Complaints cmp_by_rush=new TechComplaint("Server not working.", "IT Dept");
        Rushil.WriteComplaints(cmp_by_rush);
        AddComplaintsToSys(Rushil, cmp_by_rush);
        Complaints cmp_by_rush_2=new TechComplaint("Wifi not working.", "IT Dept");
        Rushil.WriteComplaints(cmp_by_rush_2);
        AddComplaintsToSys(Rushil, cmp_by_rush_2);
        allPortalComplaints.put(Zoyaa.getName(), new ArrayList<>());
        Complaints cmp_by_zoyaa=new AdminComplaint("Course Cell Biology and Biochemistry not available to register.", "Course Registration");
        Zoyaa.WriteComplaints(cmp_by_zoyaa);
        AddComplaintsToSys(Zoyaa, cmp_by_zoyaa);


        Sem1 s1=new Sem1();
        Sem2 s2=new Sem2();
        Sem3 s3=new Sem3();



        //Sem1 s1_sim = new Sem1();
        List<Course> Simar_completed = s1.getCourseList();
        Map<String, String> Simar_grades=new HashMap<>();
        //updateStudentGrades(Admin_portal, Simar, Simar_completed, "A+");
        Simar.setCurr_sem();
        for (Course c : Simar_completed) {
            Course duplicate=new Course(c);
            Simar_grades.put(duplicate.getCourseTitle(), "A+");
            Simar.Courses_Completed_add(duplicate);
        }
        updateStudentGrades(Admin_portal, Simar, Simar_completed, Simar_grades);
        Admin_portal.UpdateStuCG(Simar);
        List<Course> Simar_registered = s2.getCourseList();
        for(Course c: Simar_registered){
            Course duplicate=new Course(c);
            Simar.registerForCourses(duplicate);
        }

        List<Course> Rushil_completed = s1.getCourseList();
        Map<String, String> Rushil_grades=new HashMap<>();
        Rushil.setCurr_sem();
        for (Course c : Rushil_completed) {
            //int i=0;
            Course duplicate=new Course(c);
            Rushil_grades.put(duplicate.getCourseTitle(), "A-");
            Rushil.Courses_Completed_add(duplicate);
        }
        updateStudentGrades(Admin_portal, Rushil, Rushil_completed, Rushil_grades);
        Admin_portal.UpdateStuCG(Rushil);

        List<Course> Rushil_registered = s2.getCourseList();
        for(Course c: Rushil_registered){
            //replaced simar by rushil
            Course duplicate=new Course(c);
            Rushil.registerForCourses(duplicate);
        }


        List<Course> Zoyaa_completed = s1.getCourseList();
        Map<String, String> Zoyaa_grades=new HashMap<>();

        for (Course c : Zoyaa_completed) {
            //int i=0;
            Course duplicate=new Course(c);
            Zoyaa_grades.put(duplicate.getCourseTitle(), "A-");
            //Admin_portal.UpdateGrade(duplicate, Admin_portal, Zoyaa, "A-");
            Zoyaa.Courses_Completed_add(duplicate);
        }
        updateStudentGrades(Admin_portal,Zoyaa, Zoyaa_completed, Zoyaa_grades);
        Admin_portal.UpdateStuCG(Zoyaa);
        List<Course> Zoyaa_completed_2=s2.getCourseList();
        for(Course c: Zoyaa_completed_2){
            Course duplicate=new Course(c);
            Zoyaa_grades.put(duplicate.getCourseTitle(), "B-");
            //Admin_portal.UpdateGrade(duplicate, Admin_portal, Zoyaa, "B+");
            Zoyaa.Courses_Completed_add(duplicate);
        }
        updateStudentGrades(Admin_portal,Zoyaa, Zoyaa_completed, Zoyaa_grades);
        Admin_portal.UpdateStuCG(Zoyaa);




        registered_studs_portal.add(Simar);
        registered_studs_portal.add(Rushil);
        registered_studs_portal.add(Zoyaa);


        Professor DrSanjitK=new Professor("Dr Sanjit Kaul", "Wed 3-4pm", "B312", "sanjitp@iiit.in", "s1234");
        registered_profs_portal.add(DrSanjitK);
        Professor DrTammamT=new Professor("Dr Tammam Tilo", "Wed 3-4pm", "B812", "tammamp@iiit.in", "t1234");
        registered_profs_portal.add(DrTammamT);






//        List<Course> l1=s1.getCourseList();
//        s2.getCourseList();
        ArrayList<Course> TammamTiloCourses=new ArrayList<>();
        for(Course c_tt: s1.getCourseList()){
            if(c_tt.getCourseProf().equals("Dr Tammam Tilo")){
                TammamTiloCourses.add(c_tt);
            }
        }
        for(Course c_tt: s2.getCourseList()){
            if(c_tt.getCourseProf().equals("Dr Tammam Tilo")){
                TammamTiloCourses.add(c_tt);
            }
        }
        ArrayList<Student> Studs_Enrolled_TT=new ArrayList<>();
        for(Course c_t: TammamTiloCourses){
            for(Student s:registered_studs_portal){
                if(s.IsEnrolledInCourse(c_t)){
                    Studs_Enrolled_TT.add(s);
                }
            }
        }
        DrTammamT.SetCoursesassignedList(TammamTiloCourses);


        ArrayList<Course> SanjitKaulCourses=new ArrayList<>();
        for(Course c_tt: s1.getCourseList()){
            if(c_tt.getCourseProf().equals("Dr Sanjit Kaul")){
                SanjitKaulCourses.add(c_tt);
            }
        }
        for(Course c_tt: s2.getCourseList()){
            if(c_tt.getCourseProf().equals("Dr Sanjit Kaul")){
                SanjitKaulCourses.add(c_tt);
            }
        }
        ArrayList<Student> Studs_Enrolled_SK=new ArrayList<>();
        for(Course c_t: SanjitKaulCourses){
            for(Student s:registered_studs_portal){
                if(s.IsEnrolledInCourse(c_t)){
                    Studs_Enrolled_SK.add(s);
                }
            }
        }
        DrSanjitK.SetCoursesassignedList(SanjitKaulCourses);


        //ASSIGNMENT 2-ADDONS
        //presetting course allotted to Priya(who is TA) as Digital Circuits from sem1
        Course allotedToPriya=s1.getCourseList().get(3);
        Map<String, String> Priya_grades=new HashMap<>();
        TA Priya=new TA("Priya", "priya@iiitd.ac.in", "CSE", 2022451, "priya789");
        registered_studs_portal.add(Priya);
        Priya.setPreCreation(true);
        allPortalComplaints.put(Priya.getName(), new ArrayList<>());
        Complaints cmp_by_priya=new TechComplaint("Seepage issue in GCR.", "Hostel Dept");
        Priya.WriteComplaints(cmp_by_priya);
        AddComplaintsToSys(Priya, cmp_by_priya);
        List<Course> Priya_completed = s1.getCourseList();
        for (Course c :Priya_completed) {
            //int i=0;
            Course duplicate =new Course(c);
            Priya_grades.put(duplicate.getCourseTitle(), "A-");
            Priya.Courses_Completed_add(c);
        }
        updateStudentGrades(Admin_portal,Priya, Priya_completed, Priya_grades);
        Admin_portal.UpdateStuCG(Priya);
        Priya_completed=s2.getCourseList();
        for(Course c: Priya_completed){
            Course duplicate =new Course(c);
            Priya_grades.put(duplicate.getCourseTitle(), "A-");
            Priya.Courses_Completed_add(c);
        }
        updateStudentGrades(Admin_portal,Priya, Priya_completed, Priya_grades);
        Admin_portal.UpdateStuCG(Priya);
        Priya.setMyCourse_TA(allotedToPriya);
        List<Course> Priya_registered = s3.getCourseList();
        for(Course c: Priya_registered){
            Course duplicate=new Course(c);
            Priya.registerForCourses(duplicate);
        }
        Priya.setCurr_sem();
        //for TA they have completed both their sem1 and  sem2
        //---//






        // ArrayList<Professor> profs=new ArrayList<>();
        String role = "";
        String name = "";
        String mode = "";
        String pswd = "";
        String email = "";
        String pswrd = "";
        String option = "";
        String admin_choose_option = "";
        String courseToaddOrdel = "";
        int rollnum;
        int c_sem;
        int curr_sem_schedule;
        String brnch = "";
        Student new_stu = null;
        Professor new_prof = null;
        Administrator admin = new Administrator();
        Course crs1_a_d = null;
        Course crs2_a_d = null;

        //ASSIGNMENT 2-ADDONS
        TA new_ta=null;





        boolean In = true;
        while (In) {

            System.out.println("Welcome to IIITD Course Registration Portal");
            System.out.println("\nEVERY PROMPT IS CASE SENSITIVE.");

            //ASSIGNMNET 2-ADDONS-->introducing TA as a new user
            System.out.println("\nAre you a student, TA, professor or administrator? OR Do you want to LOGOUT? ");
            System.out.println("Enter one of the 5 options: ");
            role = scanner.nextLine();
            if(role.equals("LOGOUT")){
                System.out.println("Logging out from portal.......");
                break;
            }
            else if(role.equals("student")==false && role.equals("professor")==false && role.equals("administrator")==false && role.equals("TA")==false){
                System.out.println("Invalid option chosen. Select again");
            }
            else{
                System.out.println("(a) Login or Signup? ");
                System.out.println("(b) LOGOUT");
                System.out.println("Enter (a) or (b): ");
                String option_to_choose = scanner.nextLine();
                switch (option_to_choose) {
                    case "(b)":
                        System.out.println("Logging out from portal......");
                        In = false;
                        break;
                    case "(a)":
                        System.out.println("Enter Login or Signup");
                        mode = scanner.nextLine();
                        if (mode.equals("Login")) {
                            boolean EntryGranted = false;
                            while (EntryGranted == false) {
                                System.out.println("Enter your name: ");
                                name = scanner.nextLine();
                                System.out.println("Enter your Password: ");
                                pswrd = scanner.nextLine();
                                boolean personfound=false;
                                try {
                                    if (role.equals("administrator") && name.equals("AdminIIITD") && admin.VerificationPwd(pswrd)) {
                                        System.out.println("Login Successful");
                                        personfound = true;
                                        EntryGranted = true;
                                        break;
                                        // In=false;
                                        //  break;
                                    } else if (role.equals("student")) {
                                        for (Student std : registered_studs_portal) {
                                            if (std.getName() != null && std.getName().equals(name) && std.VerificationPwd(pswrd) == true) {
                                                new_stu = std;
                                                EntryGranted = true;
                                                personfound = true;
                                                System.out.println("Login Successful!");
                                                break;
                                            }
                                        }
                                        if (!personfound) {
                                            throw new InvalidLoginException("Invalid Credentials. Try Again.");
                                        }
                                    }
                                    //also assuming that TA can only login as course will be allotted to him/her beforehand
                                    //and there is no point in signing up as a TA if you are setting your course allotted as null
                                    else if (role.equals("TA")) {
                                        for (Student std : registered_studs_portal) {
                                            if (std instanceof TA) {
                                                TA t = (TA) std;
                                                if (t.getName() != null && std.getName().equals(name) && t.VerificationPwd(pswrd) == true) {
                                                    new_ta = t;
                                                    EntryGranted = true;
                                                    personfound = true;
                                                    System.out.println("Login Successful!");
                                                    break;
                                                }
                                            }

                                        }
                                        if (!personfound) {
                                            throw new InvalidLoginException("Invalid Credentials. Try Again.");
                                        }


                                    } else if (role.equals("professor")) {
                                        for (Professor pf : registered_profs_portal) {
                                            if (pf.getName().trim().equals(name.trim()) && pf.VerificationPwd(pswrd.trim()) == true) {
                                                new_prof = pf;
                                                System.out.println("Login Successful!");
                                                personfound = true;
                                                EntryGranted = true;
                                                break;
                                            }
                                        }
                                        if (!personfound) {
                                            throw new InvalidLoginException("Invalid Credentials. Try Again.");
                                        }
                                    } else {
                                        throw new InvalidLoginException("Incorrect name or password. Try Again.");
                                        // return;
                                    }
                                }
                                catch(InvalidLoginException e){
                                    System.out.println(e.getMessage());
                                }
                            }

                        } else if (mode.equals("Signup")) {
                            if(role.equals("administrator")){
                                System.out.println("Admin please Login.");
                                continue;
                            }
                            else if(role.equals("TA")){
                                System.out.println("Enter your name: ");
                                name = scanner.nextLine();
                                System.out.println("Enter your mail id: ");
                                email = scanner.nextLine();
                                System.out.println("Enter your roll number: ");
                                rollnum = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Enter branch name: ");
                                brnch = scanner.nextLine();
                                System.out.println("Create a password: ");
                                pswrd = scanner.nextLine();
                                new_ta = new TA(name, email, brnch, rollnum, pswrd);
                                new_ta.setPreCreation(false);
                                registered_studs_portal.add(new_ta);
                               // new_ta.setCurr_sem();
                            }
                            else if (role.equals("student")) {
                                System.out.println("Enter your name: ");
                                name = scanner.nextLine();
                                System.out.println("Enter your mail id: ");
                                email = scanner.nextLine();
                                System.out.println("Enter your roll number: ");
                                rollnum = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Enter branch name: ");
                                brnch = scanner.nextLine();
                                System.out.println("Create a password: ");
                                pswrd = scanner.nextLine();
                                new_stu = new Student(name, email, brnch, rollnum, pswrd);
                                new_stu.setPreCreation(false);
                                registered_studs_portal.add(new_stu);
                                new_ta.setMyCourse_TA(null);

                            }
                            else if (role.equals("professor")) {
                                System.out.println("Enter your name: ");
                                name = scanner.nextLine();
                                System.out.println("Enter your mail id: ");
                                email = scanner.nextLine();
                                System.out.println("Enter your office number: ");
                                String officenum = scanner.nextLine();
                                System.out.println("Enter your office hours: ");
                                String o_hrs = scanner.nextLine();
                                System.out.println("Create a password: ");
                                pswrd = scanner.nextLine();
                                new_prof = new Professor(name, o_hrs, officenum, email, pswrd);
                                registered_profs_portal.add(new_prof);

                            }

                            System.out.println("Signup successful!");
                        }
                        //Added this for assignmnet 2
                        else{
                            System.out.println("Enter correct option");
                            continue;
                        }
                        System.out.println("\nWELCOME " + name.toUpperCase());

                        boolean SysRunning = true;
                        while (SysRunning) {
                            if (role.equals("student")) {
                                System.out.println("\nWhat do you want to choose? ");
                                System.out.println("1-View Available Courses");
                                System.out.println("2-Register for Courses");
                                System.out.println("3-View Schedule");
                                System.out.println("4-Track Academic Progress");
                                System.out.println("5-Drop Courses");
                                System.out.println("6-Submit Complaints");
                                System.out.println("7-View Status of Complaints");
                                //ASSIGNMENT 2-ADDONS
                                System.out.println("8-Write Feedback for course");
                                //-------//
                                System.out.println("9-LOGOUT");
                                //Assignmnet 2-ADDON(asking for option number)
                                System.out.println("Enter option number: ");
                                option = scanner.nextLine();
                                switch (option) {
                                    case "1":
                                        System.out.println("View Available Courses");
                                        System.out.println("Enter current Sem: ");
                                        c_sem = scanner.nextInt();
                                        scanner.nextLine();
                                        if (c_sem == 1) {
                                            //Sem1 sem1 = new Sem1();
                                            s1.ViewAvailableCourses();
                                        } else if (c_sem == 2) {
                                            //Sem2 sem2 = new Sem2();
                                            s2.ViewAvailableCourses();
                                        } else {
                                            System.out.println("Invalid Semester Chosen.");

                                        }
                                        break;
                                    case "2":
                                        System.out.println("Register for Courses");
                                        System.out.println("Enter semester number for which you have to register courses: ");
                                        c_sem = scanner.nextInt();
                                        scanner.nextLine();
                                        Course course_selected = null;
                                        if (c_sem == 1) {
                                            // int cnt=0;
                                            // Sem1 s1 = new Sem1();
                                            if (registered_studs_portal.contains(new_stu) && new_stu.getCoursesReg_Student().size() == 5) {
                                                System.out.println("YOU HAVE REGISTERED FOR COURSES.");
                                                new_stu.view_registered_courses();
                                                break;

                                            } else {
                                                s1.ViewCourseNames();

                                                while (true) {
                                                    System.out.println("Enter course name to register: ");
                                                    String tochoose_course = scanner.nextLine();
                                                    course_selected = s1.getCourseByName((tochoose_course));
                                                    if (course_selected == null) {
                                                        System.out.println("Please enter a valid course name.");
                                                        continue;
                                                    }
                                                    new_stu.registerForCourses(course_selected);
                                                    if (new_stu.Courses_registered_total() == 20) {
                                                        System.out.println("Credit Limit Reached.");
                                                        break;
                                                    }
                                                }

                                            }

                                        }
                                        //break;
                                        else if (c_sem == 2) {
                                            // Sem2 s2 = new Sem2();
                                            if (registered_studs_portal.contains(new_stu) && new_stu.getCoursesReg_Student().size() == 5) {
                                                System.out.println("YOU HAVE REGISTERED FOR COURSES.");
                                                new_stu.view_registered_courses();
                                                break;

                                            } else {
                                                s2.ViewCourseNames();
                                                while (true) {
                                                    System.out.println("Enter course name to register: ");
                                                    String tochoose_course = scanner.nextLine();
                                                    course_selected = s2.getCourseByName((tochoose_course));

                                                    if (course_selected == null) {
                                                        System.out.println("Please enter a valid course name.");
                                                        continue;
                                                    }

                                                    new_stu.registerForCourses(course_selected);

                                                    if (new_stu.Courses_registered_total() > 20) {
                                                        System.out.println("Credit Limit Reached.");
                                                        //new_stu.drop_course_currSem(course_selected);
                                                        break;
                                                    }

                                                }

                                            }

                                        }
                                        break;
                                    case "3":
                                        System.out.println("View Schedule");
                                        new_stu.view_SemSchedule();
                                        break;
                                    case "4":
                                        System.out.println("Track Academic Progress");
                                        System.out.println("Enter Completed Semester(1/2): ");
                                        int choose_g_sem = scanner.nextInt();
                                        scanner.nextLine();
                                        //  scanner.nextLine();
                                        switch (choose_g_sem) {
                                            case 1:
                                                // Sem1 s1 = new Sem1();
                                                s1.ViewCourseNames();
                                                System.out.println("Enter Course name: ");
                                                String crs_num_grade = scanner.nextLine();
                                                String mygrade_here=new_stu.getGrade(crs_num_grade);
                                                System.out.println("Grade for this course is: "+mygrade_here);
                                                System.out.println("SGPA for semester 1: " + s1.getCGPA(new_stu));
                                                //scanner.nextLine().trim();
                                                // if (crs_num_grade > 0 && crs_num_grade <= s1.getCourseList().size()) {
                                               // Course name_c_grade = s1.getCourseByName(crs_num_grade);
//                                                if (name_c_grade == null) {
//                                                    System.out.println("Course not found in completed list or grade not assigned.");
//                                                } else {
//                                                    System.out.println("Grade for this course: " + name_c_grade.getCourseGrade(new_stu));
//                                                    System.out.println("SGPA for semester 1: " + s1.getCGPA(new_stu));
//                                                }
                                                //}
                                                break;
                                            case 2:
                                                // Sem2 s2 = new Sem2();
                                                // Sem1 s1_here=new Sem1();
                                                s2.ViewCourseNames();
                                                System.out.println("Enter Course name: ");
                                                String crs_num_grade_2 = scanner.nextLine();
                                                String mygrade_here_2=new_stu.getGrade(crs_num_grade_2);
                                                System.out.println("Grade for this course is: "+mygrade_here_2);
                                                System.out.println("SGPA for semester 1: " + s1.getCGPA(new_stu));
                                                System.out.println("SGPA for semester 2: " + s2.getCGPA(new_stu));
                                                System.out.println("CGPA for first year: " + (s1.getCGPA(new_stu) + s2.getCGPA(new_stu)) / 2.0);

                                                //if (crs_num_grade_2 > 0 && crs_num_grade_2 <= s2.getCourseList().size()) {




                                                break;
                                            default:
                                                System.out.println("Invalid Semester Chosen.");
                                                break;
                                        }
                                        break;
                                    case "5":
                                        Course course_to_drop = null;
                                       // System.out.println("Enter your current semester: ");
                                        int sem_c = new_stu.getCurrSem();
                                        //scanner.nextLine();
                                        System.out.println("Drop Courses");
                                        new_stu.view_registered_courses();
                                        System.out.println("Enter Course name you want to drop: ");
                                        String toDrop = scanner.nextLine();
                                        if (sem_c == 1) {
                                            // Sem1 s1 = new Sem1();
                                            course_to_drop = s1.getCourseByName(toDrop);
                                            if (course_to_drop == null) {
                                                System.out.println("Enter a valid course name.");
                                                continue;
                                            }
                                            if (new_stu.find_course_registered(course_to_drop)) {
                                                try{
                                                    new_stu.drop_course_currSem(course_to_drop);
                                                    System.out.println("Course dropped successfully!");
                                                    break;
                                                }catch(DropDeadlinePassedException e){
                                                    System.out.println(e.getMessage());
                                                }
                                               // new_stu.drop_course_currSem(course_to_drop);
                                            } else {
                                                System.out.println("Course not registered.");
                                            }
                                        } else if (sem_c == 2) {
                                            //  Sem2 s2 = new Sem2();
                                            course_to_drop = s2.getCourseByName(toDrop);
                                            if (course_to_drop == null) {
                                                System.out.println("Enter a valid course name.");
                                                continue;
                                            }
                                            if (new_stu.find_course_registered(course_to_drop)) {
                                                try{
                                                    new_stu.drop_course_currSem(course_to_drop);
                                                    System.out.println("Course dropped successfully!");
                                                    break;
                                                } catch(DropDeadlinePassedException e){
                                                    System.out.println(e.getMessage());
                                                }


                                            } else {
                                                System.out.println("Course not registered.");
                                            }
                                        }
                                        break;
                                    case "6":
                                        System.out.println("Submit Complaints");
                                        System.out.println("Choose complaint type:");
                                        System.out.println("1. Technical Complaint");
                                        System.out.println("2. Administrative Complaint");
                                        String complaintChoice = scanner.nextLine();

                                        System.out.println("Write description of your complaint:");
                                        String des_now = scanner.nextLine();

                                        Complaints newComplaint;

                                        switch (complaintChoice) {
                                            case "1":
                                                System.out.println("Enter affected system:");
                                                String affectedSystem = scanner.nextLine();
                                                newComplaint = new TechComplaint(des_now, affectedSystem);
                                                break;
                                            case "2":
                                                System.out.println("Enter department:");
                                                String department = scanner.nextLine();
                                                newComplaint = new AdminComplaint(des_now, department);
                                                break;
                                            default:
                                                System.out.println("Invalid choice. Submitting as a general complaint.");
                                                newComplaint = new Complaints(des_now);
                                        }

                                        new_stu.WriteComplaints(newComplaint);
                                        Main.AddComplaintsToSys(new_stu, newComplaint);
                                        break;


                                    case "7":
                                        System.out.println("View Status of Complaints");
                                        List<Complaints> studentComplaints = new_stu.getMyComplaints();
                                        if (studentComplaints.isEmpty()) {
                                            System.out.println("No Pending Complaints.");
                                        } else {
                                            for (Complaints cmp : studentComplaints) {
                                                System.out.println("Complaint ID: " + cmp.getID());
                                                System.out.println("Description: " + cmp.getDes());
                                                System.out.println("Status: " + cmp.getStatus());
                                                System.out.println("Resolution: " + (cmp.getRes() != null ? cmp.getRes() : "Not resolved yet"));

                                                if (cmp instanceof TechComplaint) {
                                                    System.out.println("Type: Technical Complaint");
                                                    System.out.println("Affected System: " + ((TechComplaint) cmp).getAffectedSystem());
                                                } else if (cmp instanceof AdminComplaint) {
                                                    System.out.println("Type: Administrative Complaint");
                                                    System.out.println("Department: " + ((AdminComplaint) cmp).getDepartment());
                                                } else {
                                                    System.out.println("Type: General Complaint");
                                                }

                                            }
                                        }
                                        break;
                                    case "8":
                                        //System.out.println(new_stu.getName());
                                        System.out.println("Write Feedback for course");
                                        System.out.println("Enter your completed semester(1/2): ");
                                        String op_f = scanner.nextLine().trim();
                                        // scanner.nextLine();
                                        switch (op_f) {
                                            case "1":
                                                if (new_stu.get_completed_Courses().equals(s1.getCourseList())) {
                                                    s1.ViewCourseNames();
                                                    System.out.println("Enter Course Name for which feedback has to be submitted: ");
                                                    String course_name_f = scanner.nextLine();
                                                    Course f = s1.getCourseByName(course_name_f.trim());
                                                    if (f != null) {
                                                        while (true) {
                                                            System.out.println("Enter rating on a scale of 1-5 (1-lowest, 5-highest): ");
                                                            int rating = scanner.nextInt();
                                                            scanner.nextLine();
                                                            if (rating < 1 || rating > 5) {
                                                                System.out.println("Enter valid rating in the range from 1 to 5.");
                                                                //continue;
                                                            } else {
                                                                System.out.println("Enter description: ");
                                                                String d = scanner.nextLine();
                                                                new_stu.assignRatingCourse(f, rating, d);
                                                                break;
                                                            }
                                                        }
                                                    }else {
                                                        System.out.println("Course not found.");
                                                    }
                                                }
                                                else{
                                                    System.out.println("Semester not completed.");
                                                }
                                                break;
                                            case "2":
                                                if (new_stu.get_completed_Courses().equals(s2.getCourseList())) {
                                                    s2.ViewCourseNames();
                                                    System.out.println("Enter Course Name for which feedback has to be submitted: ");
                                                    String course_name_f = scanner.nextLine();
                                                    Course f = s2.getCourseByName(course_name_f.trim());
                                                    if (f != null) {
                                                        while (true) {
                                                            System.out.println("Enter rating on a scale of 1-5 (1-lowest, 5-highest): ");
                                                            int rating = scanner.nextInt();
                                                            scanner.nextLine();
                                                            if (rating < 1 || rating > 5) {
                                                                System.out.println("Enter valid rating in the range from 1 to 5.");
                                                                //continue;
                                                            } else {
                                                                System.out.println("Enter description: ");
                                                                String d = scanner.nextLine();
                                                                new_stu.assignRatingCourse(f, rating, d);
                                                                break;
                                                            }
                                                        }
                                                    }else {
                                                        System.out.println("Course not found.");
                                                    }
                                                }
                                                else {
                                                    System.out.println("Semester not completed.");
                                                }
                                                break;
                                            default:
                                                System.out.println("Semester not completed or invalid option chosen.");
                                                break;
                                        }
                                        break;
                                    case "9":
                                        System.out.println("Logging out. Please wait......");
                                        SysRunning = false;
                                        break;
                                    default:
                                        System.out.println("Invalid option chosen");
                                        break;
                                }
                               // break;
                            }
                            else if(role.equals("TA")){

                                System.out.println("\nWhat do you want to choose? ");
                                System.out.println("1-View Available Courses");
                                System.out.println("2-Register for Courses");
                                System.out.println("3-View Schedule");
                                System.out.println("4-Track Academic Progress");
                                System.out.println("5-Drop Courses");
                                System.out.println("6-Submit Complaints");
                                System.out.println("7-View Status of Complaints");
                                System.out.println("8-Write Feedback for course");
                                //ASSIGNMENT 2-ADDONS
                                System.out.println("9-Select Course for TA-ship");
                                System.out.println("10-View allotted course details");
                                System.out.println("11-View student grades");
                                System.out.println("12-Update student grades");
                                System.out.println("13-Write public announcements for your allotted course");
                                //-------//
                                System.out.println("14-LOGOUT");
                                System.out.println("Enter option number: ");
                                option = scanner.nextLine();
                                switch (option) {
                                    case "1":
                                        System.out.println("View Available Courses");
                                        System.out.println("Available courses in Sem3");
                                        s3.ViewAvailableCourses();
//
                                        break;
                                    case "2":
                                        System.out.println("Register for Courses");
                                        if (registered_studs_portal.contains(new_ta) && new_ta.getCoursesReg_Student().size() == 5) {
                                                System.out.println("YOU HAVE REGISTERED FOR COURSES.");
                                                new_ta.view_registered_courses();
                                                break;
//
                                            } else {
                                                s3.ViewCourseNames();
                                            while (true) {
                                                    System.out.println("Enter course name to register: ");
                                                    String tochoose_course = scanner.nextLine();
                                                    Course course_selected_TA=null;
                                                    course_selected_TA = s3.getCourseByName((tochoose_course));
                                                    if (course_selected_TA == null) {
                                                        System.out.println("Please enter a valid course name.");
                                                        continue;
                                                    }
                                                    new_ta.registerForCourses(course_selected_TA);
                                                    if (new_ta.Courses_registered_total() == 20) {
                                                        System.out.println("Credit Limit Reached.");
                                                        break;
                                                    }
                                                }
                                        }

                                        break;
                                    case "3":
                                        System.out.println("View Schedule");
                                       // System.out.println("You have completed both Sem1 and Sem2. Sem 3 course list not added in portal. No Schedule.");
                                        new_ta.view_SemSchedule();
                                        break;
                                    case "4":
                                        System.out.println("Track Academic Progress");
                                        System.out.println("Enter Completed Semester(1/2): ");
                                        int choose_g_sem = scanner.nextInt();
                                        scanner.nextLine();
                                        //  scanner.nextLine();
                                        switch (choose_g_sem) {
                                            case 1:
                                                // Sem1 s1 = new Sem1();
                                                s1.ViewCourseNames();
                                                System.out.println("Enter Course name: ");
                                                String crs_num_grade = scanner.nextLine();
                                                String mygrade_here=new_ta.getGrade(crs_num_grade);
                                                if(mygrade_here==null){
                                                    System.out.println("Grade not assigned yet.");
                                                    break;
                                                }
                                                else{
                                                    System.out.println("Grade for this course is: "+mygrade_here);
                                                    System.out.println("SGPA for semester 1: " + s1.getCGPA(new_ta));
                                                }

                                                //}
                                                break;
                                            case 2:
                                                // Sem2 s2 = new Sem2();
                                                // Sem1 s1_here=new Sem1();
                                                s1.ViewCourseNames();
                                                System.out.println("Enter Course name: ");
                                                String crs_num_grade_2 = scanner.nextLine();
                                                String mygrade_here_2=new_ta.getGrade(crs_num_grade_2);
                                                if(mygrade_here_2==null){
                                                    System.out.println("Grade not assigned yet.");
                                                    break;
                                                }
                                                else{
                                                    System.out.println("Grade for this course is: "+mygrade_here_2);
                                                    System.out.println("SGPA for semester 1: " + s1.getCGPA(new_ta));
                                                    System.out.println("SGPA for semester 2: " + s2.getCGPA(new_ta));
                                                    System.out.println("CGPA for first year: " + (s1.getCGPA(new_ta) + s2.getCGPA(new_ta)) / 2.0);
                                                }


                                                break;
                                            default:
                                                System.out.println("Invalid Semester Chosen.");
                                                break;
                                        }
                                        break;
                                    case "5":
                                        //System.out.println("You have completed both Sem1 and Sem2. No available courses for Sem3 right in portal.");
                                        //Course course_to_drop = null;
                                       // System.out.println("Enter your current semester: ");
                                        int sem_c = new_ta.getCurrSem();
                                        //scanner.nextLine();
                                        if (sem_c !=3) {
                                            System.out.println("Enter correct current semester!");
                                            continue;
                                        }
                                        else{
                                            System.out.println("Drop Courses");
                                            new_ta.view_registered_courses();
                                            System.out.println("Enter Course name you want to drop: ");
                                            String toDrop = scanner.nextLine();
                                            Course course_drop_TA=s3.getCourseByName(toDrop);
                                            if(course_drop_TA==null){
                                                System.out.println("Enter a valid course name.");
                                                continue;
                                            }
                                            if(new_ta.find_course_registered(course_drop_TA)){
                                                try{
                                                    new_ta.drop_course_currSem(course_drop_TA);
                                                } catch (DropDeadlinePassedException e){
                                                    System.out.println(e.getMessage());
                                                }

                                            }
                                            else{
                                                System.out.println("Course not registered.");
                                            }
                                        }
                                        break;
                                    case "6":
                                        System.out.println("Submit Complaints");
                                        System.out.println("Choose complaint type:");
                                        System.out.println("1. Technical Complaint");
                                        System.out.println("2. Administrative Complaint");
                                        String complaintChoice = scanner.nextLine();

                                        System.out.println("Write description of your complaint:");
                                        String des_now = scanner.nextLine();

                                        Complaints newComplaint;

                                        switch (complaintChoice) {
                                            case "1":
                                                System.out.println("Enter affected system:");
                                                String affectedSystem = scanner.nextLine();
                                                newComplaint = new TechComplaint(des_now, affectedSystem);
                                                break;
                                            case "2":
                                                System.out.println("Enter department:");
                                                String department = scanner.nextLine();
                                                newComplaint = new AdminComplaint(des_now, department);
                                                break;
                                            default:
                                                System.out.println("Invalid choice. Submitting as a general complaint.");
                                                newComplaint = new Complaints(des_now);
                                        }

                                        new_ta.WriteComplaints(newComplaint);
                                        Main.AddComplaintsToSys(new_ta, newComplaint);
                                        break;


                                    case "7":
                                        System.out.println("View Status of Complaints");
                                        List<Complaints> studentComplaints = new_ta.getMyComplaints();
                                        if (studentComplaints.isEmpty()) {
                                            System.out.println("No Pending Complaints.");
                                        } else {
                                            for (Complaints cmp : studentComplaints) {
                                                System.out.println("Complaint ID: " + cmp.getID());
                                                System.out.println("Description: " + cmp.getDes());
                                                System.out.println("Status: " + cmp.getStatus());
                                                System.out.println("Resolution: " + (cmp.getRes() != null ? cmp.getRes() : "Not resolved yet"));

                                                if (cmp instanceof TechComplaint) {
                                                    System.out.println("Type: Technical Complaint");
                                                    System.out.println("Affected System: " + ((TechComplaint) cmp).getAffectedSystem());
                                                } else if (cmp instanceof AdminComplaint) {
                                                    System.out.println("Type: Administrative Complaint");
                                                    System.out.println("Department: " + ((AdminComplaint) cmp).getDepartment());
                                                } else {
                                                    System.out.println("Type: General Complaint");
                                                }

                                            }
                                        }
                                        break;
                                    case "8":
                                        //System.out.println(new_stu.getName());
                                        System.out.println("Write Feedback for course");
                                        System.out.println("Enter your completed semester(1/2): ");
                                        String op_f = scanner.nextLine().trim();
                                        // scanner.nextLine();
                                        switch (op_f) {
                                            case "1":
                                                if (new_ta.get_completed_Courses().equals(s1.getCourseList())) {
                                                    s1.ViewCourseNames();
                                                    System.out.println("Enter Course Name for which feedback has to be submitted: ");
                                                    String course_name_f = scanner.nextLine();
                                                    Course f = s1.getCourseByName(course_name_f.trim());
                                                    if (f != null) {
                                                        while (true) {
                                                            System.out.println("Enter rating on a scale of 1-5 (1-lowest, 5-highest): ");
                                                            int rating = scanner.nextInt();
                                                            scanner.nextLine();
                                                            if (rating < 1 || rating > 5) {
                                                                System.out.println("Enter valid rating in the range from 1 to 5.");
                                                                //continue;
                                                            } else {
                                                                System.out.println("Enter description: ");
                                                                String d = scanner.nextLine();
                                                                new_ta.assignRatingCourse(f, rating, d);
                                                                break;
                                                            }
                                                        }
                                                    }else {
                                                        System.out.println("Course not found.");
                                                    }
                                                }
                                                else{
                                                    System.out.println("Semester not completed.");
                                                }
                                                break;
                                            case "2":
                                                if (new_ta.get_completed_Courses().equals(s2.getCourseList())) {
                                                    s2.ViewCourseNames();
                                                    System.out.println("Enter Course Name for which feedback has to be submitted: ");
                                                    String course_name_f = scanner.nextLine();
                                                    Course f = s2.getCourseByName(course_name_f.trim());
                                                    if (f != null) {
                                                        while (true) {
                                                            System.out.println("Enter rating on a scale of 1-5 (1-lowest, 5-highest): ");
                                                            int rating = scanner.nextInt();
                                                            scanner.nextLine();
                                                            if (rating < 1 || rating > 5) {
                                                                System.out.println("Enter valid rating in the range from 1 to 5.");
                                                                //continue;
                                                            } else {
                                                                System.out.println("Enter description: ");
                                                                String d = scanner.nextLine();
                                                                new_ta.assignRatingCourse(f, rating, d);
                                                                break;
                                                            }
                                                        }
                                                    }else {
                                                        System.out.println("Course not found.");
                                                    }
                                                }
                                                else {
                                                    System.out.println("Semester not completed.");
                                                }
                                                break;
                                            default:
                                                System.out.println("Semester not completed or invalid option chosen.");
                                                break;
                                        }
                                        break;
                                    case "9":
                                        System.out.println("Select Course for TA-ship");
                                        if(new_ta.getMyCourseAllotted()!=null){
                                            System.out.println("You have been set as TA for "+new_ta.getMyCourseAllotted().getCourseTitle()+"!");
                                            break;
                                        }
                                        else{
                                            s1.ViewAvailableCourses();
                                            s2.ViewAvailableCourses();
                                            System.out.println("Select course name: ");
                                            String course_TA=scanner.nextLine();
                                            Course to_be_alotted=s1.getCourseByName(course_TA);
                                            if(to_be_alotted==null){
                                                to_be_alotted=s2.getCourseByName(course_TA);
                                                if(to_be_alotted==null){
                                                    System.out.println("Enter valid course name");
                                                    break;
                                                }
                                            }
                                            if(to_be_alotted!=null){
                                                new_ta.setMyCourse_TA(to_be_alotted);
                                            }
                                            else{
                                                System.out.println("Invalid course name!");
                                            }
                                        }
                                        break;
                                    case "10":
                                        System.out.println("View allotted course details");
                                        new_ta.getMyAllottedCourse_Details();
                                        break;
                                    case "11":
                                        System.out.println("View student grades");
                                        new_ta.ViewStuGrades();
                                        break;
                                    case "12":
                                        System.out.println("Update Student Grades");
                                        System.out.println("Select Student for whom you have to update grade");
                                        new_ta.viewStudentsInCourse();
                                        System.out.println("Enter number: ");
                                        int op_ta_s= scanner.nextInt();
                                        scanner.nextLine();
                                        Student selected=null;
                                        if(op_ta_s<1 || op_ta_s>new_ta.getStudentsInCourse().size()){
                                            System.out.println("Invalid number selected.");
                                            continue;
                                        }
                                        else{
                                           selected=new_ta.getStudentsInCourse().get(op_ta_s-1);
                                            System.out.println("Waiting for permission to update grade be granted....");
                                            new_ta.Permission_gradeUpdate(admin);
                                            System.out.println("Enter new grade for this student(A+/A-/B+/B-): ");
                                            String g= scanner.nextLine();
                                            new_ta.giveGrades(selected, admin, g);
                                        }
                                        break;
                                    case "13":
                                        System.out.println("Write public announcements for your allotted course");
                                        System.out.println("Write new announcement: ");
                                        String announcement = scanner.nextLine();
                                        new_ta.givePublicAnnouncemnets(new_ta.getMyCourseAllotted(), announcement);
                                        break;
                                    case "14":
                                        System.out.println("Logging out. Please wait......");
                                        SysRunning = false;
                                        break;
                                    default:
                                        System.out.println("Invalid option chosen");
                                        break;
                                }
                                // break;

                            }


                            else if (role.equals("professor")) {
                                System.out.println("What do you want to choose? ");
                                System.out.println("1-Manage Courses");
                                System.out.println("2-View Enrolled Students");
                                System.out.println("3-View Feedbacks");
                                System.out.println("4-LOGOUT");
                                System.out.println("Enter option number: ");
                                int option_prof = scanner.nextInt();
                                scanner.nextLine();
                                switch (option_prof) {
                                    case 1:
                                        System.out.println("Manage your courses");
                                        System.out.println("(a)View courses allotted " +
                                                "(b)Update course details");
                                        System.out.println("Enter (a) OR (b): ");
                                        String sub_op = scanner.nextLine();
                                        switch (sub_op) {
                                            case "(a)":
                                                new_prof.ViewMyCourses();
                                                break;
                                            case "(b)":
                                                System.out.println("(b)(i)Update Syllabus" +
                                                        "(b)(ii)Update Timings " +
                                                        "(b)(iii)Update Credits" +
                                                        "(b)(iv)Update Prerequisite" +
                                                        "(b)(v)Update Enrollment Limit");
                                                System.out.println("Enter (b)(i) OR (b)(ii) OR (b)(iv) OR (b)(v)");
                                                String sub_op_prof = scanner.nextLine();
                                                List<Course> prof_crs = new_prof.getMyListCourses();
                                                new_prof.ViewMyCoursesnames();
                                                switch (sub_op_prof) {
                                                    case "(b)(i)":
                                                        System.out.println("Enter Course name for which to update: ");
                                                        String prof_crs_0 = scanner.nextLine();
                                                        for (Course to_update : prof_crs) {
                                                            if (to_update.getCourseTitle().equals(prof_crs_0)) {
                                                                System.out.println("Enter new Syllabus: ");
                                                                String new_c_syll = scanner.nextLine();
                                                                new_prof.UpdateSyll(to_update.getCourseTitle(), new_c_syll);
                                                            }
                                                        }
                                                        break;
                                                    case "(b)(ii)":
                                                        System.out.println("Enter Course name for which to update: ");
                                                        String prof_crs_1 = scanner.nextLine();
                                                        for (Course to_update : prof_crs) {
                                                            if (to_update.getCourseTitle().equals(prof_crs_1)) {
                                                                System.out.println("Enter new Timings: ");
                                                                String new_c_time = scanner.nextLine();
                                                                new_prof.UpdateTT(to_update.getCourseTitle(), new_c_time);
                                                            }
                                                        }
                                                        break;
                                                    case "(b)(iii)":
                                                        System.out.println("Enter Course name for which to update: ");
                                                        String prof_crs_2 = scanner.nextLine();
                                                        for (Course to_update : prof_crs) {
                                                            if (to_update.getCourseTitle().equals(prof_crs_2)) {
                                                                System.out.println("Enter new credits: ");
                                                                int new_creds = scanner.nextInt();
                                                                scanner.nextLine();
                                                                new_prof.UpdateCredits(to_update.getCourseTitle(), new_creds);
                                                            }
                                                        }
                                                        break;
                                                    case "(b)(iv)":
                                                        System.out.println("Enter Course name for which to update: ");
                                                        String prof_crs_3 = scanner.nextLine();
                                                        for (Course to_update : prof_crs) {
                                                            if (to_update.getCourseTitle().equals(prof_crs_3)) {
                                                                System.out.println("Enter new prerequiste: ");
                                                                String new_crs_prereq = scanner.nextLine();
                                                                new_prof.UpdatePrereq(to_update.getCourseTitle(), new_crs_prereq);
                                                            }
                                                        }
                                                        break;
                                                    case "(b)(v)":
                                                        System.out.println("Enter Course name for which to update: ");
                                                        String prof_crs_4 = scanner.nextLine();
                                                        for (Course to_update : prof_crs) {
                                                            if (to_update.getCourseTitle().equals(prof_crs_4)) {
                                                                System.out.println("Enter new enrollment limit: ");
                                                                int new_crs_enrlim = scanner.nextInt();
                                                                scanner.nextLine();
                                                                new_prof.UpdateUpperCount(to_update.getCourseTitle(), new_crs_enrlim);
                                                            }
                                                        }
                                                        break;
                                                    default:
                                                        System.out.println("Invalid option chosen.");
                                                        break;
                                                }
                                                break;
                                            default:
                                                System.out.println("Wrong option.");
                                        }
                                        break;
                                    case 2:
                                        System.out.println("View Enrolled Students");
                                        new_prof.ViewEnrolledStudents();
                                        break;
                                    case 3:
                                        System.out.println("View Feedbacks");
                                        new_prof.viewFeedback_myCourses();
                                        break;
                                    case 4:
                                        System.out.println("Logging out. Please wait......");
                                        SysRunning = false;
                                        break;
                                    default:
                                        System.out.println("Invalid option chosen");
                                        break;
                                }
                            } else if (role.equals("administrator")) {
                                System.out.println("What do you want to choose? ");
                                System.out.println("1-Manage Course Catalog");
                                System.out.println("2-Manage Student Records");
                                System.out.println("3-Assign Professors to Courses");
                                System.out.println("4-Handle Complaints");
                                System.out.println("5-Change Drop Course Deadline");
                                System.out.println("6-LOGOUT");
                                System.out.println("Enter option number: ");
                                option = scanner.nextLine();
                                switch (option) {
                                    case "1":
                                        System.out.println("Manage Course Catalog");
                                        System.out.println("Choose Semester(1/2): ");
                                        int sem_admin = scanner.nextInt();
                                        scanner.nextLine();
                                        System.out.println("(a)View Courses" +
                                                "(b)Add Courses" +
                                                "(c)Delete Courses");
                                        System.out.println("Enter (a) OR (b) OR (c): ");
                                        admin_choose_option = scanner.nextLine();
                                        switch (admin_choose_option) {
                                            case "(a)":
                                                if (sem_admin == 1) {
                                                   // Sem1 s1 = new Sem1();
                                                    Admin_portal.viewAvailCourses_SEM1_ad(s1, Admin_portal);
                                                } else {

                                                    //Sem2 s2 = new Sem2();
                                                    Admin_portal.viewAvailCourses_SEM2_ad(s2, Admin_portal);
                                                }
                                                break;
                                            case "(b)":
                                                String crs_nm_b = "";
                                                if (sem_admin == 1) {
                                                   // Sem1 s1 = new Sem1();
                                                    System.out.println("Name the Course you want to add: ");
                                                    crs_nm_b = scanner.nextLine();
                                                    //for now only name of course
                                                    Course c1_add_ad=new Course(crs_nm_b, null, null, null, null, 4, null, LocalDateTime.of(2024, 12, 30, 23, 59));
                                                    s1.AddCourses_admin(c1_add_ad, Admin_portal);
                                                } else {
                                                   // Sem2 s2 = new Sem2();
                                                    System.out.println("Name the Course you want to add: ");
                                                    crs_nm_b = scanner.nextLine();
                                                    Course c2_add_ad=new Course(crs_nm_b, null, null, null, null, 4, null, LocalDateTime.of(2024, 12, 30, 23, 59));
                                                    s2.AddCourses_admin(c2_add_ad, Admin_portal);
                                                }
                                                break;
                                            case "(c)":
                                                //String crs_nm_c = "";
                                                if (sem_admin == 1) {
                                                   // Sem1 s1 = new Sem1();
                                                    s1.ViewCourseNames();
                                                    boolean deleted=false;
                                                    System.out.println("Name the Course you want to delete: ");
                                                    String crs_nm_c = scanner.nextLine();
                                                    Course course_to_delete_1=null;
                                                   for(Course c_del:s1.getCourseList()){
                                                       if(c_del.getCourseTitle().equals(crs_nm_c)){
                                                           course_to_delete_1=c_del;
                                                           //Admin_portal.DELSem1Courses(s1, c_del);
                                                           //s1.DelCourses_admin(c_del, Admin_portal);
                                                           deleted=true;
                                                           break;
                                                       }
                                                   }
                                                   if(deleted==true){
                                                       Admin_portal.DELSem1Courses(s1, course_to_delete_1);
                                                       System.out.println("Deletion successful.");
                                                   }
                                                   else{
                                                       System.out.println("Course doesn't exist. Can't perform deletion.");
                                                   }


                                                } else {
                                                   // Sem2 s2 = new Sem2();
                                                    s2.ViewCourseNames();
                                                    System.out.println("Name the Course you want to delete: ");
                                                    String crs_nm_c_2 = scanner.nextLine();
                                                    boolean deleted=false;
                                                    Course course_to_delete_2=null;
                                                    for(Course c_del:s2.getCourseList()){
                                                        if(c_del.getCourseTitle().equals(crs_nm_c_2)){
                                                            course_to_delete_2=c_del;
                                                           // Admin_portal.DELSem2Courses(s2, c_del);
                                                            deleted=true;
                                                            break;
                                                        }
                                                    }
                                                    if(deleted==true){
                                                        Admin_portal.DELSem2Courses(s2, course_to_delete_2);
                                                        System.out.println("Deletion successful.");
                                                    }
                                                    else{
                                                        System.out.println("Course doesn't exist. Can't perform deletion.");
                                                    }


                                                    //Admin_portal.DELSem2Courses(s2, c2);
                                                }
                                                break;
                                        }
                                        break;
                                    case "2":
                                        System.out.println("Manage Student Records");
                                        System.out.println("(a)View Student Details" +
                                                "(b)Update Student Details");
                                        System.out.println("Enter (a) OR (b): ");
                                        admin_choose_option = scanner.nextLine();
                                        switch (admin_choose_option) {
                                            case "(a)":
                                                Admin_portal.viewStudentsRegistered();
                                                break;
                                            case "(b)":
                                                Admin_portal.viewStudentsRegistered();
                                                System.out.println("Changes being made for(Student Name): ");
                                                String name_find = scanner.nextLine();
                                                Student s = null;
                                                for (Student sd : registered_studs_portal) {
                                                    if (sd.getName().equals(name_find)) {
                                                        s = sd;
                                                        break;
                                                    }
                                                }
                                                if (s == null) {
                                                    System.out.println("Student not registered.");
                                                    break;
                                                }
                                                System.out.println("(b)(i)Change Grade for a course " +
                                                        "(b)(ii)Change CGPA " +
                                                        "(b)(iii)Change mail id ");
                                                System.out.println("Enter (b)(i) OR (b)(ii) OR (b)(iii): ");
                                                String s_op = scanner.nextLine();
                                                switch (s_op) {
                                                    case "(b)(i)":
                                                        System.out.println("Enter name of course for which to edit grade: ");
                                                        String crc_ad_g = scanner.nextLine();
                                                        Course c = null;
                                                        //Sem1 s1_getcrs = new Sem1();
                                                        for(Course c_find: s1.getCourseList()){
                                                            if(c_find.getCourseTT().equals(crc_ad_g)){
                                                                c=c_find;
                                                            }
                                                        }
                                                        //Sem2 s2_getcrs = new Sem2();
                                                        for(Course c_find: s2.getCourseList()){
                                                            if(c_find.getCourseTT().equals(crc_ad_g)){
                                                                c=c_find;
                                                            }
                                                        }
                                                        if (c == null) {
                                                            System.out.println("Enter a valid course.");
                                                        } else {
                                                            System.out.println("Enter new grade: ");
                                                            String g = scanner.nextLine();
                                                            Admin_portal.UpdateGrade(c, Admin_portal, s, g);
                                                        }
                                                        break;
                                                    case "(b)(ii)":
                                                        System.out.println("Update new CGPA: ");
//                                                        double new_cgpa_stu = scanner.nextFloat();
//                                                        scanner.nextLine();
                                                        Admin_portal.UpdateStuCG(s);
                                                        break;
                                                    case "(b)(iii)":
                                                        System.out.println("Enter new mailid: ");
                                                        String new_mail_edit = scanner.nextLine();
                                                        Admin_portal.UpdateStuMail(s, new_mail_edit);
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                    case "3":

                                        System.out.println("Assign Professors to Courses");
                                        System.out.println("Enter course name for which to assign professor: ");
                                        String c_name = scanner.nextLine();
//                                        Sem1 s_1_p = new Sem1();
//                                        Sem2 s_2_p = new Sem2();
                                        Course to_prof=null;
                                       for(Course in_s1: s1.getCourseList()){
                                           if(in_s1.getCourseTT().equals(c_name)){
                                               to_prof=in_s1;
                                               break;
                                           }
                                       }
                                        for(Course in_s2: s2.getCourseList()){
                                            if(in_s2.getCourseTT().equals(c_name)){
                                                to_prof=in_s2;
                                                break;
                                            }
                                        }
                                        if (to_prof==null) {
                                            System.out.println("No such course available.");
                                        } else {
                                            System.out.println("Enter professor you want to assign this course to: ");
                                            String prof_name = scanner.nextLine();
                                            Professor p_new = new Professor(prof_name, null, null, null, null);
                                            if (to_prof != null) {
                                                Admin_portal.AssignProfToCourse(to_prof, Admin_portal, p_new);
                                            }
                                        }
                                        //Course c_noProf=c_noProf.get_crs_By_Name_prof(new_prof, c_name);

                                        break;
                                    case "4":
                                        System.out.println("Handle Complaints");
                                        System.out.println("(a)View Complaints" +
                                                "(b)Change Status of Complaints");
                                        System.out.println("Enter (a) OR (b): ");
                                        String sub_op = scanner.nextLine();
                                        switch (sub_op) {
                                            case "(a)":
                                                // Student s=studs.get(0);
                                                System.out.println("Viewing complaints made by registered students: ");
                                                for (Student stu : registered_studs_portal) {
                                                    admin.Stu_Complaints();
                                                }
                                                break;
                                            case "(b)":
                                                System.out.println("Changing complaint status: ");
                                                System.out.println("Complaints to be viewed for student(name): ");
                                                String stu_cmp = scanner.nextLine();
                                                Student selectedStu = null;
                                                for (Student s : registered_studs_portal) {
                                                    if (s.getName().equals(stu_cmp)) {
                                                        selectedStu = s;
                                                        break;
                                                    }
                                                }
                                                if (selectedStu == null) {
                                                    System.out.println("Student not found");
                                                    break;
                                                }
                                                if (selectedStu.getMyComplaints().isEmpty()) {
                                                    System.out.println("No Pending Complaints");
                                                } else {
                                                    System.out.println("Pending Complaints: ");
                                                    List<Complaints> stuComp=selectedStu.getMyComplaints();
                                                    for (int i = 0; i < stuComp.size(); i++) {
                                                        System.out.println((i + 1) + ". " + stuComp.get(i).getDes());
                                                    }
                                                    //Admin_portal.Stu_Complaints();
                                                }
                                                System.out.println("Enter Complaint number from pending complaints: ");
                                                int cmp_id_stu = scanner.nextInt();
                                                scanner.nextLine();
                                                Complaints cmp = selectedStu.getComplaints().get(cmp_id_stu - 1);
                                                if (cmp == null) {
                                                    System.out.println("No such complaint exists.");
                                                } else {
                                                    String res = "";
                                                    System.out.println("Enter resolution: ");
                                                    res = scanner.nextLine();
                                                    admin.chnageStatus(selectedStu, cmp, res);
                                                }
                                                break;


                                            default:
                                                System.out.println("Invalid option.");
                                                break;
                                        }
                                        break;
                                    case "5":
                                        System.out.println("Change Drop Deadline for Course");
                                        s1.ViewAvailableCourses();
                                        s2.ViewAvailableCourses();
                                        s3.ViewAvailableCourses();
                                        System.out.println("Enter course name for which to chnage deadline: ");
                                        String c= scanner.nextLine();
                                        Course deadline_change=null;
                                        deadline_change=s1.getCourseByName(c);
                                        if(deadline_change==null){
                                            deadline_change=s2.getCourseByName(c);
                                            if(deadline_change==null){
                                                deadline_change=s3.getCourseByName(c);
                                            }
                                        }
                                        if(deadline_change!=null){
                                            System.out.println("Enter new drop deadline in(yyyy-MM-dd HH:mm)");
                                            String newDropDeadline= scanner.nextLine();
                                            if(newDropDeadline.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")){
                                                String[] dateTimeParts = newDropDeadline.split(" ");
                                                String[] dateParts = dateTimeParts[0].split("-");
                                                String[] timeParts = dateTimeParts[1].split(":");
                                                int yr = Integer.parseInt(dateParts[0]);
                                                int mth = Integer.parseInt(dateParts[1]);
                                                int day = Integer.parseInt(dateParts[2]);
                                                int hr = Integer.parseInt(timeParts[0]);
                                                int min = Integer.parseInt(timeParts[1]);
                                                LocalDateTime DropDeadline_new = LocalDateTime.of(yr, mth, day, hr, min);
                                                deadline_change.setDropDeadline(DropDeadline_new);
                                                System.out.println("Drop deadline updated successfully.");
                                            }
                                            else{
                                                System.out.println("Invalid date format. Enter deadline in correct format specified above.");
                                            }

                                        }
                                        else{
                                            System.out.println("Course not found!");
                                        }
                                        break;
                                    case "6":
                                        System.out.println("Logging out. Please wait......");
                                        SysRunning = false;

                                        break;
                                    default:
                                        System.out.println("Invalid option chosen");
                                        break;
                                }
                            } else {
                                System.out.println("Invalid role.");
                            }

                        }
                    default:
                        System.out.println("Invalid option chosen.");
                        break;

                }
            }

            //if invalid role then write
//  break;

        }


        }
        public static void AddComplaintsToSys(Student s, Complaints cmp){
                  if(!allPortalComplaints.containsKey(s.getName())){
                      allPortalComplaints.put(s.getName(), new ArrayList<>());
                  }
                  allPortalComplaints.get(s.getName()).add(cmp);
        }



    }

















