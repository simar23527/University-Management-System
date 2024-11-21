import java.util.ArrayList;
import java.util.List;


public class Feedback<T> {
    private Course c;
    private List<FeedbacksInput<T>> feedbackList;

    public Feedback(Course course) {
        this.c = course;
        this.feedbackList=new ArrayList<>();
    }

    public void addCourseFeedback(T stuFeedback, String feedbackDes) {
        feedbackList.add(new FeedbacksInput<>(stuFeedback, feedbackDes));
    }

    public List<FeedbacksInput<T>> getFeedbackList () {
        return feedbackList;
    }

    public Course getCourse() {
        return c;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Feedback for course: ").append(c.getCourseTitle()).append("\n");
        for(int i=0; i<feedbackList.size(); i++){
            FeedbacksInput<T> entry=feedbackList.get(i);
            str.append("Feedback No.: ").append(i+1).append("\n");
            str.append("Feedback Rating: ").append(entry.getCourseRating()).append("\n");
            str.append("Feedback Description: ").append(entry.getFeedbackDescription()).append("\n");
        }
        return str.toString();
    }
}
