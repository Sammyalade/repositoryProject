package datas.repositories;

import exceptions.DiaryAppException;

public class DiaryNotFound extends DiaryAppException {
    public DiaryNotFound(String message) {
        super(message);
    }
}
