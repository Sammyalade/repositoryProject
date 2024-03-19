package services;

import datas.models.Diary;
import datas.repositories.DiaryRepository;
import datas.repositories.DiaryRepositoryImpl;
import services.dtos.EntryCreationRequest;
import services.dtos.EntryUpdateRequest;
import services.dtos.LoginRequest;
import services.dtos.RegisterRequest;
import services.exceptions.EmptyStringException;
import services.exceptions.IncorrectPasswordException;
import services.exceptions.IncorrectUsernameException;
import services.exceptions.UsernameTakenException;

public class DiaryServiceImpl implements DiaryService{
    private final DiaryRepository repository = new DiaryRepositoryImpl();
    private final EntryService entryService = new EntryServiceImpl();


    @Override
    public Diary register(RegisterRequest registerRequest) {
        checkForEmptyString(registerRequest);
        validateUsername(registerRequest);
        return createDiary(registerRequest);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        checkPassword(loginRequest, checkUsername(loginRequest));
    }

    @Override
    public void logout(String username) {
        repository.findById(username).setLock(true);
    }

    @Override
    public void createEntry(EntryCreationRequest entryCreationRequest) {
        entryService.create(entryCreationRequest);
    }

    @Override
    public void updateEntry(EntryUpdateRequest entryUpdateRequest) {
        entryService.update(entryUpdateRequest);
    }

    @Override
    public void getAllEntries(String username) {
        entryService.getAllEntries("username");
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

    private void checkPassword(LoginRequest loginRequest, Diary diaryToFind) {
        if(diaryToFind.getPassword().equals(loginRequest.getPassword())) diaryToFind.setLock(false);
        else throw new IncorrectPasswordException("Password is Incorrect");
    }
}
