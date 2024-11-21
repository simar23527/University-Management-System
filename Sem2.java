import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sem2 implements Semester{
    private static final int sem_num=2;
    private List<Course> available_courses_s2;


    public Sem2(){
        this.available_courses_s2= new ArrayList<Course>();
        addCourses_toList();
    }

    private void addCourses_toList(){
        available_courses_s2.add(new Course("Data Structures and Algorithms", "Dr Debarka Sengupta", "CSE102", "Mon/Wed/Fri 3-4pm", "Introduction to Programming", 4 ,"C102", LocalDateTime.of(2024, 12, 30, 23, 59)));
        available_courses_s2.add(new Course("Probability and Statistics", "Dr Sanjit Kaul", "MTH102", "Mon/Wed/Fri 9-11am", "Linear Algebra", 4 ,"C102", LocalDateTime.of(2024, 12, 30, 23, 59)));
        available_courses_s2.add(new Course("Fundamentals of Biology", "Dr Sriram K.", "BIO101", "Tue/Thur 3-4pm", null, 4 ,"C101", LocalDateTime.of(2024, 12, 30, 23, 59)));
        available_courses_s2.add(new Course("Computer Organization", "Dr Tammam Tilo", "ECE102", "Mon/Wed/Fri 1-3pm", "Digital Circuits", 4 ,"C201", LocalDateTime.of(2024, 12, 30, 23, 59)));
        available_courses_s2.add(new Course("Money and Banking", "Dr Kirti Kanjilal", "SOC101", "Wed 3-6pm", null, 4 ,"C201", LocalDateTime.of(2024, 12, 30, 23, 59)));

    }

    @Override
    public void AddCourses_admin(Course c1, Administrator ad){
        if(ad!=null){
            if(!available_courses_s2.contains(c1)){
                available_courses_s2.add(c1);
                System.out.println("Successfully added course in list");
            }
            else{
                System.out.println("Course already exists in list.");
            }
        }
        else{
            System.out.println("Permission to edit denied. Change your role to access this.");
        }

    }

    @Override
    public void DelCourses_admin(Course c1, Administrator ad){
        if(ad!=null){
            if(available_courses_s2.contains(c1)){
                available_courses_s2.remove(c1);
                System.out.println("Successfully removed course from list.");
            }
            else{
                System.out.println("Course doesn't exist in list.");
            }

        }
        else{
            System.out.println("Permission to edit denied. Change your role to access this.");
        }

    }





@Override
    public void ViewAvailableCourses(){
        System.out.println("Available courses for Semester 2: ");
        for(Course c:available_courses_s2){
            System.out.println("Title: "+c.getCourseTitle());
            System.out.println("Instructor: "+c.getCourseProf());
            System.out.println("Course Code: "+c.getCourseCode());
            System.out.println("Timings: "+c.getCourseTT());
            System.out.println("Prerequisite: "+c.getCoursePre_req());
            System.out.println("Credits: "+c.getCourseCredits());
            System.out.println("Venue: "+c.getCourseRoomnum()+"\n");
        }
    }



    @Override
    public void ViewCourseNames(){
        int i=1;
        System.out.println("Available courses in Semester 2: ");
        for(Course c: available_courses_s2){
            System.out.println(i+": "+c.getCourseTitle());
            i++;
        }
    }

    public Course getCourseByName(String nm){
        for(Course c:available_courses_s2){
            if(c.getCourseTitle().equals(nm)){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Course> getCourseList(){
        return available_courses_s2;
    }

    public List<String> getCourseNames(){
        List<String> names=new ArrayList<>();
        for(Course c:this.getCourseList()){
            names.add(c.getCourseTitle());
        }
        return names;
    }

    @Override
    public double getCGPA(Student s){
        double cg=0;
        HashMap<String, Integer> Mapping=new HashMap<>();
        Mapping.put("A+", 10);
        Mapping.put("A-", 9);
        Mapping.put("B+", 8);
        Mapping.put("B-", 7);
        //Mapping.put("C", 6);
        for(Course c: getCourseList()){
            String gd=s.getGrade(c.getCourseTitle());
            Integer grade_val=Mapping.get(gd);

            if(grade_val!=null){
                cg+=grade_val;
            }
            else{
                if(s.isPreCreated()==false){
                    System.out.println("Invalid Grade.");
                }


            }
        }
        return cg/getCourseList().size();
    }
}
