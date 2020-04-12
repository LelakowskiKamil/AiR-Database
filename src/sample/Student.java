package sample;

public class Student {
    private String name;
    private String surname;
    private String sex;
    private String email;
    private String major;
    private int studentCard;

    public Student(String name, String surname, String sex, String email, String major, int studentCard) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.email = email;
        this.major = major;
        this.studentCard = studentCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getStudentCard() {
        return studentCard;
    }

    public void setStudentCard(int studentCard) {
        this.studentCard = studentCard;
    }
}
