package datas.repositoryTest;


import datas.models.Diary;
import java.util.List;
import datas.repositories.DiaryRepository;
import datas.repositories.DiaryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DiaryRepositoryTest {

    private DiaryRepository repository;

    @BeforeEach
    public void initializeRepository(){
        repository = new DiaryRepositoryImpl();
    }

    @Test
    public void oneDiaryAdded_countIsOne(){
        Diary diary = new Diary();
        repository.save(diary);
        assertEquals(1, repository.count());
    }

    @Test
    public void saveDiary_findDiary_diaryIsReturnedTest(){
        Diary diary = new Diary();
        assertEquals(diary, repository.save(diary));
    }

    @Test
    public void findAllDiary_returnsAListOfDiariesTest(){
        Diary diary1 = new Diary();
        Diary diary2 = new Diary();
        repository.save(diary1);
        repository.save(diary2);
        List<Diary> diaries = List.of(new Diary[]{diary1, diary2});
        assertEquals(diaries, repository.findAll());
    }

    @Test
    public void findDiaryById_returnsDiaryTest(){
        Diary diary1 = new Diary("username", "password");
        Diary diary2 = new Diary("username1", "password1");
        repository.save(diary1);
        repository.save(diary2);
        assertEquals(diary1, repository.findById("username"));
        assertEquals(diary2, repository.findById("username1"));
    }

    @Test
    public void addTwoDiary_getCountOfDiary_countReturnsTwoTest(){
        Diary diary1 = new Diary("username", "password");
        Diary diary2 = new Diary("username1", "password1");
        repository.save(diary1);
        repository.save(diary2);
        assertEquals(2, repository.count());
    }

    @Test
    public void addTwoDiary_deleteOneByUsername_countOfDiaryIsOneTest(){
        Diary diary1 = new Diary("username", "password");
        Diary diary2 = new Diary("username1", "password1");
        repository.save(diary1);
        repository.save(diary2);
        repository.delete("username");
        assertEquals(1, repository.count());
    }

    @Test
    public void addTwoDiary_deleteOneDiary_countOfDiaryIsOneTest(){
        Diary diary1 = new Diary("username", "password");
        Diary diary2 = new Diary("username1", "password1");
        repository.save(diary1);
        repository.save(diary2);
        repository.delete(diary1);
        assertEquals(1, repository.count());
    }

    @Test
    public void addTwoDiary_deleteOneByUsername_findDiaryReturnsNullTest(){
        Diary diary1 = new Diary("username", "password");
        Diary diary2 = new Diary("username1", "password1");
        repository.save(diary1);
        repository.save(diary2);
        repository.delete("username");
        assertNull(repository.findById("username"));
    }

    @Test
    public void addTwoDiaries_deleteOneDiary_findDiaryReturnsNullTest(){
        Diary diary1 = new Diary("username", "password");
        Diary diary2 = new Diary("username1", "password1");
        repository.save(diary1);
        repository.save(diary2);
        repository.delete(diary1);
        assertNull(repository.findById("username"));
    }

    @Test
    public void findAllDiaryWhenDiaryIsEmpty_returnsNullTest(){
        assertNull(repository.findAll());
    }

}
