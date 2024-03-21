package services;

import datas.models.Entry;
import datas.repositories.EntryRepository;
import datas.repositories.EntryRepositoryImpl;
import dtos.EntryCreationRequest;
import dtos.EntryUpdateRequest;


import java.util.ArrayList;
import java.util.List;

public class EntryServiceImpl implements EntryService {
    private static EntryRepository entryRepository = new EntryRepositoryImpl();

    @Override
    public Entry create(EntryCreationRequest entry) {
        return entryRepository.save(new Entry(entry.getTitle(), entry.getBody(), entry.getUsername()));
    }

    @Override
    public List<Entry> getAllEntries(String username) {
        List<Entry> result = new ArrayList<>();
        for(Entry entry: entryRepository.findAll()) {
            if (entry.getAuthor().equals(username.toLowerCase())){
                result.add(entry);
            }
        }
        return result;
    }

    @Override
    public Entry checkEntryBy(String username) {
        return entryRepository.findBy(username);
    }

    @Override
    public Entry checkEntryBy(long id) {
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
