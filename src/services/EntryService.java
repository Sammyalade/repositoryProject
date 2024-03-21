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
    void delete(long id);

    void delete(Entry entry);

    Entry update(EntryUpdateRequest entryUpdateRequest);

    void removeAllEntries();
}
