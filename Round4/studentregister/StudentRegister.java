import java.io.*;
import java.util.*;

public class StudentRegister 
{
    SortedMap<String, Student> students = new TreeMap<>();
    SortedMap<String, Course> coursesn = new TreeMap<>();
    SortedMap<String, Course> coursesc = new TreeMap<>();
    HashMap<String, ArrayList<Attainment>> register = new HashMap<>();
    
    public StudentRegister()
    {

    }

    public ArrayList<Student> getStudents()
    {
        ArrayList<Student> slist = new ArrayList<>(); 

        for(Map.Entry<String, Student> student : students.entrySet())
        {
            slist.add(student.getValue());
        }

        return slist;
    }

    public ArrayList<Course> getCourses()
    {
        ArrayList<Course> nlist = new ArrayList<>(); 

        for(Map.Entry<String, Course> course : coursesn.entrySet())
        {
            nlist.add(course.getValue());
        }

        return nlist;
    }

    public void addStudent(Student student)
    {
        Student new_student = new Student(student.getName(), student.getStudentNumber());

        students.put(new_student.getName(), new_student);
    }

    public void addCourse(Course course)
    {
        Course new_course = new Course(course.getCode(), course.getName(), course.getCredits());

        coursesn.put(new_course.getName(), new_course);
        coursesc.put(new_course.getCode(), new_course);
    }

    public void addAttainment(Attainment att)
    {
        Attainment new_attainment = new Attainment(att.getCourseCode(), att.getStudentNumber(), att.getGrade());
        
        if(register.get(att.getStudentNumber()) == null)
        {
            ArrayList<Attainment> attlist = new ArrayList<>();
            attlist.add(new_attainment);
            register.put(att.getStudentNumber(), attlist);
        }
        else
        {
            register.get(att.getStudentNumber()).add(new_attainment);
        }
    }

    public void printStudentAttainments(String studenNumber, String order)
    {
        boolean find = false;
        String name = "";

        for(Map.Entry<String, Student> stu : students.entrySet())
        {
            if(stu.getValue().getStudentNumber() == studenNumber)
            {
                find = true;
                name = stu.getKey();
            }
        }
        
        if(!find)
        {
            System.out.format("Unknown student number: %s%n", studenNumber);
        }
        else
        {
            System.out.format("%s (%s):%n", name, studenNumber);

            SortedMap<String, Attainment> srt = new TreeMap<>();

            if(order == "by name")
            {
                srt.clear();
                for(Attainment line : register.get(studenNumber))
                {
                    srt.put(coursesc.get(line.getCourseCode()).getName(), line);
                }

                for(Map.Entry<String, Attainment> line : srt.entrySet())
                {
                    System.out.format("  %s %s: %d%n", line.getValue().getCourseCode(), line.getKey(), line.getValue().getGrade());
                }
            }
        
            else if(order == "by code")
            {
                srt.clear();
                for(Attainment line : register.get(studenNumber))
                {
                    srt.put(coursesc.get(line.getCourseCode()).getCode(), line);
                }

                for(Map.Entry<String, Attainment> line : srt.entrySet())
                {
                    System.out.format("  %s %s: %d%n", line.getKey(), coursesc.get(line.getKey()).getName(), line.getValue().getGrade());
                }
            }

            else{System.out.println("pieleen meni");}
        
        }

    }

    public void printStudentAttainments(String studenNumber)
    {
        boolean find = false;
        String name = "";

        for(Map.Entry<String, Student> stu : students.entrySet())
        {
            if(stu.getValue().getStudentNumber() == studenNumber)
            {
                find = true;
                name = stu.getKey();
            }
        }
        
        if(!find)
        {
            System.out.format("Unknown student number: %s%n", studenNumber);
        }
        else
        {
            System.out.format("%s (%s):%n", name, studenNumber);

            for(Attainment line : register.get(studenNumber))
            {
                System.out.format("  %s %s: %d%n", line.getCourseCode(), coursesc.get(line.getCourseCode()).getName(), line.getGrade());
            }

        }
    }
}

class Student
{
    private String name;
    private String studentNumber;

    public Student(String name, String studentNumber)
    {
        this.name = name;
        this.studentNumber = studentNumber;
    }

    public String getName(){return this.name;}
    public String getStudentNumber(){return this.studentNumber;}
}

class Course
{
    private String code;
    private String name;
    private int credits;
    
    public Course(String code, String name, int credits)
    {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public String getCode(){return this.code;}
    public String getName(){return this.name;}
    public int getCredits(){return this.credits;}

}

class Attainment
{
    private String courseCode;
    private String studentNumber;
    private int grade;

    Attainment(String courseCode, String studentNumber, int grade)
    {
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
    }

    public String getCourseCode() {return courseCode;}
    public String getStudentNumber() {return studentNumber;}
    public int getGrade() {return grade;}
}

