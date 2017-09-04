public class MonitorLogger {
    private String status;
    private Student student;

    public MonitorLogger(String status, Student student) {
        this.status = status;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }



}
