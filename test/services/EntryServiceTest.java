package services;

import datas.repositories.EntryRepository;
import datas.repositories.EntryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntryServiceTest {

    private EntryService entryService;

    @BeforeEach
    public void initializeEntryService(){
        EntryRepository entryRepository = new EntryRepositoryImpl();
        entryService = new EntryServiceImpl(entryRepository);
    }

    @Test
    public void createEntry_returnsEntryTest(){

        entryService.create()
    }
}
