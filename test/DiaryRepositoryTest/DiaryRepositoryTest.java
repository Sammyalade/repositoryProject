package DiaryRepositoryTest;

import datas.models.Diary;
import datas.repositories.DiaryRepository;
import datas.repositories.DiaryRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiaryRepositoryTest {

    @Test
    public void oneDiaryAdded_countIsOne(){
        DiaryRepository repository = new DiaryRepositoryImpl();
        Diary diary = new Diary();
        repository.save(diary);
        assertEquals(1, repository.count());
    }

}
