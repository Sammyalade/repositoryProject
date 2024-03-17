package services;

import datas.models.Entry;
import datas.repositories.EntryRepository;


import java.util.List;

public class EntryServiceImpl implements EntryService {
    private final EntryRepository entryRepository;

    public EntryServiceImpl(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    public Entry create(Entry entry) {
        return entryRepository.save(entry);
    }

    @Override
    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    @Override
    public Entry checkEntryById(int id) {
        return entryRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        entryRepository.delete(id);
    }

    @Override
    public void delete(Entry entry) {
        entryRepository.delete(entry);
    }
}
