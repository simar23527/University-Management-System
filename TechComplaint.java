public class TechComplaint extends Complaints {

    private String affectedSys;

    public TechComplaint(String description, String affectedSystem) {
        super(description);
        this.affectedSys = affectedSystem;
    }

    public String getAffectedSystem() {
        return affectedSys;
    }
    public void setAffectedSystem(String affectedSystem) {
        this.affectedSys = affectedSystem;
    }

    @Override
    public String toString() {
        return super.toString() + ", Affected System: " + affectedSys;
    }
}
