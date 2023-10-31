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
public class Campus extends BaseEntity{

    private int id;
    private String name;
    private ArrayList<Schedule> schedules = new ArrayList<>();
    private ArrayList<Attendance> timetables = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    public ArrayList<Attendance> getTimetables() {
        return timetables;
    }

    public void setTimetables(ArrayList<Attendance> timetables) {
        this.timetables = timetables;
    }

}
