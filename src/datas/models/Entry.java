package datas.models;

import java.time.LocalDate;

public class Entry {

    private long id;
    private String title;
    private String body;

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId() {
        return id;
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

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    private final LocalDate dateCreated = LocalDate.now();

    public Entry(String title, String body, String author) {
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public boolean equals(Object object){
        return object instanceof Entry;
    }
}
