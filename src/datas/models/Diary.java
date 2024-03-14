package datas.models;

import java.util.ArrayList;
import java.util.List;

public class Diary {

    private String username;
    private String password;
    public boolean isLocked = true;

    public Diary(){}

    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    private final List<Entry> entries = new ArrayList<>();
}
