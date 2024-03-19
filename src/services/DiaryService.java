package services;

import datas.models.Diary;
import services.dtos.EntryCreationRequest;
import services.dtos.LoginRequest;
import services.dtos.RegisterRequest;

public interface DiaryService {
    Diary register(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);
    void logout(String username);
    void createEntry(EntryCreationRequest entryCreationRequest);
}
