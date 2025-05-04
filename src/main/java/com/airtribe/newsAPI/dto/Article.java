//package com.airtribe.newsAPI.dto;
//
//import java.util.Date;
//
//public class Article {
//
//    private Date date;
//
//    private String title;
//
//    private String body;
//
//    public Article(Date date, String title, String body) {
//        this.date = date;
//        this.title = title;
//        this.body = body;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getBody() {
//        return body;
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }
//}
package com.airtribe.newsAPI.dto;

public class Article {

    private String date;
    private String title;
    private String body;

    // Constructor
    public Article(String date, String title, String body) {
        this.date = date;
        this.title = title;
        this.body = body;
    }

    // Getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

