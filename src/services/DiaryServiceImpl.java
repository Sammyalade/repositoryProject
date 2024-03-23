package services;

import datas.models.Diary;
import datas.models.Entry;
import datas.repositories.DiaryRepository;
import datas.repositories.DiaryRepositoryImpl;
import dtos.EntryCreationRequest;
import dtos.EntryUpdateRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;
import exceptions.*;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class DiaryServiceImpl implements DiaryService{
    private static DiaryRepository repository = new DiaryRepositoryImpl();
    private final EntryService entryService = new EntryServiceImpl();


    @Override
    public Diary register(RegisterRequest registerRequest) {
        if(registerRequest.getUsername().isEmpty() || registerRequest.getPassword().isEmpty()) throw new EmptyStringException("Username or Password cannot be empty");
        if(repository.findById(registerRequest.getUsername()) == null) {
            return createDiary(registerRequest);
        }
        throw new UsernameTakenException("Username is taken");
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Diary diaryToFind = findUser(loginRequest.getUsername());
        validatePassword(diaryToFind.getPassword(), loginRequest.getPassword());
        diaryToFind.setLock(false);
        repository.save(diaryToFind);
    }

    @Override
    public void logout(String username) {
        Diary diaryToFind = findUser(username.toLowerCase());
        diaryToFind.setLock(true);
        repository.save(diaryToFind);
    }

    @Override
    public void changePassword(LoginRequest loginRequest) {
        Diary diaryToChangePassword = findUser(loginRequest.getUsername());
        diaryToChangePassword.setPassword(loginRequest.getPassword());
    }






    @Override
    public Entry createEntry(EntryCreationRequest entryCreationRequest) {
        findUser(entryCreationRequest.getUsername());
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
        findUser(username);
        entryService.getAllEntries("username");
    }

    @Override
    public Entry checkEntryById(String username, long id) {
        findUser(username);
        Entry entry = entryService.checkEntryBy(id);
        if(entry.getAuthor().equals(username)){
            return entry;
        }
        throw new NoSuchEntryException("No such entry");
    }

    @Override
    public void removeAllDiaries() {
        repository.deleteAll();
    }


    @Override
    public void deleteEntry(String username, long id) {
        findUser(username);
        entryService.delete(entryService.checkEntryBy(id));
    }













    private void validatePassword(String diaryPassword, String userInputPassword) {
        if(!diaryPassword.equals(userInputPassword)) throw new IncorrectPasswordException("Password is Incorrect");
    }

    @NotNull
    private Diary findUser(String username) {
        Diary diaryToFind = repository.findById(username.toLowerCase());
        checkUserValidity(diaryToFind);
        return diaryToFind;
    }

    private void checkUserValidity(Diary diaryToFind) {
        if(diaryToFind == null) throw new UserNotFoundException("User not found");
    }

    private Diary createDiary(RegisterRequest registerRequest) {
        Diary newDiary = new Diary(registerRequest.getUsername().toLowerCase(),  registerRequest.getPassword());
        return repository.save(newDiary);
    }
}
