
import java.util.Scanner;

public class Student {
    private int stdId;
    private String name;
    private String deprt;
    private double subject1Marks;
    private double subject2Marks;
    private double subject3Marks;
    private double subject4Marks;
    private double subject5Marks;

    Student(int stdId, String name, String deprt,
            double subject1Marks, double subject2Marks,
            double subject3Marks, double subject4Marks,
            double subject5Marks) {

        this.stdId = stdId;
        this.name = name;
        this.deprt = deprt;
        this.subject1Marks = subject1Marks;
        this.subject2Marks = subject2Marks;
        this.subject3Marks = subject3Marks;
        this.subject4Marks = subject4Marks;
        this.subject5Marks = subject5Marks;
    }

    public void display() {
        System.out.println("\n========== Student Report ==========");
        System.out.println("Student ID: " + stdId);
        System.out.println("Student Name: " + name);
        System.out.println("Student Department: " + deprt);
        displaySubjectResult("Subject 1", subject1Marks);
        displaySubjectResult("Subject 2", subject2Marks);
        displaySubjectResult("Subject 3", subject3Marks);
        displaySubjectResult("Subject 4", subject4Marks);
        displaySubjectResult("Subject 5", subject5Marks);
    }

    private void displaySubjectResult(String subject, double marks) {
        String result = marks >= 40 ? "Pass" : "Fail";
        System.out.println(subject + " Marks: " + marks + " - " + result);
    }

    public void totalMarks() {
        System.out.println("Total Marks: " + getTotalMarks());
    }

    public void averageMarks() {
        System.out.println("Average Marks: " + getTotalMarks() / 5);
    }

    private double getTotalMarks() {
        return subject1Marks + subject2Marks + subject3Marks + subject4Marks + subject5Marks;
    }

    public void grade() {
        double average = (subject1Marks + subject2Marks
                          + subject3Marks + subject4Marks + subject5Marks) / 5;

        if (average >= 90) {
            System.out.println("Grade: A+");
        } else if (average >= 80) {
            System.out.println("Grade: A");
        } else if (average >= 70) {
            System.out.println("Grade: B");
        } else if (average >= 60) {
            System.out.println("Grade: C");
        } else if (average >= 50) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F");
        }
    }

    public int getStudentId() {
        return stdId;
    }

    public boolean updateSubjectMarks(int subjectNumber, double marks) {
        if (subjectNumber == 1) {
            subject1Marks = marks;
        } else if (subjectNumber == 2) {
            subject2Marks = marks;
        } else if (subjectNumber == 3) {
            subject3Marks = marks;
        } else if (subjectNumber == 4) {
            subject4Marks = marks;
        } else if (subjectNumber == 5) {
            subject5Marks = marks;
        } else {
            return false;
        }
        return true;
    }

    public static Student searchStudent(Student[] students, int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public static Student[] deleteStudent(Student[] students, int studentId) {
        int remainingCount = 0;
        for (Student student : students) {
            if (student.getStudentId() != studentId) {
                remainingCount++;
            }
        }

        if (remainingCount == students.length) {
            return students;
        }

        Student[] remainingStudents = new Student[remainingCount];
        int index = 0;
        for (Student student : students) {
            if (student.getStudentId() != studentId) {
                remainingStudents[index++] = student;
            }
        }
        return remainingStudents;
    }

    public static void displayClassTopper(Student[] students) {
        if (students.length == 0) {
            System.out.println("\nNo students available to determine the class topper.");
            return;
        }

        Student topper = students[0];
        for (Student student : students) {
            if (student.getTotalMarks() > topper.getTotalMarks()) {
                topper = student;
            }
        }

        System.out.println("\n========== Class Topper ==========");
        System.out.println("Student Name: " + topper.name);
        System.out.println("Student ID: " + topper.stdId);
        System.out.println("Total Marks: " + topper.getTotalMarks());
    }


    public static double validateMarks(Scanner sc, String subject) {

        double marks;

        while (true) {
            System.out.println("Enter " + subject + " Marks:");
            if (!sc.hasNextDouble()) {
                sc.next();
                System.out.println("Invalid Marks! Enter a number between 0 and 100.");
                continue;
            }

            marks = sc.nextDouble();

            if (marks >= 0 && marks <= 100) {
                return marks;
            } else {
                System.out.println("Invalid Marks! Enter marks between 0 and 100.");
            }
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Student Management System =====");

        System.out.println("Enter Student ID:");
        int stdId = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Student Name:");
        String name = sc.nextLine();

        System.out.println("Enter Student Department:");
        String deprt = sc.nextLine();

        double subject1Marks = validateMarks(sc, "Subject 1");
        double subject2Marks = validateMarks(sc, "Subject 2");
        double subject3Marks = validateMarks(sc, "Subject 3");
        double subject4Marks = validateMarks(sc, "Subject 4");
        double subject5Marks = validateMarks(sc, "Subject 5");

        

        Student std = new Student(stdId, name, deprt,
                subject1Marks, subject2Marks, subject3Marks, subject4Marks, subject5Marks);

        std.display();
        std.totalMarks();
        std.averageMarks();
        std.grade();

        Student[] students = { std };
        System.out.println("\nEnter Student ID to Search:");
        int searchId = sc.nextInt();
        Student foundStudent = searchStudent(students, searchId);

        if (foundStudent != null) {
            System.out.println("\nStudent Found!");
            foundStudent.display();
            System.out.println("\nWould you like to update marks? (y/n)");
            if (sc.next().equalsIgnoreCase("y")) {
                int subjectNumber;
                while (true) {
                    System.out.println("Enter subject number to update (1-5):");
                    if (!sc.hasNextInt()) {
                        sc.next();
                        System.out.println("Invalid subject number. Enter a number from 1 to 5.");
                        continue;
                    }

                    subjectNumber = sc.nextInt();
                    if (subjectNumber >= 1 && subjectNumber <= 5) {
                        break;
                    }
                    System.out.println("Invalid subject number. Enter a number from 1 to 5.");
                }

                double updatedMarks = validateMarks(sc, "Subject " + subjectNumber);
                if (foundStudent.updateSubjectMarks(subjectNumber, updatedMarks)) {
                    System.out.println("\nMarks updated successfully.");
                    foundStudent.display();
                    foundStudent.totalMarks();
                    foundStudent.averageMarks();
                    foundStudent.grade();
                }
            }

            System.out.println("\nWould you like to delete this student? (y/n)");
            if (sc.next().equalsIgnoreCase("y")) {
                students = deleteStudent(students, searchId);
                if (searchStudent(students, searchId) == null) {
                    System.out.println("Student deleted successfully.");
                }
            }
        } else {
            System.out.println("\nStudent with ID " + searchId + " not found.");
        }

        displayClassTopper(students);

        
        sc.close();
    }
}