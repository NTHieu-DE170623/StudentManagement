/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.Validation;
import java.util.ArrayList;
import java.util.Collections;
import model.Report;
import model.SortByName;
import model.Student;
import view.Menu;

/**
 *
 * @author THAO LINH
 */
public class Program extends Menu<String> {

    ArrayList<Student> stuList;
    ArrayList<Report> reportList;
    Validation va  = new Validation();
    static String opsList[] = {"Create", "Find and Sort", "Update/Delete", "Report", "Exit"};

    public Program() {
        super("Student Menu", opsList);
        stuList = new ArrayList<>();
        reportList = new ArrayList<>();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1: {
                createStudent();
                break;
            }
            case 2: {
                findAndSort(stuList);
                break;
            }
            case 3: {
                UpdateAndDelete(stuList);
                break;
            }
            case 4: {
                report(stuList);
                break;
            }
            case 5:
                System.exit(0);
            default:
                System.out.println("Please, enter again!");
        }
    }

    public void createStudent() {

        while (true) {
            int id = 0;
            do {
                id = va.getInputInt("Input Id's student");
            } while (id == -1);

            String studentName = va.checkInputString("Input name's student");
            if (!va.checkIdExist(stuList, id, studentName)) {
                System.out.println("Id has exist student. Please, input again");
                continue;
            }
            String semester = va.checkInputString("Input semester");
            String courseName = va.checkInputCourse();
            Student s = new Student(id, studentName, semester, courseName);
            if (va.checkStudentExist(stuList, s)) {
                stuList.add(s);
                System.out.println("Add student successfully");
            } else {
                System.out.println("Student has exist");
            }
            if (stuList.size() > 3) {
                if (!va.checkInputYN()) {
                    return;
                }
            }

        }

    }

    public void findAndSort(ArrayList<Student> stuList) {
        if (stuList.isEmpty()) {
            System.out.println("List Sutdent empty");
            return;
        }
        ArrayList<Student> findStudents = findByName(stuList);
        if (findStudents.isEmpty()) {
            System.out.println("Name not exist");
        } else {
            System.out.println("Sort by name");
            Collections.sort(findStudents, new SortByName());
            for (Student o : findStudents) {
                System.out.println(o.toString());
            }
        }
    }

    public ArrayList<Student> findByName(ArrayList<Student> stuList) {
        ArrayList<Student> findStudents = new ArrayList<>();
        String studentName = va.checkInputString("Input name's student ");
        for (Student o : stuList) {
            if (o.getStudentName().contains(studentName)) {
                findStudents.add(o);
            }
        }

        return findStudents;
    }

    public ArrayList<Student> getListStudentById(ArrayList<Student> stuList, int id) {
        ArrayList<Student> getListStudentById = new ArrayList<>();
        for (Student student : stuList) {
            if (id == (student.getStudentId())) {
                getListStudentById.add(student);
            }
        }
        return getListStudentById;
    }

    public void UpdateAndDelete(ArrayList<Student> stuList) {
        if (stuList.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        for (int i = 0; i < stuList.size(); i++) {
            System.out.println(stuList.get(i));
        }
        int id = va.getInputInt("Input Id's student");
        ArrayList<Student> findStudents = getListStudentById(stuList, id);
        if (findStudents.isEmpty()) {
            System.out.println("Not exist student");
            return;
        } else {
            Student student = getStudentByListFound(findStudents);
            if (va.checkInputUD()) {
                int idStudent = va.getInputInt("Input Id's student");
                String studentName = va.checkInputString("Input name's student");
                String semester = va.checkInputString("Input semester");
                String courseName = va.checkInputCourse();
                if (!va.checkChangeInfomation(student, id, studentName, semester, courseName)) {
                    System.err.println("Nothing change.");
                }
                Student s = new Student(idStudent, studentName, semester, courseName);
                if (va.checkStudentExist(stuList, s)) {
                    student.setStudentId(idStudent);
                    student.setStudentName(studentName);
                    student.setSemester(semester);
                    student.setCourseName(courseName);
                    System.err.println("Update success.");
                }
                return;
            } else {
                stuList.remove(student);
                System.err.println("Delete success.");
                return;

            }
        }

    }

    public Student getStudentByListFound(ArrayList<Student> listStudentFindByName) {
        System.out.println("List student found: ");
        int count = 1;
        System.out.printf("%-10s%-15s%-15s%-15s\n", "Number", "Student name",
                "semester", "Course Name");
        for (Student student : listStudentFindByName) {
            System.out.printf("%-10d%-15s%-15s%-15s\n", count,
                    student.getStudentName(), student.getSemester(),
                    student.getCourseName());
            count++;
        }
        System.out.print("Enter student: ");
        int choice = va.getInt("Input ", 1, listStudentFindByName.size());
        return listStudentFindByName.get(choice - 1);
    }

    public void report(ArrayList<Student> stuList) {
        if (stuList.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        for (Student student : stuList) {
            int id = student.getStudentId();
            String courseName = student.getCourseName();
            String studentName = student.getStudentName();
            int total = 0;
            for (Student studentCountTotal : stuList) {
                if (id == (studentCountTotal.getStudentId())
                        && courseName.equalsIgnoreCase(studentCountTotal.getCourseName())) {
                    total++;
                }
            }
            if (va.checkReportExist(reportList, studentName, courseName, total)) {
                reportList.add(new Report(student.getStudentName(), student.getCourseName(), total));
            }
        }
        for (int i = 0; i < reportList.size(); i++) {
            System.out.printf("%-15s|%-10s|%-5d\n", reportList.get(i).getName(),
                    reportList.get(i).getCourse(), reportList.get(i).getTotalCourse());
        }
    }
}
