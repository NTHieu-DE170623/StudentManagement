/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.util.ArrayList;
import java.util.Scanner;
import model.Report;
import model.Student;

/**
 *
 * @author THAO LINH
 */
public class Validation {

    static Scanner sc = new Scanner(System.in);

    public String checkInputString(String mess) {
        String result;
        System.out.println(mess + ": ");
        result = sc.nextLine();
        while (result.isEmpty()) {
            System.out.println("Enter again(Can not blank): ");
            result = sc.nextLine();
        }
        return result;
    }

    public int getInputInt(String mess) {
        int result = -1;
        try {
            while (true) {
                System.out.println(mess + ": ");
                String value = sc.nextLine();
                result = Integer.parseInt(value);
                if (result > 0) {
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please, enter a number: ");
        }
        return result;
    }

    public int getInt(String st, int n, int m) {
        int result = -1;
        while (true) {
            try {
                System.out.println(st + ": ");
                String value = sc.nextLine();
                result = Integer.parseInt(value);
                if (result >= n && result <= m) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please, enter a number between " + n + "and " + m);
            }
        }
        return result;
    }

    public boolean checkInputYN() {
        while (true) {
      
            String result = checkInputString("Continue (Y/N)");
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

    public boolean checkInputUD() {
        while (true) {
            String result = checkInputString("Update(U) or delete(D student");
            if (result.equalsIgnoreCase("U")) {
                return true;
            }
            if (result.equalsIgnoreCase("D")) {
                return false;
            }
        }
    }

    public String checkInputCourse() {
        while (true) {
            String result = checkInputString("Input course");
            if (result.equalsIgnoreCase("java")
                    || result.equalsIgnoreCase(".net")
                    || result.equalsIgnoreCase("c/c++")) {
                return result;
            }
            System.err.println("There are only three courses: Java, .Net, C/C++");
            System.out.print("Enter again: ");
        }
    }

    public boolean checkStudentExist(ArrayList<Student> ls, Student s) {
        for (Student student : ls) {
            if (s.getStudentId() == (student.getStudentId())
                    && s.getStudentName().equalsIgnoreCase(student.getStudentName())
                    && s.getSemester().equalsIgnoreCase(student.getSemester())
                    && s.getCourseName().equalsIgnoreCase(student.getCourseName())) {
                return false;
            }
        }
        return true;
    }

    public  boolean checkReportExist(ArrayList<Report> lr, String name,
            String course, int total) {
        for (Report report : lr) {
            if (name.equalsIgnoreCase(report.getName())
                    && course.equalsIgnoreCase(report.getCourse())
                    && total == report.getTotalCourse()) {
                return false;
            }
        }
        return true;
    }
    public boolean checkIdExist(ArrayList<Student> ls, int id, String name) {
        for (Student student : ls) {
            if (id == (student.getStudentId())
                    && !name.equalsIgnoreCase(student.getStudentName())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkChangeInfomation(Student student, int id,
            String name, String semester, String course) {
        return !(id == (student.getStudentId())
                && name.equalsIgnoreCase(student.getStudentName())
                && semester.equalsIgnoreCase(student.getSemester())
                && course.equalsIgnoreCase(student.getCourseName()));
    }

    public boolean checkId(int id, ArrayList<Student> stuList) {
        for (Student o : stuList) {
            if (id == o.getStudentId()) {
                return true;
            }
        }
        return false;
    }
}
