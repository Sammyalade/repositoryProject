package services;

import datas.models.Entry;
import datas.repositories.EntryRepository;
import datas.repositories.EntryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.dtos.EntryCreationRequest;
import services.dtos.EntryUpdateRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntryServiceTest {

    private EntryService entryService;

    @BeforeEach
    public void initializeEntryService(){
        EntryRepository entryRepository = new EntryRepositoryImpl();
        entryService = new EntryServiceImpl(entryRepository);
    }

    @Test
    public void createEntry_returnsEntryTest(){
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setTitle("First Entry");
        entryCreationRequest.setBody("This is my first entry");
        assertEquals(new Entry(1, entryCreationRequest.getTitle(), entryCreationRequest.getBody()), entryService.create(entryCreationRequest));
    }

    @Test
    public void createTwoEntry_entryIdIsUniqueTest(){
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setTitle("First Entry");
        entryCreationRequest.setBody("This is my first entry");
        EntryCreationRequest entryCreationRequest2 = new EntryCreationRequest();
        entryCreationRequest2.setTitle("First Entry");
        entryCreationRequest2.setBody("This is my first entry");
        entryService.create(entryCreationRequest);
        entryService.create(entryCreationRequest2);
        assertEquals(1, entryCreationRequest.getId());
        assertEquals(2, entryCreationRequest2.getId());
    }

    @Test
    public void createEntry_updateEntry_entryIsUpdatedTest(){
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setTitle("First Entry");
        entryCreationRequest.setBody("This is my first entry");
        entryService.create(entryCreationRequest);
        EntryUpdateRequest entryUpdateRequest = new EntryUpdateRequest();
        entryUpdateRequest.setId(1);
        entryUpdateRequest.setTitle("Updated Entry");
        entryUpdateRequest.setBody("This is my updated entry");
        Entry entryToUpdate = entryService.update(entryUpdateRequest);
        assertEquals("Updated Entry", entryToUpdate.getTitle());
    }

    @Test
    public void createTwoEntries_getAllEntries_returnAListOfEntries(){
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setTitle("First Entry");
        entryCreationRequest.setBody("This is my first entry");
        EntryCreationRequest entryCreationRequest2 = new EntryCreationRequest();
        entryCreationRequest2.setTitle("First Entry");
        entryCreationRequest2.setBody("This is my first entry");
        Entry entry1 = entryService.create(entryCreationRequest);
        Entry entry2 = entryService.create(entryCreationRequest2);
        List<Entry> entryList = List.of(new Entry[]{entry1, entry2});
        assertEquals(entryList, entryService.getAllEntries());
    }

    @Test
    public void createEntry_findEntryById_returnsEntryTest(){
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setTitle("First Entry");
        entryCreationRequest.setBody("This is my first entry");
        Entry entry = entryService.create(entryCreationRequest);
        assertEquals(entry, entryService.checkEntryById(1));
    }

    @Test
    public void
}
