/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Group_Student {
    private Group group;
    private Student student;
    private ArrayList<Schedule> timetables = new ArrayList<>();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ArrayList<Schedule> getTimetables() {
        return timetables;
    }

    public void setTimetables(ArrayList<Schedule> timetables) {
        this.timetables = timetables;
    }
    
}
