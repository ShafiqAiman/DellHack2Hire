package com.example.hack2hire20.Model;

public class historyevents {
    String Date, Name, Status, Time;


    public historyevents(){

    }

    public historyevents(String Date, String Name, String Status, String Time) {
        this.Date = Date;
        this.Name = Name;
        this.Status = Status;
        this.Time = Time;
    }

    public String getDate() {
        return Date;
    }

    public String getName() {
        return Name;
    }

    public String getStatus() {
        return Status;
    }

    public String getTime() {
        return Time;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setName(String name) { Name = name; }

    public void setStatus(String status) { Status = status; }

    public void setTime(String time) {
        Time = time;
    }

}
