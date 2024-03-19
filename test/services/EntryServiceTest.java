package services;

import datas.models.Entry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.dtos.EntryCreationRequest;
import services.dtos.EntryUpdateRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EntryServiceTest {

    private EntryService entryService;

    @BeforeEach
    public void initializeEntryService(){
        entryService = new EntryServiceImpl();
    }

    @AfterEach
    public void collapse(){
        entryService.removeAllEntries();
    }

    @Test
    public void createEntry_returnsEntryTest(){
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setTitle("First Entry");
        entryCreationRequest.setBody("This is my first entry");
        assertEquals(new Entry(entryCreationRequest.getTitle(), entryCreationRequest.getBody(), "author"), entryService.create(entryCreationRequest));
    }

    @Test
    public void createTwoEntry_entryIdIsUniqueTest(){
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setTitle("First Entry");
        entryCreationRequest.setBody("This is my first entry");
        EntryCreationRequest entryCreationRequest2 = new EntryCreationRequest();
        entryCreationRequest2.setTitle("First Entry");
        entryCreationRequest2.setBody("This is my first entry");
        Entry entry1 = entryService.create(entryCreationRequest);
        Entry entry2 = entryService.create(entryCreationRequest2);
        assertEquals(1, entry1.getId());
        assertEquals(2, entry2.getId());
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
    public void createEntry_deleteEntryById_entryIsDeletedTest(){
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setTitle("First Entry");
        entryCreationRequest.setBody("This is my first entry");
        Entry entry = entryService.create(entryCreationRequest);
        entryService.delete(1);
        assertNull(entryService.checkEntryById(1));
    }

    @Test
    public void createEntry_deleteEntry_entryIsDeletedTest(){
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setTitle("First Entry");
        entryCreationRequest.setBody("This is my first entry");
        Entry entry = entryService.create(entryCreationRequest);
        entryService.delete(entry);
        assertNull(entryService.checkEntryById(1));
    }
}
