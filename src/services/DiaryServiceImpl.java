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
            throw new NoSuchEntryException("No such entry");
        }
        return entryService.create(entryCreationRequest);
    }

    @Override
    public void updateEntry(EntryUpdateRequest entryUpdateRequest) {
        Diary diary = checkUsername(entryUpdateRequest.getUsername());
        checkForEntry(diary, "No such entry");
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
        Diary dairy = checkUsername(username);
        return checkForEntry(dairy, "No such entry attached to this user");
    }

    @Override
    public void removeAllDiaries() {
        repository.deleteAll();
    }


    @Override
    public void deleteEntry(String username, long id) {
        if (entryService.checkEntryBy(id).getAuthor().equals(username)) {
            entryService.delete(id);
        }
        throw new UserNotFoundException("User not found");
    }











    @NotNull
    private Entry checkForEntry(Diary dairy, String message) {
        Entry entry = entryService.checkEntryBy(dairy.getUsername());
        if (entry == null) {
            throw new NoSuchEntryException(message);
        }
        return entry;
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

    private Diary checkUsername(String username){
        Diary diaryToFind = repository.findById(username.toLowerCase());
        if(diaryToFind == null) throw new UserNotFoundException("User not found");
        return diaryToFind;
    }

    private Diary checkPassword(LoginRequest loginRequest, Diary diaryToFind) {
        if(diaryToFind.getPassword().equals(loginRequest.getPassword())) diaryToFind.setLock(false);
        else throw new IncorrectPasswordException("Password is Incorrect");
        return diaryToFind;
    }
}
