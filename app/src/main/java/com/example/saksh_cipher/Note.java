package com.example.saksh_cipher;

public class Note {
    private Long id;
    private String title;
    private String content;
    private String date;
    private String time;

    Note(){}
    Note(String title, String content, String date, String time){
        this.time = time;
        this.content = content;
        this.date = date;
        this.title = title;
    }
    Note(long id, String title, String content, String date, String time){
        this.id = id;
        this.time = time;
        this.content = content;
        this.date = date;
        this.title = title;
    }

    public Long getID() {
        return id;
    }

    public void setID(Long ID) {
        this.id = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
