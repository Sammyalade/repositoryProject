package datas.repositories;

import datas.models.Entry;

import java.util.List;

public interface EntryRepository {

    Entry save(Entry entry);
    List<Entry> findAll();
    Entry findById(long id);
    long count();
    void delete(long id);
    void delete(Entry entry);
    void deleteAll();
    Entry findBy(String username);
}
