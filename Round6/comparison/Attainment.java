import java.io.*;
import java.lang.*;
import java.util.*;
public class Attainment implements Comparable<Attainment>{
    
    private String courseCode;
    private String studentNumber;
    private int grade;
    

    
    
    Attainment(String courseCode, String studentNumber, int grade){
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
    }
    public String getCourseCode(){
        return courseCode;
    }
    public String getStudentNumber(){
        return studentNumber;
    }
    public int getGrade(){
        return grade;
    }
    public String toString(){
        return String.format("%s %s %d%n", courseCode,studentNumber,grade);
    }

    @Override
    public int compareTo(Attainment other){
        int cmp = studentNumber.compareTo(other.getStudentNumber());
        if(cmp == 0) {
            cmp = courseCode.compareTo(other.getCourseCode());
          }
        return cmp;
    }

    static final Comparator<Attainment> CODE_STUDENT_CMP = new Comparator<Attainment>(){
        @Override
        public int compare(Attainment a, Attainment b){
            int cmp = a.getCourseCode().compareTo(b.getCourseCode());
            if(cmp == 0) {
                cmp = a.getStudentNumber().compareTo(b.getStudentNumber());
              }
            return cmp;
        }
        
    };



    static final Comparator<Attainment> CODE_GRADE_CMP = new Comparator<Attainment>(){



        @Override
        public int compare(Attainment a, Attainment b) {
            int cmp = a.getCourseCode().compareTo(b.getCourseCode());
            if(cmp == 0) {
                cmp = Integer.compare(b.getGrade(),a.getGrade());
              }
            return cmp;
        }
    };
    
    public static void main(String[] args) throws IOException {
        String attainmentFN = args[0];
        ArrayList<Attainment> attainments = new ArrayList<>();
        try(var file = new BufferedReader(new FileReader(attainmentFN))) {
          String line;
          while((line = file.readLine()) != null) {
            String[] studentCourseGrade = line.split(" ", 3);
            attainments.add(new Attainment(studentCourseGrade[1],
                    studentCourseGrade[0], Integer.parseInt(studentCourseGrade[2])));
          }
        }
        System.out.format("Original order:%n%s%n", attainments);
        Collections.sort(attainments);
        System.out.format("Sorted in default order:%n%s%n", attainments);
        attainments.sort(Attainment.CODE_GRADE_CMP);
        System.out.format("Sorted using CODE_GRADE_CMP:%n%s%n", attainments);
        attainments.sort(Attainment.CODE_STUDENT_CMP);
        System.out.format("Sorted using CODE_STUDENT_CMP:%n%s%n", attainments);
      }

}