package datas.repositories;

import datas.models.Diary;
import exceptions.DiaryAppException;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImpl implements DiaryRepository{

    private long count;
    private final List<Diary> diaries = new ArrayList<>();
    @Override
    public Diary save(Diary diary) {
        removeExistingDiary(diary);
        diaries.add(diary);
        count++;
        return diary;
    }

    private void removeExistingDiary(Diary diary) {
        diaries.removeIf(diaryCheck -> diary.getUsername().equals(diaryCheck.getUsername()));
    }

    @Override
    public List<Diary> findAll() {
        if(diaries.isEmpty()) return null;
        return diaries;
    }

    @Override
    public Diary findById(String username) {
        for(Diary diary: diaries){
            if(diary.getUsername().equals(username)){
                return diary;
            }
        }
        return null;
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void delete(String username) {
        diaries.remove(findById(username));
        count--;
    }

    @Override
    public void delete(Diary diary) {
        diaries.remove(diary);
        count--;
    }

    @Override
    public void deleteAll() {
        diaries.clear();
    }
}
