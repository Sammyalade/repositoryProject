package datas.repositories;

import datas.models.Diary;

import java.util.List;

public class DiaryRepositoryImpl implements DiaryRepository{

    private long count;
    @Override
    public Diary save(Diary diary) {
        count++;
        return null;
    }

    @Override
    public List<Diary> findAll() {
        return null;
    }

    @Override
    public Diary findById(String username) {
        return null;
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void delete(String username) {

    }

    @Override
    public void delete(Diary diary) {

    }
}
