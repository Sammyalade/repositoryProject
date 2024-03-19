package services;

import datas.models.Entry;
import datas.repositories.EntryRepository;
import datas.repositories.EntryRepositoryImpl;
import services.dtos.EntryCreationRequest;
import services.dtos.EntryUpdateRequest;


import java.util.List;

public class EntryServiceImpl implements EntryService {
    private static EntryRepository entryRepository = new EntryRepositoryImpl();
    private long countOfEntry;

    @Override
    public Entry create(EntryCreationRequest entry) {
        ++countOfEntry;
        entry.setId(countOfEntry);
        return entryRepository.save(new Entry(entry.getTitle(), entry.getBody(), "author"));
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

    @Override
    public Entry update(EntryUpdateRequest entryUpdateRequest) {
        entryRepository.delete(entryRepository.findById(entryUpdateRequest.getId()));
        return entryRepository.save(new Entry(entryUpdateRequest.getTitle(), entryUpdateRequest.getBody(), "author"));
    }

    @Override
    public void removeAllEntries() {
        entryRepository.deleteAll();
    }
}
