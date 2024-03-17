package services;

import datas.models.Entry;
import datas.repositories.EntryRepository;
import datas.repositories.EntryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.dtos.EntryCreationRequest;

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
}
