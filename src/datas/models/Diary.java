package datas.models;

import java.util.ArrayList;
import java.util.List;

public class Diary {

    private String username;
    private String password;
    private boolean isLocked;

    public Diary(){}

    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean equals(Object object){
        return object instanceof Diary;
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

    @Override
    public String toString() {
        return "Diary{" +
                "username='" + username + '\'' +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLock(boolean setLock) {
        isLocked = setLock;
    }
}
