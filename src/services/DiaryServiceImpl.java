package services;

import datas.models.Diary;
import datas.models.Entry;
import datas.repositories.DiaryNotFound;
import datas.repositories.DiaryRepository;
import datas.repositories.DiaryRepositoryImpl;
import dtos.EntryCreationRequest;
import dtos.EntryUpdateRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;
import exceptions.*;
import org.jetbrains.annotations.NotNull;

public class DiaryServiceImpl implements DiaryService{
    private static DiaryRepository repository = new DiaryRepositoryImpl();
    private final EntryService entryService = new EntryServiceImpl();


    @Override
    public Diary register(RegisterRequest registerRequest) {
        if(registerRequest.getUsername().isEmpty() || registerRequest.getPassword().isEmpty()) throw new EmptyStringException("Username or Password cannot be empty");

        Diary diaryToFind = repository.findById(registerRequest.getUsername().toLowerCase());
        if(diaryToFind != null) throw new UsernameTakenException("Username already taken");

        return createDiary(registerRequest);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Diary diaryToFind = repository.findById(loginRequest.getUsername().toLowerCase());
        if(diaryToFind == null) throw new UserNotFoundException("User not found");
        diaryToFind.setLock(false);
        repository.save(diaryToFind);
    }

    @Override
    public void logout(String username) {
       Diary diary = repository.findById(username);
       if(diary == null){
           throw new DiaryNotFound("Diary not found");
       }
       diary.setLock(true);
       repository.save(diary);
    }




    @Override
    public Entry createEntry(EntryCreationRequest entryCreationRequest) {
        Diary diary = repository.findById(entryCreationRequest.getUsername());
        if(diary == null){
            throw new UserNotFoundException("No user by that username");
        }
        return entryService.create(entryCreationRequest);
    }

    @Override
    public void updateEntry(EntryUpdateRequest entryUpdateRequest) {
        Entry entry = entryService.checkEntryBy(entryUpdateRequest.getId());
        if (entry == null) {
            throw new NoSuchEntryException("No such entry attached to this user");
        }
        entryService.update(entryUpdateRequest);
    }

    @Override
    public void getAllEntries(String username) {
        entryService.getAllEntries("username");
    }

    @Override
    public void changePassword(LoginRequest loginRequest) {
        Diary diaryToChangePassword = repository.findById(loginRequest.getUsername());
        diaryToChangePassword.setPassword(loginRequest.getPassword());
    }

    @Override
    public Entry checkEntryById(String username, long id) {
        if(repository.findById(username) == null){
            throw new UserNotFoundException("User not found");
        }
        return entryService.checkEntryBy(id);
    }

    @Override
    public void removeAllDiaries() {
        repository.deleteAll();
    }


    @Override
    public void deleteEntry(String username, long id) {
        if(repository.findById(username) == null){
            throw new UserNotFoundException("User not found");
        }
        entryService.delete(entryService.checkEntryBy(id));
    }













    private Diary createDiary(RegisterRequest registerRequest) {
        Diary newDiary = new Diary(registerRequest.getUsername().toLowerCase(),  registerRequest.getPassword());
        return repository.save(newDiary);
    }
}
