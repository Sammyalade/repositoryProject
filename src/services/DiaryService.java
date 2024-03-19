package services;

import datas.models.Diary;
import services.dtos.EntryCreationRequest;
import services.dtos.EntryUpdateRequest;
import services.dtos.LoginRequest;
import services.dtos.RegisterRequest;

public interface DiaryService {
    Diary register(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);
    void logout(String username);
    void createEntry(EntryCreationRequest entryCreationRequest);
    void updateEntry(EntryUpdateRequest entryUpdateRequest);
    void getAllEntries(String username);
}
