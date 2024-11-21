import java.util.ArrayList;
import java.util.List;

public class Administrator {
        private static final String adminPwd="AdminIIITD@123";
        private static final String name="AdminIIITD";

        public Administrator(){

        }

        public boolean VerificationPwd(String ip){
            return adminPwd.equals(ip);
        }
 //View all students and their records
        public void viewStudentsRegistered(){
              for(Student reg_stu:Main.registered_studs_portal){
                  System.out.println("Name: "+reg_stu.getName());
                  System.out.println("Official mail: "+reg_stu.getContact());
                  System.out.println("CGPA: "+reg_stu.getCGPA());
              }
        }
        //updating student records-mail
    public void UpdateStuMail(Student s, String mailID){
        s.setContacts(mailID);
        System.out.println("Student mail updated successfully");
    }
//updating student cg
    public void UpdateStuCG(Student s){
            Sem1 s1=new Sem1();
            Sem2 s2=new Sem2();
            double s1_cg=s1.getCGPA(s);
            double s2_cg=s2.getCGPA(s);
            double newCG=(s1_cg+s2_cg)/2.0;
            s.setCGPA(newCG);
            if(!s.isPreCreated()){
                System.out.println("Student CGPA updated successfully");
            }
    }
    //updating student grade
    public void UpdateGrade(Course c, Administrator admin, Student s, String newGr){
           s.setGrade(c.getCourseTitle(), newGr, admin);


    }


    //Assigning profs to courses
        public void AssignProfToCourse(Course c, Administrator ad, Professor profs){
            c.setCourseProf(c, ad, profs);
            System.out.println("Professor "+profs.getName()+" assigned to course.");
        }

        //Managing the course catalog
        public void AddSem1Courses(Sem1 s1, Course c1){
            s1.AddCourses_admin(c1, this);
        }
        public void AddSem2Courses(Sem2 s2, Course c2){
            s2.AddCourses_admin(c2, this);
        }
        public void DELSem1Courses(Sem1 s1, Course c1){
           s1.DelCourses_admin(c1, this);
        }
        public void DELSem2Courses(Sem2 s2, Course c2){
               s2.DelCourses_admin(c2, this);
        }
        public void viewAvailCourses_SEM1_ad(Sem1 s1, Administrator ad){
            s1.ViewAvailableCourses();
        }
        public void viewAvailCourses_SEM2_ad(Sem2 s2, Administrator ad){
         s2.ViewAvailableCourses();
        }


        //4-Handle Complaints
        public void Stu_Complaints(){
            for(String s_name: Main.allPortalComplaints.keySet()){
                System.out.println("Complaints submitted by "+s_name);
                List<Complaints> cmps_by_std=Main.allPortalComplaints.get(s_name);
                for(Complaints c:cmps_by_std){
                    System.out.println(c+"\n");
                    System.out.println("Complaint ID: "+c.getID()+"\n");
                    if (c instanceof AdminComplaint) {
                        System.out.println("Department: " + ((AdminComplaint) c).getDepartment() + "\n");
                    } else if (c instanceof TechComplaint) {
                        System.out.println("Affected System: " + ((TechComplaint) c).getAffectedSystem() + "\n");
                    }
                }
            }

           // s.viewComplaints();
        }
        public void chnageStatus(Student s, Complaints cmp, String newans){
            if(cmp.getStatus().equals("Pending")){
                cmp.setStatus("Resolved");
                cmp.setRes(newans);
               // cmp.setDes(newans);
                System.out.println("Solution to complaint "+cmp.getRes());
                System.out.println("Complaint "+cmp.getID()+" resolved successfully.\n");

                if (cmp instanceof AdminComplaint) {
                    System.out.println("Administrative complaint for department: " + ((AdminComplaint) cmp).getDepartment() + " resolved\n.");
                } else if (cmp instanceof TechComplaint) {
                    System.out.println("Technical complaint for system: " + ((TechComplaint) cmp).getAffectedSystem() + " resolved\n.");
                }
            }
        }






}
