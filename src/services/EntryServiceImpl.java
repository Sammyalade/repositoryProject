package services;

import datas.models.Entry;
import datas.repositories.EntryRepository;
import services.dtos.EntryCreationRequest;
import services.dtos.EntryUpdateRequest;


import java.util.List;

public class EntryServiceImpl implements EntryService {
    private final EntryRepository entryRepository;
    private long countOfEntry;

    public EntryServiceImpl(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    public Entry create(EntryCreationRequest entry) {
        ++countOfEntry;
        entry.setId(countOfEntry);
        return entryRepository.save(new Entry(entry.getId(), entry.getTitle(), entry.getBody()));
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

    private long generateId() {
        return countOfEntry;
    }

    @Override
    public Entry update(EntryUpdateRequest entryUpdateRequest) {
        entryRepository.delete(entryRepository.findById(entryUpdateRequest.getId()));
        return entryRepository.save(new Entry(entryUpdateRequest.getId(), entryUpdateRequest.getTitle(), entryUpdateRequest.getBody()));
    }
}
