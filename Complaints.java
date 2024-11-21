import java.util.Objects;

public class Complaints {
    private static int complaints_total=1;
    private int id_complaint;
    private String description_complaint;
    private String description_resolution;
    private String status_complaint;

    public Complaints(String des){
        this.id_complaint=complaints_total++;
        this.description_complaint=des;
        this.status_complaint="Pending";
        this.description_resolution="";
    }

    public int getID(){
        return id_complaint;
    }
    public String getDes(){
        return description_complaint;
    }
    public void setDes(String resolution){
        this.description_complaint=resolution;
    }
    public String getRes(){
        return description_resolution;
    }
    public void setRes(String resolution){
        this.description_resolution=resolution;
    }
    public String getStatus(){
        return status_complaint;
    }
    public void setStatus(String newStatus){
        this.status_complaint=newStatus;
    }



    @Override
    public String toString(){
        return "Complaint ID: "+id_complaint+"\nDescription: "+description_complaint+"\nStatus: "+status_complaint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Complaints that = (Complaints) o;
        return Objects.equals(description_complaint, that.description_complaint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description_complaint);
    }
}
