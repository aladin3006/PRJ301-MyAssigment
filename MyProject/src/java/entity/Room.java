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
public class Room extends BaseEntity {

    private String rid;
    private ArrayList<Schedule> schedules = new ArrayList<>();
    private ArrayList<Attendance> timetables = new ArrayList<>();

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
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
