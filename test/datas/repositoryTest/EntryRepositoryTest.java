package datas.repositoryTest;

import datas.models.Entry;
import datas.repositories.EntryRepository;
import datas.repositories.EntryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EntryRepositoryTest {

    private EntryRepository entryRepository;

    @BeforeEach
    public void initializeEntryRepository(){
        entryRepository = new EntryRepositoryImpl();
    }
    @Test
    public void saveEntry_countReturnsOneTest(){
        Entry entry = new Entry("Story Of My Life", "123", "author");
        entryRepository.save(entry);
        assertEquals(1, entryRepository.count());
    }

    @Test
    public void find_entry_returnsEntry(){
        Entry entry = new Entry("Story Of My Life", "123", "author");
        entryRepository.save(entry);
        assertEquals(entry, entryRepository.findById(1));
    }

    @Test
    public void findEntryWhenItIsEmpty_returnsNullTest(){
        assertNull(entryRepository.findById(1));
    }

    @Test
    public void findAllEntry_returnsListOfEntries(){
        Entry entry = new Entry("Story Of My Life", "123", "author");
        Entry entry1 = new Entry("Story Of My Life", "123", "author");
        entryRepository.save(entry);
        entryRepository.save(entry1);
        List<Entry> entries = List.of(new Entry[]{entry, entry1});
        assertEquals(entries, entryRepository.findAll());
    }

    @Test
    public void findAllWhenItIsEmpty_returnsNullTest(){
        assertNull(entryRepository.findAll());
    }

    @Test
    public void addTwoEntry_deleteOneEntry_countOfEntryIsOneTest(){
        Entry entry = new Entry("Story Of My Life", "123", "author");
        Entry entry1 = new Entry("Story Of My Life", "123", "author");
        entryRepository.save(entry);
        entryRepository.save(entry1);
        entryRepository.delete(1);
        assertEquals(1, entryRepository.count());
    }

    @Test
    public void addTwoEntry_deleteOneById_findEntryReturnsNullTest(){
        Entry entry = new Entry( "Story Of My Life", "123", "author");
        Entry entry1 = new Entry( "Story Of My Life", "123", "author");
        entryRepository.save(entry);
        entryRepository.save(entry1);
        entryRepository.delete(1);
        assertNull(entryRepository.findById(1));
    }

    @Test
    public void addTwoEntries_deleteOneEntry_countOfEntryIsOneTest(){
        Entry entry = new Entry("Story Of My Life", "123", "author");
        Entry entry1 = new Entry("Story Of My Life", "123", "author");
        entryRepository.save(entry);
        entryRepository.save(entry1);
        entryRepository.delete(entry);
        assertEquals(1, entryRepository.count());
    }

    @Test
    public void addTwoEntries_deleteOneEntry_findEntryReturnsNullTest(){
        Entry entry = new Entry("Story Of My Life", "123", "author");
        Entry entry1 = new Entry("Story Of My Life", "123", "author");
        entryRepository.save(entry);
        entryRepository.save(entry1);
        entryRepository.delete(entry);
        assertNull(entryRepository.findById(1));
    }

    @Test
    public void createEntry_returnsEntryTest(){
        Entry entry = new Entry("Story Of My Life", "123", "author");
        assertEquals(entry, entryRepository.save(entry));
    }
}
