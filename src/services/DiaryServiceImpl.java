package services;

import datas.models.Diary;
import datas.models.Entry;
import datas.repositories.DiaryRepository;
import datas.repositories.DiaryRepositoryImpl;
import services.dtos.EntryCreationRequest;
import services.dtos.EntryUpdateRequest;
import services.dtos.LoginRequest;
import services.dtos.RegisterRequest;
import exceptions.EmptyStringException;
import exceptions.IncorrectPasswordException;
import exceptions.IncorrectUsernameException;
import exceptions.UsernameTakenException;

public class DiaryServiceImpl implements DiaryService{
    private static DiaryRepository repository = new DiaryRepositoryImpl();
    private final EntryService entryService = new EntryServiceImpl();


    @Override
    public Diary register(RegisterRequest registerRequest) {
        checkForEmptyString(registerRequest);
        validateUsername(registerRequest);
        return createDiary(registerRequest);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        repository.save(checkPassword(loginRequest, checkUsername(loginRequest)));
    }

    @Override
    public void logout(String username) {
       Diary diary = repository.findById(username);
       diary.setLock(true);
       repository.save(diary);
    }

    @Override
    public Entry createEntry(EntryCreationRequest entryCreationRequest) {
        return entryService.create(entryCreationRequest);
    }

    @Override
    public void updateEntry(EntryUpdateRequest entryUpdateRequest) {
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
    public void removeAllDiaries() {
        repository.deleteAll();
    }


    private Diary createDiary(RegisterRequest registerRequest) {
        Diary newDiary = new Diary(registerRequest.getUsername().toLowerCase(),  registerRequest.getPassword());
        return repository.save(newDiary);
    }

    private void checkForEmptyString(RegisterRequest registerRequest) {
        if(registerRequest.getUsername().isEmpty() || registerRequest.getPassword().isEmpty()) throw new EmptyStringException("Username or Password cannot be empty");
    }

    private void validateUsername(RegisterRequest registerRequest) {
        Diary diaryToFind = repository.findById(registerRequest.getUsername().toLowerCase());
        if(diaryToFind != null) throw new UsernameTakenException("Username already taken");
    }

    private Diary checkUsername(LoginRequest loginRequest) {
        Diary diaryToFind = repository.findById(loginRequest.getUsername().toLowerCase());
        if(diaryToFind == null) throw new IncorrectUsernameException("Username Is Incorrect");
        return diaryToFind;
    }

    private Diary checkPassword(LoginRequest loginRequest, Diary diaryToFind) {
        if(diaryToFind.getPassword().equals(loginRequest.getPassword())) diaryToFind.setLock(false);
        else throw new IncorrectPasswordException("Password is Incorrect");
        return diaryToFind;
    }
}
