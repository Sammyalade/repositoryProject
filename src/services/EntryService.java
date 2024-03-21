package services;

import datas.models.Entry;
import dtos.EntryCreationRequest;
import dtos.EntryUpdateRequest;

import java.util.List;

public interface EntryService {

    Entry create(EntryCreationRequest newEntry);

    List<Entry> getAllEntries(String username);

    Entry checkEntryBy(String username);
    Entry checkEntryBy(long id);
    void delete(int id);

    void delete(Entry entry);

    Entry update(EntryUpdateRequest entryUpdateRequest);

    void removeAllEntries();
}
