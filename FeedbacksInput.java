public class FeedbacksInput<T> {
    private T courseRating;
    private String feedbackDescription;

    public FeedbacksInput(T rating, String des){
        this.courseRating=rating;
        this.feedbackDescription=des;
    }

    public T getCourseRating(){
        return courseRating;
    }

    public String getFeedbackDescription(){
        return feedbackDescription;
    }

    @Override
    public String toString() {
        return "Rating: " + courseRating + ", Comment: " + feedbackDescription;
    }


}
