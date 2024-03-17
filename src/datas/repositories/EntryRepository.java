package datas.repositories;

import datas.models.Entry;

import java.util.List;

public interface EntryRepository {

    Entry save(Entry entry);
    List<Entry> findAll();
    Entry findById(long id);
    long count();
    void delete(int id);
    void delete(Entry entry);
}
