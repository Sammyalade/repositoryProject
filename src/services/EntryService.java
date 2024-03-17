package services;

import datas.models.Entry;
import services.dtos.EntryCreationRequest;

import java.util.List;

public interface EntryService {

    Entry create(EntryCreationRequest newEntry);

    List<Entry> getAllEntries();

    Entry checkEntryById(int id);

    void delete(int id);

    void delete(Entry entry);
}
