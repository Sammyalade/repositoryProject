package datas.models;

import java.time.LocalDate;

public class Entry {

    private final long id;
    private String title;
    private String body;

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

    public Entry(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public boolean equals(Object object){
        return object instanceof Entry;
    }
}
