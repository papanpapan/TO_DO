package com.example.samim.to_do_application;

/**
 * Created by SAMIM on 11/12/2017.
 */

public class Item {
    String title,description,status,date;

    Item(String title, String description, String date, String status) {
        this.setTitle(title);
        this.setDescription(description);
        this.setDate(date);
        this.setStatus(status);

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status =status ;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date =date ;
    }


}
