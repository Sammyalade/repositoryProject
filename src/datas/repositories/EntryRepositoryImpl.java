package datas.repositories;

import datas.models.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepository{
    private long count;
    private final List<Entry> entries = new ArrayList<>();

    @Override
    public Entry save(Entry entry) {
        entries.add(entry);
        count++;
        return entry;
    }

    @Override
    public List<Entry> findAll() {
        if(entries.isEmpty()) return null;
        return entries;
    }

    @Override
    public Entry findById(int id) {
        for(Entry entry: entries) {
            if (entry.getId() == id) return entry;
        }
        return null;
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void delete(int id) {
        entries.remove(findById(id));
        count--;
    }

    @Override
    public void delete(Entry entry) {
        entries.remove(entry);
        count--;
    }
}
