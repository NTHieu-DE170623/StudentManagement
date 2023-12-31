/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Comparator;

/**
 *
 * @author THAO LINH
 */
public class SortByName implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getStudentName().compareTo(o2.getStudentName());
    }
    
}
