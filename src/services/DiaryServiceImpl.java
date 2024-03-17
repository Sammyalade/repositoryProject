package services;

import datas.models.Diary;
import datas.repositories.DiaryRepository;
import services.dtos.LoginRequest;
import services.dtos.RegisterRequest;
import services.exceptions.EmptyStringException;
import services.exceptions.IncorrectPasswordException;
import services.exceptions.IncorrectUsernameException;
import services.exceptions.UsernameTakenException;

public class DiaryServiceImpl implements DiaryService{
    private final DiaryRepository repository;

    public DiaryServiceImpl(DiaryRepository diaryRepository){
        repository = diaryRepository;
    }

    @Override
    public Diary register(RegisterRequest registerRequest) {
        checkForEmptyString(registerRequest);
        validateUsername(registerRequest);
        return createDiary(registerRequest);
    }

    @Override
    public boolean login(LoginRequest loginRequest) {
        return checkPassword(loginRequest, checkUsername(loginRequest));
    }

    @Override
    public void createEntry(LoginRequest loginRequest, ) {

    }


    private Diary createDiary(RegisterRequest registerRequest) {
        Diary newDiary = new Diary(registerRequest.getUsername(),  registerRequest.getPassword());
        return repository.save(newDiary);
    }

    private void checkForEmptyString(RegisterRequest registerRequest) {
        if(registerRequest.getUsername().isEmpty() || registerRequest.getPassword().isEmpty()) throw new EmptyStringException("Username or Password cannot be empty");
    }

    private void validateUsername(RegisterRequest registerRequest) {
        Diary diaryToFind = repository.findById(registerRequest.getUsername());
        if(diaryToFind != null) throw new UsernameTakenException("Username already taken");
    }

    private Diary checkUsername(LoginRequest loginRequest) {
        Diary diaryToFind = repository.findById(loginRequest.getUsername());
        if(diaryToFind == null) throw new IncorrectUsernameException("Username Is Incorrect");
        return diaryToFind;
    }

    private boolean checkPassword(LoginRequest loginRequest, Diary diaryToFind) {
        if(diaryToFind.getPassword().equals(loginRequest.getPassword())) return true;
        throw new IncorrectPasswordException("Password is Incorrect");
    }
}
