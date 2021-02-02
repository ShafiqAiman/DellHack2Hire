package com.example.hack2hire20.Model;

public class trainings {
    String Date, Name, Status, Time, Description, Location;


    public trainings(){

    }

    public trainings(String Date, String Name, String Status, String Time, String Description, String Location) {
        this.Date = Date;
        this.Name = Name;
        this.Status = Status;
        this.Time = Time;
        this.Description = Description;
        this.Location = Location;
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

    public String getDescription() {
        return Description;
    }

    public String getLocation() {
        return Location;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setName(String name) { Name = name; }

    public void setStatus(String status) { Status = status; }

    public void setTime(String time) {
        Time = time;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
